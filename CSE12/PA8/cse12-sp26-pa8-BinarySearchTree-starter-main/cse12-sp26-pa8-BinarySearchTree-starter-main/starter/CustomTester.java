/*
 * Name: Connor Gibson
 * Email: cogibosn@ucsd.edu
 * PID: A18677169
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
/*
 * This class creates a custom test for my bst file
 */
public class CustomTester {

    MyBST<Integer, String> tree;
    /*
    * This is an optional set up before the tests
    */
    @Before
    public void setup() {
        tree = new MyBST<>();
    }

    /*
    * Test for the basic structure
    */
    @Test
    public void testInsertBasicStructure() {
        tree.insert(5, "a");
        tree.insert(3, "b");
        tree.insert(7, "c");

        assertEquals(3, tree.size());
        assertEquals("a", tree.search(5));
        assertEquals("b", tree.search(3));
        assertEquals("c", tree.search(7));

        assertEquals("c", tree.insert(7, "z"));
        assertEquals("z", tree.search(7));
    }

    /*
    * Test for the null and missing search
    */
    @Test
    public void testSearchNullAndMissing() {
        tree.insert(10, "x");
        tree.insert(5, "y");

        assertNull(tree.search(null));
        assertNull(tree.search(999));
        assertEquals("y", tree.search(5));
    }

    /*
    * Test for the successor with two children
    */
    @Test
    public void testSuccessorTwoChildren() {
        tree.insert(20, "a");
        tree.insert(10, "b");
        tree.insert(30, "c");
        tree.insert(25, "d");
        tree.insert(40, "e");

        ArrayList<MyBST.MyBSTNode<Integer,String>> nodes = tree.rangeQuery(10, 40);

        MyBST.MyBSTNode<Integer,String> n20 = null;
        MyBST.MyBSTNode<Integer,String> n25 = null;

        for (MyBST.MyBSTNode<Integer,String> n : nodes) {
            if (n.getKey().equals(20)) n20 = n;
            if (n.getKey().equals(25)) n25 = n;
        }

        assertNotNull(n20);
        assertNotNull(n25);

        assertEquals(Integer.valueOf(25), n20.successor().getKey());

        assertEquals(Integer.valueOf(30), n25.successor().getKey());
    }

    /*
    * Test for removing two children
    */
    @Test
    public void testRemoveTwoChildren() {
        tree.insert(50, "a");
        tree.insert(30, "b");
        tree.insert(70, "c");
        tree.insert(20, "d");
        tree.insert(40, "e");
        tree.insert(60, "f");
        tree.insert(80, "g");

        assertEquals("a", tree.remove(50));  
        assertNull(tree.search(50));          
        assertEquals(6, tree.size());        

        assertEquals("f", tree.search(60));

 
    }

    /*
    * Test for range query (basic)
    */
    @Test
    public void testRangeQueryBasic() {
        tree.insert(10, "a");
        tree.insert(5, "b");
        tree.insert(15, "c");
        tree.insert(3, "d");
        tree.insert(7, "e");

        ArrayList<MyBST.MyBSTNode<Integer,String>> result = tree.rangeQuery(4, 15);

        assertEquals(4, result.size());
        assertEquals(Integer.valueOf(5), result.get(0).getKey());
        assertEquals(Integer.valueOf(7), result.get(1).getKey());
        assertEquals(Integer.valueOf(10), result.get(2).getKey());
        assertEquals(Integer.valueOf(15), result.get(3).getKey());
    }

    /*
    * Test the deep copy method
    */
    @Test
    public void testCopyDeepCopy() {
        tree.insert(8, "a");
        tree.insert(4, "b");
        tree.insert(12, "c");
        tree.insert(10, "d");
        tree.insert(14, "e");

        MyBST<Integer,String> copy = tree.copy();

        assertEquals(5, copy.size());
        assertEquals("a", copy.search(8));
        assertEquals("b", copy.search(4));
        assertEquals("c", copy.search(12));
        assertEquals("d", copy.search(10));
        assertEquals("e", copy.search(14));

        tree.insert(2, "x");
        tree.remove(12);

        assertNull(copy.search(2));
        assertEquals("c", copy.search(12));
    }
}
