package sample01_kotlin.demo016_image_compress

import net.coobird.thumbnailator.Thumbnails
import java.io.File

// pngquant (png 만 됨)
// brew install pngquant (mac) // sudo apt install pngquant (ubuntu)
fun main() {
    val input = File("/Users/parkjongha/Desktop/winner_reward_250503.png")
    val output = File("/Users/parkjongha/Desktop/winner_reward_250503_q10-30.png")

    val process = ProcessBuilder(
        "pngquant",
        "--quality=10-30",
        "--force",
        "--output", output.absolutePath,
        input.absolutePath
    ).redirectErrorStream(true).start()

    val exitCode = process.waitFor()
    if (exitCode != 0) {
        val errorOutput = process.inputStream.bufferedReader().readText()
        throw RuntimeException("pngquant 실패: $errorOutput")
    }
}

// implementation("net.coobird:thumbnailator:0.4.20")
// 단점 png 깨짐, 어떤 파일은 용량이 더 커지는 것도 있음
fun main2() {
    println( "-----------------------" )
    val inputPath = "/Users/parkjongha/Desktop/eur.jpg"
    val outputPath = "/Users/parkjongha/Desktop/eur_q7.png"

    Thumbnails.of(File(inputPath))
        .scale(1.0) // 크기 변경 없으면 1.0
        .outputQuality(0.7) // 0.0 ~ 1.0 (70% 품질)
        .toFile(File(outputPath))
}

// jpeg ()
// brew install jpegoptim (mac) // sudo apt install jpegoptim
fun main1() {
    val file = File("/Users/parkjongha/Desktop/유럽.jpg") // 해당 파일을 변경함

    val process = ProcessBuilder(
        "jpegoptim",
        "--max=80",            // 품질 상한 (0~100)
        "--strip-all",         // 메타데이터 제거
        "--all-progressive",   // progressive JPEG 저장
        file.absolutePath
    ).redirectErrorStream(true).start()

    val result = process.inputStream.bufferedReader().readText()
    val exitCode = process.waitFor()
    if (exitCode != 0) {
        throw RuntimeException("jpegoptim 실패:\n$result")
    }
}