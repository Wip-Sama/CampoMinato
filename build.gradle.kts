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
    testImplementation( "org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly( "org.junit.jupiter:junit-jupiter-engine:5.8.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.openjfx:javafx-controls:22")
    implementation("org.openjfx:javafx-fxml:22")
}

javafx {
    version = "22"
    modules = listOf(
        "javafx.controls",
        "javafx.fxml",
        "javafx.graphics",
        "javafx.media",
        "javafx.swing",
        "javafx.web",
        "javafx.base"
    )
}

tasks.test {
    useJUnitPlatform()
    useTestNG()
}