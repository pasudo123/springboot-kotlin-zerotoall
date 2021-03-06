package rsync

import com.github.fracpete.processoutput4j.output.ConsoleOutputProcessOutput
import com.github.fracpete.processoutput4j.output.StreamingProcessOutput
import com.github.fracpete.rsync4j.RSync
import com.github.fracpete.rsync4j.Ssh
import com.github.fracpete.rsync4j.SshPass

class Rsync4jEx {

    fun simpleRsync() {
        val rsync = RSync()
            .source("/Users/account/pasudo-study/rsyncdir/one/")
            .destination("/Users/account/pasudo-study/rsyncdir/two")
            .recursive(true)

        val output = rsync.execute()
        println("out : ${output.stdOut}")
        val exitCode = output.exitCode
        println("exit code : $exitCode")

        if (exitCode > 0) {
            println("err : ${output.stdErr}")
        }
    }

    fun simpleRsyncWithOption() {
        val sour = "/Users/account/pasudo-study/rsyncdir/one/"
        val dest = "/Users/account/pasudo-study/rsyncdir/two"

        val rsync = RSync().also {
            it.setOptions(arrayOf("-r", sour, dest))
        }

        val output = rsync.execute()
        println("out : ${output.stdOut}")
        val exitCode = output.exitCode
        println("exit code : $exitCode")

        if (exitCode > 0) {
            println("err : ${output.stdErr}")
        }
    }

    fun simpleRsyncWithConsole() {
        val sour = "/Users/account/pasudo-study/rsyncdir/one/"
        val dest = "/Users/account/pasudo-study/rsyncdir/two"

        val rsync = RSync()
            .source(sour)
            .destination(dest)
            .archive(true)
            .delete(true)

        val output = ConsoleOutputProcessOutput()
        output.monitor(rsync.builder())
    }

    fun simpleRsyncWithStreaming() {
        val sour = "/Users/account/pasudo-study/rsyncdir/one/"
        val dest = "/Users/account/pasudo-study/rsyncdir/two"

        val rsync = RSync()
            .outputCommandline(true)
            .source(sour)
            .destination(dest)
            .recursive(true)
            .verbose(true)

        val output = StreamingProcessOutput(CustomOutput())
        output.monitor(rsync.builder())
    }

    /**
     * ?????? ???????????? ???????????? ?????? 1??? ??????????????? ??????.
     */
    fun sshCommand() {
        val user = ""
        val host = ""

        val ssh = Ssh()
            .outputCommandline(true)
            // remote ???????????? host ?????? ?????? : ?????? ??????????????? ?????? ????????? ???????????? ??????.
            .option("StrictHostKeyChecking=no")
            .hostname("$user@$host")
            .command("cd test; ls -al")

        val output = ConsoleOutputProcessOutput()
        output.monitor(ssh.builder())
    }

    fun sshCommandWithPath() {
        val sshPass = SshPass()
            .password("")

        val user = ""
        val host = ""

        val ssh = Ssh()
            .outputCommandline(true)
            .sshPass(sshPass)
            .option("StrictHostKeyChecking=no")
            .hostname("$user@$host")
            .command("cd test; ls -al")

        val output = ConsoleOutputProcessOutput()
        output.monitor(ssh.builder())
    }

    /**
     * ?????? ???????????? ???????????? ?????? 1??? ??????????????? ??????.
     */
    fun remoteRsync() {
        val user = ""
        val host = ""
        val sour = "$user@$host:test/"
        val dest = "/Users/account/pasudo-study/rsyncdir/remote"

        val rsync = RSync()
            .source(sour)
            .destination(dest)
            .archive(true)
            .recursive(true)
            .verbose(true)

        val output = StreamingProcessOutput(CustomOutput())
        output.monitor(rsync.builder())
    }
}