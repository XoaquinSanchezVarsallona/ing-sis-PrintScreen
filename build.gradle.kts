plugins {
    id("java")
    kotlin("jvm") version "2.0.0"
    id("com.diffplug.spotless") version "6.7.1"
    id("org.jetbrains.kotlinx.kover") version "0.7.6"
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
    `maven-publish`
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

allprojects {
    group = "com.printscript"
    version = "1.1-SNAPSHOT"

    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "org.jetbrains.kotlinx.kover")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
    }

}

tasks.test {
    useJUnitPlatform()
}

subprojects {
    apply(plugin = "maven-publish")

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifactId = project.name.lowercase()
                from(components["java"])
            }
        }

        repositories {
            maven {
                name = "GithubPackages"
                url = uri("https://maven.pkg.github.com/snippetsearcher-ingsis/printscript")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }

            maven {
                name = "NotMavenCentral"
                url = uri("https://maven.mediaversetv.com/snapshots")
                credentials {
                    username = System.getenv("NOT_MAVEN_CENTRAL_USERNAME")
                    password = System.getenv("NOT_MAVEN_CENTRAL_PASSWORD")
                }
            }
        }
    }
}
