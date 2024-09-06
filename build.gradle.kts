plugins {
    kotlin("jvm") version "1.9.23"
    id("com.gradleup.shadow") version "8.3.0"
    id("io.papermc.paperweight.userdev") version "1.7.2"
}

group = "me.maanraj514"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    implementation("org.mongodb:mongodb-driver-kotlin-sync:5.1.2")
}

kotlin {
    jvmToolchain(21)
}

tasks.assemble {
    dependsOn(tasks.reobfJar)
}