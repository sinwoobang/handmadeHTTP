package http

import java.io.BufferedReader


class Request(reader: BufferedReader) {
    lateinit var method: String
        private set
    lateinit var path: String
        private set
    var headers = mutableMapOf<String, String>()
        private set
    var body: String = ""
        private set

    init {
        var isFirstLine = true  // All headers lines consist of Key: Value except the first line.
        var line: String
        headerParser@ while (true) {
            line = reader.readLine()
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

        if (this.headers.containsKey("Content-Length")) {
            val length: Int = Integer.parseInt(this.headers["Content-Length"])
            val bodyCharArr = CharArray(length)
            reader.read(bodyCharArr)
            this.body = String(bodyCharArr)
        }
    }

    override fun toString(): String {
        return "Request($method $path)"
    }
}