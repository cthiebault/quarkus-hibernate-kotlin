import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.8.21"
}


allprojects {

  group = "org.acme"
  version = "1.0.0-SNAPSHOT"

  apply(plugin = "kotlin")

  repositories {
    mavenCentral()
    mavenLocal()
  }

  java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = sourceCompatibility
    withSourcesJar()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = java.sourceCompatibility.toString()
      javaParameters = true
    }
  }

  tasks.withType<GenerateModuleMetadata> {
    suppressedValidationErrors.add("enforced-platform")
  }

  tasks.test {
    useJUnitPlatform()
    testLogging {
      showExceptions = true
      exceptionFormat = TestExceptionFormat.FULL
    }
  }

}
