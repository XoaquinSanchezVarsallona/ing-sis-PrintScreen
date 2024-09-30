plugins {
    kotlin("jvm") version "2.0.0"
}

dependencies {
    implementation("org.eclipse.jgit:org.eclipse.jgit:7.0.0.202409031743-r")
}

allprojects {
    group = "com.printscript"
    version = "1.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
    }

}