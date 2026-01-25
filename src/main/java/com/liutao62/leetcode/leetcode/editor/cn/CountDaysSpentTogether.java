
package com.liutao62.leetcode.leetcode.editor.cn;

public class CountDaysSpentTogether {
    public static void main(String[] args) {
        Solution solution = new CountDaysSpentTogether().new Solution();
        solution.countDaysTogether("08-15", "08-18", "08-16", "08-19");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
            java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("MM-dd");
            try {
                java.util.Date arriveAliceDate = simpleDateFormat.parse(arriveAlice);
                java.util.Date leaveAliceDate = simpleDateFormat.parse(leaveAlice);
                java.util.Date arriveBobDate = simpleDateFormat.parse(arriveBob);
                java.util.Date leaveBobDate = simpleDateFormat.parse(leaveBob);
                if (arriveAliceDate.getTime() > leaveBobDate.getTime() || arriveBobDate.getTime() > leaveAliceDate.getTime()){
                    return 0;
                }
                long min = Math.min(leaveAliceDate.getTime(), leaveBobDate.getTime());
                long max = Math.max(arriveAliceDate.getTime(), arriveBobDate.getTime());
                return (int) ((min - max) / 86400000 + 1);
            } catch (java.text.ParseException e) {
                return -1;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}