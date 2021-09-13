package commands.changelog.markdown.parser

import commands.changelog.model.Document
import commands.changelog.model.Project
import commands.changelog.model.Section
import java.io.File

fun parseReleaseDocument(file: File): Document {
    return parseReleaseDocument(file.readLines())
}

fun parseReleaseDocument(lines: List<String>): Document {
    val document = Document(mutableListOf())
    var currentProjectSections = mutableListOf<Section>()
    var currentSectionLines = mutableListOf<String>()

    lines.forEachIndexed { i, line ->
        when {
            isStartOfProject(line) || i == lines.lastIndex -> {
                if (i == lines.lastIndex) {
                    currentSectionLines.add(line)
                }

                currentProjectSections.add(Section(currentSectionLines))
                document.add(Project(currentProjectSections))

                currentSectionLines = mutableListOf(line)
                currentProjectSections = mutableListOf()
            }

            isStartOfSection(line) -> {
                currentProjectSections.add(Section(currentSectionLines))
                currentSectionLines = mutableListOf(line)
            }

            line.startsWith("-") -> {
                currentSectionLines.add(line)
            }
        }
    }

    return document
}

fun isStartOfProject(line: String) = line.startsWith("##") && !line.startsWith("###")

fun isStartOfSection(line: String) = line.startsWith("###")

private fun printProject(project: Project) {
    project.flatten().forEach(::println)
}

private fun printSection(section: Section) {
    section.forEach(::println)
}