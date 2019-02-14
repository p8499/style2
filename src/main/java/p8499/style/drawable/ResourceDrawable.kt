package p8499.style.drawable

import p8499.style.DrawableSelector
import p8499.style.Environment
import p8499.style.Style
import java.io.File
import javax.xml.transform.OutputKeys
import javax.xml.transform.sax.SAXTransformerFactory
import javax.xml.transform.stream.StreamResult

class ResourceDrawable(val root: Root) : Cloneable, Drawable {
    override fun clone(): ResourceDrawable = ResourceDrawable(root)

    override fun print(folder: File, environment: Environment, style: Style, selector: DrawableSelector, index: Int): File? {
        val file = File(folder, "drawable${File.separator}${style.name}_${selector.name}_$index.xml")
        file.parentFile.takeIf { it.exists() }?.mkdirs()
        file.createNewFile()
        val outputStream = file.outputStream()
        val tff = SAXTransformerFactory.newInstance() as SAXTransformerFactory
        val handler = tff.newTransformerHandler()
        handler.transformer.also {
            it.setOutputProperty(OutputKeys.ENCODING, "utf-8")
            it.setOutputProperty(OutputKeys.INDENT, "yes")
            it.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
        }
        handler.setResult(StreamResult(outputStream))
        handler.startDocument()
        handler.startPrefixMapping("android", "http://schemas.android.com/apk/res/android")
        root.output(handler)
        handler.endDocument()
        outputStream.flush()
        outputStream.close()
        return file
    }
}