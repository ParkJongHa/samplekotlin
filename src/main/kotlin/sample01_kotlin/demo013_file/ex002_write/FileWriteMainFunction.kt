package sample01_kotlin.demo013_file.ex002_write

import java.io.File

fun main() {
//    fileWrite()
}

fun fileWrite(lineList: List<String>) {
    val pathName = "C:\\Users\\zoyng\\OneDrive\\바탕 화면\\test\\temp_for_file_write.txt"
    val stringBuilder = StringBuilder()

    File(pathName).useLines {
        it.toList().forEach { aLine ->
            stringBuilder.append("\n"+aLine)
        }}

    lineList.forEach { aLine ->
        stringBuilder.append("\n$aLine")
    }

    File(pathName).writeText(stringBuilder.toString().trim())

}

