package com.xc.kotlindemo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import retrofit2.http.HEAD;
import retrofit2.http.Query;

public class LeetCodeJava {


    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
    //


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //定义一个新联表伪指针，用来指向头指针，返回结果
        ListNode prev = new ListNode(0);
        //定义一个进位数的指针，用来存储当两数之和大于10的时候，
        int carry = 0;
        //定义一个可移动的指针，用来指向存储两个数之和的位置
        ListNode cur = prev;
        //当l1 不等于null或l2 不等于空时，就进入循环
        while (l1 != null || l2 != null) {
            //如果l1 不等于null时，就取他的值，等于null时，就赋值0，保持两个链表具有相同的位数
            int x = l1 != null ? l1.val : 0;
            //如果l1 不等于null时，就取他的值，等于null时，就赋值0，保持两个链表具有相同的位数
            int y = l2 != null ? l2.val : 0;
            //将两个链表的值，进行相加，并加上进位数
            int sum = x + y + carry;
            //计算进位数
            carry = sum / 10;
            //计算两个数的和，此时排除超过10的请况（大于10，取余数）
            sum = sum % 10;
            //将求和数赋值给新链表的节点，
            //注意这个时候不能直接将sum赋值给cur.next = sum。这时候会报，类型不匹配。
            //所以这个时候要创一个新的节点，将值赋予节点
            cur.next = new ListNode(sum);
            //将新链表的节点后移
            cur = cur.next;
            //当链表l1不等于null的时候，将l1 的节点后移
            if (l1 != null) {
                l1 = l1.next;
            }
            //当链表l2 不等于null的时候，将l2的节点后移
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //如果最后两个数，相加的时候有进位数的时候，就将进位数，赋予链表的新节点。
        //两数相加最多小于20，所以的的值最大只能时1
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        //返回链表的头节点
        return prev.next;
    }

    //取最大长度不重复字符串
    //abcabcbb
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);  //a
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
    //map a 1  b2  c 3  a 4 b5 c6 b7 b8
    //ans 1 2 3  3


    //      a b c a b c b b
    //      0 1 2 3 4 5 6 7
    //left  0 0 0 1 2
    //max   1 2 3 3 3
    //取最大长度不重复字符串
    public int lengthOfLongestSubstring11(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    //取最大长度不重复字符串  滑动窗口
    //abcabcbb
    //pwwkew
    public static int lengthOfLongestSubstring22(String s) {
        if (s.length() == 0) return 0;
        int max = 1;
        int left = 1;
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            str += c;
            for (int j = i + 1; j < s.length(); j++) {
                char c1 = s.charAt(j);
                if (c == c1 || str.indexOf(String.valueOf(c1)) != -1) {
                    break;
                } else {
                    left = left + 1;
                    str += c1;
                }
                Log.e("tag", str);
            }
            str = "";
            max = Math.max(max, left);
            left = 1;
        }
        return max;
    }

    //数组非递减排序  双指针
    public int[] sortedSquares(int[] nums) {
        int[] newNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = nums[i] * nums[i];
        }
        Arrays.sort(newNums);
        return newNums;
    }

    //[-8,-5,1,2,6,7]   双指针排序  平方 然后排序
    public int[] sortedSquares1(int[] nums) {
        int[] newNums = new int[nums.length];
        int n = nums.length;
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                newNums[pos] = nums[i] * nums[i];
                i++;
            } else {
                newNums[pos] = nums[j] * nums[j];
                j--;
            }
            pos--;
        }
        return newNums;
    }

    //[1,2,3,4,5,6,7]   k=3
    //5 6 7 1 2 3 4
    // 双指针
    public void rotate(int[] nums, int k) {
        int[] newNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newNums[(i + k) % nums.length] = nums[i];
        }
        Log.e("tag", newNums.toString());
    }


    //nums1 = [1,2], nums2 = [3,4]  [1,2,3,4]  (2+3)/2=2.5
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num = nums1.length + nums2.length;
        int[] newArray = new int[num];

        for (int i = 0; i < newArray.length; i++) {
            if (i < nums1.length) {
                newArray[i] = nums1[i];
            } else {
                newArray[i] = nums2[i - nums1.length];
            }
        }
        Arrays.sort(newArray);

        //3 1 2 0 4 0
        if (num % 2 == 0) {
            return (double) (newArray[num / 2] + newArray[num / 2 - 1]) / 2;
        } else {
            return newArray[num / 2];
        }
    }

    //动态规划  中心扩散法
    //aba bccb   回文
    public static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        String str = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String st = getSt(s, i - 1, i + 1);
            String st1 = getSt(s, i, i + 1);
            String s1 = st.length() >= st1.length() ? st : st1;
            str = str.length() > s1.length() ? str : s1;
        }
        return str;
    }

    public static String getSt(String s, int left, int right) {
        String str = "";
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                String substring = s.substring(left, right + 1);
                str = str.length() < substring.length() ? substring : str;
                left--;
                right++;
            } else {
                break;
            }
        }
        Log.e("tag++++++++++++", str);
        return str;
    }


    //双指针 移动0
    //[0,0,1,3,4] -- 1 3 4 0 0
    // 1 0 0 3 4
    // 1 3 0 0 4
    // 1 3 4 0 0
    //是0，右指针右移
    //不是0，交换数据，左右指针都往右移
    //
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        int n = nums.length;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    //两数之和  双循环 辣鸡
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[0];
    }

    //两数之和  双指针
    public int[] twoSumS(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (start != end) {
            if (numbers[start] + numbers[end] == target) {
                return new int[]{start + 1, end + 1};
            } else if (numbers[start] + numbers[end] > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[0];
    }

    //[1,8,6,2,5,4,8,3,7]  ---- 49
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left != right) {
            int nex = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, nex);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }


    // -4 -1 -1 0 1 2
    public List<List<Integer>> threeSum(int[] nums) {
        //定义一个结果集
        List<List<Integer>> res = new ArrayList<>();
        //数组的长度
        int len = nums.length;
        //当前数组的长度为空，或者长度小于3时，直接退出
        if (nums == null || len < 3) {
            return res;
        }
        //将数组进行排序
        Arrays.sort(nums);
        //遍历数组中的每一个元素
        for (int i = 0; i < len; i++) {
            //如果遍历的起始元素大于0，就直接退出
            //原因，此时数组为有序的数组，最小的数都大于0了，三数之和肯定大于0
            if (nums[i] > 0) {
                break;
            }
            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = len - 1;
            //当 l 不等于 r时就继续遍历
            while (l < r) {
                //将三数进行相加
                int sum = nums[i] + nums[l] + nums[r];
                //如果等于0，将结果对应的索引位置的值加入结果集中
                if (sum == 0) {
                    // 将三数的结果集加入到结果集中
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //在将左指针和右指针移动的时候，先对左右指针的值，进行判断
                    //如果重复，直接跳过。
                    //去重，因为 i 不变，当此时 l取的数的值与前一个数相同，所以不用在计算，直接跳
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    //去重，因为 i不变，当此时 r 取的数的值与前一个相同，所以不用在计算
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    //将 左指针右移，将右指针左移。
                    l++;
                    r--;
                    //如果结果小于0，将左指针右移
                } else if (sum < 0) {
                    l++;
                    //如果结果大于0，将右指针左移
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return res;
    }

    //反向字符串
    // 1 2 3 4
    public void reverseString(char[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            char temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
        }
        reverse(chars, i, chars.length - 1);
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i++] = chars[j];
            chars[j--] = temp;
        }
    }


    //链表中间值  快慢指针
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && slow.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //head = [1,2,3,4,5], n = 2   [1,2,3,5]
    // 1  1  1  1  1
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        int lent = getLent(head);
        for (int i = 1; i < 4; i++) {
            head = head.next;
        }
        return temp;
    }

    private int getLent(ListNode head) {
        int num = 0;
        while (head.next != null) {
            head = head.next;
            ++num;
        }
        return num;
    }

    //s1 = "ab" s2 = "eidbaooo"
    public boolean checkInclusion(String s1, String s2) {
        if (s2.contains(s1)) {
            return true;
        }
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            for (int j = 0; j < s2.length(); j++) {
                if (s2.charAt(j) == c) {

                }
            }
        }
        return true;
    }

    public static void aaa() {
        int[] target = new int[26];
        Log.e("TAGGG", target['c' - 'a']++ + "");
        for (int i = 0; i < target.length; i++) {
            Log.e("TAGGG", target[i]++ + "");
        }
    }


    public void addHead(int val) {
        ListNode cur = new ListNode(val);
        cur.next = this.head;
        head = cur;
    }

    public void addEnd(int val) {
        Node end = new Node(val);
        end.next = null;
        Node cur = head1;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = end;
    }


    int size;//链表长度
    ListNode head;//头节点
    Node head1;//头节点

    public LeetCodeJava() {
        this.size = 0;
        this.head = null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    //广度优先搜索
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int cur = image[sr][sc];
        if (cur == color) {
            return image;
        }
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] == cur) {

                }
            }
        }
        return image;
    }

    //最大岛
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;
    }

    public int dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[0].length || grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = 0;
        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};
        int ans = 1;
        for (int index = 0; index != 4; ++index) {
            int next_i = x + di[index], next_j = y + dj[index];
            ans += dfs(grid, next_i, next_j);
        }
        return ans;
    }


    //这个没做出来
    public int[][] updateMatrix(int[][] mat) {
        int[][] newMat = mat;
        for (int i = 0; i < mat.length; i++) {
            for (int m = 0; m < mat[i].length; m++) {
                newMat[i][m] = jl(mat, newMat, i, 0);
//                newMat[i][m] = jl(mat, newMat, 0, m);
            }
        }
        return newMat;
    }

    public int jl(int[][] mat, int[][] newMat, int x, int y) {
        int[] cur = {-1, 1, 0, 0};
        int max = 0;
        if (x < 0 || y < 0 || x == mat.length || y == mat[0].length) {
            return -1;
        }

        for (int i = 0; i < cur.length; i++) {

        }
        return max - 1;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur = 0;
        while (p1 < m || p2 < n) {
            if (m == 0) {

            } else if (n == 0) {

            }
            if (nums1[p1] == nums2[p2]) {
                sorted[cur] = nums1[p1];
                cur++;
                if (++p1 == m) {
                    p1 = p1 - 1;
                }
            } else if (nums1[p1] > nums2[p2]) {
                sorted[cur] = nums2[p2];
                cur++;
                if (++p2 == n) {
                    p2 = p2 - 1;
                }
            } else if (nums1[p1] < nums2[p2]) {
                sorted[cur] = nums1[p1];
                cur++;
                if (++p1 == m) {
                    p1 = p1 - 1;
                }
            }
        }
//        for (int i = 0; i != m + n; ++i) {
//            nums1[i] = sorted[i];
//        }
    }

    //合并列表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public int smallestEvenMultiple(int n) {

        if (n % 2 == 0) {
            return n;
        } else {
            return n * 2;
        }
    }

    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode cur = new ListNode(-1);
        ListNode pre = cur;
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                pre.next = list2;
                list2 = list2.next;
            } else {
                pre.next = list1;
                list1 = list1.next;
            }
            pre = pre.next;
        }

        pre.next = list1 == null ? list2 : list1;
        return cur.next;
    }

    //1234  4321
    //newNode = 2
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    //f(n)=f(n-1)+f(n-2)
    public int climbStairs(int n) {
        int dp[] = new int[n + 1];
        if (n == 1) return 1;
        if (n == 2) return 2;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    integers.add(1);
                } else {
                    integers.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(integers);
        }
        return ret;
    }

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(1);
            return integers;
        }
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    integers.add(1);
                } else {
                    integers.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(integers);
        }
        return ret.get(rowIndex);
    }


    //7 1 5 4 3
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - min > max) {
                max = prices[i] - min;
            } else {
                min = Math.min(prices[i], min);
            }
        }
        return max;
    }

    //--------------------------------- 回溯
    List<List<Integer>> listTotal = new ArrayList<>();

    public List<List<Integer>> combine1(int n, int k) {
        backtrack(n, k, new ArrayList<Integer>(), 1);
        return listTotal;
    }

    //n-2 2
    private void backtrack(int n, int k, List<Integer> list, int index) {
        if (list.size() == k) {
            listTotal.add(new ArrayList<>(list));
            return;
        }
        //i =  2
        for (int i = index; i <= n; i++) {
            // 经典回溯模板
            list.add(i);
            // 以 i + 1进行递归
            backtrack(n, k, list, i + 1);
            list.remove(list.size() - 1);
        }
    }


    //全排列 列表用回溯   全排列个数用 动态规划
    //[0,1]
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums);
        return res;
    }

    //[0,1]
    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                backtrack1(res, list, nums);
//                backtrack(res, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    //list=[0]
    public void backtrack1(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        //num=[0,1]
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                backtrack2(res, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    //list=[0,1]
    public void backtrack2(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                backtrack(res, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    public List<String> letterCasePermutation(String s) {
        char[] cs = s.toCharArray();
        List<String> ans = new ArrayList<>();
        bt(cs, ans, 0);

        return ans;
    }

    //"a1b2"
    //aA1bB2
    private void bt(char[] cs, List<String> ans, int index) {
        ans.add(new String(cs));
        for (int i = index; i < cs.length; i++) {
            if (cs[i] >= 'A') {
                cs[i] ^= 32;
                bt(cs, ans, i + 1);
                cs[i] ^= 32;
            }
        }
    }

    //动态规划
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额
        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            dp[k] = Math.max(dp[k - 1], nums[k - 1] + dp[k - 2]);
        }
        return dp[N];
    }


    //16
    public boolean isPowerOfTwo(int n) {
        return isF(n);
    }

    boolean isF(float n) {
        if (n < 1) return false;
        if (n == 1) return true;
        if (n % 2 == 1 && n != 1) return false;
        return isF(n / 2);
    }

    //[3,2,2,3]    3
    // 4 4 3 2     3
    public int removeElement(int[] nums, int val) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                //******
                nums[num] = nums[i];
                num++;
            }
        }
        return num;
    }

    //[1,1,2] [1,2,_]
    //[1,2,2,4,6,6] [1,2,_]
    //
    //
    //
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();
    }

    public void rotate1(int[] nums, int k) {
        int k1 = k;
        int[] newNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (k1 != 0) {
                newNums[i] = nums[nums.length - k + i];
                --k1;
            } else {
                newNums[i] = nums[i - k];
            }
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];

        return answer;
    }


}





























