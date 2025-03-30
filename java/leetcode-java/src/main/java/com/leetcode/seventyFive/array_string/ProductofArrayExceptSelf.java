package com.leetcode.seventyFive.array_string;

public class ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        /**
         * fill ans with right prod array
         * [xn*xn-1..x0,... xn*xn-1, xn]
         * ans:
         * [r1, r2*]
         */
        int n = nums.length;
        int[] ans = new int[n];
        ans[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            ans[i] = ans[i + 1] * nums[i];
        }
        int leftProduct = nums[0];
        ans[0] = ans[1];
        for (int i = 1; i < n - 1; i++) {
            ans[i] = ans[i + 1] * leftProduct;
            leftProduct *= nums[i];
        }
        ans[n - 1] = leftProduct;
        return ans;
    }

    public int[] productExceptSelfSpaceO_n_(int[] nums) {
        /*
         * [1,2,3,4]
         * 0: n1*n2*n3
         * 1: n0*n2*n3
         *
         * ..
         * left:
         * [x0, x0*x1, x0*x1*x2,... ,x0*x1..*xn]
         * right:
         * [ xn*xn-1...x0 ...,xn*xn-1,xn]
         *
         * f(x) = left[x-1] * f[x+1]
         */
        int n = nums.length;
        int[] leftProduct = new int[n];
        int[] rightProduct = new int[n];
        int[] ans = new int[n];
        leftProduct[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftProduct[i] = leftProduct[i - 1] * nums[i];
        }
        rightProduct[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i];
        }

        ans[0] = rightProduct[1];
        ans[n - 1] = leftProduct[n - 2];
        for (int i = 1; i < n - 1; i++) {
            ans[i] = leftProduct[i - 1] * rightProduct[i + 1];
        }
        return ans;

    }
}
