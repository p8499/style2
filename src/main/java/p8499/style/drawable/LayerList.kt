package p8499.style.drawable

import javax.xml.transform.sax.TransformerHandler

class LayerList(var layerItemList: List<LayerItem>) : Cloneable, Root {
    override public fun clone(): LayerList = LayerList(layerItemList.map { it.clone() })
    override fun output(handler: TransformerHandler) {
        handler.startElement("", "", "layer-list", null)
        for (item in layerItemList)
            item.output(handler)
        handler.endElement("", "", "layer-list")
    }
}