package model.cycleList

import model.comparator.Comparator
import model.usertype.prototype.UserType
import java.io.*

class CycleList : Serializable {
    private var head: Node? = null
    private var length = 0

    private var comparator: Comparator? = null

    constructor(comparator: Comparator){
        this.comparator = comparator
    }

     class Node(var data: Any?) : Serializable {
        var next: Node? = null
        var prev: Node? = null
        public override fun toString(): String {
            return data.toString()
        }
    }

    fun add(data: Any) {
        if (head == null) {
            val node: Node = Node(data)
            node.prev = node
            node.next = node.prev
            head = node
            length++
            return
        }
        val tail: Node? = head!!.prev
        val node: Node = Node(data)
        node.next = head
        head!!.prev = node
        node.prev = tail
        tail!!.next = node
        length++
    }

    fun add(data: Any, index: Int) {
        val tmp: Node? = getNode(index)
        val newNode: Node = Node(data)
        val tail: Node? = head!!.prev
        if (tmp !== head) {
            tmp!!.prev!!.next = newNode
            newNode.prev = tmp.prev
        } else {
            head = newNode
        }
        newNode.next = tmp
        tmp!!.prev = newNode
        tail!!.next = head
        head!!.prev = tail
        length++
    }

    fun remove(index: Int) {
        val tmp: Node? = getNode(index)
        var tail: Node? = head!!.prev
        if (tmp !== head) {
            tmp!!.prev!!.next = tmp.next
        } else {
            head = tmp!!.next
        }
        if (tmp !== tail) {
            tmp.next!!.prev = tmp.prev
        } else {
            tail = tmp.prev
        }
        tmp.prev = null
        tmp.next = tmp.prev
        tail!!.next = head
        head!!.prev = tail
        length--
    }

    fun getByIndex(index: Int): Any? {
        return getNode(index)!!.data
    }

    private fun getNode(index: Int): Node? {
        if (index < 0 || index >= length) throw IndexOutOfBoundsException()
        var tmp: Node? = head
        for (i in 0 until index) {
            tmp = tmp!!.next
        }
        return tmp
    }

    fun forEach(iterator: Iterator) {
        var tmp: Node? = head
        for (i in 0 until length) {
            iterator.toDo(tmp!!.data)
            tmp = tmp.next
        }
    }

    fun sort() {
        if ((head != null) && (head!!.next !== head) && (head!!.prev !== head)) {
            var tail: Node? = head!!.prev
            tail!!.next = null
            head!!.prev = null
            head = mergeSort(head)
            tail = getNode(length - 1)
            tail!!.next = head
            head!!.prev = tail
        }
    }

    private fun mergeSort(headNode: Node?): Node? {
        if (headNode == null || headNode.next == null) {
            return headNode
        }
        val middle: Node? = getMidNode(headNode)
        val middleNext: Node? = middle!!.next
        middle.next = null
        val left: Node? = mergeSort(headNode)
        val right: Node? = mergeSort(middleNext)
        return merge(left, right)
    }

    private fun merge(_firstNode: Node?, _secondNode: Node?): Node? {
        var firstNode: Node? = _firstNode
        var secondNode: Node? = _secondNode
        val merged: Node = Node(null)
        var temp: Node? = merged
        var tail: Node? = head!!.prev
        while (firstNode != null && secondNode != null) {
            if (comparator!!.compare(firstNode.data!!, secondNode.data!!) < 0) {
                temp!!.next = firstNode
                firstNode.prev = temp
                firstNode = firstNode.next
            } else {
                temp!!.next = secondNode
                secondNode.prev = temp
                secondNode = secondNode.next
            }
            temp = temp.next
        }
        while (firstNode != null) {
            temp!!.next = firstNode
            firstNode.prev = temp
            firstNode = firstNode.next
            temp = temp.next
        }
        while (secondNode != null) {
            temp!!.next = secondNode
            secondNode.prev = temp
            secondNode = secondNode.next
            temp = temp.next
            tail = temp
        }
        return merged.next
    }

    private fun getMidNode(node: Node): Node? {
        var previousNode: Node? = node
        var currentNode: Node? = node
        while (currentNode!!.next != null && currentNode.next!!.next != null) {
            previousNode = previousNode!!.next
            currentNode = currentNode.next!!.next
        }
        return previousNode
    }

    fun forEachReverse(iterator: Iterator) {
        var tmp: Node? = head
        for (i in 0 until length) {
            iterator.toDo(tmp!!.data)
            tmp = tmp.prev
        }
    }

    fun printList() {
        var tmp: Node? = head
        for (i in 0 until length) {
            print(i.toString() + ") ")
            println(tmp!!.data)
            tmp = tmp.next
        }
    }

    public override fun toString(): String {
        var str: String = ""
        var tmp: Node? = head
        for (i in 0 until length) {
            str = str + tmp!!.data + "\n"
            tmp = tmp.next
        }
        return str
    }

    private fun clearList() {
        head = null
        length = 0
    }

    fun save(userType: UserType, fileName: String?) {
        val writer = BufferedWriter(FileWriter(fileName))
        writer.write(userType.typeName() + "\n")
        this.forEach(object : Iterator {
            override fun toDo(obj: Any?) {
                val x = obj
                writer.write(
                        """
                        ${x.toString()}
                        
                        """.trimIndent()
                )
            }
        })
        if (writer != null) writer.close()
    }

    fun load(userType: UserType, fileName: String?) {
        clearList()
        try {
            BufferedReader(FileReader(fileName)).use { br ->
                var line: String?
                line = br.readLine()
                if (!userType.typeName().equals(line)) {
                    throw Exception("Wrong file structure")
                }
                while (br.readLine().also { line = it } != null) {
                    add(userType.parseValue(line!!))
                }
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}