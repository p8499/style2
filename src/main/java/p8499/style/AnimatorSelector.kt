package p8499.style

import org.xml.sax.helpers.AttributesImpl
import p8499.style.xml.Node
import java.io.File
import javax.xml.transform.OutputKeys
import javax.xml.transform.sax.SAXTransformerFactory
import javax.xml.transform.stream.StreamResult

class AnimatorSelector(override val name: String, val map: Map<Set<Pair<String, Boolean>>, Node> = mapOf()) : StyleItem, Selector {
    fun clone(name: String): AnimatorSelector = AnimatorSelector(name, map)
    operator fun plus(node: Node): AnimatorSelector = plus(Pair(emptyCondition, node))
    operator fun plus(pair: Pair<Set<Pair<String, Boolean>>, Node>): AnimatorSelector = AnimatorSelector(name, map + pair)
    operator fun minus(condition: Set<Pair<String, Boolean>>): AnimatorSelector = AnimatorSelector(name, map.toMutableMap().also { it.remove(condition) })

    override fun text(styleGroup: StyleGroup, style: Style): String = "@animator/${style.name}_$name"
    override fun print(folder: File, styleGroup: StyleGroup, style: Style): File {
        val file = File(folder, "animator${File.separator}${style.name}_$name.xml")
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
        map.forEach { condition, node ->
            val attrsItem = AttributesImpl().also {
                condition.forEach { c -> it.addAttribute("", "", c.first, "", c.second.toString()) }
            }
            handler.startElement("", "", "item", attrsItem)
            node.output(handler)
            handler.endElement("", "", "item")
        }
        handler.endElement("", "", "selector")
        handler.endDocument()
        outputStream.flush()
        outputStream.close()
        return file
    }
}