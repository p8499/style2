package p8499.style

import com.greenbird.xml.prettyprinter.PrettyPrinterFactory
import p8499.style.color.Color
import java.io.File

class ColorSelector(override val name: String, val map: Map<Set<Pair<String, Boolean>>, Color> = mapOf()) : StyleItem, Selector {
    fun clone(name: String): ColorSelector = ColorSelector(name, map)
    operator fun plus(color: Color): ColorSelector = plus(Pair(emptyCondition, color))
    operator fun plus(pair: Pair<Set<Pair<String, Boolean>>, Color>): ColorSelector = ColorSelector(name, map + pair)
    operator fun minus(condition: Set<Pair<String, Boolean>>): ColorSelector = ColorSelector(name, map.toMutableMap().also { it.remove(condition) })

    override fun text(environment: Environment, style: Style): String = "@color/${style.name}_$name"
    override fun print(folder: File, environment: Environment, style: Style): File {
        val unformatted = StringBuilder()
        unformatted.append("""<?xml version="1.0" encoding="utf-8"?>""")
        unformatted.append("""<selector xmlns:android="http://schemas.android.com/apk/res/android">""")
        map.forEach { condition, color ->
            unformatted.append(String.format("""<item android:color="%s"""", color))
            condition.forEach { unformatted.append(String.format("""%s="%b"""", it.first, it.second)) }
            unformatted.append("/>")
        }
        unformatted.append("</selector>")
        val formatted = java.lang.StringBuilder().also { PrettyPrinterFactory.newInstance().newPrettyPrinter().process(unformatted.toString(), it) }
        val file = File(folder, "color${File.separator}${style.name}_$name.xml")
        file.parentFile.takeUnless { it.exists() }?.mkdirs()
        file.createNewFile()
        file.writeBytes(formatted.toString().toByteArray())
        return file
    }
}