package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyJUnitStopWatch extends Stopwatch {

    private static final Logger log = LoggerFactory.getLogger(MyJUnitStopWatch.class);

    private static Map<String, Long> testsResult = new HashMap<>();

    public static void printFinalResult() {
        Formatter formatter = new Formatter();
        testsResult.forEach((test, time) -> {
            formatter.format("%n %-25s %d ms", test, time);
        });
        log.info(formatter.toString());
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, nanos);
    }

    private void logInfo(Description description,long nanos) {
        String testName = description.getMethodName();
        testsResult.putIfAbsent(testName, TimeUnit.NANOSECONDS.toMillis(nanos));

        log.info(String.format("Test %s spent %d milliseconds",
                testName, TimeUnit.NANOSECONDS.toMillis(nanos)));
    }
}
