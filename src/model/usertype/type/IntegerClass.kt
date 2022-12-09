package model.usertype.type

import java.io.Serializable

class IntegerClass : Serializable {
    private var integerValue = 0

    constructor(integerValue: Int)  {
        this.integerValue = integerValue
    }

    fun getIntegerValue(): Int {
        return integerValue
    }

    override fun toString(): String {
        return integerValue.toString()
    }
}