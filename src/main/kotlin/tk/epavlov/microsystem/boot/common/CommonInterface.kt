package tk.epavlov.microsystem.boot.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface CommonInterface {
    val log: Logger
        get() = LoggerFactory.getLogger(this::class.java)
}