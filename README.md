# Quarkus 3 / Hibernate inheritance / Kotlin 

Sample project to prove that Quarkus 3.0.4.Final does not support Hibernate inheritance with Kotlin.

Quarkus 2.16.7.Final works fine:
```shell
git checkout quarkus2
./gradlew clean build --no-build-cache
```

Quarkus  3.0.4.Final fails:
```shell
git checkout quarkus3
./gradlew clean build --no-build-cache
```
