package datax;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *@ClassName DataXTest
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2020/5/14 17:46
 *@Version 1.0
 **/
public class DataXTest {

    @Test
    public void test() throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append("python ");
        sb.append("D:\\Tools\\datax2\\bin\\datax.py");
        sb.append(" ");
        sb.append("E:\\IdeaProjects\\study\\java_study\\dataX-demo\\jobs");
        sb.append("/");
        sb.append("mysql2oracle.json");
        sb.append(" -p ");
        sb.append("\"");
//        map.keySet().forEach(key -> sb.append(" -D").append(key).append("=").append(map.get(key)));
        sb.append("\"");
        String windowcmd = sb.toString();
        System.out.println(windowcmd);
        Process pr = Runtime.getRuntime().exec(windowcmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
        pr.waitFor();
    }
}
