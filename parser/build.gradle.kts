plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api(project(":models"))
    testImplementation(project(":interpreter"))
}

tasks.test {
    useJUnitPlatform()
}
