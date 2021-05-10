import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringNoRepeat {


    /**
     * Given a string s, 
     * find the length of the longest substring without repeating characters.
     * 
     * Runtime: 5 ms, faster than 76.38% of Java online submissions.
     * Memory Usage: 38.8 MB, less than 89.86% of Java online submissions.
     */
    static int lengthOfLongestSubstring0(String s) {
        
        // **** sanity checks ****
        if (s.length() <= 1)
            return s.length();

        // **** initialization ****
        HashMap<Character, Integer> window = new HashMap<>();
        window.put(s.charAt(0), 1);

        int longest = 1;
        int left    = 0;
        int right   = 0;

        // **** traverse string ****
        while (right < s.length() - 1) {

            // **** next character in s (for ease of use) ****
            Character ch = s.charAt(++right);

            // **** add character to window (if not in window) ****
            if (!window.containsKey(ch)) {

                // ???? ????
                // System.out.println("<<< ch: " + ch + " not in window");

                // **** add ch to window ****
                window.put(ch, 1);

                // **** increment longest window size (if needed) ****
                if (window.size() > longest)
                    longest++;
            } 
            
            // **** remove character(s) from window ****
            else {

                // ???? ????
                // System.out.println("<<< ch: " + ch + " already in window");

                // **** remove characters from start until ch is removed ****
                boolean done = false;
                do {

                    // **** remove ch from window ****
                    if (s.charAt(left) == ch) 
                        done = true;
                    else 
                        window.remove(s.charAt(left));

                    // **** increment left ****
                    left++;

                } while (!done);

            }

            // ???? ????
            // System.out.println("<<< sub ==>" + s.substring(left, right + 1) + "<==");
            // System.out.println("<<< left: " + left + " right: " + right + " longest: " + longest);
            // System.out.println("<<< window: " + window.toString());

        }

        // **** return longest window ****
        return longest;
    }


    /**
     * Given a string s, 
     * find the length of the longest substring without repeating characters.
     * Slidding window approach.
     * 
     * Runtime: 2 ms, faster than 99.84% of Java online submissions.
     * Memory Usage: 39 MB, less than 70.70% of Java online submissions.
     */
    static int lengthOfLongestSubstring1(String s) {
        
        // **** sanity checks ****
        if (s.length() <= 1)
            return s.length();

        // **** initialization ****
        int[] chars = new int[128];
        int left    = 0;
        int right   = 0;
        int longest = 0;

        // **** traverse the string ****
        while (right < s.length()) {

            // **** get right character ****
            char r = s.charAt(right);

            // ???? ????
            // System.out.println("<<< r: " + r);

            // **** increment character count ****
            chars[r]++;

            // ???? ????
            // System.out.println("<<< chars: " + Arrays.toString(chars));

            // **** remove duplicate character(s) ****
            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            // ???? ????
            // System.out.println("<<< right - left: " + (right - left));

            // **** update longest window ****
            longest = Math.max(longest, right - left + 1);

            // ???? ????
            // System.out.println("<<< sub ==>" + s.substring(left, right + 1) + "<==");
            // System.out.println("<<< longest: " + longest);

            // **** get ready to process next character in s ****
            right++;
        }

        // **** return longest window ****
        return longest;
    }



    /**
     * Given a string s, 
     * find the length of the longest substring without repeating characters.
     * Sliding window optimized using hashmap.
     * 
     * Runtime: 5 ms, faster than 76.42% of Java online submissions.
     * Memory Usage: 39.3 MB, less than 40.42% of Java online submissions.
     */
    static int lengthOfLongestSubstring2(String s) {
        
        // **** sanity checks ****
        if (s.length() <= 1)
            return s.length();

        // **** initialization ****
        int n       = s.length();
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        
        // **** extend the range between i and j ****
        for (int j = 0, i = 0; j < n; j++) {

            // ???? ????
            // System.out.println("<<< j: " + j + " ch: " + s.charAt(j));

            // **** ****
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }

            // ???? ????
            // System.out.println("<<< i: " + i);

            // **** compute max range between i and j ****
            longest = Math.max(longest, j - i + 1);

            // ???? ????
            // System.out.println("<<< sub ==>" + s.substring(i, j + 1) + "<==");
            // System.out.println("<<< longest: " + longest);

            // **** ****
            map.put(s.charAt(j), j + 1);

            // ???? ????
            // System.out.println("<<< map: " + map.toString());
        }

        // **** return longest window ****
        return longest;
    }


    /**
     * Given a string s, 
     * find the length of the longest substring without repeating characters.
     * Sliding window optimized using array.
     * 
     * Runtime: 2 ms, faster than 99.84% of Java online submissions.
     * Memory Usage: 39.2 MB, less than 49.90% of Java online submissions.
     */
    static int lengthOfLongestSubstring(String s) {
        
        // **** sanity checks ****
        if (s.length() <= 1)
            return s.length();

        // **** ****
        Integer[] chars = new Integer[128];

        int left        = 0;
        int right       = 0;
        int longest     = 0;

        // **** ****
        while (right < s.length()) {

            // **** ****
            char r = s.charAt(right);

            // **** ****
            Integer index = chars[r];
            if (index != null && index >= left && index < right)
                left = index + 1;
 
            // **** ****
            longest = Math.max(longest, right - left + 1);

            // **** ****
            chars[r] = right;

            // **** ****
            right++;
        }

        // **** return longest window ****
        return longest;
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read input string ****
        String s = br.readLine().trim();

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< s ==>" + s + "<==");
        System.out.println("main <<<      1234567890...");

        // **** call method of interest and display result ****
        System.out.println("main <<< output: " + lengthOfLongestSubstring0(s));

        // **** call method of interest and display result ****
        System.out.println("main <<< output: " + lengthOfLongestSubstring1(s));

        // **** call method of interest and display result ****
        System.out.println("main <<< output: " + lengthOfLongestSubstring2(s));

        // **** call method of interest and display result ****
        System.out.println("main <<< output: " + lengthOfLongestSubstring(s));
    }
}