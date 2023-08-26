package com.example.demo

import com.example.demo.business.beans.FibonacciBean
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest

class TestFibonacci() {

    private lateinit var fibonacciBean: FibonacciBean;

    @BeforeEach
    fun setup() {
        fibonacciBean = FibonacciBean();
    }

    @Test
    fun testRecursive() {
        assertThat( fibonacciBean).isNotNull;

        assertThat( fibonacciBean.recursive(0)).isEqualTo(0);
        assertThat( fibonacciBean.recursive(1)).isEqualTo(1);
        assertThat( fibonacciBean.recursive(2)).isEqualTo(1);
        assertThat( fibonacciBean.recursive(4)).isEqualTo(3);
        assertThat( fibonacciBean.recursive(10)).isEqualTo(55);
    }

    @Test
    fun testDynamic() {
        assertThat( fibonacciBean).isNotNull;

        assertThat( fibonacciBean.dynamic(0)).isEqualTo(0);
        assertThat( fibonacciBean.dynamic(1)).isEqualTo(1);
        assertThat( fibonacciBean.dynamic(2)).isEqualTo(1);
        assertThat( fibonacciBean.dynamic(4)).isEqualTo(3);
        assertThat( fibonacciBean.dynamic(10)).isEqualTo(55);

        assertThat( fibonacciBean.listValues()).isNotEmpty;
    }

}