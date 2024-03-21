plugins {
    alias(libs.plugins.sonatypeCentralPortalPublisher)
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

centralPortal {
    name = "space-spigot-api"

    username = project.findProperty("sonatypeUsername") as String
    password = project.findProperty("sonatypePassword") as String

    pom {
        name.set("Space Spigot Api")
        description.set("Extensions for spigot minecraft server")
        url.set("https://github.com/avionik-world/space-spigot")

        developers {
            developer {
                id.set("niklasnieberler")
                email.set("admin@avionik.world")
            }
        }
        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        scm {
            url.set("https://github.com/avionik-world/space-spigot.git")
            connection.set("git:git@github.com:avionik-world/space-spigot.git")
        }
    }
}