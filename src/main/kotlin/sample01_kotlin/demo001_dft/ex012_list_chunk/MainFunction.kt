package sample01_kotlin.demo001_dft.ex012_list_chunk

fun main() {
    val sampleList = listOf(
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z"
    )

    sampleList.chunked(5).forEach{
        println(it.joinToString("-"))
    }
    /*
a-b-c-d-e
f-g-h-i-j
k-l-m-n-o
p-q-r-s-t
u-v-w-x-y
z
     */
}