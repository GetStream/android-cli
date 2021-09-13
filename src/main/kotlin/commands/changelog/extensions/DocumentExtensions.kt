package commands.changelog.extensions

import commands.changelog.model.Document
import commands.changelog.model.Project
import commands.changelog.model.Section

fun List<List<Section>>.toDocument(): Document =
    this.map { sections ->
        Project(sections.toMutableList())
    }.let { projects ->
        Document(projects.toMutableList())
    }
