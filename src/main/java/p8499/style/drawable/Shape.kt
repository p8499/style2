package p8499.style.drawable

import org.xml.sax.helpers.AttributesImpl
import p8499.style.color.Argb
import p8499.style.color.Color
import p8499.style.dp
import javax.xml.transform.sax.TransformerHandler

class Shape(
        var shapeType: ShapeType = ShapeType.Rectangle,
        var paddingTop: String = 0.dp,
        var paddingBottom: String = 0.dp,
        var paddingLeft: String = 0.dp,
        var paddingRight: String = 0.dp,
        var backgroundColor: Color = Argb(0, 0, 0, 0),
        var borderWidth: String = 0.dp,
        var borderColor: Color = Argb(0, 0, 0, 0),
        var radiusTopLeft: String = 0.dp,
        var radiusTopRight: String = 0.dp,
        var radiusBottomLeft: String = 0.dp,
        var radiusBottomRight: String = 0.dp) : Cloneable, Root {
    override fun clone(): Shape = Shape(shapeType, paddingTop, paddingBottom, paddingLeft, paddingRight, backgroundColor, borderWidth, borderColor, radiusTopLeft, radiusTopRight, radiusBottomLeft, radiusBottomRight)

    override public fun output(handler: TransformerHandler) {
        val shapeType = when (shapeType) {
            ShapeType.Rectangle -> "rectangle"
        }
        val attrsShape = AttributesImpl().also {
            it.addAttribute("", "", "android:shape", "", shapeType)
        }
        handler.startElement("", "", "shape", attrsShape)

        val attrsSolid = AttributesImpl().also {
            it.addAttribute("", "", "android:color", "", backgroundColor.toString())
        }
        handler.startElement("", "", "solid", attrsSolid)
        handler.endElement("", "", "solid")

        val attrsPadding = AttributesImpl().also {
            it.addAttribute("", "", "android:top", "", paddingTop)
            it.addAttribute("", "", "android:left", "", paddingLeft)
            it.addAttribute("", "", "android:right", "", paddingRight)
            it.addAttribute("", "", "android:bottom", "", paddingBottom)
        }
        handler.startElement("", "", "padding", attrsPadding)
        handler.endElement("", "", "padding")

        val attrsCorners = AttributesImpl().also {
            it.addAttribute("", "", "android:topLeftRadius", "", radiusTopLeft)
            it.addAttribute("", "", "android:topRightRadius", "", radiusTopRight)
            it.addAttribute("", "", "android:bottomLeftRadius", "", radiusBottomLeft)
            it.addAttribute("", "", "android:bottomRightRadius", "", radiusBottomRight)
        }
        handler.startElement("", "", "corners", attrsCorners)
        handler.endElement("", "", "corners")

        val attrsStroke = AttributesImpl().also {
            it.addAttribute("", "", "android:width", "", borderWidth)
            it.addAttribute("", "", "android:color", "", borderColor.toString())
        }
        handler.startElement("", "", "stroke", attrsStroke)
        handler.endElement("", "", "stroke")

        handler.endElement("", "", "shape")
    }
}
