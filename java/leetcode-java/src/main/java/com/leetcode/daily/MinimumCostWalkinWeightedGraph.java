package com.leetcode.daily;

import java.util.HashSet;
import java.util.Set;

public class MinimumCostWalkinWeightedGraph {
    private int[] parent;
    private int[] depth;

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        /*
         * Repeatedly visiting is allowed
         *
         * 1. make a set about connected area
         * 2. check whether s -> t is possible
         * 3. find the minium number needs to be used inside the set
         * what if some number are must pass through?
         * Never mind, an and operation only decreases but never increases
         *
         * recordArr:
         *
         * [ [{set of nodes}, {set of weight}], .... ]
         */

        parent = new int[n];
        depth = new int[n];
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            cost[i] = Integer.MAX_VALUE;
        }
        for (int[] edge : edges) {
            // union the connected nodes
            union(edge[0], edge[1]);
        }

        for (int[] edge : edges) {
            // calculate the cost
            int root = find(edge[0]);
            int weight = edge[2];
            cost[root] &= weight;
        }
        int[] ans = new int[query.length];
        for (int i = 0; i < query.length; i++) {

            int start = query[i][0];
            int end = query[i][1];
            int sRoot = find(start);
            int sEnd = find(end);
            if (sRoot != sEnd) {
                // unconnected
                ans[i] = -1;
            } else {
                ans[i] = cost[sRoot];
            }
        }
        return ans;
    }

    private int find(int node) {
        if (parent[node] == -1) {
            // root found
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    private void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        // unconnected
        if (aRoot == bRoot)
            return;

        if (depth[a] < depth[b]) {
            int temp = aRoot;
            aRoot = bRoot;
            bRoot = temp;
        }
        parent[bRoot] = aRoot;

        if (depth[aRoot] == depth[bRoot]) {
            depth[aRoot]++;
        }

    }
}

class FailedMinimumCostWalkinWeightedGraph {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        /*
         * Repeatedly visiting is allowed
         *
         * 1. make a set about connected area
         * 2. check whether s -> t is possible
         * 3. find the minium number needs to be used inside the set
         * what if some number are must pass through?
         * Never mind, an and operation only decreases but never increases
         *
         * recordArr:
         *
         * [ [{set of nodes}, {set of weight}], .... ]
         */
        Set<Integer>[][] recordArr = new HashSet[n][2];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            Set<Integer> connectedSet = new HashSet<>();
            Set<Integer> weightSet = new HashSet<>();

            if (recordArr[u][0] == null & (recordArr[v][0] != null)) {
                // only v has recorded
                connectedSet = recordArr[v][0];
                weightSet = recordArr[v][1];
            }
            if (recordArr[u][0] != null) {
                // u recorded and v may or may not recorded
                connectedSet = recordArr[u][0];
                weightSet = recordArr[u][1];
            }

            recordArr[u][0] = connectedSet;
            recordArr[v][0] = connectedSet;

            connectedSet.add(u);
            connectedSet.add(v);

            recordArr[u][1] = weightSet;
            recordArr[v][1] = weightSet;

            weightSet.add(w);
        }
        int[] ans = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            ans[i] = handleQuery(query[i], recordArr);
        }

        return ans;

    }

    private int handleQuery(int[] query, Set<Integer>[][] recordArr) {
        int s = query[0];
        int t = query[1];
        if ((recordArr[s][0] == null) || (recordArr[t][0] == null) || !recordArr[s][0].contains(t)) {
            return -1;
        }
        int[] intArray = recordArr[s][1].stream().mapToInt(Integer::intValue).toArray();
        int res = intArray[0];
        for (int num : intArray) {
            res &= num;
        }

        return res;
    }

}
