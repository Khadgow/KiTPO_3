package model.usertype.type

import java.io.Serializable

class TimeClass : Serializable {
    private var hour = 0
    private var minute = 0
    private var second = 0

    constructor(hour: Int, minute: Int, second: Int) {
        try {
            setHour(hour)
            setMinute(minute)
            setSecond(second)
        } catch (ex: Exception) {
            println("Bad time")
        }
    }

    constructor() {
        try {
            setHour(11)
            setMinute(11)
            setSecond(11)
        } catch (ex: Exception) {
            println("Bad time")
        }
    }

    fun getHour(): Int {
        return hour
    }

    @Throws(Exception::class)
    fun setHour(hour: Int) {
        if (hour < 0 || hour > 23) {
            throw Exception("Bad hour")
        }
        this.hour = hour
    }

    fun getMinute(): Int {
        return minute
    }

    @Throws(Exception::class)
    fun setMinute(minute: Int) {
        if (minute < 0 || minute > 59) {
            throw Exception("Bad minute")
        }
        this.minute = minute
    }

    fun getSecond(): Int {
        return second
    }

    @Throws(Exception::class)
    fun setSecond(second: Int) {
        if (second < 0 || second > 59) {
            throw Exception("Bad second")
        }
        this.second = second
    }

    override fun toString(): String {
        var total = ""
        if (hour < 10) total += "0"
        total += hour.toString()
        total += ":"
        if (minute < 10) total += "0"
        total += minute.toString()
        total += ":"
        if (second < 9) total += "0"
        total += second.toString()
        return total
    }
}