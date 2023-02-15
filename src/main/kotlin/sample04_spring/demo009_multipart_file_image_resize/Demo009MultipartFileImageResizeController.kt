package sample04_spring.demo009_multipart_file_image_resize

import common.TimeUtil
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files

/*
How Can I Resize an Image Using Java?
https://www.baeldung.com/java-resize-image
 */
@RestController
class Demo009MultipartFileImageResizeController(
	val resizeService: Demo009MultipartFileImageResizeService
)  {

	private val imageTypeList = listOf(
		MediaType.IMAGE_JPEG_VALUE,
		MediaType.IMAGE_GIF_VALUE,
		MediaType.IMAGE_PNG_VALUE
	)

	/*
	postman
		1. select POST
		2. Body form-data radioCheck
		3. key value select File
	 */
	@PostMapping("/api/demo009")
	fun postFile(
		@RequestParam(name = "mFile") multipartFile: MultipartFile
	): String {

		println("""
			multipartFile.name: ${multipartFile.name} // mFile
			multipartFile.originalFilename: ${multipartFile.originalFilename} // text/html
			multipartFile.contentType: ${multipartFile.contentType} // index.html
			multipartFile.size: ${multipartFile.size} // 22101
		""".trimIndent())

		val extension = (multipartFile.originalFilename?:"").substringAfterLast(".", "")
		val newFileName = "C:\\uploadfilestorage\\${TimeUtil.now()}"
		val file = File(newFileName) // folder must exist

		Files.copy(multipartFile.inputStream, file.toPath())// save original file

		if (imageTypeList.contains(multipartFile.contentType)) { // image resize
			resizeService.resize(
				multipartFile.inputStream,
				"${newFileName}_thumb",
				extension
			)
		}

		return "ok"
	}

}