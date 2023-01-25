
package sample04spring.multipartfile

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class FileService {

	@Transactional
	fun registFile(
		mimeType: String,
		fileName: String,
		file: MultipartFile
	) {
		file.inputStream.available().toLong()
	}

}
