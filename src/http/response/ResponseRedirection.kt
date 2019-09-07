package http.response

class ResponseRedirection(private val location: String): AbstractResponse()  {
    override val statusCode: Int = 302
    override fun toHTTPText(): String {
        /*
        HTTP Response Example:

        HTTP/1.1 302 Found
Location: http://www.iana.org/domains/example/
        */

        return """HTTP/1.1 302 Found
Location: $location"""
    }
}