package common.output

import commands.changelog.model.Document
import commands.changelog.model.Section

interface Printer {
    fun println(text: String)
}

fun Document.print(printer: Printer) {
    flatten().println(printer)
}

fun List<Section>.println(printer: Printer) {
    forEach { section ->
        section.forEach(printer::println)
        printer.println("")
    }
}
