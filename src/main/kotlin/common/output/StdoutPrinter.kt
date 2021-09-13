package common.output

import com.github.ajalt.clikt.output.TermUi.echo

class StdoutPrinter : Printer {
    override fun print(text: String) {
        echo(text)
    }
}