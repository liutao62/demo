package com.liutao62.data_structure.string;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class StringMatcher {

    public static void main(String[] args) {
        // 测试用例
        String antisense = "aggaga", transcript = "aggagabbbb";
        int maxWildcard = 3;
        List<DisplayResult> displayResults = matchStrings(antisense, transcript, maxWildcard);
        for (DisplayResult displayResult : displayResults) {
            System.out.println(displayResult);
        }
//
//        antisense = "aggaga";
//        transcript = "aggbbagafd";
//        displayResults = matchStrings11(antisense, transcript, maxWildcard);
//        for (DisplayResult displayResult : displayResults) {
//            System.out.println(displayResult);
//        }

//        antisense = "agcgagab";
//        transcript = "aggbagaabc";
//        displayResults = matchStrings(antisense, transcript, maxWildcard);
//        for (DisplayResult displayResult : displayResults) {
//            System.out.println(displayResult);
//        }

//        antisense = "agabbcc";
//        transcript = "acabbcd";
//        displayResults = matchStrings(antisense, transcript, maxWildcard);
//        for (DisplayResult displayResult : displayResults) {
//            System.out.println(displayResult);
//        }

//        antisense = "aggbbaa";
//        transcript = "baggagaaag";
//        displayResults = matchStrings(antisense, transcript, maxWildcard);
//        for (DisplayResult displayResult : displayResults) {
//            System.out.println(displayResult);
//        }
//
//        antisense = "aggaga";
//        transcript = "bbaggagaaag";
//        displayResults = matchStrings11(antisense, transcript, maxWildcard);
//        for (DisplayResult displayResult : displayResults) {
//            System.out.println(displayResult);
//        }

//        antisense = "bcaggaga";
//        transcript = "aggagaaau";
//        displayResults = matchStrings11(antisense, transcript, maxWildcard);
//        for (DisplayResult displayResult : displayResults) {
//            System.out.println(displayResult);
//        }

        antisense = "UGAAACUCCAGACCUAGACCU";
        transcript = "aggggaagggaatgtgaccaggtctaggtctggagtttcagcttggacactgagctaagtagacaagcaaaacaagccaggacacgccatcctgccccaggcccagcttctctcctgccttctaatgccatggggagcagtctcagcccccagctctacctgatgcccttcatcttgggcctcttatctggaggtgtgaccaccactccattgtcttcggcccagcctcaaggatcctgctctctggagggggtagagatcaaaggtggctccttccgacttctccaagagggccaggcactggaatacgtgtgtccttctggcttctacccgtaccctgtgcagacacgtacctgcagatccacggggtcctggagcaccctgcagactcaagatcgaaaaactgtcaagaaggcagagtgcagagcaatccgctgtccacgaccacaggacttcgagaacggggaataccggccccggtctccctactacaatgtgagtgatgagatctctttccactgctatgacggttacactctccggggctctgccaatcgcacctgccaagtgaatggccggtggagtgggcagacagcgatctgtgacaacggagcggggtactgctccaacccaggcatccccattggcacaaggaaggtgggcagccggtaccgccttgaagacagcgtcacctaccactgcagccgggggcttaccctgcgtggctcccagcggcgaacatgtcaggaaggtggctcttggagcgggacggagccttcctgccaagactccttcatgtacgacacccctcaagaggtggccgaagctttcctgtcttccctgacggagaccatagaaggagtcgatgccgaggatgggcacagcccaggggaacaacagaagcggaggatcatcctagacccttcaggctccatgaacatctacctggtgctagatggatcagacagcattggggccggcaacttcacaggagccaaaaagtgtctagtcaacttaattgagaaggtggcaagttatggtgtgaagccaagatatgctctagtgacatatgccacataccccagaatttgggtcaaagtgtctgaccaagagagcagcaatgcagactgggtcacgaagaagctcagtgaaatcaattatgaagaccacaagttgaagtcagggactaacaccaagagggccctccaggcagtgtacagcatgatgagttggccagaggacatccctcctgaaggctggaaccgcacccgccatgtcatcatcctcatgaccgatggattgcacaacatgggcggggacccaattactgtcattgatgagatccgggacttgttatacatcggcaaggatcgcaaaaacccgagggaggattatctggatgtctatgtgtttggggttggacctttggtggaccaagtgaacatcaatgctttggcttccaagaaagacaatgagcaacatgtgttcaaagtcaaggatatggaaaacctggaagacgttttcttccaaatgattgatgaaagccagtctctgagtctctgtggcatggtttgggaacacacgacgggtaccgattaccacaagcaaccatggcaggccaagatctcagtcactcgcccttcgaagggacatgagagctgtatgggggctgtggtgtctgagtactttgtgctgacagcagcacattgttttactgtggacgacaaggaacactcgatcaaggtcagcgtggggaagaagcgggacctggagatagaaaaagtcctatttcaccccgactacaacattagcgggaaaaaagaagcaggaattcctgaattttatgactatgacgttgccctgatcaagctcaagaaaaagttgaattatgacccgactatcaggcccatttgtctcccctgtaccgagggaacaactcgagctttgaggcttcctccaactaccacttgccagcaacagaaggaagagctgctccctgcacaggatatcaaagctctgtttgtgtctgaggaggagaagaagctgactcggaaggaggtctacatcaagaatggggataagaaaggcagctgtgagagagatgctcaatatgccccaggctatgacaaagtcaaggacatctcggaggtggtcacccctcggttcctttgtactggaggagtgagtccctatgctgaccccaatacttgcagaggtgattctggcggccccttgatagttcacaagagaagtcgtttcattcaagttggtgtcatcagctggggagtagtggatgtctgcaaaaaccagaagcggcaaaagcaggtacctgctcacgcccgagactttcacgtcaacctcttccaagtgctgccctggctgaaggagaaactccaagatgaggatttgggttttctctaaggggtttcctgctggacaggggcgcgggattgaattaaaacagctgcgacaaca";
        displayResults = matchStrings(antisense, transcript, maxWildcard);
        for (DisplayResult displayResult : displayResults) {
            System.out.println(displayResult);
        }
    }

    public static List<DisplayResult> matchStrings(String antisense, String transcript, int maxWildcard) {
        String senseStrand = reverse(transfer(antisense));

        int m = senseStrand.length();
        int n = transcript.length();
        // dp[i][j]表示原字符串原子原索引i，目标字符串索引j的匹配情况
        boolean[][] dp = new boolean[m][n];
        // dp 减少 maxWildcard 后，转录本跳过匹配 后 length 不够导致场景丢失
        // 如         String antisense = "aggaga", transcript = "aggagabbbb";
        // 现在仅返回     agAGga  agGAga
        // todo 希望增加返回     ag*agga  aggag*a
        match(senseStrand, transcript, 0, 0, dp);

        List<MinCostPathsDijkstra.Result> minCostPathsDijkstra = MinCostPathsDijkstra.findMinCostPathsDijkstra(dp, m, maxWildcard);
        Optional<MinCostPathsDijkstra.Result> max = minCostPathsDijkstra.stream().min(MinCostPathsDijkstra.Result::compare);
        if (max.isPresent()) {
            int cost = max.get().cost;
            List<MinCostPathsDijkstra.Result> collect = minCostPathsDijkstra.stream().filter(result -> result.cost == cost).collect(Collectors.toList());
            List<DisplayResult> displayResults = Lists.newArrayList();
            for (MinCostPathsDijkstra.Result result : collect) {
                transferResult(antisense, transcript, result, senseStrand, displayResults);
            }
            return displayResults;
        }
        return Collections.EMPTY_LIST;
    }

    private static void transferResult(String antisense, String transcript, MinCostPathsDijkstra.Result result, String senseStrand, List<DisplayResult> displayResults) {
        int preY = 0;
        int preX = 0;
        DisplayResult displayResult = new DisplayResult();
        StringBuilder originMatch = new StringBuilder();
        List<MinCostPathsDijkstra.Node> path = result.path;
        StringBuilder targetMatch = new StringBuilder();
        for (MinCostPathsDijkstra.Node node : path) {
            if (node.cost != 0) {
                if (node.x - preX > node.y - preY) {
                    for (int i = node.x - node.cost; i < node.x; i++) {
                        targetMatch.append("*");
                        originMatch.append(senseStrand.charAt(i));
                    }
                    targetMatch.append(transcript.charAt(node.y));
                    originMatch.append(senseStrand.charAt(node.x));
                } else if (node.x - preX < node.y - preY) {
                    for (int i = node.y - node.cost; i < node.y; i++) {
                        originMatch.append("*");
                        targetMatch.append(transcript.charAt(i));
                    }
                    targetMatch.append(transcript.charAt(node.y));
                    originMatch.append(senseStrand.charAt(node.x));
                } else {
                    targetMatch.append(Character.toUpperCase(transcript.charAt(node.y)));
                    originMatch.append(Character.toUpperCase(senseStrand.charAt(node.x)));
                }
            } else {
                targetMatch.append(transcript.charAt(node.y));
                originMatch.append(senseStrand.charAt(node.x));
            }
            preY = node.y;
            preX = node.x;
        }
        displayResult.transSense = originMatch.toString();
        displayResult.transcriptFragment = targetMatch.toString();
        displayResult.pos = path.get(0).y;
        displayResult.diffCount = result.cost;
        displayResult.antisense = antisense;
        displayResult.transcript = reverse(displayResult.transcriptFragment.replaceAll("\\*", ""));
        displayResults.add(displayResult);
    }

    public static String transfer(String sequence) {
        return sequence.replace("A", "t").replace("U", "a").replace("C", "g").replace("G", "c");
    }

    private static String reverse(String marc1) {
        return new StringBuilder(marc1).reverse().toString();
    }

    private static void match(String original, String target, int originalIndex, int targetIndex, boolean[][] dp) {
        int targetLength = target.length();
        int originalLength = original.length();
        if (originalIndex == originalLength || targetIndex == targetLength) {
            return;
        }
        // 减少无效递归
        dp[originalIndex][targetIndex] = original.charAt(originalIndex) == target.charAt(targetIndex);
        for (int i = targetIndex + 1; i < targetLength; i++) {
            dp[originalIndex][i] = original.charAt(originalIndex) == target.charAt(i);
        }
        for (int i = originalIndex + 1; i < originalLength; i++) {
            dp[i][targetIndex] = original.charAt(i) == target.charAt(targetIndex);
        }
        match(original, target, originalIndex + 1, targetIndex + 1, dp);
    }

    @Data
    static class DisplayResult {
        int diffCount, pos;
        String antisense, transSense, transcriptFragment, transcript;

        @Override
        public String toString() {
            return "diffCount:" + diffCount + "\n"
                    + "pos:" + pos + "\n"
                    + "antisense:\t\t\t" + antisense + "\n"
                    + "transcript:\t\t\t" + transcript + "\n"
                    + "----" + "\n"
                    + "transSense:\t\t\t" + transSense + "\n"
                    + "transcriptFragment:\t" + transcriptFragment + "\n";
        }
    }

}


class MinCostPathsDijkstra {

    // 结果类，包含路径和最小成本
    @Data
    static class Result {
        int cost;
        List<Node> path;

        Result(int cost, List<Node> path) {
            this.cost = cost;
            this.path = path;
        }

        public static int compare(Result o1, Result o2) {
            return o1.cost - o2.cost;
        }

    }

    // 表示一个位置 (x, y) 和其当前的成本、路径长度
    @Data
    static class Node {
        int x, y, cost, totalCost, length;
        @Setter
        Node parent;

        Node(int x, int y, int cost, int totalCost, int length, Node parent) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.totalCost = totalCost;
            this.length = length;
            this.parent = parent;
        }
    }

    // Dijkstra 算法：查找最小成本路径
    public static List<Result> findMinCostPathsDijkstra(boolean[][] arr, int routeCount, int maxWildcard) {
        int m = arr.length;
        int n = arr[0].length;
        List<Result> results = new ArrayList<>();

        // 用优先队列来模拟 Dijkstra 算法的 "开放列表"
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.totalCost));
        // 遍历所有可能的起点
        for (int y = 0; y < n; y++) {
            if (arr[0][y]) {  // 如果 (0, y) 是 true
                Node startNode = new Node(0, y, 0, 0, 1, null);  // 起点
                pq.add(startNode);
            }
        }

        for (int x = 1; x < m; x++) {
            if (arr[x][0]) {  // 如果 (x, 0) 是 true
                Node startNode = new Node(x, 0, x, x, 1, null);  // 起点
                pq.add(startNode);
            }
        }

        // Dijkstra 算法：处理最小成本路径
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 如果当前节点的成本已经超过 routeCount，跳过
            if (current.totalCost > maxWildcard) {
                continue;  // 跳过不符合条件的路径
            }

            // 如果当前节点的路径长度已达到 routeCount，并且成本不超过 routeCount，返回结果
            if (current.length == routeCount) {
                List<Node> path = new ArrayList<>();
                Node temp = current;
                while (temp != null) {
                    path.add(temp);
                    temp = temp.parent;
                }
                Collections.reverse(path);
                Integer total = path.stream().map(Node::getTotalCost).max(Integer::compareTo).get();
                results.add(new Result(total, path));
                continue;  // 找到一条符合条件的路径，继续处理下一个节点
            }


            if (current.x + 1 < m && current.y + 1 < n) {
                boolean isMatch = arr[current.x + 1][current.y + 1];
                int newCost = isMatch ? current.totalCost : current.totalCost + 1;
                Node neighbor = new Node(current.x + 1, current.y + 1, isMatch ? 0 : 1, newCost, current.length + 1, current);
                pq.add(neighbor);

                int surplus = maxWildcard - current.cost;
                for (int i = 1; i <= surplus; i++) {
                    if (current.y + 1 + i < n && arr[current.x + 1][current.y + 1 + i]) {
                        neighbor = new Node(current.x + 1, current.y + 1 + i, i, current.totalCost + i, current.length + 1, current);
                        pq.add(neighbor);
                    }
                    if (current.x + 1 + i < m && arr[current.x + 1 + i][current.y + 1]) {
                        neighbor = new Node(current.x + 1 + i, current.y + 1, i, current.totalCost + i, current.length + 1, current);
                        pq.add(neighbor);
                    }
                }
            }

        }

        return results;
    }
}
