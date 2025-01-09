pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "bebra-cinema-android"

include(":app")

include(":domain")
project(":domain").apply {
    projectDir = file("core/domain")
    buildFileName = "build.gradle.kts"
}

include(":remote-bebra-cinema-retrofit")
project(":remote-bebra-cinema-retrofit").apply {
    projectDir = file("data/remote-bebra-cinema-retrofit")
    buildFileName = "build.gradle.kts"
}

include(":output-port-adapter-retrofit")
project(":output-port-adapter-retrofit").apply {
    projectDir = file("data/output-port-adapter-retrofit")
    buildFileName = "build.gradle.kts"
}