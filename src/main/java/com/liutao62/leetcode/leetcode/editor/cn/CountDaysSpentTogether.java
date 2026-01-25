
package com.liutao62.leetcode.leetcode.editor.cn;

public class CountDaysSpentTogether {
    public static void main(String[] args) {
        Solution solution = new CountDaysSpentTogether().new Solution();
        solution.countDaysTogether("10-01", "10-31", "11-01", "12-31");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
            int arriveAliceDays = getAbsDays(arriveAlice);
            int leaveAliceDays = getAbsDays(leaveAlice);
            int arriveBobDays = getAbsDays(arriveBob);
            int leaveBobDays = getAbsDays(leaveBob);

            if (arriveAliceDays > leaveBobDays || arriveBobDays > leaveAliceDays){
                return 0;
            }
            return Math.min(leaveAliceDays, leaveBobDays) - Math.max(arriveAliceDays, arriveBobDays) + 1;
        }

        private int getAbsDays(String date) {
            int month = (date.charAt(0) -48) * 10 + (date.charAt(1)  -48);
            int days = 0;
            for (int i = 1; i < month; i++) {
                days += getDays(i);
            }
            int day = (date.charAt(3) -48) * 10 + (date.charAt(4) -48);
            return days + day;
        }

        private int getDays(int month) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return 31;
                case 4:
                case 6:
                case 9:
                case 11:
                    return 30;
                case 2:
                    return 28;
                default:
                    return 0;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}