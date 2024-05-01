plugins {
    kotlin("jvm") version "1.9.23"
    jacoco
}

group = "com.github.ralfstuckert"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://maven.pkg.github.com/ralfstuckert/pdftools")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USER") ?: ""
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }

    mavenCentral()
}



dependencies {
    implementation("org.jetbrains:markdown:0.5.0")
    implementation("com.github.librepdf:openpdf:1.3.34")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.8.0")
    testImplementation("com.github.ralfstuckert:pdftools:0.3.0")
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

val jacocoExclude =
    listOf("**/Main.kt")

tasks.withType<JacocoReport> {
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
    classDirectories.setFrom(
        classDirectories.files.map {
            fileTree(it).matching {
                exclude(jacocoExclude)
            }
        },
    )
}

