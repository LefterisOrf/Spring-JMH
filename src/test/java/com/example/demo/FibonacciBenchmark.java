package com.example.demo;

import com.example.demo.business.beans.FibonacciBean;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;


@SpringBootTest(classes = FibonacciBean.class)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FibonacciBenchmark extends AbstractBenchmark {
    private static final Logger logger = LoggerFactory.getLogger(FibonacciBenchmark.class);
    private static final int FIBONACCI_BENCHMARK = 20;


    @Benchmark
    public long recursive() {
        logger.info("Testing recursive fibonacci implementation.");
        return new FibonacciBean().recursive(FIBONACCI_BENCHMARK);
    }

    @Benchmark
    public long dynamic() {
        logger.info("Testing dynamic fibonacci implementation.");
        return new FibonacciBean().dynamic(FIBONACCI_BENCHMARK);
    }
}
