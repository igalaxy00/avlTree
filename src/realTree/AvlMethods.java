package realTree;

import org.jetbrains.annotations.NotNull;

import java.util.*;



public class AvlMethods<T extends Comparable<T>> implements SortedSet<T> {

    private Tree<T> tree= new Tree<>();
    private class TreeIterator implements Iterator<T> {
        T current ;
        T first = null;
        T last = null;
        boolean end = false;

        TreeIterator() {
            if (!isEmpty()) {
                first = tree.first();
                current = tree.first();
                last = last();
            }
        }

        // переопределение hasNext, next
        @Override
        public boolean hasNext() {
            return (!isEmpty() && !end);
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T next = current;
            current = tree.next(next);
            end = current == null;
            return next;
        }

        @Override
        public void remove() {

        }
    }

    // переопределение  comparator, subSet, headSet, tailSet, first, last
    @Override
    public Comparator<? super T> comparator() {
        return Comparator.naturalOrder();
    }


    private SortedSet<T> subSet(T fromElement, T toElement, boolean withHigher) {
        AvlMethods<T> subSet = new AvlMethods<>();
        Iterator<T> iterator = new TreeIterator();
        while (iterator.hasNext()) {
            T i = iterator.next();

            if (i.compareTo(fromElement) >= 0 && ((withHigher) ?
                    i.compareTo(toElement) <= 0: i.compareTo(toElement) < 0))
                subSet.add(i);
        }
        return subSet;
    }

    @NotNull
    @Override
    public SortedSet<T> subSet(T start, T end) {
        return subSet(start, end, false);
    }

    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        if (isEmpty())
            return new AvlMethods<>();
        return subSet(first(), toElement, true);
    }

    @NotNull
    @Override
    public SortedSet<T> tailSet(T start) {
        if (isEmpty()) return new AvlMethods<>();
        return subSet(start, last(), true);
    }

    @Override
    public T first() {
        if (isEmpty()) throw new NoSuchElementException();
        return tree.first();
    }

    @Override
    public T last() {
        if (isEmpty()) throw new NoSuchElementException();
        return tree.last();
    }

    /* переопределение  size, isEmpty, contains, containsAll, iterator, toArray, add,
    addAll, remove, removeAll, retainAll, clear, equals, hashCode
    */
    @Override
    public int size() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return tree.getSize() == 0;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        return !isEmpty() && tree.contains(t);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        Iterator<T> iterator = new TreeIterator();
        Object[] o = new Object[size()];
        for (int i = 0; i < size(); i++) {
            o[i] = iterator.next();
        }
        return o;
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray( T1[] a) {
        Iterator<T> iterator = new TreeIterator();
        Object[] result = a;
        if (a.length < size())
            a = (T1[])java.lang.reflect.Array.newInstance(a.getClass(), size());

        for (int i = 0; i < size(); i++) {
            result[i] = iterator.next();
        }
        if (a.length > size())
            for (int i = size(); i < a.length; i++)
                result[i] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        tree.insert(t);
        return tree.changed();
    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        tree.remove(t);
        return tree.changed();
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        if (isEmpty() && c.size() > 0) return false;
        Object[] objects = c.toArray();
        for (Object object : objects) {
            @SuppressWarnings("unchecked")
            T t = (T) object;
            if (!contains(t)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll( Collection<? extends T> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean retainAll( Collection<?> c) {
        Tree<T> newRoot = new Tree<>();
        Object[] elements = c.toArray();
        for (Object o : elements) {
            @SuppressWarnings("unchecked")
            T t = (T) o;
            if (contains(t)) {
                newRoot.insert(t);
            }
        }
        tree = newRoot;
        return true;
    }

    @Override
    public boolean removeAll( Collection<?> c) {
        c.forEach(E -> {
            @SuppressWarnings("unchecked")
            T t = (T) E;
            remove(t);
        });
        return true;
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;

        if (!(o instanceof Set))
            return false;
        Set<?> s = (Set<?>) o;
        if (s.size() != size())
            return false;

        for (T t : this)
            if (!s.contains(t))
                return false;

        return true;
    }


}