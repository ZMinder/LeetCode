package P301_400.P331_340;

import org.junit.Test;

import java.util.*;

/**
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。
 * 请你对该行程进行重新规划排序。
 * <p>
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * 如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * <p>
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 */
public class P332 {
    LinkedList<String> res = new LinkedList<>();//存储最终合理的行程

    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, TreeMap<String, Integer>> towards = init(tickets);
        res.add("JFK");//加入起点
        backtrace(towards, tickets.size() + 1);
        return res;
    }

    public boolean backtrace(HashMap<String, TreeMap<String, Integer>> towards,
                             int target) {
        String start = res.getLast();
        if (res.size() == target) {//无路可走
            return true;//机票全部用完，说明找到了合理的规划，且字典序是最小的
        }
        if (!towards.containsKey(start)) {
            return false;//死胡同
        }
        TreeMap<String, Integer> ends = towards.get(start);//当前起点所能到达的终点
        for (Map.Entry<String, Integer> entry : ends.entrySet()) {
            int last = entry.getValue();
            if (last <= 0) {//没有机票可用
                continue;
            }
            entry.setValue(last - 1);//标记当前机票已被使用
            res.addLast(entry.getKey());
            if (backtrace(towards, target)) {//找到了就不再继续找了
                return true;
            }
            entry.setValue(last);
            res.removeLast();
        }
        return false;
    }

    public HashMap<String, TreeMap<String, Integer>> init(List<List<String>> tickets) {
        HashMap<String, TreeMap<String, Integer>> toward = new HashMap<>();//从某个机场可以前往的机场
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            //加入当前机票
            TreeMap<String, Integer> towards = toward.getOrDefault(from, new TreeMap<>(new MyComparator()));
            towards.put(to, towards.getOrDefault(to, 0) + 1);
            toward.put(from, towards);
        }
        return toward;
    }

    public class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    @Test
    public void testConvert() {
        String res = "[[\"MEL\",\"PER\"],[\"SYD\",\"CBR\"],[\"AUA\",\"DRW\"],[\"JFK\",\"EZE\"],[\"PER\",\"AXA\"],[\"DRW\",\"AUA\"],[\"EZE\",\"SYD\"],[\"AUA\",\"MEL\"],[\"DRW\",\"AUA\"],[\"PER\",\"ANU\"],[\"CBR\",\"EZE\"],[\"EZE\",\"PER\"],[\"MEL\",\"EZE\"],[\"EZE\",\"MEL\"],[\"EZE\",\"TBI\"],[\"ADL\",\"DRW\"],[\"ANU\",\"EZE\"],[\"AXA\",\"ADL\"]]";
        res = res.replaceAll("\\[", "{");
        res = res.replaceAll("]", "}");
        System.out.println(res);
    }

    @Test
    public void test() {
        String[][] list = {{"MEL", "PER"}, {"SYD", "CBR"}, {"AUA", "DRW"}, {"JFK", "EZE"}, {"PER", "AXA"}, {"DRW", "AUA"}, {"EZE", "SYD"}, {"AUA", "MEL"}, {"DRW", "AUA"}, {"PER", "ANU"}, {"CBR", "EZE"}, {"EZE", "PER"}, {"MEL", "EZE"}, {"EZE", "MEL"}, {"EZE", "TBI"}, {"ADL", "DRW"}, {"ANU", "EZE"}, {"AXA", "ADL"}};
        List<List<String>> ans = new ArrayList<>();
        for (String[] strings : list) {
            ans.add(Arrays.asList(strings));
        }
        List<String> itinerary = findItinerary(ans);
        System.out.println(itinerary);
    }
}
