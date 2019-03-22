package p8499.style

import java.io.File

class Environment(var styleGroupList: MutableList<StyleGroup> = mutableListOf(), var resourceList: MutableList<Resource> = mutableListOf()) {
    operator fun plusAssign(style: Style) = plusAssign(StyleGroup(style.name, mutableListOf(style)))
    operator fun plusAssign(styleGroup: StyleGroup) = styleGroupList.plusAssign(styleGroup)
    operator fun plusAssign(resource: Resource) = resourceList.plusAssign(resource)

    fun print(folder: File) {
        styleGroupList.forEach { it.print(folder) }
        resourceList.forEach { it.print(folder) }
    }
}