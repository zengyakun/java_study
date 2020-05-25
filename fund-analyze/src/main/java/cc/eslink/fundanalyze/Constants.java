package cc.eslink.fundanalyze;

/**
 *@ClassName Constants
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/5/24 21:17
 *@Version 1.0
 **/
public class Constants {

    /*所有基金*/
    public static final String ALL_FUND_URL = "http://fund.eastmoney.com/allfund.html";

    /*基金概况*/
    public static final String FUND_PROFILE_URL = "http://fundf10.eastmoney.com/%s.html";

    /*阶段涨幅*/
    @Deprecated
    public static final String FUND_JDZF_URL = "http://fundf10.eastmoney.com/jdzf_%s.html";

    /*阶段涨幅*/
    public static final String FUND_JDZF_URL2 = "http://fundf10.eastmoney.com/FundArchivesDatas.aspx?type=jdzf&code=%s&rt=%s";

    /*基金净值*/
    public static final String FUND_NET_URL = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18304145621482272508_1590241752976&fundCode=%s&pageIndex=%d&pageSize=%d&startDate=%s&endDate=&_=%s";

    /*基金净值*/
    public static final String FUND_NET_REFER = "http://fundf10.eastmoney.com/jjjz_%s.html";
}
