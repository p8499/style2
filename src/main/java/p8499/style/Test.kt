package p8499.style

fun main(args: Array<String>) {
    println(Environment("fd",
            listOf(full, wrap)).print())
}

val width_full = Style("width_full").android_layout_width("match_parent".styleItem())
val height_full = Style("width_full").android_layout_height("match_parent".styleItem())
val width_wrap = Style("width_wrap").android_layout_width("wrap_content".styleItem())
val height_wrap = Style("width_wrap").android_layout_height("wrap_content".styleItem())
val full = width_full + height_full
val wrap = width_wrap + height_wrap