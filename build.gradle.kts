import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    kotlin("jvm") version "1.5.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("AndroidCliKt")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.ajalt.clikt:clikt:3.2.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
}