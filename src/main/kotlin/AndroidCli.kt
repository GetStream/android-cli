import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.file
import commands.changelog.markdown.clean
import commands.changelog.markdown.parser.parseReleaseDocument
import commands.ktlint.ktlintCommand
import commands.ktlint.parseModules
import commands.ktlint.unitTestCommand
import common.output.StdoutPrinter
import common.output.print
import common.terminal.runCommand

private class AndroidCli : CliktCommand() {
    override fun run() = Unit
}

private class ParseUnreleadChangelog : CliktCommand(help = "Parses the unreleased changeg .md file") {
    private val changeLogFile by argument(help = "Path of file").file(mustBeReadable = true, mustExist = true)

    override fun run() {
        parseReleaseDocument(changeLogFile)
            .clean()
            .print(StdoutPrinter())
    }
}

private class KtlintSelected : CliktCommand(help = "Applies ktlint only in the selected modules ") {
    private val modulesFile by argument(help = "Path of file").file(mustBeReadable = true, mustExist = true)

    override fun run() {
        parseModules(modulesFile)
            .ktlintCommand()
            .runCommand(printCommand = true)
    }
}

private class UnitTestSelected : CliktCommand(help = "Applies unit tests only in the selected modules ") {
    private val modulesFile by argument(help = "Path of file").file(mustBeReadable = true, mustExist = true)

    override fun run() {
        parseModules(modulesFile)
            .unitTestCommand()
            .runCommand(printCommand = true)
    }
}

private fun subCommands() = listOf(ParseUnreleadChangelog(), KtlintSelected(), UnitTestSelected())

fun main(args: Array<String>) = AndroidCli()
    .subcommands(subCommands())
    .main(args)
