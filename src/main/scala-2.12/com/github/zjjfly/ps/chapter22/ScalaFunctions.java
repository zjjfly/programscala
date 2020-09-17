package com.github.zjjfly.ps.chapter22;

import scala.Function1;

/**
 * Created by zjjfly on 2017/6/4.
 */
public class ScalaFunctions {
    public static void main(String[] args) {
        //原文中说这段代码无法编译，是因为书中使用的版本是2.11，而2.12把Function和Java的lambda统一了，所以可以编译
        Function1<String, Integer> string2Integer = Integer::parseInt;
        System.out.println(string2Integer.apply("1"));
    }
}
