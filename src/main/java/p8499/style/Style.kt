package p8499.style

import com.greenbird.xml.prettyprinter.PrettyPrinterFactory
import java.io.File

class Style(val name: String, val map: Map<String, StyleItem>) {
    fun clone(name: String): Style = Style(name, map)
    operator fun plus(pair: Pair<String, StyleItem>): Style = Style(name, map + pair)
    operator fun plus(style: Style): Style = Style(name, map + style.map)
    operator fun minus(itemName: String) = Style(name, map - itemName)

    fun print(folder: File, environment: Environment): File {
        val unformatted = StringBuilder()
        unformatted.append("""<?xml version="1.0" encoding="utf-8"?>""")
        unformatted.append("""<selector xmlns:android="http://schemas.android.com/apk/res/android">""")
        map.forEach { itemName, item ->
            unformatted.append(String.format("""<item name="%s">%s</item>""", itemName, item.text(environment, this)))
            (item as? Selector)?.print(folder, environment, this)
        }
        unformatted.append("</selector>")
        val formatted = java.lang.StringBuilder().also { PrettyPrinterFactory.newInstance().newPrettyPrinter().process(unformatted.toString(), it) }
        val file = File(folder, "values${File.separator}style_$name.xml")
        file.parentFile.takeIf { it.exists() }?.mkdirs()
        file.createNewFile()
        file.writeBytes(formatted.toString().toByteArray())
        return file
    }
}