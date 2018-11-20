package tk.epavlov.microsystem.boot.common

import org.slf4j.LoggerFactory

fun safe(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        LoggerFactory.getLogger(block.javaClass).error(e.message, e)
    }
}