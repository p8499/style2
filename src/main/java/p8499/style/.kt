package p8499.style

import java.io.File

interface StyleItem {
    fun text(environment: Environment, style: Style): String
}

fun String?.styleItem() = object : StyleItem {
    override fun text(environment: Environment, style: Style): String = this@styleItem ?: "@null"
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

fun Style.android_layout_width(item: StyleItem) = this + ("android:layout_width" to item)
fun Style.android_layout_height(item: StyleItem) = this + ("android:layout_height" to item)
fun Style.android_layout_weight(item: StyleItem) = this + ("android:layout_weight" to item)
fun Style.android_layout_gravity(item: StyleItem) = this + ("android:layout_gravity" to item)
fun Style.android_minWidth(item: StyleItem) = this + ("android:minWidth" to item)
fun Style.android_minHeight(item: StyleItem) = this + ("android:minHeight" to item)
fun Style.android_layout_marginLeft(item: StyleItem) = this + ("android:layout_marginLeft" to item)
fun Style.android_layout_marginStart(item: StyleItem) = this + ("android:layout_marginStart" to item)
fun Style.android_layout_marginRight(item: StyleItem) = this + ("android:layout_marginRight" to item)
fun Style.android_layout_marginEnd(item: StyleItem) = this + ("android:layout_marginEnd" to item)
fun Style.android_layout_marginTop(item: StyleItem) = this + ("android:layout_marginTop" to item)
fun Style.android_layout_marginBottom(item: StyleItem) = this + ("android:layout_marginBottom" to item)
fun Style.android_paddingLeft(item: StyleItem) = this + ("android:paddingLeft" to item)
fun Style.android_paddingStart(item: StyleItem) = this + ("android:paddingStart" to item)
fun Style.android_paddingRight(item: StyleItem) = this + ("android:paddingRight" to item)
fun Style.android_paddingEnd(item: StyleItem) = this + ("android:paddingEnd" to item)
fun Style.android_paddingTop(item: StyleItem) = this + ("android:paddingTop" to item)
fun Style.android_paddingBottom(item: StyleItem) = this + ("android:paddingBottom" to item)
fun Style.android_enabled(item: StyleItem) = this + ("android:enabled" to item)
fun Style.android_orientation(item: StyleItem) = this + ("android:orientation" to item)
fun Style.android_baselineAligned(item: StyleItem) = this + ("android:baselineAligned" to item)
fun Style.android_layout_alignParentStart(item: StyleItem) = this + ("android:layout_alignParentStart" to item)
fun Style.android_layout_alignParentEnd(item: StyleItem) = this + ("android:layout_alignParentEnd" to item)
fun Style.android_layout_centerVertical(item: StyleItem) = this + ("android:layout_centerVertical" to item)
fun Style.android_gravity(item: StyleItem) = this + ("android:gravity" to item)
fun Style.android_scaleType(item: StyleItem) = this + ("android:scaleType" to item)
fun Style.android_focusable(item: StyleItem) = this + ("android:focusable" to item)
fun Style.android_clickable(item: StyleItem) = this + ("android:clickable" to item)
fun Style.android_longClickable(item: StyleItem) = this + ("android:longClickable" to item)
fun Style.android_contextClickable(item: StyleItem) = this + ("android:contextClickable" to item)
fun Style.android_background(item: StyleItem) = this + ("android:background" to item)
fun Style.android_inputType(item: StyleItem) = this + ("android:inputType" to item)
fun Style.android_imeOptions(item: StyleItem) = this + ("android:imeOptions" to item)
fun Style.android_textAllCaps(item: StyleItem) = this + ("android:textAllCaps" to item)
fun Style.android_textSize(item: StyleItem) = this + ("android:textSize" to item)
fun Style.android_textColor(item: StyleItem) = this + ("android:textColor" to item)
fun Style.android_textColorHint(item: StyleItem) = this + ("android:textColorHint" to item)
fun Style.android_src(item: StyleItem) = this + ("android:src" to item)
fun Style.android_typeface(item: StyleItem) = this + ("android:typeface" to item)
fun Style.android_ellipsize(item: StyleItem) = this + ("android:ellipsize" to item)
fun Style.android_maxLines(item: StyleItem) = this + ("android:maxLines" to item)
fun Style.android_overScrollMode(item: StyleItem) = this + ("android:overScrollMode" to item)
fun Style.android_keyPreviewLayout(item: StyleItem) = this + ("android:keyPreviewLayout" to item)
fun Style.android_keyTextColor(item: StyleItem) = this + ("android:keyTextColor" to item)
fun Style.android_keyTextSize(item: StyleItem) = this + ("android:keyTextSize" to item)
fun Style.android_keyBackground(item: StyleItem) = this + ("android:keyBackground" to item)
fun Style.android_shadowRadius(item: StyleItem) = this + ("android:shadowRadius" to item)
fun Style.iconifiedByDefault(item: StyleItem) = this + ("iconifiedByDefault" to item)
fun Style.queryBackground(item: StyleItem) = this + ("queryBackground" to item)
fun Style.searchHintIcon(item: StyleItem) = this + ("searchHintIcon" to item)
fun Style.searchIcon(item: StyleItem) = this + ("searchIcon" to item)

fun Style.contentScrim(item: StyleItem) = this + ("contentScrim" to item)
fun Style.layout_scrollFlags(item: StyleItem) = this + ("layout_scrollFlags" to item)
fun Style.android_fillViewport(item: StyleItem) = this + ("android:fillViewport" to item)
fun Style.layout_behavior(item: StyleItem) = this + ("layout_behavior" to item)
fun Style.android_contentDescription(item: StyleItem) = this + ("android:contentDescription" to item)
fun Style.android_hint(item: StyleItem) = this + ("android:hint" to item)
fun Style.android_singleLine(item: StyleItem) = this + ("android:singleLine" to item)
fun Style.android_drawableTop(item: StyleItem) = this + ("android:drawableTop" to item)
