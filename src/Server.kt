/* ClientHandler handles data. HTTP Parser is located on the handler. */
import http.Response
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    print("Input Port Number: ")
    val port: Int = readLine()!!.toInt()
    val server = ServerSocket(port)
    println("Server is running on port ${server.localPort}")

    while (true) {
        val client = server.accept()
        println("Client connected: ${client.inetAddress.hostAddress}")

        thread { ClientHandler(client).run() }
    }

}

class ClientHandler(private val client: Socket) {
    private val reader: Scanner = Scanner(client.getInputStream())
    private val writer: OutputStream = client.getOutputStream()
    private var isRunning: Boolean = false

    fun run() {
        isRunning = true
        while (isRunning) {
            try {
                val raw = reader.nextLine()
                println(">> $raw")
                if (raw == "EXIT"){
                    shutdown()
                    continue
                }

                val response = Response("OK")
                print("Res >> \n" + response.toString())
                write(response)
            } catch (ex: Exception) {
                shutdown()
            } finally {

            }

        }
    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun write(response: Response) {
        write(response.toString())
    }

    private fun shutdown() {
        isRunning = false
        client.close()
        println("${client.inetAddress.hostAddress} closed the connection")
    }

}