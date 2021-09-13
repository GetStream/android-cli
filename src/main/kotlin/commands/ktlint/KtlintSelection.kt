package commands.ktlint

import com.squareup.moshi.Types
import common.json.moshi
import java.io.File

fun parseModules(modulesFile: File): List<String> {
    val json = modulesFile.readLines().joinToString()
    return moshi()
        .adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java))
        .fromJson(json)
        ?: emptyList()
}

fun List<String>.ktlintCommand(): String = filterKtlintModules().generateGradleCommand { "ktlintCheck" }

fun List<String>.unitTestCommand(): String = filterUnitTestableModules().generateGradleCommand { "testDebug" }

private fun List<String>.filterKtlintModules(): List<String> =
    filter { module -> !optOutKtlintModules().contains(module) }

private fun List<String>.filterUnitTestableModules(): List<String> =
    filter { module -> optInUnitTestModules().contains(module) }

private fun optOutKtlintModules() = listOf(
    "stream-chat-android-core",
    "stream-chat-android-test",
)

private fun optInUnitTestModules() = listOf(
    "stream-chat-android",
    "stream-chat-android-client",
    "stream-chat-android-offline",
    "stream-chat-android-ui-common",
    "stream-chat-android-ui-components",
)

private fun List<String>.generateGradleCommand(commandFunction: (String) -> String): String {
    val command = joinToString(separator = " ") { module -> "$module:${commandFunction(module)}" }

    return buildString {
        append("./gradlew ")
        append(command)
    }
}
