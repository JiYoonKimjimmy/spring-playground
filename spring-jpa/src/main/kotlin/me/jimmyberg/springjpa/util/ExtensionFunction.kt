package me.jimmyberg.springjpa.util

import org.slf4j.Logger

fun printLine(message: String? = null) {
    if (message != null) {
        println("==================== $message ====================")
    } else {
        println("====================================================================")
    }
}

fun Logger.printLog(message: String? = null) {
    message?.let { this.info(it) }
}

fun Logger.printLog(stepIndex: Int? = null) {
    stepIndex?.let { this.info("step-$it") } ?: this.info("")
}