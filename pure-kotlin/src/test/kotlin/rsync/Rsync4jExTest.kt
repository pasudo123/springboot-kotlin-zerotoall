package rsync

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class Rsync4jExTest {

    private val rsync4jEx = Rsync4jEx()

    @Test
    @DisplayName("simpleRsync")
    fun simpleRsyncTest() {

        // given
        rsync4jEx.simpleRsync()

        // when

        // then
    }

    @Test
    @DisplayName("simpleRsyncWithOption")
    fun simpleRsyncWithOptionTest() {

        // given
        rsync4jEx.simpleRsyncWithOption()

        // when

        // then
    }

    @Test
    @DisplayName("simpleRsyncWithConsole")
    fun simpleRsyncWithConsoleTest() {

        // given
        rsync4jEx.simpleRsyncWithConsole()

        // when

        // then
    }

    @Test
    @DisplayName("simpleRsyncWithStreaming")
    fun simpleRsyncWithStreamingTest() {

        // given
        rsync4jEx.simpleRsyncWithStreaming()

        // when

        // then
    }

    @Test
    @DisplayName("sshCommand ")
    fun sshCommandTest() {

        // given
        rsync4jEx.sshCommand()

        // when

        // then
    }

    @Test
    @DisplayName("remoteRsync ")
    fun remoteRsyncTest() {

        // given
        rsync4jEx.remoteRsync()

        // when

        // then
    }

    @Test
    @DisplayName("sshCommandWithPath")
    fun sshCommandWithPathTest() {

        // given
        rsync4jEx.sshCommandWithPath()

        // when

        // then
    }
}