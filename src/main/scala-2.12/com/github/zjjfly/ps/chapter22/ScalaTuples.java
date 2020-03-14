package com.github.zjjfly.ps.chapter22;

import scala.Tuple2;

/**
 * Created by zjjfly on 2017/6/4.
 */
public class ScalaTuples {
    public static void main(String[] args) {
        Tuple2<String, Integer> stringIntegerTuple2 = new Tuple2<>("one", 2);
        System.out.println(stringIntegerTuple2);
    }
}
