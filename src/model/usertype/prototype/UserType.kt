package model.usertype.prototype

import model.comparator.Comparator
import java.io.IOException
import java.io.InputStream

interface UserType {
    fun typeName(): String
    fun create(): Any
    fun clone(obj: Any): Any
    @Throws(IOException::class)
    fun readValue(inputStream: InputStream): Any
    fun parseValue(line: String): Any
    fun getTypeComparator(): Comparator
}