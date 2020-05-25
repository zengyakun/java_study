package cc.eslink.fundanalyze.enums;

/**
 *@ClassName RankingEnum
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/25 0:35
 *@Version 1.0
 **/
public enum RankingEnum {

    excellent("优秀", 4),
    good("良好", 3),
    general("一般", 2),
    poor("不佳", 1);

    private String name;

    private int score;

    RankingEnum(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static int scoreByName(String name) {
        for (RankingEnum ae : RankingEnum.values()) {
            if (ae.name.equals(name)) {
                return ae.score;
            }
        }
        return poor.score;
    }
}
