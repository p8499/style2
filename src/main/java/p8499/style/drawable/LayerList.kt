package p8499.style.drawable

import javax.xml.transform.sax.TransformerHandler

class LayerList(val layerItemList: List<LayerItem>) : Cloneable, Root {
    override public fun clone(): LayerList = LayerList(layerItemList.map { it.clone() })
    operator fun plus(shape: Shape): LayerList = LayerList(layerItemList + LayerItem(shape = shape))
    operator fun plus(item: LayerItem): LayerList = LayerList(layerItemList + item)
    operator fun minus(item: LayerItem) = LayerList(layerItemList - item)
    override fun output(handler: TransformerHandler) {
        handler.startElement("", "", "layer-list", null)
        for (item in layerItemList)
            item.output(handler)
        handler.endElement("", "", "layer-list")
    }
}