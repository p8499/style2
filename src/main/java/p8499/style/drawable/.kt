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

interface Node : Root

fun Root.drawable() = ResourceDrawable(this)

fun shape(vararg attrs: Pair<String, String>): Tag = Tag("shape", *attrs)
fun solid(vararg attrs: Pair<String, String>): Tag = Tag("solid", *attrs)
fun padding(vararg attrs: Pair<String, String>): Tag = Tag("padding", *attrs)
fun corners(vararg attrs: Pair<String, String>): Tag = Tag("corners", *attrs)
fun stroke(vararg attrs: Pair<String, String>): Tag = Tag("stroke", *attrs)
fun item(vararg attrs: Pair<String, String>): Tag = Tag("item", *attrs)
fun layer_list(vararg attrs: Pair<String, String>): Tag = Tag("layer-list", *attrs)


fun shape(v: Any?) = "android:shape" to (v?.toString() ?: "@null")
val shape_rectangle = shape("rectangle")
fun top(v: Any?) = "android:top" to (v?.toString() ?: "@null")
fun bottom(v: Any?) = "android:bottom" to (v?.toString() ?: "@null")
fun left(v: Any?) = "android:left" to (v?.toString() ?: "@null")
fun right(v: Any?) = "android:right" to (v?.toString() ?: "@null")
fun color(v: Any?) = "android:color" to (v?.toString() ?: "@null")
fun width(v: Any?) = "android:width" to (v?.toString() ?: "@null")
fun topLeftRadius(v: Any?) = "android:topLeftRadius" to (v?.toString() ?: "@null")
fun topRightRadius(v: Any?) = "android:topRightRadius" to (v?.toString() ?: "@null")
fun bottomLeftRadius(v: Any?) = "android:bottomLeftRadius" to (v?.toString() ?: "@null")
fun bottomRightRadius(v: Any?) = "android:bottomRightRadius" to (v?.toString() ?: "@null")

