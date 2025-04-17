package com.leetcode.seventyFive.stack;

import java.util.Arrays;
import java.util.Stack;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int ast : asteroids) {

            Boolean isColision = !stack.isEmpty() && stack.peek() > 0;
            Boolean isAlive = true;
            while (!stack.isEmpty() && stack.peek() > 0 && ast < 0) {
                // collision happens
                int topAst = stack.pop();
                if (topAst == Math.abs(ast)) {
                    isAlive = false;
                    break;
                }
                if (topAst > Math.abs(ast)) {
                    ast = topAst;
                    break;
                }
                // if (topAst < Math.abs(ast)) {
                // continue
                // }

            }

            if (isAlive) {
                stack.push(ast);
            }
        }

        int n = stack.size();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    public int[] asteroidCollisionDep(int[] asteroids) {
        /*
         *
         * 10, -5,10,-50
         * from left to right to evaluate
         */
        int n = asteroids.length;
        int[] res = new int[n];
        res[0] = asteroids[0];
        int i = 1;
        int j = 1;
        while (i < n) {
            if (j == 0) {
                res[0] = asteroids[i];
                i++;
                continue;
            }
            // check if collision
            // The two asteroids will be collision only the previous asteroid is flying to
            // right, and the current asteroid is flying to left
            int leftAst = res[j - 1];
            int rightAst = asteroids[i];
            if (j > 0 && leftAst > 0 && rightAst < 0) {
                if (leftAst == Math.abs(rightAst)) {
                    j--;
                    res[j] = 0;
                } else if (leftAst < Math.abs(rightAst)) {
                    // res[j-1] = rightAst;
                    // j--;
                    while (j - 1 >= 0 && res[j - 1] > 0 && res[j - 1] < Math.abs(rightAst)) {
                        j--;
                        res[j] = 0;
                    }
                    if (j == 0) {
                        res[j] = rightAst;
                        j++;
                    } else if (res[j - 1] == Math.abs(rightAst)) {
                        res[j - 1] = 0;
                        j--;
                    } else if (res[j - 1] < 0) {
                        res[j] = rightAst;
                        j++;
                    } else {
                        res[j] = 0;
                    }
                }
            } else {
                // no collision
                res[j] = rightAst;
                j++;
            }
            i++;
        }
        if (j == 0)
            return new int[0];
        return Arrays.copyOfRange(res, 0, j);

    }

}
