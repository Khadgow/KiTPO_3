package model.comparator

import model.usertype.type.IntegerClass
import java.io.Serializable

class IntegerComparator constructor() : Comparator, Serializable {
    public override fun compare(o1: Any, o2: Any): Int {
        return (o1 as IntegerClass).getIntegerValue() - (o2 as IntegerClass).getIntegerValue()
    }
}