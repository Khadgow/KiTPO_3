package model.comparator

interface Comparator {
    fun compare(o1: Any, o2: Any): Int
}