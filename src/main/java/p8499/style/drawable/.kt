package p8499.style.drawable

import p8499.style.DrawableSelector
import p8499.style.Environment
import p8499.style.Style
import p8499.style.dp
import java.io.File
import javax.xml.transform.sax.TransformerHandler

enum class ShapeType { Rectangle }
interface Drawable {
    fun text(environment: Environment, style: Style, selector: DrawableSelector, index: Int): String
    fun print(folder: File, environment: Environment, style: Style, selector: DrawableSelector, index: Int): File?
}

interface Root {
    fun output(handler: TransformerHandler)
}

fun rectangle() = ShapeBuilder(ShapeType.Rectangle)
fun Root.drawable() = ResourceDrawable(this)
fun Shape.layer(top: String = 0.dp, bottom: String = 0.dp, left: String = 0.dp, right: String = 0.dp) = Layer(top, bottom, left, right, this)