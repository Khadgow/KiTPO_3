package model.usertype.prototype

import model.comparator.Comparator
import model.comparator.TimeComparator
import model.usertype.type.TimeClass
import java.io.InputStream
import java.util.*

class TimeType : UserType {
    override fun typeName(): String {
        return "Time"
    }

    override fun create(): Any {
        val minHour = 0
        val maxHour = 23
        val minTime = 0
        val maxTime = 59
        val rand = Random()
        val hour = rand.nextInt(maxHour - minHour)
        val minute = rand.nextInt(maxTime - minTime)
        val second = rand.nextInt(maxTime - minTime)
        val dateTimeValue: TimeClass
        dateTimeValue = try {
            TimeClass(hour, minute, second)
        } catch (ex: Exception) {
            println("Bad time, generating using a static values")
            TimeClass()
        }
        return dateTimeValue
    }

    override fun clone(obj: Any): Any {
        val copyDateTime: TimeClass
        copyDateTime = try {
            TimeClass((obj as TimeClass).getHour(), obj.getMinute(), obj.getSecond())
        } catch (ex: Exception) {
            TimeClass()
        }
        return copyDateTime
    }

    override fun readValue(inputStream: InputStream): Any {
        return inputStream.read()
    }

    override fun parseValue(line: String): Any {
        val timeStr = line.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val timeInt = arrayOfNulls<Int>(3)
        for (i in 0..2) {
            timeInt[i] = timeStr[i].toInt()
        }
        val dateTimeValue: TimeClass
        dateTimeValue = try {
            TimeClass(timeInt[0]!!, timeInt[1]!!, timeInt[2]!!)
        } catch (ex: Exception) {
            println("Bad time, generating using a static values")
            TimeClass()
        }
        return dateTimeValue
    }

    override fun getTypeComparator(): Comparator {
        return TimeComparator()
    }
}