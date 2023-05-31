plugins {
  kotlin("jvm") version "1.8.21"
  kotlin("plugin.allopen") version "1.8.21"
  kotlin("plugin.jpa") version "1.8.21"
  kotlin("plugin.noarg") version "1.8.21"
  id("io.quarkus")
}


val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
  implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
  implementation("io.quarkus:quarkus-arc")
  implementation("io.quarkus:quarkus-hibernate-orm")
  implementation("io.quarkus:quarkus-hibernate-orm-panache-kotlin")
  implementation("io.quarkus:quarkus-hibernate-validator")
  implementation("io.quarkus:quarkus-jdbc-postgresql")
  implementation("io.quarkus:quarkus-kotlin")
  implementation("io.quarkus:quarkus-liquibase")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  testImplementation("io.quarkus:quarkus-junit5")
}

allOpen {
  annotation("javax.ws.rs.Path")
  annotation("javax.enterprise.context.ApplicationScoped")
  annotation("io.quarkus.test.junit.QuarkusTest")
  annotation("javax.persistence.Entity")
}
