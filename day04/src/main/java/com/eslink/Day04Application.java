package com.eslink;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

//@SpringBootApplication
public class Day04Application {

    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(Day04Application.class, args);
//        Integer a = new Integer(127);
        Integer a = Integer.valueOf(127);
        Integer b = new Integer(127);
        System.out.println(a == b);

        System.out.println(Date.from(Instant.now().minus(15, ChronoUnit.MINUTES)));
        System.out.println(Date.from(Instant.now().minus(3, ChronoUnit.DAYS)));

        Instant now = Instant.now();

        //business code
        Thread.sleep(2000);

        System.out.println(ChronoUnit.SECONDS.between(now, Instant.now()));
        System.out.println(ChronoUnit.DAYS.between(now, Instant.now()));

    }

    @Test
    public void test() {
        Map<Integer, String> tMap = new TreeMap<>();
        tMap.put(30, "c");
        tMap.put(20, "b");
        tMap.put(50, "e");
        tMap.put(40, "d");
        tMap.put(10, "a");
        System.out.println(tMap);
        List<Integer> l = new ArrayList(tMap.keySet());
        for (int i = 0; i < l.size(); i++) {
            int start = l.get(i), end = (i + 1 < l.size()) ? l.get(i + 1) : -1;
            System.out.println("start=" + start + ",end=" + end);
        }

        List<String> strs = Arrays.asList("103,204,509".split(","));
        Integer[] objects = strs.parallelStream().map(s -> Integer.valueOf(s)).toArray(Integer[]::new);
        System.out.println(objects);
    }

}
