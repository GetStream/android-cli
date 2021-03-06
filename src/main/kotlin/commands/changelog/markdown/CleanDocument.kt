package commands.changelog.markdown

import commands.changelog.extensions.toDocument
import commands.changelog.markdown.parser.isStartOfProject
import commands.changelog.model.Document
import commands.changelog.model.Project
import commands.changelog.model.Section

internal fun Document.clean(): Document =
    removeEmptyProjects()
        .map { project -> project.removeEmptySections() }
        .toDocument()

internal fun Document.removeEmptyProjects(): List<Project> =
    filter { project ->
        project.flatten().any { line ->
            line.startsWith("-")
        }
    }

internal fun List<Section>.removeEmptySections(): List<Section> =
    filter { section ->
        section.any { line ->
            line.startsWith("-") || isStartOfProject(line)
        }
    }