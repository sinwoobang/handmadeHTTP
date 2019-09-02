package http

import java.text.SimpleDateFormat
import java.util.Date


class Response(private var body: String = "") {
    private var statusCode: Int = 200

    override fun toString(): String {
        /*
        HTTP Response Example:

        HTTP/1.1 200 OK
        Date: Mon, 19 Jul 2004 16:18:20 GMT
        Server: Apache
        Last-Modified: Sat, 10 Jul 2004 17:29:19 GMT
        ETag: "1d0325-2470-40f0276f"
        Accept-Ranges: bytes
        Content-Length: 9328
        Connection: close
        Content-Type: text/html
        */

        val date = Date()
        val dateTxt = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(date)
        return "HTTP/1.1 $statusCode OK\n" +
                "Date: $dateTxt\n" +
                "Accept-Ranges: bytes\n" +
                "Server: Kotlin\n" +
                "Content-Length: ${body.length}\n" +
                "Connection: Closed\n\n" +
                body
    }
}