package sample04_spring.demo027_reqreswrapper

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.apache.commons.io.IOUtils
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class CachingRequestWrapper: HttpServletRequestWrapper {

    val encoding: Charset
    private lateinit var rawData: ByteArray

    @Throws(IOException::class)
    constructor(request: HttpServletRequest) : super(request) {
        var characterEncoding = request.characterEncoding

        if(characterEncoding.isNullOrBlank()) {
            characterEncoding = StandardCharsets.UTF_8.name()
        }

        this.encoding = Charset.forName(characterEncoding)

        this.rawData = IOUtils.toByteArray(request.inputStream)
    }

    override fun getInputStream(): ServletInputStream {
        return CachedServletInputStream(rawData)
    }

    override fun getReader(): BufferedReader {
        return BufferedReader(InputStreamReader(this.inputStream, this.encoding))
    }

    class CachedServletInputStream(contents: ByteArray) : ServletInputStream() {

        private val buffer: ByteArrayInputStream

        init {
            this.buffer = ByteArrayInputStream(contents)
        }

        override fun read(): Int {
            return buffer.read()
        }

        override fun isFinished(): Boolean {
            return buffer.available() == 0
        }

        override fun isReady(): Boolean {
            return true
        }

        override fun setReadListener(listener: ReadListener?) {
            throw UnsupportedOperationException()
        }

    }
}