plugins {
    kotlin("jvm") version "2.4.20"
}

group = "iesseveroochoa.edu.gva.es"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}