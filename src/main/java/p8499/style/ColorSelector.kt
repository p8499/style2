package p8499.style

import org.xml.sax.helpers.AttributesImpl
import p8499.style.color.Color
import java.io.File
import javax.xml.transform.OutputKeys
import javax.xml.transform.sax.SAXTransformerFactory
import javax.xml.transform.stream.StreamResult

class ColorSelector(override val name: String, val map: Map<Set<Pair<String, Boolean>>, Color> = mapOf()) : StyleItem, Selector {
    fun clone(name: String): ColorSelector = ColorSelector(name, map)
    operator fun plus(color: Color): ColorSelector = plus(Pair(emptyCondition, color))
    operator fun plus(pair: Pair<Set<Pair<String, Boolean>>, Color>): ColorSelector = ColorSelector(name, map + pair)
    operator fun minus(condition: Set<Pair<String, Boolean>>): ColorSelector = ColorSelector(name, map.toMutableMap().also { it.remove(condition) })

    override fun text(environment: Environment, style: Style): String = "@color/${style.name}_$name"
    override fun print(folder: File, environment: Environment, style: Style): File {
        val file = File(folder, "color${File.separator}${style.name}_$name.xml")
        file.parentFile.takeUnless { it.exists() }?.mkdirs()
        file.createNewFile()
        val outputStream = file.outputStream()
        val tff = SAXTransformerFactory.newInstance() as SAXTransformerFactory
        val handler = tff.newTransformerHandler()
        handler.transformer.also {
            it.setOutputProperty(OutputKeys.ENCODING, "utf-8")
            it.setOutputProperty(OutputKeys.INDENT, "yes")
            it.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
        }
        handler.setResult(StreamResult(outputStream))
        handler.startDocument()
        handler.startPrefixMapping("android", "http://schemas.android.com/apk/res/android")
        val attrsSelector = AttributesImpl()
        handler.startElement("", "", "selector", attrsSelector)
        map.forEach { condition, color ->
            val attrsItem = AttributesImpl().also {
                it.addAttribute("", "", "android:color", "", color.toString())
                condition.forEach { c -> it.addAttribute("", "", c.first, "", c.second.toString()) }
            }
            handler.startElement("", "", "item", attrsItem)
            handler.endElement("", "", "item")
        }
        handler.endElement("", "", "selector")
        handler.endDocument()
        outputStream.flush()
        outputStream.close()
        return file
    }
}