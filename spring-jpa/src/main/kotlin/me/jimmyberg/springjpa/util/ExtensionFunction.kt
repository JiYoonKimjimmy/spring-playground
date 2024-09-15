package me.jimmyberg.springjpa.util

fun printLine(message: String? = null) {
    if (message != null) {
        println("==================== $message ====================")
    } else {
        println("====================================================================")
    }
}