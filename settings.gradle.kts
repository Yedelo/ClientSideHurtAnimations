pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net")
		maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.10-alpha.7"
	id("dev.kikugie.loom-back-compat") version "0.4.2"
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

loomx.loomVersion = "1.16-SNAPSHOT"

stonecutter {
	create(rootProject) {
		fun registerProject(versionString: String, loader: String) {
			version("$versionString-$loader", versionString).buildscript("build.$loader.gradle.kts")
		}

		registerProject("26.1", "fabric")
		registerProject("26.1", "neoforge")
		vcsVersion = "26.1-fabric"
	}
}