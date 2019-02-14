package p8499.style

import java.io.File

class Environment(val name: String, val styleList: List<Style> = listOf()) {
    fun clone(name: String, folder: File): Environment = Environment(name, styleList)
    operator fun plus(style: Style): Environment = Environment(name, styleList + style)
    operator fun plus(environment: Environment): Environment = Environment(name, styleList + environment.styleList)
    operator fun minus(style: Style) = Environment(name, styleList - style)
    fun print(folder: File = File(System.getProperty("java.io.tmpdir"), name)): File {
        folder.delete()
        styleList.forEach { it.print(folder, this) }
        return folder
    }
}