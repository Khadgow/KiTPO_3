package model.factory

import model.usertype.prototype.IntegerType
import model.usertype.prototype.TimeType
import model.usertype.prototype.UserType

class FactoryType {
    private var typeList : List<UserType> = listOf(IntegerType(), TimeType())

    fun getTypeNameList(): List<String>{
        var listOfType: List<String> = listOf()
        for (t in typeList){
            listOfType +=  t.typeName()
        }
        return listOfType
    }

    fun getBuilderByName(name: String): UserType{
        for (userType in typeList){
            if (name == userType.typeName())
                return userType
        }
        throw IllegalArgumentException()
    }
}