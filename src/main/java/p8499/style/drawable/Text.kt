package p8499.style.drawable

import javax.xml.transform.sax.TransformerHandler

class Text(val text: String) : Node {
    override fun output(handler: TransformerHandler) {
        text.toCharArray().let { handler.characters(it, 0, it.size) }
    }
}