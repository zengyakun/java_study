package com.eslink.j8new;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 *@ClassName FieldUpdaterTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/6 17:10
 *@Version 1.0
 **/
public class FieldUpdaterTest {

    private volatile int index;

    private static final AtomicIntegerFieldUpdater intFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(FieldUpdaterTest.class, "index");

    private volatile String value;

    private static final AtomicReferenceFieldUpdater refFieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(FieldUpdaterTest.class, String.class, "value");

    private volatile long time;

    private static final AtomicLongFieldUpdater longFieldUpdater =
            AtomicLongFieldUpdater.newUpdater(FieldUpdaterTest.class, "time");

    @Override
    public String toString() {
        return "MyVolatileType{" +
                "index=" + index +
                ", value='" + value + '\'' +
                ", time=" + time +
                '}';
    }

    public FieldUpdaterTest(int index, String value, long time) {
        this.index = index;
        this.value = value;
        this.time = time;
    }

    public void test() {
        System.out.println("get(obj):获取值----初始值：" + refFieldUpdater.get(this));
        //set(obj,newValue)
        refFieldUpdater.set(this, "New Day!");
        System.out.println("set(obj,newValue):设置值---" + refFieldUpdater.get(this));
        //lazySet(obj,newValue)
        refFieldUpdater.lazySet(this, "Lazy Day!");
        System.out.println("lazySet(obj,newValue):设置值(无可见性)---" + refFieldUpdater.get(this));
        //getAndSet(obj,newValue)
        System.out.println("getAndSet(obj,newValue):赋值，并返回旧值：" + refFieldUpdater.getAndSet(this, "Good Day!"));
        //compareAndSet(obj,expect,newValue)
        System.out.println("compareAndSet(obj,expect,newValue):如果是期望的值,则赋值,并返回赋值结果："
                + refFieldUpdater.compareAndSet(this, "Good Day!", "Good good Day!")
                + ",---" + refFieldUpdater.get(this) + "\n");
    }

    public static void main(String[] args) {
        FieldUpdaterTest test = new FieldUpdaterTest(1, "David", System.currentTimeMillis());
        System.out.println("原始值：" + test.toString() + "\n");
        //字段更新器的通用方法
        test.test();
        System.out.println("当前值：" + test.toString() + "\n");
    }
}
