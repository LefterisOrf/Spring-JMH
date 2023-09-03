package com.example.demo;

import com.example.demo.business.beans.FibonacciBean;
import com.example.demo.business.beans.UsersBean;
import com.example.demo.persistence.dtos.UserDTO;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1)
@Fork(value = 1)
@Threads(value = 2)
public class FibonacciBenchmark {
    private static final int FIBONACCI_BENCHMARK = 20;

    @Autowired
    private FibonacciBean bean;

    @Autowired
    private UsersBean usersBean;

    @Setup
    public void setup() {
        SpringContext.setContext();
        SpringContext.autowireBean(this);
    }

   /* @Benchmark
    public long recursive() {
        //logger.info("Testing recursive fibonacci implementation.");
        return bean.recursive(FIBONACCI_BENCHMARK);
    }

    @Benchmark
    public long dynamic() {
        //logger.info("Testing dynamic fibonacci implementation.");
        return bean.dynamic(FIBONACCI_BENCHMARK);
    }*/

    @Benchmark
    public List<UserDTO> findUsers() {
        //logger.info("Testing dynamic fibonacci implementation.");
        return usersBean.getUsersByDateRange(LocalDate.of(1800, 12,12),
                LocalDate.of(2200, 12,12));
    }

    @TearDown
    public void close() {
        SpringContext.close();
    }
}
