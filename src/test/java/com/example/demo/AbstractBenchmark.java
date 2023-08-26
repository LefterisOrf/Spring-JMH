package com.example.demo;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

public abstract class AbstractBenchmark {

    private final static Integer MEASUREMENT_ITERATIONS = 1;
    private final static Integer WARMUP_ITERATIONS = 1;

    /**
     * Any benchmark, by extending this class, inherits this single @Test method for JUnit to run.
     */
    @Test
    public void executeJmhRunner() throws RunnerException {
        System.out.println("\n\n\n Initializing runner \n\n\n\n");
        Options jmhRunnerOptions = new OptionsBuilder()
                // set the class name regex for benchmarks to search for to the current class
                .include(".*")
                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATIONS)
                // do not use forking or the benchmark methods will not see references stored within its class
                .forks(0)
                // do not use multiple threads
                .threads(1)
                .verbosity(VerboseMode.EXTRA)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .result("/opt/benchmark.json")
                .resultFormat(ResultFormatType.JSON)
                .jvmArgs("-server")
                .build();

        new Runner(jmhRunnerOptions).run();
    }

}
