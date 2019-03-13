package p8499.style

import org.xml.sax.helpers.AttributesImpl
import java.io.File
import javax.xml.transform.OutputKeys
import javax.xml.transform.sax.SAXTransformerFactory
import javax.xml.transform.stream.StreamResult

class StyleGroup(val name: String, val styleList: List<Style> = listOf()) {
    constructor(name: String, vararg styles: Style) : this(name, styles.asList())

    fun clone(name: String, folder: File): StyleGroup = StyleGroup(name, styleList)
    operator fun plus(style: Style): StyleGroup = StyleGroup(name, styleList + style)
    operator fun plus(styleGroup: StyleGroup): StyleGroup = StyleGroup(name, styleList + styleGroup.styleList)
    operator fun minus(style: Style) = StyleGroup(name, styleList - style)
    fun print(folder: File = File(System.getProperty("java.io.tmpdir"))): File {
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

        styleList.forEach { it.output(folder, this, handler) }
        handler.endElement("", "", "resources")
        handler.endDocument()
        outputStream.flush()
        outputStream.close()
        return file
    }
}