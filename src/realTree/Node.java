package realTree;


class Node<T extends Comparable<T>> {

    T key;
    int height;
    Node<T> left;
    Node<T> right;


    Node (T key) {
        this.key = key;
        height = 1;
    }

    /**
     * @return  возвращает высоту поддерева
     */
    int getHeight() {
        return height;
    }

    /**
     * пересчёт высот поддерева от текущего узла
     */
    void recount() {
        int leftHeight = (left != null) ? left.getHeight() : 0;
        int rightHeight = (right != null) ? right.getHeight() : 0;
        this.height = (Math.max(leftHeight, rightHeight)) + 1;
    }

    // Считаем разницу высот поддеревьев относительно правого поддрева
    int bFactor() {
        int leftHeight = (left != null) ? left.getHeight() : 0;
        int rightHeight = (right != null) ? right.getHeight() : 0;
        return rightHeight - leftHeight;
    }
}
