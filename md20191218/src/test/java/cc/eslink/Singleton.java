package cc.eslink;

/**
 *@ClassName Singleton
 *@Description
 * 按照上边的写法已经对new Singleton();这个操作进行了synchronize操作，已经保证了多线程只能串行执行这个实例化代码。
 * 事实上，synchronize保证了线程执行实例化这段代码是串行的，但是Synchronize并不具备禁止指令重排的特性。
 * 而instance = new Singleton(); 主要做了3件事情：
 * (1) java虚拟机为对象分配一块内存x。
 * (2) 在内存x上为对象进行初始化 。
 * (3) 将内存x的地址赋值给instance 变量。
 * 如果编译器进行重排为：
 * (1) java虚拟机为对象分配一块内存x。
 * (2) 将内存x的地址赋值给instance 变量。
 * (3) 在内存x上为对象进行初始化 。
 * 第一种情况，无volatile修饰：此时，有两个线程执行getInstance()方法，加入线程A进入代码的注释中的第②处，并执行到了重排指令的（2），
 * 与其同时线程B刚好代码注释中的第①处的if判断。此时，instance有线程A把内存地址x地址赋值给了instance，那么instance已经不为空只是没有初始化完成，
 * 线程B就返回了一个没有完成初始化的instance，最终使用时候会处现空指针的错误。
 * 第二种情况，有volatile修饰：instance因为被volatile的禁止指令重排的特性，那只会安装先初始化对象再赋值给instance这样顺序执行，
 * 这样就能保证返回正常的实例化的对象。
 *@Author zeng.yakun (0178)
 *@Date 2020/1/12 16:33
 *@Version 1.0
 **/
public class Singleton {

    private volatile static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) { // 第①处，避免每次执行synchronized
            synchronized (Singleton.class) {
                if (instance == null) {  // 第②处，可能已经不为空
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
