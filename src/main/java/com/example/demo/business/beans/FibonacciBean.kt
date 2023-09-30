package com.example.demo.business.beans

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class FibonacciBean {
    private val logger: Logger = LoggerFactory.getLogger(FibonacciBean::class.java)

    private val values:ConcurrentHashMap<Int, Long> = ConcurrentHashMap()

    fun listValues() : Collection<Long> {
        return values.values
    }

    fun recursive( value: Int):Long {
        if (value < 0)
            throw IllegalArgumentException("Must be >= 0")

        if (value == 1)
            return 1

        if (value == 0)
            return 0

        return recursive( value - 1) + recursive( value - 2)
    }

    fun dynamic( value: Int ):Long {

        if (value < 0)
            throw IllegalArgumentException("Must be >= 0")

        if (values.containsKey(value)) {
            logger.debug("Found value $value in dictionary.")
            return values[value]!!
        }

        if ( value == 0) {
            values[0] = 0
            return 0
        }

        if (value == 1) {
            values[1] = 1
            return 1
        }

        val result = dynamic(value - 1) + dynamic( value - 2)
        logger.debug("Adding $value and $result to dictionary.")
        values[value] = result
        return result
    }

}