package model.usertype.prototype

import model.comparator.Comparator
import model.comparator.IntegerComparator
import model.usertype.type.IntegerClass
import java.io.InputStream
import java.util.*

class IntegerType : UserType {
    override fun typeName(): String {
        return "Integer"
    }

    override fun create(): Any {
        val min = -1000
        val max = 1000
        val rand = Random()
        return IntegerClass(rand.nextInt((max - min)) + min)
    }

    override fun clone(obj: Any): Any {
        return IntegerClass((obj as IntegerClass).getIntegerValue())
    }

    override fun readValue(inputStream: InputStream): Any {
        return inputStream.read()
    }

    override fun parseValue(line: String): Any {
        return IntegerClass(line.toInt())
    }

    override fun getTypeComparator(): Comparator {
        return IntegerComparator()
    }
}