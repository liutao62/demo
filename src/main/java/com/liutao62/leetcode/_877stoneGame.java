package com.liutao62.leetcode;

/**
 * @author liutao
 * @date Created in 2021/6/16 19:40
 * @description 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子piles[i]。 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回true，当李赢得比赛时返回false。
 */
public class _877stoneGame {
    public boolean stoneGame(int[] piles) {
        if (piles == null || piles.length == 0) {
            return false;
        }
        return first(piles, 0, piles.length - 1) > after(piles, 0, piles.length - 1);
    }

    private int after(int[] piles, int left, int right) {
        if (left == right) {
            return 0;
        }
        return Math.min(first(piles, left + 1, right),
                first(piles, left, right - 1));
    }

    private int first(int[] piles, int left, int right) {
        if (left == right) {
            return piles[left];
        }
        return Math.max(piles[left] + after(piles, left + 1, right),
                piles[right] + after(piles, left, right - 1));
    }


    public static void main(String[] args) {
        boolean b = new _877stoneGame().stoneGame(new int[]{1, 99, 1, 1, 1, 1, 1, 1, 1});
        System.out.println(b);
    }

}
