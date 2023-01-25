package sample04spring.multipartfile

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class FileController (
	val fileService: FileService
) {

	@PostMapping("/api/file")
	fun postMentFile(
		@RequestParam(name = "fileName") fileName: String,
		@RequestParam(name = "mimeType") mimeType: String,
		@RequestParam file: MultipartFile
	): String {

		fileService.registFile(
			mimeType = mimeType,
			fileName = fileName,
			file = file
		)

		return "ok";
	}

}