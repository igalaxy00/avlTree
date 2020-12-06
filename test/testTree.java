
import realTree.AvlMethods;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class TestAvlTree {





    @Test
    void testAdd() {
        AvlMethods<Integer> set = new AvlMethods<>();

        //тестируем add
        assertTrue(set.add(12));
        assertTrue(set.add(8));
        assertTrue(set.add(18));
        assertTrue(set.add(5));
        assertTrue(set.add(11));
        assertTrue(set.add(17));
        assertTrue(set.add(4));

        //тест size
        assertEquals(set.size(),7);

        //тест remove
        assertTrue(set.remove(12));
        assertTrue(set.remove(8));

        //тест size
        assertEquals(set.size(),5);

        //Тест для contains
        assertFalse(set.contains(8));
        assertTrue(set.contains(4));

        //Тест для containsAll
        List<Integer> list1 = new ArrayList<>();
        list1.add(4);
        list1.add(18);
        assertTrue(set.containsAll(list1));
        list1.add(102);
        assertFalse(set.containsAll(list1));
        set.clear();

       //Тест для addAll
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list3.add(1);
        list3.add(2);
        list3.add(3);
        assertTrue(set.addAll(list2));
        assertTrue(set.containsAll(list3));

       //Тест для removeAll
        assertTrue(set.removeAll(list2));
        assertTrue(set.isEmpty());

        //Тест для clear
        assertTrue(set.addAll(list2));
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testIterator() {

        AvlMethods<Integer> treeIt = new AvlMethods<>();

        assertTrue(treeIt.add(7));
        assertTrue(treeIt.add(4));
        assertTrue(treeIt.add(2));
        assertTrue(treeIt.add(0));
        assertTrue(treeIt.add(3));
        assertTrue(treeIt.add(6));
        assertTrue(treeIt.add(5));
        assertTrue(treeIt.add(13));
        assertTrue(treeIt.add(9));
        assertTrue(treeIt.add(14));
        assertTrue(treeIt.add(11));

        Iterator<Integer> iterator1 = treeIt.iterator();
        Iterator<Integer> iterator2 = treeIt.iterator();

        while (iterator1.hasNext()) {
            assertEquals(iterator2.next(), iterator1.next());
        }

}

    @Test
    void subSet() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        AvlMethods<Integer> treeSub = new AvlMethods<>();
        TreeSet<Integer> origin = new TreeSet<>();
        assertEquals(origin.subSet(2,7), treeSub.subSet(2,7));
        assertEquals(origin.tailSet(3), treeSub.tailSet(3));
        assertEquals(origin.headSet(10), treeSub.headSet(10));
        origin.addAll(list);
        treeSub.addAll(list);
        assertEquals(origin.subSet(2,7), treeSub.subSet(2,7));
        assertEquals(origin.tailSet(3), treeSub.tailSet(3));

    }
    @Test
    void toArr() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        AvlMethods<Integer> treeSub = new AvlMethods<>();
        TreeSet<Integer> origin = new TreeSet<>();
        origin.addAll(list);
        treeSub.addAll(list);
        assertArrayEquals(origin.toArray(), treeSub.toArray());
    }
    @Test
    void testAll(){
        toArr();
        subSet();
        testIterator();
        testAdd();
    }
}