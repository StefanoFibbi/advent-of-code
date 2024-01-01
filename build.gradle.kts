import org.jetbrains.kotlin.com.intellij.openapi.vfs.StandardFileSystems.jar

plugins {
    kotlin("jvm") version "1.9.21"
}

group = "adventofcode"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(project("aoc2023"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Main-Class" to "AdventOfCodeApplication"
        )
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}