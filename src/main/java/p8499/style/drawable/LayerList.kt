package p8499.style.drawable

import javax.xml.transform.sax.TransformerHandler

class LayerList(val layerList: List<Layer> = listOf()) : Cloneable, Root {
    override public fun clone(): LayerList = LayerList(layerList.map { it.clone() })
    operator fun plus(shape: Shape): LayerList = LayerList(layerList + Layer(shape = shape))
    operator fun plus(item: Layer): LayerList = LayerList(layerList + item)
    operator fun minus(item: Layer) = LayerList(layerList - item)
    override fun output(handler: TransformerHandler) {
        handler.startElement("", "", "layer-list", null)
        for (item in layerList)
            item.output(handler)
        handler.endElement("", "", "layer-list")
    }
}