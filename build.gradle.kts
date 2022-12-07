import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")

        }
    }

    dependencies {
        classpath("org.owasp:dependency-check-gradle:7.3.2")
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

group = "nl.rabobank.customer"
version = "1.0"

plugins {
    kotlin("jvm") version "1.7.21"
    id("org.owasp.dependencycheck") version "7.3.2"
    id("application")
    id ("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.21")
    implementation("com.opencsv:opencsv:5.7.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("com.google.code.gson:gson:2.10")


    // force dependency version
    implementation("org.jetbrains.kotlin:kotlin-reflect") {
        version {
            strictly("1.7.21")
        }
    }

    // testing
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}


dependencyCheck {
    failBuildOnCVSS = 1.0f
    failOnError = true
}

application {
    mainClass.set("nl.rabobank.customer.statement.ConsoleApplication")
}