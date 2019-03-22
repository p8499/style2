package p8499.style

import org.xml.sax.helpers.AttributesImpl
import p8499.style.xml.Tag
import java.io.File
import javax.xml.transform.OutputKeys
import javax.xml.transform.sax.SAXTransformerFactory
import javax.xml.transform.stream.StreamResult

class DrawableSelector(override val name: String, val map: MutableMap<Set<Pair<String, Boolean>>, Tag> = mutableMapOf()) : StyleItem, Resource {
    fun clone(name: String): DrawableSelector = DrawableSelector(name, map.toMutableMap())
    operator fun plusAssign(node: Tag) = plusAssign(Pair(emptyCondition, node))
    operator fun plusAssign(pair: Pair<Set<Pair<String, Boolean>>, Tag>) = plusAssign(listOf(pair))
    operator fun plusAssign(selector: AnimatorSelector) = plusAssign(selector.map.map { it.key to it.value })
    operator fun plusAssign(pairs: Iterable<Pair<Set<Pair<String, Boolean>>, Tag>>) = pairs.forEach { pair -> map += pair.first to (map[pair.first]?.merge(pair.second) ?: pair.second) }
    operator fun minusAssign(condition: Set<Pair<String, Boolean>>) = map.minusAssign(condition)
    operator fun plus(node: Tag): AnimatorSelector = plus(Pair(emptyCondition, node))
    operator fun plus(pair: Pair<Set<Pair<String, Boolean>>, Tag>): AnimatorSelector = plus(listOf(pair))
    operator fun plus(selector: AnimatorSelector): AnimatorSelector = plus(selector.map.map { it.key to it.value })
    operator fun plus(pairs: Iterable<Pair<Set<Pair<String, Boolean>>, Tag>>) = AnimatorSelector(name, map.toMutableMap().also { pairs.forEach { pair -> it += pair.first to (it[pair.first]?.merge(pair.second) ?: pair.second) } })
    operator fun minus(condition: Set<Pair<String, Boolean>>): AnimatorSelector = AnimatorSelector(name, map.toMutableMap().also { it.remove(condition) })

    override fun text(): String = "@drawable/$name"
    fun normalize(): StyleItem = text().styleItem()
    override fun print(folder: File): File {
        val file = File(folder, "drawable${File.separator}$name.xml")
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