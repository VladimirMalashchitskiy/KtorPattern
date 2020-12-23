package controller

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.thymeleaf.*
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.io.File

fun main() {

    val server = embeddedServer(Netty, port = 9090) {

        routing {

            install(Thymeleaf) {
                setTemplateResolver(
                        ClassLoaderTemplateResolver().apply {
                            prefix = "pages/"
                            suffix = ".html"
                            characterEncoding = "utf-8"
                        }
                )
            }

            get("/") { call.respondFile(File("./src/main/resources/pages/Main.html")) }
        }
    }
    server.start(wait = true)
}

