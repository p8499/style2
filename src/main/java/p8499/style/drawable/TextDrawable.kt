package p8499.style.drawable

import p8499.style.DrawableSelector
import p8499.style.Environment
import p8499.style.Style
import java.io.File

class TextDrawable(val text: String) : Cloneable, Drawable {
    override fun print(folder: File, environment: Environment, style: Style, selector: DrawableSelector, index: Int): File? = null
    override fun clone(): TextDrawable = TextDrawable(text)
}
