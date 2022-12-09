package model.comparator

import model.usertype.type.TimeClass
import java.io.Serializable

class TimeComparator constructor() : Comparator, Serializable {
    public override fun compare(o1: Any, o2: Any): Int {
        val hourL: Int = (o1 as TimeClass).getHour()
        val hourR: Int = (o2 as TimeClass).getHour()
        val minuteL: Int = o1.getMinute()
        val minuteR: Int = o2.getMinute()
        val secondL: Int = o1.getSecond()
        val secondR: Int = o2.getSecond()
        val timeL: Int = (hourL * 60 * 60) + (minuteL * 60) + secondL
        val timeR: Int = (hourR * 60 * 60) + (minuteR * 60) + secondR
        return timeL - timeR
    }
}