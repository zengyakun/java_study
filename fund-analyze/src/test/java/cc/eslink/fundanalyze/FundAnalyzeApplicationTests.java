package cc.eslink.fundanalyze;

import cc.eslink.fundanalyze.entity.Fund;
import cc.eslink.fundanalyze.entity.FundNet;
import cc.eslink.fundanalyze.entity.FundStage;
import cc.eslink.fundanalyze.entity.User;
import cc.eslink.fundanalyze.enums.RankingEnum;
import cc.eslink.fundanalyze.enums.StageTypeEnum;
import cc.eslink.fundanalyze.mapper.FundStageDao;
import cc.eslink.fundanalyze.service.FundNetService;
import cc.eslink.fundanalyze.service.FundService;
import cc.eslink.fundanalyze.service.FundStageService;
import cc.eslink.fundanalyze.service.UserService;
import cc.eslink.fundanalyze.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@MapperScan("cc.eslink.fundanalyze.mapper")
@SpringBootTest
class FundAnalyzeApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    FundService fundService;
    @Autowired
    FundStageService fundStageService;
    @Autowired
    FundNetService fundNetService;

//    @Test
//    void contextLoads() {
//        User user = userService.Sel(1);
//        System.out.println(user);
//    }

//    @Test
//    public void test() {
//        List<Fund> list = new ArrayList<>();
//        Fund f1 = new Fund();
//        f1.setFundCode("000727");
//        f1.setFundName("融通健康产业灵活配置混合A/B");
//        f1.setFundType("混合型");
//        f1.setIssueDate(new Timestamp(DateUtil.StringToDate("2006年10月09日").getTime()));
//        f1.setAsset(8.63d);
//        f1.setFundManager("万民远");
//        list.add(f1);
//
//        Fund f2 = new Fund();
//        f2.setFundCode("121005");
//        list.add(f2);
//
//        Fund f3 = new Fund();
//        f3.setFundCode("161122");
//        list.add(f3);
//
////        fundService.save(f1);
//        fundService.batchSave(list);
//    }

    /**
     * @Description 解析全部基金
     * @Author zeng.yakun (0178)
     * @Date 2020/5/24 22:33
     * @param
     * @return
     **/
    @Test
    public void saveFund() throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        String fileName = String.format("allfund_%s.html", DateUtil.getCurrentDate2());
        File file = new File("E:\\IdeaProjects\\study\\java_study\\fund-analyze\\src\\test\\resources\\", fileName);
        Document doc = Jsoup.parse(file, "gb2312");
        Pattern p = Pattern.compile("^（(\\d{6})）((\\S)+)");
        Elements es = doc.select("ul > li > div");
        List<Fund> fundList = new ArrayList<>();
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
                Fund fund = new Fund(m.group(1), m.group(2));
                fundList.add(fund);

            }
        }
        System.out.println(String.format("共解析%d只基金，耗时%d 毫秒", fundList.size(), (System.currentTimeMillis() - start)));
        start = System.currentTimeMillis();
        // 开启线程抓取基金概况
        CountDownLatch latch = new CountDownLatch(fundList.size());
        for (Fund fund : fundList) {
            ThreadPoolUtil.execute(new FundProfileTask(fund, latch));
        }
        latch.await();
        System.out.println(String.format("爬取基金概况，耗时%d 毫秒", (System.currentTimeMillis() - start)));
        start = System.currentTimeMillis();
        // 批量保存
        List<String> fundCodeList = fundList.parallelStream().map(f -> f.getFundCode()).collect(Collectors.toList());
        List<String> existFundList = fundService.queryByCodeList(fundCodeList);
        List<Fund> insertList = new ArrayList<>();
        for (Fund fund : fundList) {
            if (existFundList.contains(fund.getFundCode())) {
                fundService.update(fund);
            } else {
                insertList.add(fund);
            }
        }
        if (!insertList.isEmpty()) {
            List<List<Fund>> lists = ListUtil.averageAssign(insertList, 2000);
            for (List<Fund> list : lists) {
                fundService.batchSave(list);
            }
        }
        System.out.println(String.format("保存基金信息，耗时%d 毫秒", (System.currentTimeMillis() - start)));
    }

    /**
     * @Description 解析执行基金
     * @Author zeng.yakun (0178)
     * @Date 2020/5/24 22:33
     * @param
     * @return
     **/
    @Test
    public void saveFund2() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<String> strs = Arrays.asList("000055");
        List<Fund> fundList = new ArrayList<>();
        strs.forEach(s -> fundList.add(new Fund(s, null)));
        System.out.println(String.format("共解析%d只基金，耗时%d 毫秒", fundList.size(), (System.currentTimeMillis() - start)));
        start = System.currentTimeMillis();
        // 开启线程抓取基金概况
        CountDownLatch latch = new CountDownLatch(fundList.size());
        for (Fund fund : fundList) {
            ThreadPoolUtil.execute(new FundProfileTask(fund, latch));
        }
        latch.await();
        System.out.println(String.format("爬取基金概况，耗时%d 毫秒", (System.currentTimeMillis() - start)));
        start = System.currentTimeMillis();
        // 批量保存
        List<String> fundCodeList = fundList.parallelStream().map(f -> f.getFundCode()).collect(Collectors.toList());
        List<String> existFundList = fundService.queryByCodeList(fundCodeList);
        List<Fund> insertList = new ArrayList<>();
        for (Fund fund : fundList) {
            if (existFundList.contains(fund.getFundCode())) {
                fundService.update(fund);
            } else {
                insertList.add(fund);
            }
        }
        if (!insertList.isEmpty()) {
            fundService.batchSave(insertList);
        }
        System.out.println(String.format("保存基金信息，耗时%d 毫秒", (System.currentTimeMillis() - start)));
    }

    class FundProfileTask implements Runnable {

        Fund fund;
        CountDownLatch latch;

        public FundProfileTask(Fund fund, CountDownLatch latch) {
            this.fund = fund;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Document doc = Jsoup.connect(String.format(Constants.FUND_PROFILE_URL, fund.getFundCode())).get();
                Elements trs = doc.select(".info tr");
                for (Element tr : trs) {
                    for (int i = 0; i < tr.childNodeSize(); i++) {
                        String text = "";
                        if (i < tr.childNodeSize() - 1) {
                            text = tr.child(i + 1).text();
                        }
                        if (StringUtils.isBlank(text)) {
                            continue;
                        }
                        if ("发行日期".equals(tr.child(i).text())) {
                            fund.setIssueDate(new Timestamp(DateUtil.StringToDate(text).getTime()));
                        } else if ("资产规模".equals(tr.child(i).text())) {
                            String number = StringUtil.getNumber(text);
                            if (StringUtils.isNotBlank(number)) {
                                fund.setAsset(Double.valueOf(number));
                            }
                        } else if ("基金经理人".equals(tr.child(i).text())) {
                            fund.setFundManager(text);
                        } else if ("基金类型".equals(tr.child(i).text())) {
                            fund.setFundType(text);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(latch.getCount() + "," + fund.toString());
                latch.countDown();
            }
        }
    }

    /**
     * @Description 保存基金明细
     * @Author zeng.yakun (0178)
     * @Date 2020/5/24 22:41
     * @param
     * @return
     **/
    @Test
    public void saveFundNet() throws InterruptedException {
        long start = System.currentTimeMillis();
        String startDate = "2019-01-01";
//        startDate = "2020-05-01";
        List<String> fundCodeList = fundService.queryCodeList();
//        fundCodeList = Arrays.asList("000727", "121005");
        System.out.println(String.format("查询到%d只基金，耗时%d 毫秒", fundCodeList.size(), (System.currentTimeMillis() - start)));
        start = System.currentTimeMillis();
        List<List<String>> fundLists = ListUtil.averageAssign(fundCodeList, 1000);
        for (List<String> fundList : fundLists) {
            CountDownLatch latch = new CountDownLatch(fundList.size());
            List<FundNet> netList = new CopyOnWriteArrayList();

            for (String fundCode : fundList) {
                ThreadPoolUtil.execute(new FundNetTask(fundCode, startDate, netList, latch));
            }
            latch.await();
            List<FundNet> insertList = new ArrayList<>();
            if (!netList.isEmpty()) {
                insertList.addAll(netList.parallelStream()
//                    .filter(fundNet -> !fundNetService.isExists(fundNet))
                        .collect(Collectors.toList()));
            }
            if (!insertList.isEmpty()) {
                List<List<FundNet>> lists = ListUtil.averageAssign(insertList, 2000);
                for (List<FundNet> list : lists) {
                    fundNetService.batchSave(list);
                }
            }
            System.out.println(String.format("保存单位净值，耗时%d 毫秒", (System.currentTimeMillis() - start)));
            start = System.currentTimeMillis();
        }
    }

    class FundNetTask implements Runnable {

        String fundCode;
        String startDate;
        List<FundNet> netList;
        CountDownLatch latch;

        public FundNetTask(String fundCode, String startDate, List<FundNet> netList, CountDownLatch latch) {
            this.fundCode = fundCode;
            this.startDate = startDate;
            this.netList = netList;
            this.latch = latch;
        }

        @Override
        public void run() {
            List<FundNet> tmpList = new ArrayList<>();
            DefaultHttpClient httpClient = new DefaultHttpClient();
            try {
                int pageSize = 1000;
                String url = String.format(Constants.FUND_NET_URL, fundCode, 1, pageSize, startDate, System.currentTimeMillis());
                HttpGet api = new HttpGet(url);
                api.setHeader("Referer", String.format(Constants.FUND_NET_REFER, fundCode));
                HttpEntity entity = httpClient.execute(api).getEntity();
                String content = EntityUtils.toString(entity, "utf-8");
                content = content.substring(content.indexOf("(") + 1, content.length() - 1);
                JSONObject obj = JSON.parseObject(content);
                if (0 != obj.getIntValue("ErrCode")) {
                    return;
                }
                JSONArray LSJZList = obj.getJSONObject("Data").getJSONArray("LSJZList");
                for (int i = 0; i < LSJZList.size(); i++) {
                    JSONObject item = LSJZList.getJSONObject(i);
                    FundNet fundNet = new FundNet(fundCode);
                    fundNet.setNetDate(DateUtil.StringToString(item.getString("FSRQ"), DateStyle.YYYYMMDD));
                    fundNet.setNetValue(item.getDouble("DWJZ"));
                    fundNet.setTotalValue(item.getDouble("LJJZ"));
                    fundNet.setDailyRate(item.getDouble("JZZZL"));
                    fundNet.setSubStatus(item.getString("SGZT"));
                    fundNet.setRedStatus(item.getString("SHZT"));
                    tmpList.add(fundNet);
                }
                netList.addAll(tmpList);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(latch.getCount() + ",fundCode=" + fundCode + ",size=" + tmpList.size());
                latch.countDown();
                httpClient.close();
            }
        }
    }

    /**
     * @Description 保存阶段涨幅明细
     * @Author zeng.yakun (0178)
     * @Date 2020/5/24 22:41
     * @param
     * @return
     **/
    @Test
    public void saveFundStage() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<String> fundCodeList = fundService.queryCodeList();
//        fundCodeList = Arrays.asList("000727", "121005");
        System.out.println(String.format("查询到%d只基金，耗时%d 毫秒", fundCodeList.size(), (System.currentTimeMillis() - start)));
        start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(fundCodeList.size());
//        List<FundStage> stageList = Collections.synchronizedList(new ArrayList<>());
        List<FundStage> stageList = new CopyOnWriteArrayList();
        String curDate = DateUtil.getCurrentDate2();
        // 删除当日明细
        fundStageService.deleteCurData(curDate, fundCodeList);
        for (String fundCode : fundCodeList) {
            ThreadPoolUtil.execute(new FundStageTask(new FundStage(fundCode, curDate), stageList, latch));
        }
        latch.await();
        if (!stageList.isEmpty()) {
            List<List<FundStage>> lists = ListUtil.averageAssign(stageList, 2000);
            for (List<FundStage> list : lists) {
                fundStageService.batchSave(list);
            }
        }
        System.out.println(String.format("保存阶段涨幅，耗时%d 毫秒", (System.currentTimeMillis() - start)));
    }

    class FundStageTask implements Runnable {

        FundStage fundStage;
        List<FundStage> stageList;
        CountDownLatch latch;

        public FundStageTask(FundStage fundStage, List<FundStage> stageList, CountDownLatch latch) {
            this.fundStage = fundStage;
            this.stageList = stageList;
            this.latch = latch;
        }

        @Override
        public void run() {
            List<FundStage> tmpList = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(String.format(Constants.FUND_JDZF_URL2, fundStage.getFundCode(), String.valueOf(Math.random()))).get();
                Elements uls = doc.select(".jdzfnew ul");
                for (int i = 1; i < uls.size(); i++) {
                    FundStage stage = new FundStage(fundStage.getFundCode(), fundStage.getCalcDate());
                    Element ul = uls.get(i);
                    // 今年来，近1周，近1月，近3月，近6月，近1年，近2年，近3年，近5年，成立来
                    String title = ul.child(0).text();
                    stage.setStageType(StageTypeEnum.typeByName(title));
                    String t1 = StringUtil.getNumber(ul.child(1).text());//涨幅
                    if (StringUtils.isNotBlank(t1)) {
                        stage.setStageRate(Double.valueOf(t1));
                    }
                    String t2 = StringUtil.getNumber(ul.child(2).text());//同类平均
                    if (StringUtils.isNotBlank(t2)) {
                        stage.setAvgRate(Double.valueOf(t2));
                    }
                    String t3 = StringUtil.getNumber(ul.child(3).text());//沪深300
                    if (StringUtils.isNotBlank(t3)) {
                        stage.setHushen300(Double.valueOf(t3));
                    }
                    String t4 = ul.child(4).text(); //同类排名
                    if (StringUtils.isNotBlank(t4) && t4.indexOf("|") > 0) {
                        String[] strs = t4.split("\\|");
                        String sort = StringUtil.getNumber(strs[0]);
                        if (StringUtils.isNotBlank(sort)) {
                            stage.setSort(Integer.valueOf(sort));
                        }
                        if (strs.length > 1) {
                            String total = StringUtil.getNumber(strs[1]);
                            if (StringUtils.isNotBlank(total)) {
                                stage.setTotal(Integer.valueOf(total));
                            }
                        }
                    }
                    Element sifen = ul.child(6).selectFirst(".sifen");//四分排名
                    if (null != sifen) {
                        stage.setRanking(sifen.text());
                        stage.setScore(RankingEnum.scoreByName(sifen.text()));
                    }
                    tmpList.add(stage);
                }
                tmpList.sort(Comparator.comparing(FundStage::getStageType));
                stageList.addAll(tmpList);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(latch.getCount() + "," + tmpList);
                latch.countDown();
            }
        }
    }
}
