import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.9.22"
}

allprojects {
    group = "world.avionik"
    version = "1.0.1"

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("com.github.johnrengelman.shadow")
    }

    repositories {
        mavenCentral()

        // papermc repositories
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

subprojects {
    dependencies {
        compileOnly(kotlin("stdlib"))

        // papermc dependencies
        compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

        // avionik dependencies
        compileOnly("world.avionik:fancy-kotlin-paper:1.0.2")
        compileOnly("world.avionik:minecraft-common:1.0.1")
    }

    tasks.named("shadowJar", ShadowJar::class) {
        mergeServiceFiles()
    }
}