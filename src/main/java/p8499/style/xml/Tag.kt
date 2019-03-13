package p8499.style.xml

import org.xml.sax.helpers.AttributesImpl
import javax.xml.transform.sax.TransformerHandler

class Tag(val name: String, val attributes: Map<String, String>, val children: List<Node>) : Node {
    constructor(name: String) : this(name, mapOf(), listOf())
    constructor(name: String, attrs: Iterable<Pair<String, String>>) : this(name, attrs.toMap(), listOf())
    constructor(name: String, vararg attrs: Pair<String, String>) : this(name, attrs.toMap(), listOf())

    fun set(vararg childn: Node): Tag = Tag(name, attributes, children + childn)
    override fun output(handler: TransformerHandler) {
        val attrs = AttributesImpl().also {
            attributes.forEach { t, u -> it.addAttribute("", "", t, "", u) }
        }
        handler.startElement("", "", name, attrs)
        children.forEach { it.output(handler) }
        handler.endElement("", "", name)
    }
}
