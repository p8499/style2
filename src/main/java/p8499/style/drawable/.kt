package p8499.style.drawable

import p8499.style.DrawableSelector
import p8499.style.Environment
import p8499.style.Style
import p8499.style.color.Color
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
fun rectangle(vararg attrs: Pair<String, String>): Tag = Tag("shape", android_shape("rectangle"), *attrs)
fun solid(vararg attrs: Pair<String, String>): Tag = Tag("solid", *attrs)
fun solid(color: Color): Tag = solid(android_color(color))
fun padding(vararg attrs: Pair<String, String>): Tag = Tag("padding", *attrs)
fun padding(top: String, bottom: String, left: String, right: String): Tag = padding(android_top(top), android_bottom(bottom), android_left(left), android_right(right))
fun padding(padding: String) = padding(android_top(padding), android_bottom(padding), android_left(padding), android_right(padding))
fun corners(vararg attrs: Pair<String, String>): Tag = Tag("corners", *attrs)
fun corners(topLeftRadius: String, topRightRadius: String, bottomLeftRadius: String, bottomRightRadius: String): Tag = corners(android_topLeftRadius(topLeftRadius), android_topRightRadius(topRightRadius), android_bottomLeftRadius(bottomLeftRadius), android_bottomRightRadius(bottomRightRadius))
fun corners(radius: String): Tag = corners(android_radius(radius))
fun stroke(vararg attrs: Pair<String, String>): Tag = Tag("stroke", *attrs)
fun item(vararg attrs: Pair<String, String>): Tag = Tag("item", *attrs)
fun layer_list(vararg attrs: Pair<String, String>): Tag = Tag("layer-list", *attrs)

fun android_shape(v: Any?) = "android:shape" to (v?.toString() ?: "@null")
fun android_top(v: Any?) = "android:top" to (v?.toString() ?: "@null")
fun android_bottom(v: Any?) = "android:bottom" to (v?.toString() ?: "@null")
fun android_left(v: Any?) = "android:left" to (v?.toString() ?: "@null")
fun android_right(v: Any?) = "android:right" to (v?.toString() ?: "@null")
fun android_color(v: Any?) = "android:color" to (v?.toString() ?: "@null")
fun android_width(v: Any?) = "android:width" to (v?.toString() ?: "@null")
fun android_radius(v: Any?) = "android:radius" to (v?.toString() ?: "@null")
fun android_topLeftRadius(v: Any?) = "android:topLeftRadius" to (v?.toString() ?: "@null")
fun android_topRightRadius(v: Any?) = "android:topRightRadius" to (v?.toString() ?: "@null")
fun android_bottomLeftRadius(v: Any?) = "android:bottomLeftRadius" to (v?.toString() ?: "@null")
fun android_bottomRightRadius(v: Any?) = "android:bottomRightRadius" to (v?.toString() ?: "@null")
fun android_dashGap(v: Any?) = "android:dashGap" to (v?.toString() ?: "@null")
fun android_dashWidth(v: Any?) = "android:dashWidth" to (v?.toString() ?: "@null")

