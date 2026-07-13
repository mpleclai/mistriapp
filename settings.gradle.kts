pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "mistriapp"
include(":app")
include(":feature:villagers")
include(":feature:home")
include(":feature:museum")
include(":feature:fishing")
include(":core:common")
include(":core:ui")
include(":core:navigation")
include(":core:data")
include(":core:domain")
