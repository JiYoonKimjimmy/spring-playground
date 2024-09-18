package me.jimmyberg.springjpa.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

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

fun Logger.printStep(stepIndex: Int) {
    this.info("step-$stepIndex")
}

fun <T> T.printStep(stepIndex: Int? = null): T {
    val logger = LoggerFactory.getLogger("$this")
    return this.also { logger.info("step-$stepIndex") }
}