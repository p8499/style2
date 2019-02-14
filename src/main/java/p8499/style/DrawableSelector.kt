package p8499.style

import com.greenbird.xml.prettyprinter.PrettyPrinterFactory
import p8499.style.drawable.Drawable
import java.io.File

class DrawableSelector(override val name: String, val map: Map<Set<Pair<String, Boolean>>, Drawable>) : StyleItem, Selector {
    fun clone(name: String): DrawableSelector = DrawableSelector(name, map)
    operator fun plus(drawable: Drawable): DrawableSelector = plus(Pair(emptyCondition, drawable))
    operator fun plus(pair: Pair<Set<Pair<String, Boolean>>, Drawable>): DrawableSelector = DrawableSelector(name, map + pair)
    operator fun minus(condition: Set<Pair<String, Boolean>>): DrawableSelector = DrawableSelector(name, map.toMutableMap().also { it.remove(condition) })

    override fun text(environment: Environment, style: Style): String = "@drawable/${style.name}_$name"
    override fun print(folder: File, environment: Environment, style: Style): File {
        val unformatted = StringBuilder()
        unformatted.append("""<?xml version="1.0" encoding="utf-8"?>""")
        unformatted.append("""<selector xmlns:android="http://schemas.android.com/apk/res/android">""")
        var i = 1
        map.forEach { condition, drawable ->
            unformatted.append(String.format("""<item android:drawable="%s"""", drawable))
            condition.forEach { unformatted.append(String.format("""%s="%b"""", it.first, it.second)) }
            unformatted.append("/>")
            drawable.print(folder, environment, style, this, i++)
        }
        unformatted.append("</selector>")
        val formatted = java.lang.StringBuilder().also { PrettyPrinterFactory.newInstance().newPrettyPrinter().process(unformatted.toString(), it) }
        val file = File(folder, "drawable${File.separator}${style.name}_$name.xml")
        file.parentFile.takeIf { it.exists() }?.mkdirs()
        file.createNewFile()
        file.writeBytes(formatted.toString().toByteArray())
        return file

    }
}