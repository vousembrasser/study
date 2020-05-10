package com.dingwd.netty.study.decorator;

/**
 * Copyright (C),2019-2020 中盈优创
 *
 * @author: dingwd
 * @date: 2020/5/9 0009 13:58
 */
public class ConcreteDecorator2 extends Decorator{
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherSomething();
    }

    private void doAnotherSomething() {
        System.out.println("功能C");
    }
}
