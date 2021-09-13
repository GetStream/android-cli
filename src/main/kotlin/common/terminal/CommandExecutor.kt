package common.terminal

import com.github.ajalt.clikt.output.TermUi.echo
import java.io.File
import java.util.concurrent.TimeUnit

fun executeCommand(command: String) {
    Runtime.getRuntime().exec(command)
}

fun String.runCommand(workingDir: File = File("."), printCommand: Boolean = false) {
    if (printCommand) {
        echo("Command: $this")
    }

    ProcessBuilder(*split(" ").toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()
        .waitFor(30, TimeUnit.SECONDS)
}