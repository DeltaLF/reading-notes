package com.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllPossibleRecipesfromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        /*
         * for brute force:
         * first n: go thorugh recpie
         * second n:
         * if supplies changed go throgh recipe again
         * in the worest case recipe: [ing0, ing1, ing2... ing99]
         * the third m: go through ingred in each recipe
         * O(n*n*n)
         *
         * sol
         * 1. make supplies set
         * 2. go through recipe
         * 3. add to supplies set whenver it's possible
         * 4. if no new supply is added (no possible recipe) => stop
         * (in the worest scenario, it takes n time (+1 recipe each loop))
         */
        List<String> ans = new ArrayList<>();
        int n = recipes.length;
        Set<String> suppliesSet = new HashSet<>(Arrays.asList(supplies));
        boolean isNewSuppliesFound = true;
        while (isNewSuppliesFound) {
            isNewSuppliesFound = false;
            for (int i = 0; i < n; i++) {
                List<String> ingreds = ingredients.get(i);
                if (ingreds == null)
                    continue;
                int ingredMatced = 0;
                for (String ingred : ingreds) {
                    if (suppliesSet.contains(ingred)) {
                        ingredMatced++;
                    }
                }
                if (ingreds.size() == ingredMatced) {
                    suppliesSet.add(recipes[i]);
                    ans.add(recipes[i]);
                    isNewSuppliesFound = true;
                    ingredients.set(i, null);
                }

            }
        }

        return ans;

    }
}