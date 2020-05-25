package cc.eslink.fundanalyze.enums;

/**
 *@ClassName StageTypeEnum
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 0:18
 *@Version 1.0
 **/
public enum StageTypeEnum {

    week("近1周", 0),
    month("近1月", 1),
    month3("近3月", 2),
    month6("近6月", 3),
    thisyear("今年来", 4),
    year("近1年", 5),
    year2("近2年", 6),
    year3("近3年", 7),
    year5("近5年", 8),
    since("成立来", 99);

    private String name;

    private int type;

    StageTypeEnum(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public static int typeByName(String name) {
        for (StageTypeEnum ae : StageTypeEnum.values()) {
            if (ae.name.equals(name)) {
                return ae.type;
            }
        }
        return since.type;
    }
}
