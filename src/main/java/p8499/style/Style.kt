package p8499.style

import org.xml.sax.helpers.AttributesImpl
import java.io.File
import javax.xml.transform.sax.TransformerHandler

class Style(val parent: String? = null, val name: String, val map: Map<String, StyleItem> = mapOf()) {
    constructor(name: String, map: Map<String, StyleItem> = mapOf()) : this(null, name, map)

    fun clone(name: String): Style = Style(parent, name, map)
    operator fun plus(pair: Pair<String, StyleItem>): Style = Style(parent, name, map + pair)
    operator fun plus(style: Style): Style = Style(parent, name, map + style.map)
    operator fun minus(itemName: String) = Style(parent, name, map - itemName)

     fun output(folder: File, styleGroup: StyleGroup, handler: TransformerHandler){
         val attrsStyle = AttributesImpl().also {
             it.addAttribute("", "", "name", "", name)
             parent?.also { parent -> it.addAttribute("", "", "parent", "", parent) }
         }
         handler.startElement("", "", "style", attrsStyle)
         map.forEach { itemName, item ->
             val attrsItem = AttributesImpl().also {
                 it.addAttribute("", "", "name", "", itemName)
             }
             handler.startElement("", "", "item", attrsItem)
             item.text(styleGroup, this).also { handler.characters(it.toCharArray(), 0, it.length) }
             handler.endElement("", "", "item")
             (item as? Selector)?.print(folder, styleGroup, this)
         }
         handler.endElement("", "", "style")
     }

//    fun print(folder: File, environment: StyleGroup): File {
//        val file = File(folder, "values${File.separator}style_$name.xml")
//        file.parentFile.takeUnless { it.exists() }?.mkdirs()
//        file.createNewFile()
//        val outputStream = file.outputStream()
//        val tff = SAXTransformerFactory.newInstance() as SAXTransformerFactory
//        val handler = tff.newTransformerHandler()
//        handler.transformer.also {
//            it.setOutputProperty(OutputKeys.ENCODING, "utf-8")
//            it.setOutputProperty(OutputKeys.INDENT, "yes")
//            it.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
//        }
//        handler.setResult(StreamResult(outputStream))
//        handler.startDocument()
//        handler.startPrefixMapping("android", "http://schemas.android.com/apk/res/android")
//        val attrsResources = AttributesImpl()
//        handler.startElement("", "", "resources", attrsResources)
//
//
//        val attrsStyle = AttributesImpl().also {
//            it.addAttribute("", "", "name", "", name)
//            parent?.also { parent -> it.addAttribute("", "", "parent", "", parent) }
//        }
//        handler.startElement("", "", "style", attrsStyle)
//        map.forEach { itemName, item ->
//            val attrsItem = AttributesImpl().also {
//                it.addAttribute("", "", "name", "", itemName)
//            }
//            handler.startElement("", "", "item", attrsItem)
//            item.text(environment, this).also { handler.characters(it.toCharArray(), 0, it.length) }
//            handler.endElement("", "", "item")
//            (item as? Selector)?.print(folder, environment, this)
//        }
//        handler.endElement("", "", "style")
//
//
//        handler.endElement("", "", "resources")
//        handler.endDocument()
//        outputStream.flush()
//        outputStream.close()
//        return file
//    }
}