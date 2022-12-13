package testing

import model.cycleList.CycleList
import model.cycleList.Iterator
import model.factory.FactoryType
import model.usertype.prototype.UserType

class Testing constructor() {
    fun testIntegerType() {
        val factoryType = FactoryType()
        var userType = factoryType.getBuilderByName("Integer")
        var cycleList = CycleList(userType.getTypeComparator())
        println("\n--------------TEST FOR INTEGER-------------")
        userType = factoryType.getBuilderByName("Integer")
        cycleList = CycleList(userType.getTypeComparator())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        println("---------PRINT CYCLE LIST---------")
        cycleList.printList()
        println("-----SAVE TO FILE .DAT----")
        try {
            cycleList.save(userType, INT_FILE_SAVE)
            println("Saving is successful!")
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        println("\n----GET NODE BY INDEX 3, 4, 5, 6----")
        println("3) = " + cycleList.getByIndex(3).toString())
        println("4) = " + cycleList.getByIndex(4).toString())
        println("5) = " + cycleList.getByIndex(5).toString())
        println("6) = " + cycleList.getByIndex(6).toString())
        println("\n---DELETE NODE BY INDEX 3, 4, 5, 6--")
        cycleList.remove(3)
        cycleList.remove(4)
        cycleList.remove(5)
        cycleList.remove(6)
        cycleList.printList()
        println("-----------SORTING----------")
        cycleList.sort()
        cycleList.printList()
        println("---LOAD FROM FILE----")
        cycleList.load(userType, INT_FILE_SAVE)
        cycleList.printList()
        System.out.println("-----------SORTING BY FUNCTIONAL STYLE----------")
        cycleList = cycleList.sortFuncStyle()
        cycleList.printList()
        println("---------ITERATOR-----------")
        cycleList.forEach(object : Iterator {
            override fun toDo(obj: Any?) {
                val x = obj
                println(x)
            }
        })
        println("---------ITERATOR REVERSE-----------")
        cycleList.forEachReverse(object : Iterator {
            override fun toDo(obj: Any?) {
                val x = obj
                println(x)
            }
        })
    }

    fun testTimeType() {
        val factoryType = FactoryType()
        println("\n--------------TEST FOR TIME-------------")
        var userType = factoryType.getBuilderByName("Time")
        var cycleList = CycleList(userType.getTypeComparator())
        userType = factoryType.getBuilderByName("Time")
        cycleList = CycleList(userType.getTypeComparator())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        cycleList.add(userType.create())
        println("---------PRINT CYCLE LIST---------")
        cycleList.printList()
        println("-----SAVE TO FILE .DAT----")
        try {
            cycleList.save(userType, TIME_FILE_SAVE)
            println("Saving is successful!")
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
        println("\n----GET NODE BY INDEX 3, 4, 5, 6----")
        println("3) = " + cycleList.getByIndex(3).toString())
        println("4) = " + cycleList.getByIndex(4).toString())
        println("5) = " + cycleList.getByIndex(5).toString())
        println("6) = " + cycleList.getByIndex(6).toString())
        println("\n---DELETE NODE BY INDEX 3, 4, 5, 6--")
        cycleList.remove(3)
        cycleList.remove(4)
        cycleList.remove(5)
        cycleList.remove(6)
        cycleList.printList()
        println("-----------SORTING----------")
        cycleList.sort()
        cycleList.printList()
        println("---LOAD FROM FILE----")
        cycleList.load(userType, TIME_FILE_SAVE)
        cycleList.printList()
        System.out.println("-----------SORTING BY FUNCTIONAL STYLE----------")
        cycleList = cycleList.sortFuncStyle()
        cycleList.printList()
        println("---------ITERATOR-----------")
        cycleList.forEach(object : Iterator {
            override fun toDo(obj: Any?) {
                val x = obj
                println(x)
            }
        })
        println("---------ITERATOR REVERSE-----------")
        cycleList.forEachReverse(object : Iterator {
            override fun toDo(obj: Any?) {
                val x = obj
                println(x)
            }
        })
    }

    companion object {
        private val INT_FILE_SAVE: String = "integer.dat"
        private val TIME_FILE_SAVE: String = "time.dat"
    }
}