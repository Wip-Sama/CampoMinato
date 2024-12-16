plugins {
    kotlin("jvm") version "2.0.21"
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.beryx.jlink") version "3.1.0-rc-1"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"

}

kotlin {
    jvmToolchain(22)
}

group = "org.wip"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.openjfx:javafx-controls:22")
    implementation("org.openjfx:javafx-fxml:22")

}

tasks.test {
    useJUnitPlatform()
}