package com.dingwd.netty.study.decorator;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/9 0009 13:55
 */
public class ConcreteComponent implements Component{
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
