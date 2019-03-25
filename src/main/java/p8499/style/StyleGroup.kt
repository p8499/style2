package p8499.style

import org.xml.sax.helpers.AttributesImpl
import java.io.File
import javax.xml.transform.OutputKeys
import javax.xml.transform.sax.SAXTransformerFactory
import javax.xml.transform.stream.StreamResult

class StyleGroup(val name: String, val styleList: MutableList<Style> = mutableListOf()) {
    constructor(name: String, vararg styles: Style) : this(name, styles.toMutableList())

    fun clone(name: String): StyleGroup = StyleGroup(name, styleList.toMutableList())
    operator fun plusAssign(style: Style) = styleList.plusAssign(style)
    operator fun plusAssign(styleGroup: StyleGroup) = styleList.plusAssign(styleGroup.styleList)
    operator fun minusAssign(style: Style) = styleList.minusAssign(style)
    fun print(folder: File): File {
        val file = File(folder, "values${File.separator}style_$name.xml")
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
        val attrsResources = AttributesImpl()
        handler.startElement("", "", "resources", attrsResources)
        styleList.forEach { it.output(folder, handler) }
        handler.endElement("", "", "resources")
        handler.endDocument()
        outputStream.flush()
        outputStream.close()
        println("write: ${file.absolutePath}")
        return file
    }
}