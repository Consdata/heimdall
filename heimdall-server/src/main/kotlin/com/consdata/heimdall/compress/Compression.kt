package com.consdata.heimdall.compress

interface Compression {

    fun compress(text: String): ByteArray
    fun decompress(bytes: ByteArray): String

}