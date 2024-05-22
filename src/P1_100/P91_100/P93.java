package P1_100.P91_100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
 * 但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，
 * 这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。
 * 你可以按 任何 顺序返回答案。
 */
public class P93 {
    final int POINT = 3;//点的总数
    List<String> res = new ArrayList<>();//存储有效IP地址
    String[] temp = new String[POINT];//记录当前IP

    public List<String> restoreIpAddresses(String s) {
        backtrace(s, 3, 0);
        return res;
    }

    public void backtrace(String str, int cuts, int begin) {
        if (cuts == 0) {//用于分割的.分割结束
            String sub = str.substring(begin);//最后一部分
            if (check(sub)) {//如果符合条件 构成一个有效IP 否则直接返回上一层
                StringBuilder builder = new StringBuilder();
                for (String s : temp) {//累积前面的整数
                    builder.append(s + ".");
                }
                builder.append(sub);
                res.add(builder.toString());
            }
            return;
        }
        for (int i = begin; i < Math.min(begin + 3, str.length() - cuts); i++) {//每一个整数最多三位
            if (str.length() - i - 1 > cuts * 3) {//后续的数用.分割 每个整数不止三位数 当前整数多加一位数
                continue;
            }
            String sub = str.substring(begin, i + 1);
            if (check(sub)) {//判断当前整数是否有效
                temp[POINT - cuts] = sub;
                backtrace(str, cuts - 1, i + 1);
            }
            if (str.charAt(begin) == '0' && i == begin) {//如果当前整数存在前导0 只需处理一个整数
                break;
            }
        }
    }

    public boolean check(String str) {
        if (str.length() > 1 && str.charAt(0) == '0') {//两位数或三位数不能含有前导0
            return false;
        }
        int num = Integer.parseInt(str);
        return num >= 0 && num <= 255;
    }

    @Test
    public void test() {
        String str = "25525511135";
        List<String> list = restoreIpAddresses(str);
        System.out.println(list);
    }
}
