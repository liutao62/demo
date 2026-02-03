package com.liutao62.leetcode.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

public class RestoreIpAddresses {
    public static void main(String[] args) {
        Solution solution = new RestoreIpAddresses().new Solution();
        List<String> strings = solution.restoreIpAddresses("172162541");
        // output : ["172.16.25.41","172.16.254.1","172.162.5.41","172.162.54.1"]
        // except : ["17.216.25.41","17.216.254.1","172.16.25.41","172.16.254.1","172.162.5.41","172.162.54.1"]
        System.out.println(strings);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> res = new LinkedList<>();
            if (s == null || s.length() < 4 || s.length() > 12) return res;
            restoreIpAddresses(s, 0, new LinkedList<>(), res);
            return res;
        }

        private void restoreIpAddresses(String s, int index, LinkedList<String> path, List<String> res) {
            if (index == s.length() && path.size() == 4) {
                res.add(String.join(".", path));
                return;
            }
            if (path.size() == 4 || index >= s.length()) return;
            path.add(String.valueOf(s.charAt(index)));
            restoreIpAddresses(s, index + 1, path, res);
            path.removeLast();
            char c = s.charAt(index);
            if (c == '0') {
                return;
            }

            if (s.length() > index + 1) {
                path.add(s.substring(index, index + 2));
                restoreIpAddresses(s, index + 2, path, res);
                path.removeLast();
            }

            if (c == '1' && index + 2 < s.length() || c == '2' && index + 2 < s.length() && s.charAt(index + 1) <= '5' && s.charAt(index + 2) <= '5') {
                path.add(s.substring(index, index + 3));
                restoreIpAddresses(s, index + 3, path, res);
                path.removeLast();
            }


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}