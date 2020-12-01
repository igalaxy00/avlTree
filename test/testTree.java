
import main.avlTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAvlTree {
    @Test
    void testTree() {

        avlTree tree = new avlTree();

        /* Constructing tree given in the above figure */
       tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);
        /*
        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 8);
        tree.root = tree.insert(tree.root, 18);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, 17);
        tree.root = tree.insert(tree.root, 4);
        System.out.println(tree.find(17).key);*/
        assertEquals(tree.find(30).left.key,20);
		/* The constructed AVL Tree would be
			30
			/ \
		   20 40
		   / \  \
		  10 25  50
		*/
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);
    }
}