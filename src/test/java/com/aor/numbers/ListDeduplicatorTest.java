package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class ListDeduplicatorTest {

    private ListDeduplicator deduplicator;
    private List<Integer> expected = new ArrayList<>();
    @Before
    public void helper(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);


        expected.add(1);
        expected.add(2);
        expected.add(4);
        deduplicator =  new ListDeduplicator(list);
    }
    @Test
    public void deduplicate() {
        class SortStub implements IListSorter{
            @Override
            public List<Integer> sort() {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(4);
                list.add(2);

                List<Integer> sorted = new ArrayList();
                for (Integer number : list)
                    sorted.add(number);

                for (int i = 0; i < sorted.size() ; i++)
                    for (int j = i + 1; j < sorted.size() ; j++)
                        if (sorted.get(i) > sorted.get(j))
                            Collections.swap(sorted, i, j);

                return sorted;
            }
        }

        List<Integer> distinct = deduplicator.deduplicate(new SortStub());

        assertEquals(expected, distinct);
    }
}