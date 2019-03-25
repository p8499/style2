package p8499.style

import p8499.style.xml.Tag
import java.io.File
import javax.xml.transform.OutputKeys
import javax.xml.transform.sax.SAXTransformerFactory
import javax.xml.transform.stream.StreamResult

class Drawable(override val name: String, val node: Tag) : StyleItem, Resource {
    fun clone(name: String): Drawable = Drawable(name, node)
    override fun text(): String = "@drawable/$name"
    fun normalize(): StyleItem = text().styleItem()
    override fun print(folder: File): File {
        val file = File(folder, "drawable${File.separator}$name.xml")
        file.parentFile.takeUnless { it.exists() }?.mkdirs()
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
        node.output(handler)
        handler.endDocument()
        outputStream.flush()
        outputStream.close()
        println("write: ${file.absolutePath}")
        return file
    }
}