package p8499.style

import p8499.style.color.Color
import p8499.style.drawable.Drawable
import java.io.File

interface StyleItem {
    fun text(environment: Environment, style: Style): String
}

fun Any?.styleItem() = object : StyleItem {
    override fun text(environment: Environment, style: Style): String = this@styleItem?.toString() ?: "@null"
}

interface Selector {
    val name: String
    fun print(folder: File, environment: Environment, style: Style): File
}

val Int.dp: String get() = "${this}dp"
val Float.dp: String get() = "${String.format("%.2f", this)}dp"
val Double.dp: String get() = "${String.format("%.2f", this)}dp"
val Int.sp: String get() = "${this}sp"
val Int.percent: String get() = "${this}%"

val state_focused = "android:state_focused" to true
val state_first = "android:state_first" to true
val state_accelerated = "android:state_accelerated" to true
val state_activated = "android:state_activated" to true
val state_active = "android:state_active" to true
val state_drag_can_accept = "android:state_drag_can_accept" to true
val state_drag_hovered = "android:state_drag_hovered" to true
val state_enabled = "android:state_enabled" to true
val state_hovered = "android:state_hovered" to true
val state_last = "android:state_last" to true
val state_middle = "android:state_middle" to true
val state_selected = "android:state_selected" to true
val state_single = "android:state_single" to true
val state_window_focused = "android:state_window_focused" to true
val state_checkable = "android:state_checkable" to true
val state_checked = "android:state_checked" to true
val state_pressed = "android:state_pressed" to true

operator fun Pair<String, Boolean>.not(): Pair<String, Boolean> = this.first to !this.second
operator fun Pair<String, Boolean>.plus(another: Pair<String, Boolean>): Set<Pair<String, Boolean>> = setOf(this, another)
operator fun Set<Pair<String, Boolean>>.plus(new: Pair<String, Boolean>): Set<Pair<String, Boolean>> = setOf(*toTypedArray(), new)
operator fun Set<Pair<String, Boolean>>.minus(target: Pair<String, Boolean>): Set<Pair<String, Boolean>> = dropWhile { it == target }.toSet()
val emptyCondition: Set<Pair<String, Boolean>> = setOf()
fun Pair<String, Boolean>.condition(): Set<Pair<String, Boolean>> = setOf(this)
fun Map<Set<Pair<String, Boolean>>, Drawable>.selector(name: String) = DrawableSelector(name, this)
fun Map<Set<Pair<String, Boolean>>, Color>.selector(name: String) = ColorSelector(name, this)

fun android_layout_width(item: StyleItem): Style = Style("", mapOf("android:layout_width" to item))
fun android_layout_height(item: StyleItem): Style = Style("", mapOf("android:layout_height" to item))
fun android_layout_weight(item: StyleItem): Style = Style("", mapOf("android:layout_weight" to item))
fun android_layout_gravity(item: StyleItem): Style = Style("", mapOf("android:layout_gravity" to item))
fun android_minWidth(item: StyleItem): Style = Style("", mapOf("android:minWidth" to item))
fun android_minHeight(item: StyleItem): Style = Style("", mapOf("android:minHeight" to item))
fun android_layout_marginLeft(item: StyleItem): Style = Style("", mapOf("android:layout_marginLeft" to item))
fun android_layout_marginStart(item: StyleItem): Style = Style("", mapOf("android:layout_marginStart" to item))
fun android_layout_marginRight(item: StyleItem): Style = Style("", mapOf("android:layout_marginRight" to item))
fun android_layout_marginEnd(item: StyleItem): Style = Style("", mapOf("android:layout_marginEnd" to item))
fun android_layout_marginTop(item: StyleItem): Style = Style("", mapOf("android:layout_marginTop" to item))
fun android_layout_marginBottom(item: StyleItem): Style = Style("", mapOf("android:layout_marginBottom" to item))
fun android_paddingLeft(item: StyleItem): Style = Style("", mapOf("android:paddingLeft" to item))
fun android_paddingStart(item: StyleItem): Style = Style("", mapOf("android:paddingStart" to item))
fun android_paddingRight(item: StyleItem): Style = Style("", mapOf("android:paddingRight" to item))
fun android_paddingEnd(item: StyleItem): Style = Style("", mapOf("android:paddingEnd" to item))
fun android_paddingTop(item: StyleItem): Style = Style("", mapOf("android:paddingTop" to item))
fun android_paddingBottom(item: StyleItem): Style = Style("", mapOf("android:paddingBottom" to item))
fun android_enabled(item: StyleItem): Style = Style("", mapOf("android:enabled" to item))
fun android_orientation(item: StyleItem): Style = Style("", mapOf("android:orientation" to item))
fun android_baselineAligned(item: StyleItem): Style = Style("", mapOf("android:baselineAligned" to item))
fun android_layout_alignParentStart(item: StyleItem): Style = Style("", mapOf("android:layout_alignParentStart" to item))
fun android_layout_alignParentEnd(item: StyleItem): Style = Style("", mapOf("android:layout_alignParentEnd" to item))
fun android_layout_centerVertical(item: StyleItem): Style = Style("", mapOf("android:layout_centerVertical" to item))
fun android_gravity(item: StyleItem): Style = Style("", mapOf("android:gravity" to item))
fun android_scaleType(item: StyleItem): Style = Style("", mapOf("android:scaleType" to item))
fun android_focusable(item: StyleItem): Style = Style("", mapOf("android:focusable" to item))
fun android_clickable(item: StyleItem): Style = Style("", mapOf("android:clickable" to item))
fun android_longClickable(item: StyleItem): Style = Style("", mapOf("android:longClickable" to item))
fun android_contextClickable(item: StyleItem): Style = Style("", mapOf("android:contextClickable" to item))
fun android_background(item: StyleItem): Style = Style("", mapOf("android:background" to item))
fun android_inputType(item: StyleItem): Style = Style("", mapOf("android:inputType" to item))
fun android_imeOptions(item: StyleItem): Style = Style("", mapOf("android:imeOptions" to item))
fun android_textAllCaps(item: StyleItem): Style = Style("", mapOf("android:textAllCaps" to item))
fun android_textSize(item: StyleItem): Style = Style("", mapOf("android:textSize" to item))
fun android_textColor(item: StyleItem): Style = Style("", mapOf("android:textColor" to item))
fun android_textColorHint(item: StyleItem): Style = Style("", mapOf("android:textColorHint" to item))
fun android_src(item: StyleItem): Style = Style("", mapOf("android:src" to item))
fun android_typeface(item: StyleItem): Style = Style("", mapOf("android:typeface" to item))
fun android_ellipsize(item: StyleItem): Style = Style("", mapOf("android:ellipsize" to item))
fun android_maxLines(item: StyleItem): Style = Style("", mapOf("android:maxLines" to item))
fun android_overScrollMode(item: StyleItem): Style = Style("", mapOf("android:overScrollMode" to item))
fun android_keyPreviewLayout(item: StyleItem): Style = Style("", mapOf("android:keyPreviewLayout" to item))
fun android_keyTextColor(item: StyleItem): Style = Style("", mapOf("android:keyTextColor" to item))
fun android_keyTextSize(item: StyleItem): Style = Style("", mapOf("android:keyTextSize" to item))
fun android_keyBackground(item: StyleItem): Style = Style("", mapOf("android:keyBackground" to item))
fun android_shadowRadius(item: StyleItem): Style = Style("", mapOf("android:shadowRadius" to item))
fun iconifiedByDefault(item: StyleItem): Style = Style("", mapOf("iconifiedByDefault" to item))
fun queryBackground(item: StyleItem): Style = Style("", mapOf("queryBackground" to item))
fun searchHintIcon(item: StyleItem): Style = Style("", mapOf("searchHintIcon" to item))
fun searchIcon(item: StyleItem): Style = Style("", mapOf("searchIcon" to item))

fun contentScrim(item: StyleItem): Style = Style("", mapOf("contentScrim" to item))
fun layout_scrollFlags(item: StyleItem): Style = Style("", mapOf("layout_scrollFlags" to item))
fun android_fillViewport(item: StyleItem): Style = Style("", mapOf("android:fillViewport" to item))
fun layout_behavior(item: StyleItem): Style = Style("", mapOf("layout_behavior" to item))
fun android_contentDescription(item: StyleItem): Style = Style("", mapOf("android:contentDescription" to item))
fun android_hint(item: StyleItem): Style = Style("", mapOf("android:hint" to item))
fun android_singleLine(item: StyleItem): Style = Style("", mapOf("android:singleLine" to item))
fun android_drawableTop(item: StyleItem): Style = Style("", mapOf("android:drawableTop" to item))
