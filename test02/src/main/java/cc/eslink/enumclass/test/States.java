package cc.eslink.enumclass.test;

/**
 *@ClassName States
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:40
 *@Version 1.0
 **/
enum States implements State {
    RED {
        public boolean process(Context context) {
            context.state(States.GREEN);
            System.out.println("Current State = " + this);
            return true;
        }
    }, GREEN {
        public boolean process(Context context) {
            context.state(States.BLACK);
            System.out.println("Current State = " + this);
            return true;
        }
    }, BLACK {
        public boolean process(Context context) {
            context.state(States.YELLOW);
            System.out.println("Current State = " + this);
            return true;
        }
    }, YELLOW {
        public boolean process(Context context) {
            context.state(States.WHITE);
            System.out.println("Current State = " + this);
            return true;
        }
    }, WHITE {
        public boolean process(Context context) {
            context.state(States.BLUE);
            System.out.println("Current State = " + this);
            return true;
        }
    }, BLUE {
        public boolean process(Context context) {
            context.state(States.RED);
            System.out.println("Current State = " + this);
            return false;
        }
    };

    public abstract boolean process(Context context);
}

