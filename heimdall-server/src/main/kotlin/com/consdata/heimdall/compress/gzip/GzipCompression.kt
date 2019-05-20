package com.consdata.heimdall.compress.gzip

import com.consdata.heimdall.compress.Compression
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

@Component
class GzipCompression : Compression {

    override fun compress(text: String): ByteArray {
        val bos = ByteArrayOutputStream()
        GZIPOutputStream(bos).bufferedWriter(StandardCharsets.UTF_8).use { it.write(text) }
        return bos.toByteArray()
    }

    override fun decompress(bytes: ByteArray): String {
        return GZIPInputStream(bytes.inputStream()).bufferedReader(StandardCharsets.UTF_8).use { it.readText() }
    }

}