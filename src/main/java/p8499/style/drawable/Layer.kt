package p8499.style.drawable

import org.xml.sax.helpers.AttributesImpl
import p8499.style.dp
import javax.xml.transform.sax.TransformerHandler

class Layer(
        val top: String = 0.dp,
        val bottom: String = 0.dp,
        val left: String = 0.dp,
        val right: String = 0.dp,
        val shape: Shape = Shape()) : Cloneable, Root {
    override public fun clone(): Layer = Layer(top, bottom, left, right, shape)
    override fun output(handler: TransformerHandler) {
        val attrsItem = AttributesImpl().also {
            it.addAttribute("", "", "android:top", "", top)
            it.addAttribute("", "", "android:bottom", "", bottom)
            it.addAttribute("", "", "android:left", "", left)
            it.addAttribute("", "", "android:right", "", right)
        }
        handler.startElement("", "", "item", attrsItem)
        shape.output(handler)
        handler.endElement("", "", "item")
    }
}