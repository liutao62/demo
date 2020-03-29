package com.liutao62.leetcode;

public class _71simplifyPath {
    public String simplifyPath(String path) {
        if (path == null || "".equals(path)) return "/";
        String[] split = path.split("/");
        java.util.LinkedList<String> stringQueue = new java.util.LinkedList<>();
        for (String s : split) {
            if ("..".equals(s)) {
                if (stringQueue.size() > 0)
                    stringQueue.removeLast();
            } else if (!".".equals(s) && !"".equals(s)) stringQueue.add(s);
        }
        if (stringQueue.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : stringQueue) {
            sb.append("/" + s);
        }
        return sb.toString();
    }

}
