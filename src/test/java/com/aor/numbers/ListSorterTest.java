package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListSorterTest {
    private List<Integer> expected;
    private ListSorter sorter;

    @Before
    public void helper(){
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);

        expected = new ArrayList();
        expected.add(1);
        expected.add(2);
        expected.add(2);
        expected.add(4);

        sorter = new ListSorter(list);

    }
    @Test
    public void sort() {

        List<Integer> sorted = sorter.sort();

        assertEquals(expected, sorted);
    }
}