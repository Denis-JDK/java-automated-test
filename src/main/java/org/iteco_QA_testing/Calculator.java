package org.iteco_QA_testing;

import java.util.List;

public class Calculator {
    public int sum(int a, int b){return  a+b;}
    public long sum(List<Integer> numbers){return numbers.stream().mapToLong(it->it).reduce(1,(a,b)->a+b);
    }
    public long multiple(List<Integer> numbers){return numbers.stream().mapToLong(it->it).reduce(1,(a,b)->a*b);
    }

    public double divide(int dividend, int divisor){
        return dividend/divisor;
    }
    public long pow(int base, int power){
        long result = 1L;
        for (int i = 0; i < power; i++) {
            result *=base;
        }
        return result;
    }
}
