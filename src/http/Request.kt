package http

import java.util.Scanner


class Request(scanner: Scanner) {
    private lateinit var method: String
    private lateinit var path: String
    private var headers = mutableMapOf<String, String>()
    private var body: String = ""

    init {
        var isFirstLine = true  // All headers lines consist of Key: Value except the first line.

        headerParser@ while (true) {
            val line = scanner.nextLine()
            when {
                isFirstLine -> {  // ex. GET / HTTP/1.1
                    isFirstLine = false

                    val basicInfo: List<String> = line.split(' ')
                    this.method = basicInfo[0]
                    this.path = basicInfo[1]
                }
                line == "" -> {  // End of Headers
                    break@headerParser
                }
                else -> {  // Normal case of parsing headers
                    val headerKeyValue = line.split(": ")
                    val key: String = headerKeyValue[0]
                    val value: String = headerKeyValue[1]
                    this.headers[key] = value
                }
            }
        }
    }

    override fun toString(): String {
        return "Request($method $path) - $headers"
    }
}