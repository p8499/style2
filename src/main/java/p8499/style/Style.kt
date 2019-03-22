package p8499.style

import org.xml.sax.helpers.AttributesImpl
import java.io.File
import javax.xml.transform.sax.TransformerHandler

class Style(val parent: String? = null, val name: String, val map: MutableMap<String, StyleItem> = mutableMapOf()) {
    constructor(name: String, map: Map<String, StyleItem> = mapOf()) : this(null, name, map.toMutableMap())

    fun clone(name: String): Style = Style(parent, name, map.toMutableMap())
    operator fun plusAssign(pair: Pair<String, StyleItem>) = map.plusAssign(pair)
    operator fun plusAssign(style: Style) = map.plusAssign(style.map)
    operator fun minusAssign(itemName: String) = map.minusAssign(itemName)
    operator fun plus(pair: Pair<String, StyleItem>): Style = Style(parent, name, (map + pair).toMutableMap())
    operator fun plus(style: Style): Style = Style(parent, name, (map + style.map).toMutableMap())
    operator fun minus(itemName: String) = Style(parent, name, (map - itemName).toMutableMap())

    fun output(folder: File, handler: TransformerHandler) {
        val attrsStyle = AttributesImpl().also {
            it.addAttribute("", "", "name", "", name)
            parent?.also { parent -> it.addAttribute("", "", "parent", "", parent) }
        }
        handler.startElement("", "", "style", attrsStyle)
        map.forEach { itemName, item ->
            val attrsItem = AttributesImpl().also {
                it.addAttribute("", "", "name", "", itemName)
            }
            handler.startElement("", "", "item", attrsItem)
            item.text().also { handler.characters(it.toCharArray(), 0, it.length) }
            handler.endElement("", "", "item")
            (item as? Resource)?.print(folder)
        }
        handler.endElement("", "", "style")
    }
}