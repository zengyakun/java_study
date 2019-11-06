package cc.eslink.enumclass;

/**
 *@ClassName Operation
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/9/27 17:31
 *@Version 1.0
 **/
public enum Operation {
    PLUS("*") {
        double eval(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        double eval(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        double eval(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        double eval(double x, double y) {
            return x / y;
        }
    };

    String symbol = null;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return symbol;
    }

    // Do arithmetic op represented by this constant
    abstract double eval(double x, double y);

}
