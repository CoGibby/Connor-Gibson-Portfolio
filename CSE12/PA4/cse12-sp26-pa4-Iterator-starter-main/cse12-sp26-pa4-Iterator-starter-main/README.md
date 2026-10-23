# CSE 12 PA4: Iterators

**Due date: Thursday, Apr. 30 @ 11:59 PM PST**

There is an FAQ post on Piazza. Please read that post first if you have any questions.


### **Learning goals**

* Implement a data structure similar to Java’s Iterator with generic types
* Solve a coding problem using your new Iterator
* Write JUnit tests to verify proper implementation

In this PA, you will implement an iterator for `MyLinkedList` and write JUnit tests to ensure that your implementation functions correctly. Additionally, you will apply your new `MyListIterator` to last week's coding problem.

Begin by downloading the starter code and put it in a directory in your working environment. 

Inside, you will find the following files:

```
+-- PA4
|    +-- MyAlgorithm.java                   Edit this file
|    +-- MyListIteratorCustomTester.java    Edit this file
|    +-- MyListIteratorPublicTester.java
```

**Copy over MyLinkedList.java from PA3 to this directory**, because you will also edit this file.

## Assignment Breakdown (100 Points)

* **Task 1** [100 points] You will earn points based on the autograder tests that your code passes. If the autograder tests are not able to run (e.g., your code does not compile or it does not match the specifications in this writeup), you may not earn credit.
    * **Tester** [20 points]
        * The autograder will test your implementation of the JUnit tests. Your unit tests are expected to fail or pass based on the [Part 1 Test Table](#Tests-Table-List-of-test-cases-to-write-and-their-description).
    * **MyListIterator** [65 points]
        * The autograder will test your implementation of `MyListIterator` on the public test cases given in `MyListIteratorPublicTester.java` and hidden test cases not described in this PA writeup.
    * **MyAlgorithm** (10 points)
        * The autograder will test your implementation of `MyAlgorithm` on the public test cases and hidden test cases from **PA3**. ⚠️ These are expected to fail if you don't meet the time complexity requirements!
    * **Coding Style** [5 points]
        * `MyLinkedList.java` and `MyAlgorithm.java` will be graded on style. `MyListIteratorCustomTester.java` will only be graded on file header.

Note that there is no **Task 2** this PA.

## Submission Instructions

* Submit all of the following files to **PA4** on Gradescope. 
    * `MyLinkedList.java`
    * `MyAlgorithm.java`
    * `MyListIteratorCustomTester.java`

**Important**: Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.

**Read the entire write-up before you start coding.**

## Task 1: Testing, Implementation, and Application of MyListIterator

### Part 1: JUnit Testing (20 points)

We provide two test files:

* `MyListIteratorPublicTester.java`
    * This contains all the public test cases we will use to grade your `MyListIterator` implementation (visible on Gradescope).
* `MyListIteratorCustomTester.java`
    * Contains some of the headers and description to the hidden test cases we will use to grade your MyListIterator implementation (hidden until the PA is graded). ⚠️There are hidden tests on Gradescope not described in the write-up. Make sure to write additional tests to verify your implementation's correctness! ⚠️

Your task: Implement the missing unit tests in `MyListIteratorCustomTester.java` based on the description in the Tests table below.

* Your tests will be graded by checking if they pass on a good implementation and fail on a bad implementation. If a test fails on a good implementation, then the test is written incorrectly. If a test passes on a bad implementation, it may be written incorrectly or may be not be rigorous enough (try adding more asserts).
* Some of your tests will be run on several bad implementations. You will receive 2 pts for every bad implementation your test fails (if your test also passes on the good implementation).
* DO NOT CHANGE THE TEST HEADERS!

**Note:** For MyAlgorithm, the coding problem is the same as last PA, so we do not ask you to write any tests. ⚠️ However, you should use the tests you wrote last week to verify that your solution still works by creating a **separate** tester file. You do not need to submit this tester file. We have provided the public tests from last time in `MyListIteratorPublicTester`. 

#### Tests Table: List of test cases to write and their description
<table>
  <tr>
    <td>
      <strong>Test Cases</strong>
    </td>
    <td>
      <strong>Description</strong>
    </td>
    <td>
      <strong>Point Value</strong>
    </td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testNextEnd</code>
   	</td>
   	<td>
      Call <code>next</code> when iterator is at the end of a list.
   	</td>
   	<td>3
   	</td>
  </tr>
  
  <tr>
    <td>
      <code>testPreviousStart</code>
    </td>
    <td>
      Call <code>previous</code> when iterator is at the beginning of a list.
    </td>
    <td>3
    </td>
  </tr>
  
  <tr>
    <td>
       <code>testAddInvalid</code>
    </td>
    <td>
      <code>add</code> an invalid element to the list
    </td>
    <td>2
    </td>
  </tr>
  
  <tr>
    <td>
      <code>testCantSet</code>
    </td>
    <td>
      <code>set</code> an element when <code>canRemoveOrSet</code> is <code>false</code>.
    </td>
    <td>2
    </td>
  </tr>

  <tr>
   	<td>
   	  <code>testCantRemove</code>
   	</td>
   	<td>
      <code>remove</code> an element when <code>canRemoveOrSet</code> is <code>false</code>.
   	</td>
   	<td>2
   	</td>
  </tr>

  <tr>
   	<td>
   	  <code>testHasNextEnd</code>
   	</td>
   	<td>
      Call <code>hasNext</code> when the iterator is at the end of a list.
   	</td>
   	<td>2
   	</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testHasPreviousStart</code>
   	</td>
   	<td>
      Call <code>hasPrevious</code> when the iterator is at the beginning of a list.
   	</td>
   	<td>2
   	</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testPreviousIndexStart</code>
   	</td>
   	<td>
      Call <code>previousIndex</code> when the iterator is at the beginning of a list.
   	</td>
   	<td>2
   	</td>
  </tr>
  
  <tr>
   <td>
     <code>testNextIndexEnd</code>
   </td>
   <td>
     Call <code>nextIndex</code> when the iterator is at the end of a list.
   </td>
    <td>2</td>
  </tr>

</table>

#### **How to compile and run the public tester:**

Running the tester on UNIX based systems (including Mac): 
- Compile: `javac -cp ../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar:. MyListIteratorPublicTester.java`
- Execute: `java -cp ../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MyListIteratorPublicTester`

Running the tester on Windows systems:
- Compile: `javac -cp ".;..\lib\junit-4.13.2.jar;..\lib\hamcrest-core-1.3.jar" MyListIteratorPublicTester.java`
- Execute: `java -cp ".;..\lib\junit-4.13.2.jar;..\lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MyListIteratorPublicTester`

To run the custom tester, replace references to `MyListIteratorPublicTester` with `MyListIteratorCustomTester` in the above commands. If you would like to compile all files at once, replace `MyListIteratorPublicTester.java` with `*.java`.

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

⚠️**Your code must first compile in order to receive credit for the different test cases. You will receive a zero if your code doesn’t compile.** ⚠️

### Part 2: Implementation of ListIterator (65 points)

#### Overview:

It is important to thoroughly test out your `MyLinkedList` implementation before working on this, as a buggy `MyLinkedList` implementation will give you a hard time testing your ListIterator. On Gradescope, the **PA3 Tester** assignment is currently open and all `MyLinkedList` test cases are visible. This is not for credit so test away! 

You should also import `java.util.NoSuchElementException`, `java.util.ListIterator`, and `java.util.Iterator` apart from the imports you used in PA3.

Inside MyLinkedList.java, create a class named `MyListIterator` that implements the [`ListIterator`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ListIterator.html) interface as an inner class (contained inside the definition of the `MyLinkedList` class). This is the approach you should use to create your `MyListIterator`.

``` java
public class MyLinkedList<E> extends AbstractList<E> {
    // class variables

    // Node inner class

    // MyLinkedList methods

    protected class MyListIterator implements ListIterator<E> {

        // class variables here

        // MyListIterator methods
        public boolean hasNext() {
            // your code here
        }
        // more methods, etc.
    }
}
```

The `MyListIterator` is able to traverse the list by moving a space at a time in either direction. The `MyListIterator` can also add, set, and remove elements in the list. However, unlike the add, set, and remove implemented in PA3 for `MyLinkedList` that can directly use a specific index, the `MyListIterator` must first move positions (using next and previous) to modify the desired index in add, set, and remove.

#### **Public methods to add to MyLinkedList.java**

Once you are sure your iterator is working, you should add the following methods in MyLinkedList to override the AbstractList implementations. Each of these should just create a new `MyListIterator` and return it.
**Note:** Make sure you are adding these methods to `MyLinkedList.java` and not `MyListIterator.java`

``` java
public ListIterator<E> listIterator() {
    return new MyListIterator();
}

public Iterator<E> iterator() {
    return new MyListIterator();
}
```

#### **MyListIterator Implementation:**

* Your methods should properly throw `IllegalStateException`, `NoSuchElementException`, and `NullPointerException` exceptions. This means you DO NOT HAVE TO IMPLEMENT throwing ALL exceptions as the official Javadoc would indicate. Please refer to the table below. Note that our test cases do not check for the exception message, just that the correct exception was thrown. 
* You should use the getters and setters defined in the Node class to access and modify Node objects.  Do not modify or access the Node’s member variables directly.
* Do not use MyLinkedList methods to write iterator methods. If you use a MyLinkedList method in an iterator method, you will get a 0 for that method. We will use our MyLinkedList class when running test cases for MyListIterator, and it will throw an exception if a MyLinkedList method is used. However, feel free to use the Node methods.
* Do not define any helper methods in the MyLinkedList class. If you have helper methods, they should go in the inner class MyListIterator.
* DO NOT import and use Java’s built-in LinkedList. **If we see that you _import java.util.LinkedList;_ you will not receive any credit! ⚠️**
* Do not make any instance variables private, they should have the default access modifier.
* You may add private static final variables to be used as constants, but do not add any other instance variables and do not add any static variables.


#### Instance Variable Descriptions
<table>
  <tr>
  	<td>
  	  <strong>Instance Variable</strong>
  	</td>
  	<td>
      <strong>Description</strong>
  	</td>
  </tr>
  
  <tr>
   	<td>
      <code>Node left,right</code>
   	</td>
   	<td>
      Two node references to determine the iterator location.<br>It is useful to have a number of state fields to simplify the writing of the methods below. Since the cursor of the ListIterator exists logically between 2 nodes, it is useful to just keep references to the next Node and the previous Node.
   	</td>
  </tr>

  <tr>
   	<td>
      <code>int idx</code>
   	</td>
   	<td>
      Int value of the index of the next node.
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>boolean forward</code>
   	</td>
   	<td>
      Determine the current moving direction of the iterator. <code>forward</code> is <code>true</code> after calling <code>next()</code>, <code>false</code> after calling <code>previous()</code>. <code>forward</code> can be either true or false after initialization (because it is only used for <code>remove</code> and <code>set</code>), however in our examples it is initially true.
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>boolean canRemoveOrSet</code>
   	</td>
   	<td>
      Helper boolean variable to keep track of whether the current state of the iterator allows remove or set operation. It should be false initially.<br>True after calling <code>next</code> or <code>previous</code>. False after calling <code>add</code> or <code>remove</code>.
   	</td>
  </tr>
</table>


#### Method Descriptions
<table>
  <tr>
   	<td>
      <strong>Method Name</strong>
   	</td>
   	<td>
      <strong>Description</strong>
   	</td>
   	<td>
      <strong>Exceptions to Throw</strong>
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public MyListIterator()</code>
   	</td>
   	<td>
      Constructor that is used to initialize the iterator.
   	</td>
   	<td>
      None
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public boolean hasNext()</code>
   	</td>
   	<td>
      Return true if there is an element node when going in the forward (head to tail) direction from the current iterator position. Sentinel (dummy) nodes do not count as element nodes.
   	</td>
   	<td>
      None
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public E next()</code>
   	</td>
   	<td>
      Return the next element in the list when going forward, and move the iterator forward by one node.
   	</td>
   	<td>
      Throw a <code>NoSuchElementException</code> if there is no such element.
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public boolean hasPrevious()</code>
   	</td>
   	<td>
      Return true if there is an element node when going in the backward (tail to head) direction from the current iterator position. Sentinel (dummy) nodes do not count as element nodes.
   	</td>
   	<td>
      None
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public E previous()</code>
   	</td>
   	<td>
      Return the next element in the list when going backward, and move the iterator backward by one node.
   	</td>
   	<td>
      Throw a <code>NoSuchElementException</code> if there is no such element.
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public int nextIndex()</code>
   	</td>
   	<td>
      Return the index of the element that would be returned by a call to <code>next()</code>.<br>Return the list size if at the end of the list.
   	</td>
   	<td>
      None
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public int previousIndex()</code>
   	</td>
   	<td>
      Return the index of the element that would be returned by a call to <code>previous()</code>.<br>Return -1 if at the start of the list.
   	</td>
   	<td>
      None
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public void add(E element)</code>
   	</td>
   	<td>
      Insert the given item into the list immediately before the element that would be returned by <code>next()</code>.<br>If we call <code>previous()</code> immediately following add, the newly added item would be returned.<br>The value of the current index of the list iterator is increased by one.
   	</td>
   	<td>
      Throw a <code>NullPointerException</code> if <code>element</code> is null.
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public void set(E element)</code>
   	</td>
   	<td>
      For the node returned by the most recent <code>next</code>/<code>previous</code> call, replace its value with the new value <code>element</code>.
   	</td>
   	<td>
      Throw a <code>NullPointerException</code> if <code>element</code> is null.<br>Throw an <code>IllegalStateException</code> if neither <code>next</code> nor <code>previous</code> were called, or if <code>add</code> or <code>remove</code> have been called since the most recent <code>next</code>/<code>previous</code> call.
   	</td>
  </tr>
  
  <tr>
   	<td>
      <code>public void remove()</code>
   	</td>
   	<td>
      Remove the last element node returned by the most recent <code>next</code>/<code>previous</code> call.
   	</td>
   	<td>
      Throw an <code>IllegalStateException</code> if neither <code>next</code> nor <code>previous</code> were called, or if <code>add</code> or <code>remove</code> have been called since the most recent <code>next</code>/<code>previous</code> call.
   	</td>
  </tr>

</table>

Examples for the MyListIterator Methods can be found in the [Appendix](#Appendix).

### Part 3: MyAlgorithm Implementation (10 points)

In this part of the assignment, you will use `MyListIterator` to solve a "challenge" programming problem. The goal of this section is to give you basic familiarity with the advantages/behavior of the data structure(s) you have just implemented. You will see that this problem is the exact same as last week; however, with two **important restrictions**. 

* This time, you are **not** allowed to use any `MyLinkedList` methods (yes, even `size()`), `MyLinkedList` variables, or `MyListIterator` variables. The only exceptions are `listIterator()`, `iterator()` and the `MyLinkedList` constructor. Therefore, you should use your new iterator instead.
* In order to get credit, **your solution must run in O(n) time**. Please start early on this, as the autograder can become very slow near the deadline. If you submit last-minute, it is possible that our test times out even if your solution is O(n). Reach out over Piazza if this becomes an issue. 
   * **Hint:** Think about how you would loop over a `LinkedList` with and without an iterator. What are the time complexities of both? (think Big-O notation)

For clarity, here is the method description and examples from before.

|Method Name|Description|Exceptions to Throw|
|-----------|-----------|-------------------|
|`public static <E extends Comparable<E>> MyLinkedList<E> mergeLists(MyLinkedList<E> left, MyLinkedList<E> right)`|You are given two lists sorted in ascending order, `left` and `right`. Return a new sorted `MyLinkedList` which contains all the elements of both lists. There may be duplicates, and the list sizes may not be equal. For this problem, follow the restrictions mentioned above; **otherwise, you will receive 0 points for this part**. Feel free to use any of the `MyListIterator` methods. |Throw a `NullPointerException` when `left` or `right` is null.| 

**Note:** if you would like to reuse or write additional tests for this part, write them in a different file so `MyAlgorithm` isn't accidentally flagged wrong by the autograder.

#### Examples
<pre>
// Example 1
left     ->   <1>  <-> <2> <-> <3>
right    ->   <4>  <-> <5> <-> <6>
                                            // Sorted merged list 
MyAlgorithm.mergeLists(left, right)    ->   <1> <-> <2> <-> <3> <-> <4> <-> <5> <-> <6>

// Example 2
left     ->   <1> <-> <2> <-> <2>
right    ->   <2> <-> <3> <-> <4>
                                            // Sorted merged list, duplicates kept
MyAlgorithm.mergeLists(left, right)    ->   <1> <-> <2> <-> <2> <-> <2> <-> <3> <-> <4>
</pre>

### Part 4: Coding Style (5 points)

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
* Javadoc conventions (`@param`, `@return` tags, `/** comments */`, etc.)


A full style guide can be found [here](https://github.com/CaoAssignments/guides/blob/main/style.md) and a sample styled file can be found [here](https://github.com/CaoAssignments/guides/blob/main/resources/SampleFile.java). If you need any clarifications, feel free to ask on Piazza.
    
## Appendix

### **Example for MyListIterator Methods**

It might be helpful to consider that the iterator has (size+1) positions in the list: just after the sentinel head node (i.e. just before the first node containing data), between the 0 and 1 index, ..., just before the sentinel tail node (i.e., just after the last node containing data).

As seen in the figure below, the iterator has 4 positions when the size of the MyLinkedList is 3. Green numbers indicate the indices for MyLinkedList, while blue numbers indicate the indices for MyListIterator.

Suppose we already have a MyLinkedList with 3 nodes:  \
`head <-> c <-> s <-> e <-> tail`

**MyListIterator should look like this after initialization:**
![](https://i.imgur.com/yk221Kz.png)

After calling the constructor, `idx` should be 0, `forward` can be true or false (here we set it to true), `canRemoveOrSet` should be false, `left` should be pointing to the sentinel head node, and `right` should be pointing to the sentinel head node’s next node.

**After calling `next()`:**
![](https://i.imgur.com/voY82cZ.png)

“c” should be returned. Since we just called `next()`, `canRemoveOrSet` is set to `true `and `forward` is set to `true`.

**After calling `add("a")`:**
![](https://i.imgur.com/8jDBwYJ.png)

`MyLinkedList` `size` as well as `MyListIterator` `idx` increments, `canRemoveOrSet` changes to `false`. \
If `next()` is called now, “s” should be returned. If `remove()` is called now, an `IllegalStateException` should be thrown since `canRemoveOrSet` is `false`.

**After calling `previous()`:**
![](https://i.imgur.com/H1ARXSB.png)

“a” is returned by the iterator. `forward` is set to `false` since the iterator is going in the reverse direction now. `idx` decrements, `canRemoveOrSet` is again `true`.

**After calling `remove()`:**
![](https://i.imgur.com/TmQKhDl.png)

`MyLinkedList` `size` decrements. “a” is removed from the linked list. `canRemoveOrSet` is set to `false`.
