package com.aor.numbers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ListAggregatorTest {
    private ListAggregator aggregator;



    @Before
    public void helper (){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(2);
        aggregator = new ListAggregator(list);
    }


    @Test
    public void sum() {

        int sum = aggregator.sum();

        assertEquals(9, sum);
    }

    @Test
    public void max() {


        int max = aggregator.max();
        assertEquals(4, max);




    }

    @Test
    public void min() {

        int min = aggregator.min();

        assertEquals(1, min);
    }



    @Test
    public void distinct() {


        class DeduplicateStub implements IListDeduplicator {
            @Override
            public List<Integer> deduplicate(IListSorter listSorter) {
                List<Integer> list = new ArrayList<>();
                list.add(1);
                list.add(2);
                list.add(4);
                list.add(2);
                //ListSorter listSorter = new ListSorter(list);
                List<Integer> sorted = listSorter.sort();
                List<Integer> unique = new ArrayList<>();

                Integer last = null;

                for (Integer number : sorted)
                    if (!number.equals(last)) {
                        last = number;
                        unique.add(number);
                    }

                return unique;
            }
        }
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

        int distinct = aggregator.distinct(new DeduplicateStub(),new SortStub());
        assertEquals(3, distinct);
    }
}