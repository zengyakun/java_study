package cc.eslink.jvm;

/**
 *@ClassName JVMTestLife
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/30 9:48
 *@Version 1.0
 **/
public class JVMTestLife {

    public static void main(String[] args) {
        System.out.println("main:" + Thread.currentThread().getId());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("inner:" + Thread.currentThread().getId());
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.currentThread().sleep(i * 10000);
                        System.out.println("睡了" + i * 10 + "秒");
                    } catch (Exception e) {
                        System.out.println(e.getCause());
                    }
                }
            }
        });
        System.out.println("outer:" + thread.getId());
        thread.start();
        for (int i = 0; i < 50; i++) {
            System.out.print(i);
        }
    }
}
