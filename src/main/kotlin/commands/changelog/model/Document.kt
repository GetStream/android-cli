package commands.changelog.model

data class Document(val projects: MutableList<Project>): MutableList<Project> by projects