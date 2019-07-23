package com.data_structure.string;

public class KMP {
    public static int kmp(String text,String target){
        if (text == null || target == null || "".equals(text)
                || "".equals(target))
            return -1;
        char[] targetChars = target.toCharArray();
        char[] textChars = text.toCharArray();
        int[] next = getNext(target);
        int textIndex = 0,targetIndex = 0;
        while (targetIndex < targetChars.length && textIndex < textChars.length){
            if (targetIndex == -1 || targetChars[targetIndex] == textChars[textIndex]){
                ++targetIndex;
                ++textIndex;
            }else targetIndex = next[targetIndex];
        }
        if (targetIndex == targetChars.length) return textIndex - targetIndex;
        return -1;
    }
    public static int[] getNext(String target) {
        char[] p = target.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int kmp = kmp("abcababcabxqwe", "ababa");
        System.out.println(kmp);
        int sjl = kmp("abcababcabxqwe", "abcabx");
        System.out.println(sjl);
    }
        /*int[] next = getNext("之");
        for (int i = 0; i < next.length; i++) {
            System.out.println("next[" + i + "] =" + next[i]);
        }
        next = getNext("abcabx");
        for (int i = 0; i < next.length; i++) {
            System.out.println("next[" + i + "] =" + next[i]);
        }

        next = getNext("aaaaaab");
        for (int i = 0; i < next.length; i++) {
            System.out.println("next[" + i + "] =" + next[i]);
        }

    }
*/
    //pat子串    txt  父串
    /*public boolean kmpSearch(String pat, String txt) {
        int[] next = getNext(pat);
        int res = kmp(txt, pat,next);
        return res >= 0;
    }

    public int kmp(String str, String dest,int[] next){//str父串  dest 子串
        for(int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;
            }
        }
        return -1;
    }
    *//**
     *
     * @param str
     * @return
     *
     * 这个是KMP算法最核心的部分，得出子串的重复字符，
     * 也就是和父串匹配失败后下一次子串匹配开始的位置
     *//*
    public  int[] getNext(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        for(int i = 1,j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(j) != str.charAt(i)){
                j = next[j - 1];
            }
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }*/
}
