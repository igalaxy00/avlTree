
import main.avlTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAvlTree {
    @Test
    void testTree() {

        avlTree tree = new avlTree();
        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 8);
        tree.root = tree.insert(tree.root, 18);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, 17);
        tree.root = tree.insert(tree.root, 4);
        System.out.println(tree.find(17).key);
        assertEquals(tree.find(8).left.key,5);
		/* дерево
			12
			/ \
		   8   18
		  / \   \
	     5  11   17
        /
	   4
		*/
        System.out.println("печать дерева в глубину: ");
        tree.prTree(tree.root);
    }
}