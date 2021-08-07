public class Solution {
    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (s.length() > 0 && p.length() == 0) {
            return false;
        }
        boolean matchAny = p.charAt(0) == '.';
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() == 0) {
                return false;
            }
            if (!matchAny && s.charAt(0) != p.charAt(0)) {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        }
        if (isMatch(s, p.substring(2))) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!matchAny && s.charAt(i) != p.charAt(0)) {
                return false;
            }
            if (isMatch(s.substring(i+1), p.substring(2))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("a", "ab*"));
    }
}
