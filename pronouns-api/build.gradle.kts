/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    `maven-publish`
    signing
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications.create<MavenPublication>("mavenJava") {
        from(components["java"])
        pom {
            artifactId = artifactId
            description.set("A Minecraft: Java Edition plugin that allows players to set pronouns.")
            url.set("https://lucyy.me")
            name.set("squirtgun")
            licenses {
                license {
                    name.set("GPL-3.0-or-later")
                    url.set("http://www.gnu.org/licenses/gpl-3.0.txt")
                }
            }
            developers {
                developer {
                    id.set("lucyy-mc")
                    name.set("Lucy Poulton")
                    email.set("lucy@poulton.xyz")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/lucyy-mc/ProNouns.git")
                developerConnection.set("scm:git:git://github.com/lucyy-mc/ProNouns.git")
                url.set("https://github.com/lucyy-mc/ProNouns")
            }
        }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
    if (signatory == null) {
        logger.warn("No signatories available, skipping signing.")
    }
    sign(publishing.publications["mavenJava"])
}

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
}

description = "pronouns-api"