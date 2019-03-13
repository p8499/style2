//package p8499.style.drawable
//
//import p8499.style.DrawableSelector
//import p8499.style.StyleGroup
//import p8499.style.Style
//import p8499.style.xml.Node
//import java.io.File
//
//enum class ShapeType { Rectangle }
//interface Drawable {
//    fun text(environment: StyleGroup, style: Style, selector: DrawableSelector, index: Int): String
//    fun print(folder: File, environment: StyleGroup, style: Style, selector: DrawableSelector, index: Int): File?
//}
//
//fun Node.drawable() = ResourceDrawable(this)
//
