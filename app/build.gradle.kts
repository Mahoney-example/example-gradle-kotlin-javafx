plugins {
  kotlin("jvm")
  id("org.openjfx.javafxplugin")
  application
}

dependencies {
  implementation(project(":core"))
}

configure<org.openjfx.gradle.JavaFXOptions> {
  modules = listOf("javafx.controls")
}

application {
  mainClassName = "$moduleName/example.app.Main"
}
