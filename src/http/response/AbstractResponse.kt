package http.response


abstract class AbstractResponse {
    abstract val statusCode: Int
    abstract fun toHTTPText(): String
}