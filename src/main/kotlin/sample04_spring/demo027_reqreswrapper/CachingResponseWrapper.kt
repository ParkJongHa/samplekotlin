package sample04_spring.demo027_reqreswrapper

import io.micrometer.common.util.StringUtils
import jakarta.servlet.ServletOutputStream
import jakarta.servlet.WriteListener
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletResponseWrapper
import org.apache.commons.io.output.TeeOutputStream
import org.springframework.util.FastByteArrayOutputStream
import java.io.*
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class CachingResponseWrapper: HttpServletResponseWrapper {

    private val content: FastByteArrayOutputStream = FastByteArrayOutputStream(1024)
    private var outputStream: ServletOutputStream? = null
    private lateinit var writer: PrintWriter
    public val encoding: Charset

    @Throws(IOException::class)
    constructor(response: HttpServletResponse) : super(response) {

        var characterEncoding: String = response.characterEncoding
        if (StringUtils.isEmpty(characterEncoding)) {
            characterEncoding = StandardCharsets.UTF_8.name()
        }

        this.encoding = Charset.forName(characterEncoding)
    }

    override fun getOutputStream(): ServletOutputStream? {
        if (this.outputStream == null) {
            this.outputStream = CachedServletOutputStream(response.outputStream, this.content)
        }
        return this.outputStream
    }

    override fun getWriter(): PrintWriter {
        if (writer == null) {
            writer = PrintWriter(OutputStreamWriter(content, this.characterEncoding), true)
        }
        return writer
    }

    class CachedServletOutputStream(one: OutputStream, two: OutputStream) : ServletOutputStream() {

        private val targetStream: TeeOutputStream

        init {
            targetStream = TeeOutputStream(one, two)
        }

        override fun write(arg: Int) {
            this.targetStream.write(arg)
        }

        override fun write(b: ByteArray) {
            super.write(b)
        }

        override fun flush() {
            super.flush()
            this.targetStream.flush()
        }

        override fun close() {
            super.close()
            this.targetStream.close()
        }

        override fun isReady(): Boolean {
            return false
        }

        override fun setWriteListener(listener: WriteListener?) {
            throw UnsupportedOperationException("not support")
        }
    }
}