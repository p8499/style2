package p8499.style

import java.io.File

class Environment(var styleGroupList: MutableList<StyleGroup> = mutableListOf(), var resourceList: MutableList<Resource> = mutableListOf()) {
    operator fun plusAssign(items: Array<out Any>) = plusAssign(items.asIterable())
    operator fun plusAssign(items: Iterable<Any>) = items.forEach {
        when (it) {
            is Style -> plusAssign(it)
            is StyleGroup -> plusAssign(it)
            is Resource -> plusAssign(it)
        }
    }

    operator fun plusAssign(style: Style) = plusAssign(StyleGroup(style.name, mutableListOf(style)))
    operator fun plusAssign(styleGroup: StyleGroup) = styleGroupList.plusAssign(styleGroup)
    operator fun plusAssign(resource: Resource) = resourceList.plusAssign(resource)

    fun print(folder: File) {
        resourceList.forEach { it.print(folder) }
        styleGroupList.forEach { it.print(folder) }
    }
}