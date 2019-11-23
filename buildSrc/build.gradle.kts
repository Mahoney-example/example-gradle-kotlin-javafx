plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  jcenter()
  mavenCentral()
}

kotlin {
  sourceSets {
    main { kotlin.setSrcDirs(setOf("src")) }
    test { kotlin.setSrcDirs(setOf("tests")) }
  }
}

dependencies {
  runtimeOnly("org.javamodularity:moduleplugin:1.5.0")
  runtimeOnly("org.openjfx:javafx-plugin:0.0.8")
}

kotlinDslPluginOptions {
  experimentalWarning.set(false)
}
