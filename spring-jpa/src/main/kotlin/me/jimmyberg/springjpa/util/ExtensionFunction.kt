package me.jimmyberg.springjpa.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

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

fun <T> T.printStep(stepIndex: Int? = null): T {
    val logger = LoggerFactory.getLogger("$this")
    return this.also { logger.info("step-$stepIndex") }
}

fun generateUUID(length: Int = 10): String {
    return UUID.randomUUID().toString().replace("-".toRegex(), "").substring(0, length)
}