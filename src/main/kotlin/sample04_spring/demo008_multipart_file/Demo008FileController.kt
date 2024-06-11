package sample04_spring.demo008_multipart_file

import common.TimeUtil
import org.apache.commons.io.IOUtils
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files

@RestController
class Demo008FileController {

	/*
	postman
		1. select POST
		2. Body form-data radioCheck
		3. key value select File
	 */
	@PostMapping("/api/demo008")
	fun postFile(
		@RequestParam(name = "mFile") multipartFile: MultipartFile
	): String {

		println("""
			multipartFile.name: ${multipartFile.name} // mFile
			multipartFile.originalFilename: ${multipartFile.originalFilename} // text/html
			multipartFile.contentType: ${multipartFile.contentType} // index.html
			multipartFile.size: ${multipartFile.size} // 22101
		""".trimIndent())

		val fileNameForSave = TimeUtil.getNow()

		val file = File("C:\\uploadfilestorage\\${fileNameForSave}") // folder must exist

		Files.copy(multipartFile.inputStream, file.toPath())

		return "ok"
	}

	/*
	http://localhost:8080/api/demo008/imageFileId
	 */
	@ResponseBody
	@GetMapping(
		value = ["/api/demo008/{imageFileId}"]
		, produces = [MediaType.IMAGE_JPEG_VALUE] //   consumes / declare data type client -> server   //   produces / declare data type server -> client
	)
	fun getImageFile(
		@PathVariable(name = "imageFileId") imageFileId: String?
	): ByteArray? {
		if (imageFileId.isNullOrBlank()) return null

		val filePath = "C:\\uploadfilestorage\\$imageFileId"

		if (! File(filePath).exists()) return null

		return try {
			FileInputStream(filePath).use {fis -> IOUtils.toByteArray(fis)}
		} catch(e: Exception) {
			e.printStackTrace()
			null
		}
	}

}