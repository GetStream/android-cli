import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.file
import commands.changelog.markdown.clean
import commands.changelog.markdown.parser.parseChangelogFile
import commands.ktlint.ktlintCommand
import commands.ktlint.parseModules
import commands.ktlint.unitTestCommand
import common.output.FilePrinter
import common.output.StdoutPrinter
import common.output.print
import common.output.println
import common.terminal.runCommand
import java.io.File

private class AndroidCli : CliktCommand() {
    override fun run() = Unit
}

private class ParseUnreleasedChangelog : CliktCommand(help = "Parses the unreleased changeg .md file") {
    private val changeLogFile by argument(help = "Path of file").file(mustBeReadable = true, mustExist = true)

    override fun run() {
        val document = parseChangelogFile(changeLogFile).clean()

        FilePrinter("CHANGELOG_PARSED.md").use { printer ->
            document.print(printer)
        }

        println("CHANGELOG_PARSED.md generated")
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

private fun subCommands() = listOf(ParseUnreleasedChangelog(), KtlintSelected(), UnitTestSelected())

fun main(args: Array<String>) = AndroidCli()
    .subcommands(subCommands())
    .main(args)
