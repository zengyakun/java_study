package cc.eslink.fundanalyze.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    /**
     *
     * TODO 切割list
     *
     * @param source
     * @param n
     *            平均切割数
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n; // (先计算出余数)
        int number = source.size() / n; // 然后是商
        int page = remaider > 0 ? number + 1 : number;
        for (int i = 0; i < page; i++) {
            List<T> value = null;
            if (source.size() < (i + 1) * n) {
                value = source.subList(i * n, source.size());
            } else {
                value = source.subList(i * n, (i + 1) * n);
            }

            result.add(value);
        }
        return result;
    }

    /**
     * 获取最后一个元素
     *
     * @param source
     * @return
     */
    public static <T> T getLastElement(List<T> source) {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }
        return source.get(source.size() - 1);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("num_" + i);
        }
        List<List<String>> result = averageAssign(list, 30);
        System.err.println("长度：" + result.size());
        for (List<String> list2 : result) {
            for (String string : list2) {
                System.out.print(string);
            }
            System.out.println("list");

        }
    }
}
