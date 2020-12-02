package main;

public class avlTree {

        public Node root;

       //вычисл высоту
        int height(Node N) {
            if (N == null)
                return 0;
            return N.height;
        }

    // переворачиваем направо
        Node rightRotate(Node y) {
            Node x = y.left;
            Node z = x.right;

            x.right = y;
            y.left = z;

            // высоты меняем
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            return x;
        }

        // переворачиваем налево
        Node leftRotate(Node x) {
            Node y = x.right;
            Node z = y.left;

            // вращаем
            y.left = x;
            x.right = z;

            // меняем высоты длоя актуальности
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            // возвращаем ноый корень
            return y;
        }

        // получаем баланс узла
        int getBalance(Node nodeToBalance) {
            if (nodeToBalance == null)
                return 0;

            return height(nodeToBalance.left) - height(nodeToBalance.right);
        }

        public Node insert(Node node, int key) {
            //обычная вставка узла в дерево
            if (node == null)
                return (new Node(key));

            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);
            else
                return node;// чтобы не дублировать ключи

            //новая высота предка
            node.height = 1 + Math.max(height(node.left),
                    height(node.right));


            //проверка баланса чтобы узел не стал несбалансированным
            int balance = getBalance(node);

           //если не сбалансирован то проверяем все случаи
            // право право
            if (balance > 1 && key < node.left.key)
                return rightRotate(node);

            // лево лево
            if (balance < -1 && key > node.right.key)
                return leftRotate(node);

            //  лево право
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            //  право лево
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            //возвращаем узел
            return node;
        }

        // печать дерева в глубину
        public void prTree(Node node) {
            if (node != null) {
                System.out.print(node.key + " ");
                prTree(node.left);
                prTree(node.right);
            }
            //https://clck.ru/Ey7Az
            System.out.print("Если хотите узнать о строении avl дерева : https://clck.ru/Ey7Az ");
        }

    public Node find(int key) {
        Node current;
        for (current=root; current != null; current=key < current.key ? current.left: current.right)
            if (current.key == key)
                return current;
        throw new NullPointerException("нет узла");
    }

    }





