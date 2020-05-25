package cc.eslink.fundanalyze;

import cc.eslink.fundanalyze.enums.StageTypeEnum;
import cc.eslink.fundanalyze.util.DateStyle;
import cc.eslink.fundanalyze.util.DateUtil;
import cc.eslink.fundanalyze.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.Timestamp;
import java.text.Format;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@ClassName Tests
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2020/5/24 1:01
 *@Version 1.0
 **/
public class Tests {

    @Test
    public void test() {
        System.out.println(DateUtil.StringToDate("2006年10月09日"));
    }

    @Test
    public void test2() throws IOException {
        // 基金列表：http://fund.eastmoney.com/allfund.html

        // 混合型：000727
//        HttpGet index = new HttpGet("https://fund.eastmoney.com");
//        HttpGet index = new HttpGet("http://fundf10.eastmoney.com/jjjz_550016.html");
//        DefaultHttpClient httpClient = new DefaultHttpClient(new PoolingClientConnectionManager());
        DefaultHttpClient httpClient = new DefaultHttpClient();
//        httpClient.execute(index);
//        CookieStore cookieStore = httpClient.getCookieStore();
//        httpClient.setCookieStore(cookieStore);

        HttpGet api = new HttpGet("http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18304145621482272508_1590241752976&fundCode=550016&pageIndex=1&pageSize=10000&startDate=&endDate=&_=" + System.currentTimeMillis());
        api.setHeader("Referer", "http://fundf10.eastmoney.com/jjjz_550016.html");
        HttpEntity entity = httpClient.execute(api).getEntity();
        System.out.println(EntityUtils.toString(entity, "utf-8"));
    }

    @Test
    public void test3() throws IOException {
        Document doc = Jsoup.connect(Constants.ALL_FUND_URL).get();

        String title = doc.title();
        System.out.println(title);
        System.out.println(doc);

//        Elements es = doc.select("ul > li > div");
//        for (Element e : es) {
//            System.out.println(e.selectFirst("a[href^=http://fund.eastmoney.com]"));
//        }

//        Elements es = doc.select("a[href^=http://fund.eastmoney.com]");
//
//        for (Element e : es) {
//            System.out.println(e);
//        }
//
//        System.out.println(es.size());
    }

    @Test
    public void test4() throws IOException {
//        Document doc = Jsoup.parse(new File("C:\\Users\\曾亚坤\\Desktop\\基金代码查询一览表(按基金代码排序)_天天基金网.html"), "gb2312");

        Document doc = Jsoup.parse(new File("E:\\IdeaProjects\\study\\java_study\\fund-analyze\\src\\test\\resources\\allfund_20200524.html"), "gb2312");

        String title = doc.title();
        System.out.println(title);

        Elements es = doc.select("ul > li > div");
        Pattern p = Pattern.compile("^（(\\d{6})）((\\S)+)");
        for (Element e : es) {
            Element li = e.selectFirst("a[href^=http://fund.eastmoney.com]");
            if (null == li) {
                continue;
            }
            String text = li.text();
            if (StringUtils.isBlank(text)) {
                continue;
            }
            Matcher m = p.matcher(text);
            if (m.find()) {
                System.out.println("基金代码：" + m.group(1) + "，基金名称：" + m.group(2));
            }
        }
        System.out.println(es.size());
    }

    @Test
    public void test5() throws IOException {
//        String text = "（000041）华夏全球股票(QDII)";
//        Pattern p = Pattern.compile("^（(\\d{6})）((\\S)+)");
//        Matcher m = p.matcher(text);
//        System.out.println(m.find());
//        System.out.println(m.groupCount());
//        System.out.println(m.group(1));
//        System.out.println(m.group(2));

        Document doc = Jsoup.connect(Constants.ALL_FUND_URL).get();
//        System.out.println(doc);
        String date = DateUtil.DateToString(new Date(), DateStyle.YYYYMMDD);
        File file = new File("E:\\IdeaProjects\\study\\java_study\\fund-analyze\\src\\test\\resources\\allfund_" + date + ".html");
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), "gb2312");
        fw.write(doc.toString());
        fw.flush();
        fw.close();
    }

    @Test
    public void test6() throws IOException {
        Document doc = Jsoup.connect("http://fundf10.eastmoney.com/000727.html").get();

        String title = doc.title();
        System.out.println(title);
        Elements trs = doc.select(".info tr");
        System.out.println(trs.size());
        for (Element tr : trs) {
            for (int i = 0; i < tr.childNodeSize(); i++) {
                if ("发行日期".equals(tr.child(i).text())) {
                    System.out.println("发行日期:" + tr.child(i + 1).text());
                } else if ("资产规模".equals(tr.child(i).text())) {
                    String text = tr.child(i + 1).text();
                    Pattern p = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?");
                    Matcher m = p.matcher(text);
                    if (m.find()) {
                        String asset = m.group(0);
                        System.out.println(asset);
                    }
                    System.out.println("资产规模:" + tr.child(i + 1).text());
                } else if ("基金经理人".equals(tr.child(i).text())) {
                    System.out.println("基金经理人:" + tr.child(i + 1).text());
                } else if ("基金类型".equals(tr.child(i).text())) {
                    System.out.println("基金类型:" + tr.child(i + 1).text());
                }
            }
        }
    }

    @Test
    public void test7() throws IOException {
        Document doc = Jsoup.connect(String.format(Constants.FUND_JDZF_URL, "000727")).get();
        System.out.println(doc);
        String title = doc.title();
        System.out.println(title);
        Elements uls = doc.select(".jdzfnew");
        System.out.println(uls.size());
    }

    @Test
    public void test8() throws IOException {
        Document doc = Jsoup.connect(String.format(Constants.FUND_JDZF_URL2, "000727", String.valueOf(Math.random()))).get();
        Elements uls = doc.select(".jdzfnew ul");
        System.out.println(uls.size());
        for (int i = 1; i < uls.size(); i++) {
            Element ul = uls.get(i);
            // 今年来，近1周，近1月，近3月，近6月，近1年，近2年，近3年，近5年，成立来
            String title = ul.child(0).text();
            int type = StageTypeEnum.typeByName(title);
            String t1 = StringUtil.getNumber(ul.child(1).text());//涨幅
            String t2 = StringUtil.getNumber(ul.child(2).text());//同类平均
            String t3 = StringUtil.getNumber(ul.child(3).text());//沪深300
            String t4 = StringUtil.getNumber(ul.child(4).text());//同类排名
            Element sifen = ul.child(6).selectFirst(".sifen");//四分排名
            System.out.println(String.format("%s，%s，%s，%s，%s，%s", title, t1, t2, t3, t4, ((null != sifen) ? sifen.text() : "")));
        }
    }

    @Test
    public void test9() throws IOException {
        String fundCode = "000727";
        String startDate = "";
//        startDate = "20200501";
        int pageSize = 10000;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = String.format(Constants.FUND_NET_URL, fundCode, 1, pageSize, startDate, System.currentTimeMillis());
        HttpGet api = new HttpGet(url);
        api.setHeader("Referer", String.format(Constants.FUND_NET_REFER, fundCode));
        HttpEntity entity = httpClient.execute(api).getEntity();
        String content = EntityUtils.toString(entity, "utf-8");
        content = content.substring(content.indexOf("(") + 1, content.length() - 1);
        JSONObject obj = JSON.parseObject(content);
        System.out.println(obj.getIntValue("ErrCode"));
        System.out.println(obj.getJSONObject("Data").getJSONArray("LSJZList").size());
    }
}
