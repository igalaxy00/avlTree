package realTree;

import java.util.NoSuchElementException;

public class Tree<T extends Comparable<T>> {

    private Node<T> root;
    private int size = 0;
    private boolean wasChanged = false;

    public int getSize() {
        return size;
    }

    /**
     * метод ниже осуществляет малое левое вращение
     *
     * @param node узел который вращаем
     */
    private Node<T> leftRotate(Node<T> node) {
        Node<T> right = node.right;
        node.right = right.left;
        right.left = node;

        right.recount();
        node.recount();
        return right;
    }


    /**
     * метод ниже осуществляет малый правый
     *
     * @param node узел который вращаем
     */
    private Node<T> RightRotate(Node<T> node) {
        Node<T> left = node.left;
        node.left = left.right;
        left.right = node;

        node.recount();
        left.recount();
        return left;
    }


    /**
     * метод ниже осуществляет балансировку дерева для добавления или удаления узла
     *
     * @param node узел баланс которого ищем
     */
    private Node<T> balance(Node<T> node) {
        node.recount();
        if (node.bFactor() == 2) {
            if (node.right.bFactor() < 0)
                node.right = RightRotate(node.right);
            return leftRotate(node);
        }
        if (node.bFactor() == -2) {
            if (node.left.bFactor() > 0)
                node.left = leftRotate(node.left);
            return RightRotate(node);
        }
        return node;
    }


    /**
     * метод ниже осуществляет поиск минимального элемента в левом поддереве
     *
     * @param node узел относительно которого изем минимальный
     */
    private Node<T> minimum(Node<T> node) {
        if (node.left == null)
            return node;
        else
            return minimum(node.left);
    }


    /**
     * метод ниже осуществляет поиск максимального элемента в правом поддереве
     *
     * @param node узел относительно которого изем максимальный
     */
    private Node<T> maximum(Node<T> node) {
        if (node.right == null) return node;
        else return maximum(node.right);
    }


    /**
     * метод ниже осуществляет поиск следующего узла
     *
     * @param node узел относительно которого изем следующий узел
     */
    private Node<T> nextNode(Node<T> node, Node<T> prev, T key) {
        int compareResult = key.compareTo(node.key);

        Node<T> closest = (compareResult < 0 && node.key.compareTo(prev.key) < 0) ? node : prev;
        if (compareResult < 0 && node.left != null) {
            closest = nextNode(node.left, closest, key);
        } else if (compareResult > 0 && node.right != null) {
            closest = nextNode(node.right, closest, key);
        } else {
            if (node.right != null) {
                return minimum(node.right);
            }
        }
        return closest;
    }


    /**
     * метод ниже осуществляет поиск ключа ниже данного узла
     *
     * @param node узел относительно которого изем ключ
     */
    private boolean contains(Node<T> node, T key) {
        if (node == null)
            return false;
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return contains(node.left, key);
        } else
            return (compareResult == 0 || contains(node.right, key));
    }


    /**
     * метод ниже осуществляет проверку есть ли такой ключ в дереве
     *
     * @param key ключ который ищем
     */
    public boolean contains(T key) {
        return (contains(root, key));
    }


    /**
     * служебный метод ниже осуществляет вставку узла в дерево
     *
     * @param node узел который вставляем
     * @param key  ключ узла который вставляем
     */
    private Node<T> insert(Node<T> node, T key) {
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            if (node.left != null) {
                node.left = insert(node.left, key);
            } else {
                node.left = new Node<>(key);
                size++;
                wasChanged = true;
            }
        }
        if (compareResult > 0) {
            if (node.right != null) {
                node.right = insert(node.right, key);
            } else {
                node.right = new Node<>(key);
                size++;
                wasChanged = true;
            }
        }
        return balance(node);
    }


    /**
     * публичный метод ниже осуществляет вставку в дерево узла
     *
     * @param key ключ узла который вставляем
     */
    public void insert(T key) {
        if (root == null) {
            root = new Node<>(key);
            size++;
            wasChanged = true;
        } else
            root = insert(root, key);
    }


    /**
     * метод ниже осуществляет удаление минимального элемента
     *
     * @param node узел относительно которого убираем минимальный элемент
     */
    private Node<T> removeMinimum(Node<T> node) {
        if (node.left == null)
            return node.right;
        node.left = removeMinimum(node.left);
        return balance(node);
    }


    private Node<T> remove(Node<T> n, T key) {
        int compareResult = key.compareTo(n.key);

        if (compareResult < 0 && n.left != null) {
            n.left = remove(n.left, key);
        } else if (compareResult > 0 && n.right != null) {
            n.right = remove(n.right, key);
        } else  {
            size--;
            wasChanged = true;
            if (n.right == null) return n.left;
            Node<T> change = minimum(n.right);
            change.right = removeMinimum(n.right);
            change.left = n.left;
            return balance(change);
        }
        return n;
    }


    public boolean remove(T key) {
        if (root == null) throw new NoSuchElementException();
        root = remove(root, key);
        return true;
    }


    /**
     * метод ниже осуществляет отчистку вего дерева
     */
    public void clear() {
        size = 0;
        root = null;
    }


    /**
     * метод ниже осуществляет поиск левого элемента для итератора
     */
    public T first() {
        if (root == null) throw new NoSuchElementException();
        return minimum(root).key;
    }


    /**
     * метод ниже осуществляет поиск полседнего элемента для итератора
     */
    public T last() {
        if (root == null) throw new NoSuchElementException();
        return maximum(root).key;
    }


    /**
     * метод ниже осуществляет поиск следующего элемента
     *
     * @param key ключ относительно которо ищем следующий узел
     */
    public T next(T key) {
        if (root == null) return null;
        T next = nextNode(root, maximum(root), key).key;
        return (next.compareTo(key) > 0) ? next : null;
    }


    /**
     * метод ниже осуществляет проверку изменялся ли узел для  метода add и remove
     */
    public boolean changed() {
        if (wasChanged) {
            wasChanged = false;
            return true;
        }
        return false;
    }
}
