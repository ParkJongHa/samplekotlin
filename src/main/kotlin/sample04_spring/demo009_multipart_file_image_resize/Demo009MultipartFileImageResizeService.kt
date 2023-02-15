package sample04_spring.demo009_multipart_file_image_resize

import org.imgscalr.Scalr
import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStream
import javax.imageio.ImageIO

@Service
class Demo009MultipartFileImageResizeService {

    fun resize(fileInputStream: InputStream, newThumbnailFileName: String, extension: String) {
        val bufferedImage = ImageIO.read(fileInputStream)
        val outputImage = Scalr.resize(bufferedImage, 128/* pixel width */)
        val file = File(newThumbnailFileName)

        if (! file.createNewFile()) return // err
        ImageIO.write(outputImage, extension, file);
        outputImage.flush()
    }

}