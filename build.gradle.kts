import org.gradle.api.JavaVersion.VERSION_11
import org.javamodularity.moduleplugin.ModuleSystemPlugin
import org.javamodularity.moduleplugin.tasks.TestModuleOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openjfx.gradle.JavaFXOptions

plugins {
  val kotlinVersion = "1.3.60"
  kotlin("jvm") version kotlinVersion apply false
  id("org.javamodularity.moduleplugin") apply false
}

allprojects {
  repositories {
    jcenter()
    mavenCentral()
  }
}

val javaVersion = VERSION_11

subprojects {

  pluginManager.withPlugin("kotlin") {

    val test by tasks.existing(Test::class)

    val api by configurations
    val testImplementation by configurations

    val mainSrc = setOf("src")
    val testSrc = setOf("tests")

    configure<JavaPluginExtension> {
      sourceCompatibility = javaVersion
      targetCompatibility = javaVersion

      configure<SourceSetContainer> {
        named("main") { java.setSrcDirs(mainSrc) }
        named("test") { java.setSrcDirs(testSrc) }
      }
    }

    configure<KotlinJvmProjectExtension> {
      sourceSets {
        named("main") { kotlin.setSrcDirs(mainSrc) }
        named("test") { kotlin.setSrcDirs(testSrc) }
      }
    }

    dependencies {
      api(kotlin("stdlib"))

      testImplementation(kotlintest("core"))
      testImplementation(kotlintest("runner-junit5"))
    }

    tasks.withType<KotlinCompile> {
      kotlinOptions.jvmTarget = javaVersion.toString()
    }

    apply<ModuleSystemPlugin>()

    tasks {
      test {
        useJUnitPlatform()
        extensions.configure(TestModuleOptions::class) {
          runOnClasspath = true
        }
      }
    }
  }

  pluginManager.withPlugin("org.openjfx.javafxplugin") {
    configure<JavaFXOptions> {
      version = javaVersion.toString()
    }
  }
}
