package com.example.demo.rest

import com.example.demo.business.beans.FibonacciBean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FibonacciController(private val fibonacciBean: FibonacciBean ) {

    @GetMapping("/find/recursive/")
    fun findOne( @RequestParam("val") value: Int) :Long {
        return fibonacciBean.recursive(value);
    }

    @GetMapping("/find/dynamic/")
    fun findOneDynamically( @RequestParam("val") value : Int) :Long {
        return fibonacciBean.dynamic(value);
    }

    @GetMapping("/find")
    fun findAll() :Collection<Long> {
        return fibonacciBean.listValues();
    }
}