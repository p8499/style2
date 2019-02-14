package p8499.style.drawable

import p8499.style.DrawableSelector
import p8499.style.Environment
import p8499.style.Style
import java.io.File
import javax.xml.transform.sax.TransformerHandler

enum class ShapeType { Rectangle }

fun rectangle() = Shape(ShapeType.Rectangle)

interface Drawable {
    fun print(folder: File, environment: Environment, style: Style, selector: DrawableSelector, index: Int): File?
}

interface Root {
    fun output(handler: TransformerHandler)
}