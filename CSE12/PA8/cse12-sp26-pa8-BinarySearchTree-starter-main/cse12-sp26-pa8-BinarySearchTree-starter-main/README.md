# CSE 12 PA8: BST

Due date: **Tuesday, June 2nd @ 11:59 PM PT** (note: PA8 resubmission will be due Sunday, June 7th @ 11:59PM PT)

There is an FAQ post on Piazza. Please read that post first if you have any questions.


#### Learning goals:


* Implement a Binary Search Tree 
* Write JUnit tests to verify proper implementation
* Understand real world requirements and select appropriate data structure

In this assignment, you will implement a Binary Search Tree and write JUnit tests to ensure that your implementation functions correctly.

Make sure to **read the entire write-up and also the starter code (including the comments)** before you start coding. You may not change any public class/method or variable names given in the write-up or starter code.

Begin by downloading the starter code and put it in a directory in your working environment. 

Inside, you will find the following files: 

```
| +-- MyBST.java          Edit this file				
| +-- CustomTester.java   Create this file
| +-- PublicTester.java
| +-- SortDetective.jar   Compressed Java program to run
| +-- SortDetective.java  Part of source code for SortDetective to give hint
```

## Assignment Breakdown [100 points]

> **IMPORTANT**: There are 3 separate Gradescope assignments. They are all named PA8-\<something>. Please ensure you are submitting the correct files to each Gradescope assignment.

* **Task 1** [96 points] You will earn points based on the autograder tests that your code passes. If the autograder tests are not able to run (e.g., your code does not compile or it does not match the specifications in this writeup), you may not earn credit.
    * **Part 1** [90 points] - submit to <u>PA8-Task1-Part1</u>
      * **MyBST Implementation** [65 points]
         * The autograder will test your implementation on the public test cases given in `PublicTester.java` and hidden test cases not described in this PA writeup.
      * **Testing** [20 points]
         * The autograder will test your implementation of the JUnit tests. 
         * This section has a maximum of 20 pts. This means if you pass at least 8 out of 11 custom tester cases, you will get full points for the Testing portion.
      * **Coding Style** [5 points]
         * `MyBST.java` will be graded on style. `CustomTester.java` will be graded on file, class, method headers and indentation but not magic numbers.
   * **Part 2** [6 points] - submit to <u>PA8-Task1-Part2(sort-detective)</u>
      * **Sort Detective Problem**: `Detective.json` will be tested and graded. This is NOT eligible for resubmission.
* **Task 2** [4 points] - fill out form in <u>PA8-Task2</u>
  * Fill out **PA8 Task 2** form on Gradescope. There is no `submission.txt`. This is NOT eligible for resubmission.

**Note:** There is no `MyAlgorithm` for this PA. However, there are methods similar in nature that you have to implement in `MyBST`.

## Submission Instructions

#### Turning in your code

Submit all of the following files to Gradescope by the deadline:
* **For Task 1 - Part 1**:
   * `MyBST.java`				
   * `CustomTester.java`

* **For Task 1 - Part 2**: (NOT eligible for resubmission)
   * `detective.json`				

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.

#### Submitting Task 2 

Fill out the **PA8 Task 2** form on Gradescope. NOT eligible for resubmission

## Task 1 - Part 1: Testing and Implementation of Binary Search Tree [90 points]

### Part 1A: `MyBSTNode`


This class is a static nested class of the MyBST class. Objects of this class represent the nodes of the binary search tree and contain the following private instance variables: 


<table>
  <tr>
   <td><strong>Instance Variable </strong>
   </td>
   <td><strong>Description </strong>
   </td>
  </tr>
  <tr>
   <td><code>K key</code>
   </td>
   <td>The key that we are using to sort our nodes
<ul>

<li>This key cannot be <code>null</code> 

<li>Duplicate keys are not supported (duplicates are defined by compareTo() returning that the keys are equal)
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>V value</code>
   </td>
   <td>The data stored in this MyBSTNode
<ul>

<li>This value can be <code>null</code>
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>MyBSTNode<K, V> parent</code>
   </td>
   <td>A reference to the parent of this MyBSTNode
<ul>

<li>If this node has no parent, then this will be <code>null</code>
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>MyBSTNode<K, V> left</code>
   </td>
   <td>A reference to the left child of this MyBSTNode
<ul>

<li>If this node has no left child, then this will be <code>null</code>
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>MyBSTNode<K, V> right</code>
   </td>
   <td>A reference to the right child of this MyBSTNode
<ul>

<li>If this node has no right child, then this will be <code>null</code>
</li>
</ul>
   </td>
  </tr>
</table>


Since these instance variables are all private, remember to use getter/setter methods to access/modify them. 

**Method to implement:**

**`public MyBSTNode<K, V> successor()`**

* This method returns the node with the smallest key that is still larger than the key of the current node. If there is no larger key, return `null`.
* Every node in our BST should / will already have the proper connections (parent and child references) with our sorted key property.
* Hint: Refer to zyBooks section 7.3 on how to implement successor if you are stuck!
* Important: **You are not allowed to change the class signature! If you change the class signature, you’ll receive 0 for this part.**

### Part 1B: `MyBST`


The MyBST class is the public class of MyBST.java file. It represents a binary search tree where sorting is done based on the keys. This is not a self-balancing tree. Make sure you don’t change the class header, otherwise you will receive 0 points for the entire file.

MyBST has the property that all nodes in the left subtree of a node will have a key smaller than `node.key`, and all nodes in the right subtree of node will have a key greater than `node.key`. **We will not be allowing duplicate keys in our tree**, but we can have many different keys associated with the same value. We will be using the `compareTo`  method to compare smaller/greater/equal keys.


<table>
  <tr>
   <td><strong>Instance Variable </strong>
   </td>
   <td><strong>Description </strong>
   </td>
  </tr>
  <tr>
   <td><code>MyBSTNode<K, V> root</code>
   </td>
   <td>A reference to the root node of our tree.
<ul>

<li>If the tree is empty, then this will be <code>null</code>. 
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td><code>int size  </code>
   </td>
   <td>Represents the number of nodes in the tree.
   </td>
  </tr>
</table>


#### Methods to implement (iteration and recursion are both allowed for this PA):


##### 
**`public V insert(K key, V value)`**



* If `key` is `null`, throw a `NullPointerException`.
* Insert a new node containing the arguments `key` and `value` into the binary search tree according to the binary search tree properties. Update the tree size accordingly.
* If a node with `key` already exists in the tree, replace the value in the existing node with `value`. ([Example](#insert-an-element))
    * Note: We won't change the key of the pre-existing node. 
* If a node with `key` already exists in the tree, return the original value replaced by `value`. Otherwise, if a new node was created (no replacement occurred), return `null`.
    * Note: We overloaded the meaning of returning `null`  as we’ve inserted a node with a new key into the tree, or that we have replaced the value of a node whose original value was null. 

##### 
**`public V search(K key)`**

* Search for a node with key equal to `key` and return the value associated with that node. The tree structure should not be affected by this.
* If no such node exists, return `null` instead. Again, note that we've overloaded the meaning of returning `null`.
* Note: `key` might be `null`. We'll assume that no other key is equal to `null` (even though the `compareTo()` of type K might define this differently). This means that `search(null)` should always return `null`.

##### 



##### 
**`public V remove(K key)`**

* Search for a node with key equal to `key` and return the value associated with that node. The node should be removed (disconnected) from the tree and all references should be fixed, if needed. Update the tree size accordingly.
* When removing the node, if the node is not a leaf node, then we may have to make some changes to the tree to maintain its integrity. Make sure to fix all relevant references to parents/children.
    * For leaf nodes, no replacement is needed.
    * If the node has a single child, move that child up to take its place. ([Example](#remove-a-node-that-has-a-single-child))
    * If the node has two children, then we will just overwrite the data at the node we plan to remove with the data from the successor, saving us the need to reconnect all the nodes if we had completely removed the node from the tree. Then remove the successor from the tree (this does not need to be done iteratively, you can do this with a recursive call). ([Example](#remove-a-node-that-has-two-children))
* If no such node exists, return `null` instead. Again, note that we've overloaded the meaning of returning `null`.
* Note: `key` might be `null`. We'll assume that no other key is equal to `null` (even though the `compareTo()`of type K might define this differently). This means that `remove(null)`should always return `null` and not remove any nodes.

##### 
**`public ArrayList<MyBSTNode<K, V>> rangeQuery(K low, K high)`**

* Do an in-order traversal of the elements within the inclusive range [`low`, `high`] (i.e., nodes whose key satisfies `low <= key <= high` as defined by `compareTo()`), adding each node to the end of an `ArrayList`, which will be returned.
* After the entire traversal, this `ArrayList` should contain *only* the nodes that are within the range. These nodes will be sorted by the key order (with the smallest key in the range at index 0) due to the order of the traversal.
* You may assume `low` and `high` are valid (non-null) keys.
* If the tree is empty, an **empty** (not `null`) `ArrayList` should be returned.
* Using sorting algorithms is not allowed.
* Hint: it might be helpful to think about how we used recursion to perform an in-order traversal in class. You may find `addAll` from the [Collection interface](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Collection.html#addAll(java.util.Collection)) useful for concatenating ArrayLists.

##### 
**`public MyBST<K, V> copy()`**

* Copy the BST and return a new `MyBST<K, V>` reference that is an exact copy of all nodes (and connections between nodes) from the original tree. 
* For each node in the copied BST, the corresponding `key` and `value` should remain the same but `left`, `right`, and `parent` must not point back to nodes in the original BST.
* We encourage you to implement this method using a preorder traversal.
* Hint: it might be helpful to think about using a helper method to recursively copy the nodes.

### Part 2: Testing (20 points)

We provide `PublicTester.java`, which contains all the public test cases we will use to grade your BST implementation (visible on Gradescope). See the comments in the file for test case details.


Your task: create `CustomTester.java` and implement tests that can correctly distinguish between good and bad implementations.

* Your tests will be graded by checking if they pass on a good implementation and fail on a bad implementation. If a test fails on a good implementation, then the test is written incorrectly. If a test passes on a bad implementation, it may be written incorrectly or may be not be rigorous enough (try adding more asserts).
* Gradescope will report whether your CustomTester fails on our good implementation (solution code) as a way to sanity check some of your test cases. However, you will not be able to see whether your tests pass/fail on the bad implementations until the PA is graded. Do your best to write comprehensive test asserts based on the write-up description and public tester examples.
* Any test you write in `CustomTester.java` will be run against all bad implementations. You will receive 2.5 points for every bad implementation your test is expected to fail, up to 20 points (if your test also passes on the good implementation).
* There is a total of 11 bad implementations for the following methods:
  * `successor`
  * `insert`
  * `search`
  * `remove`
  * `rangeQuery`
  * `copy`

#### How to compile and run the testers:

Running the tester on UNIX based systems (including Mac):

* Compile: `javac -cp ../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar:. PublicTester.java`
* Execute: `java -cp ../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore PublicTester`

Running the tester on Windows systems:

* Compile: `javac -cp ".;..\lib\junit-4.13.2.jar;..\lib\hamcrest-core-1.3.jar" PublicTester.java`
* Execute: `java -cp ".;..\lib\junit-4.13.2.jar;..\lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore PublicTester`

To run the custom tester, replace references to `PublicTester` with `CustomTester` in the above commands. If you would like to compile all files at once, replace `PublicTester.java` with `*.java`.

**NOTE:** These commands assume the following workspace setup:

```
# Once your file system looks like this, you should open a terminal inside the 'starter' folder
# You can do this by right-clicking 'starter' in VSCode's `Explorer` tab and clicking 'Open in Integrated Terminal'
+-- lib
|   +-- hamcrest-core-1.3.jar
|   +-- junit-4.13.2.jar
+-- starter
|   ... your code files
```

### Part 3: Coding Style (5 points)

Coding style is an important part of ensuring readability and maintainability of your code. We will grade your code style in all submitted code files according to the style guidelines. Namely, there are a few things you must have in each file/class/method:

* File header
* Class header
* Method header(s)
* Inline comments
* Proper indentation
* Descriptive variable names
* No magic numbers (Exception: Magic numbers can be used for testing.)
* Reasonably short methods (if you have implemented each method according to the specification in this write-up, you’re fine). This is not enforced as strictly.
* Lines shorter than 80 characters
* Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full style guide can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/README.md) and a sample styled file can be found [here](https://github.com/CaoAssignments/guides/blob/main/resources/SampleFile.java). If you need any clarifications, feel free to ask on Piazza.

## Task 1 - Part 2: Sort Detective [6 points] 


*This assignment is developed from [http://nifty.stanford.edu/2002/LevineSort/labWriteUp.htm](http://nifty.stanford.edu/2002/LevineSort/labWriteUp.htm)*

In this assignment, you are given a program which is designed to measure comparisons, data movements, and execution time for some of the sorting algorithms we discussed in class and in your readings: **insertion sort, selection sort, merge sort and quicksort**. 

You may ask Professors/TAs/Tutors for some guidance and help. You can, of course, discuss the assignment with your classmates, but don’t just give away the answers!

The following files are provided for you and can be found in the starter repo:


```
| +-- SortDetective.java				
| +-- SortDetective.jar
```

You will submit the following file for this assignment:



* `detective.json`

As you know from class, if you double the size of the input data that you give to a quadratic algorithm, it will do four times the work; by contrast, an $O(n\ log(n))$ algorithm will do a bit more than twice as much; and, a linear algorithm will do only twice as much work. The properties of the input data set can affect the expected performance of many of our sorting algorithms. Before you begin, you should review the expected performance of the algorithms on various data sets.


1. Execute `SortDetective.jar` (to execute the program type `java -jar SortDetective.jar` at the command line, or double click the jar file in a file explorer), enter your PID on the top right corner and play with it a bit. Each of you would get a different matching based on your PID, and we will grade based on your PID as well. Create a list with some size and property of your choice by clicking "Create The List".
2. Click on each Greek letter under "Which Sort Is Which?" to see the number of comparisons, movement and running time of this execution under "Experimental Results". Notice that the Greek letters do not give any indication which sort they will execute.
3. As you click each Greek letter, take careful notes as you go. From your notes and the expected behavior of different sorting algorithms, match each Greek letter to some sorting algorithm and think about the rationale behind your guess.
4. Select the sorting algorithms for each Greek letter, and click on "Save to File" to export a json file named `detective.json` that you'll submit to gradescope. Make sure your PID is correct! Do no modify the `detective.json` file for any reason.
5. We have also provided `SortDetective.java` for you to know how we implemented each algorithm in `SortDetective.jar` and how we generate number of comparisons and execution time. Looking at the code is optional but encouraged, which is a review of how we can implement these algorithms, and moreover, make your guess more accurate.

## Task 2: Runtime [4 points]

A core goal of this course is to teach students how to identify the time complexity (Big-O notation) of algorithms and data structures. In practice, knowing the runtime characteristics of different data structures helps you pick the right one for the job.

In this section, you will be given real-world scenarios and asked to select the most appropriate data structure (ArrayList, LinkedList, Hash Map, Hash Set, Stack, Queue, Priority Queue, and BST) based on the operations described and their runtimes.


**Your task:** Fill out the **PA8 Task 2** form on Gradescope. This will **NOT** be eligible for resubmission.


## Appendix


###### Insert an element
<table width="100%">
  <tr>
   <td width="65%">


![](/imgur/img1.png)
     

   </td>
   <td>Call to <code>insert(100, "Niema").</code>
<p>
Begin insertion of a node with key = 100 and value = "Niema".
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img2.png)

   </td>
   <td>Go down the BST to find the place of insertion. 
<p>
First compare with the root. 
<p>
Since the key of the root (20) is less than 100, we go right, as indicated by the green arrow.
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img3.png)

   </td>
   <td>We continue to go down the BST to find the place of insertion. 
<p>
Since the key of the right child of the root (95) is less than 100, we continue to go right, as indicated by the green arrow.
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img4.png)


   </td>
   <td>Now we want to go left because the key at the current node (120) is greater than 100.
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img5.png)

   </td>
   <td>We continue to go left because the key at the current node (105) is greater than 100.
   </td>
  </tr>
  <tr>
   <td>
       
![](/imgur/img6.png)       

   </td>
   <td>However, because a node with a key equal to 100 already exists in the BST, we will replace the original value ("Sander") with the new value ("Niema").
   </td>
  </tr>
</table>



###### Remove a node that has a single child


<table width="100%">
  <tr>
   <td width="65%">

![](/imgur/img7.png)

   </td>
   <td>Call to <code>remove(11).</code>
<p>
Before this step, we've already iterated down the tree to find where the node with key 11 is. 
<p>
Begin removal of the node with key = 11, and value = "Joe".
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img8.png)
       
   </td>
   <td>Because the node we want to remove only has one child (the left child), we simply replace the node with its only child.
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img9.png)

   </td>
   <td>We disconnect the original node from the tree and fix the parent pointer of the replacement (8:"Gerald") and child pointer of the parent (12:"Haytham"), completing the removal and replacement.
   </td>
  </tr>
</table>



###### Remove a node that has two children


<table width="100%">
  <tr>
   <td width="65%">

![](/imgur/img10.png)

   </td>
   <td>Call to <code>remove(95)</code>. 
<p>
Before this step, we've already iterated down the tree to find where the node with key 95 is. 
<p>
Begin removal of the node with key = 95 and value = "Paul".
<p>
Note: the steps to locate this node are not shown.
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img11.png)

   </td>
   <td>Since this node has two children, we want to first find its successor (the blue node) and replace the data (key and value) in this node with its successor's data.
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img12.png)

   </td>
   <td>Now we replace the data (key and value) in this node with its successor's data, keeping all the parent/child references.
   </td>
  </tr>
  <tr>
   <td>

![](/imgur/img13.png)
   </td>
   <td>The final step is to remove the successor and now we are done with removing a node with two children.
   </td>
  </tr>
</table>
