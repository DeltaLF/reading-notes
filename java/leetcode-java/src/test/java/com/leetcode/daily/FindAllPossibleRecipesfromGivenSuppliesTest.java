package com.leetcode.daily;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class FindAllPossibleRecipesfromGivenSuppliesTest {
    @Test
    public void testFindAllPossibleRecipesfromGivenSupplies() {
        FindAllPossibleRecipesfromGivenSupplies solution = new FindAllPossibleRecipesfromGivenSupplies();
        String[] expected = { "a", "b", "c", "d", "e" };
        String[] actual = solution.findAllRecipes(
                new String[] { "a", "b", "c", "d", "e" },
                Arrays.stream(new String[][] { { "b" }, { "c" }, { "d" }, { "e" }, { "z" } })
                        .map(Arrays::asList)
                        .collect(Collectors.toList()),
                new String[] { "z" }).toArray(new String[0]); // Convert List<String> to String[]

        Arrays.sort(expected);
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}
