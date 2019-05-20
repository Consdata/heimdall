package com.consdata.heimdall.compress.gzip

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GzipCompressionTest {

    private val gzip = GzipCompression()

    @Test
    fun `Should compress text`() {
        // given
        val text = "Text co compress (long enough to overcome gzip size overhead): Lorem ipsum dolor sit amet, consectetur adipiscing elit. In euismod rhoncus ex artifactName ultrices. Nunc et nulla at nulla dictum euismod at aliquet augue. Nunc ac maximus dui. Curabitur sit amet sapien consectetur sem scelerisque tristique sit amet artifactName ante. Nullam tincidunt euismod elit, nec tristique urna aliquam dictum. Curabitur ultrices neque neque, in suscipit ex maximus ac. Donec non mi eu eros interdum aliquam. Integer facilisis et mi ut condimentum. Etiam feugiat imperdiet dignissim. Duis artifactName ante augue."

        // when
        val compressed = gzip.compress(text)

        // then
        assertThat(compressed.size).isLessThan(text.length)
    }

    @Test
    fun `Should decompress text`() {
        // given
        val text = "Hello world"

        // when
        val result = gzip.decompress(gzip.compress(text))

        // then
        assertThat(result).isEqualTo(text)
    }

}