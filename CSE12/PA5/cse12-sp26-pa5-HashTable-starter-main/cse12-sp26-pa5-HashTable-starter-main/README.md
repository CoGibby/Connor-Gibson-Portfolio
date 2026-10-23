# CSE 12 PA5: Hash Tables

**Due date: Thursday, May 7th @ 11:59 PM PST**

There is an FAQ post on Piazza. Please read that post first if you have any questions.


## **Learning goals:**

* Implement data structures similar to Java’s HashMap and HashSet with generic types
* Write JUnit tests to verify proper implementation
* Understand how to perform complexity analysis in non-trivial situations

In this PA, you will implement a `HashSet` and `HashMap` as well as write JUnit tests to ensure that your implementation functions correctly. Additionally, you will apply your new data structures to a coding problem.

Begin by downloading the starter code and put it in a directory in your working environment.

Inside, you will find the following files:

```
+-- PA5
|   +-- MyHashMap.java       Edit this file
|   +-- MyHashSet.java       Edit this file
|   +-- MyAlgorithm.java     Edit this file
|   +-- PublicTester.java
|   +-- CustomTester.java    Edit this file
|   +-- submission.txt       Edit this file (for task 2)
```

## Assignment Breakdown [100 points]

* **Task 1** [95 points] You will earn points based on the autograder tests that your code passes. If the autograder tests are not able to run (e.g., your code does not compile or it does not match the specifications in this writeup), you may not earn credit.
    * Tester [20 points]
        * The autograder will test your implementation of the JUnit tests. Your unit tests are expected to fail or pass based on the tests above.
        * This section has a maximum of 20 pts.
    * HashMap/HashSet Implementation [60 points]
        * The autograder will test your implementation on the public test cases given in `PublicTester.java` and hidden test cases not described in this PA writeup.
    * **MyAlgorithm** (10 points)
        * The autograder will test your implementation of `MyAlgorithm` on the public test cases given in `PublicTester.java` and hidden test cases not described in this PA writeup.
    * **Coding Style** [5 points]
        * `MyHashMap.java`, `MyHashSet.java`, and `MyAlgorithm.java` will be graded on style. `CustomTester.java` will be graded on file, class, method headers and indentation.
* **Task 2** [5 points]
    * Fill out **PA5 Task 2** on Gradescope.

## Submission Instructions

#### Turning in your code

* Submit all of the following files to Gradescope
    * `MyHashMap.java`
    * `MyHashSet.java`
    * `MyAlgorithm.java`
    * `CustomTester.java`

#### Completing Task 2

* Fill out and submit the **PA5 Task 2** form on Gradescope.

**Important**: Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.

**Read the entire write-up before you start coding.**

## Task 1: Testing, Implementation, and Application of HashMap and HashSet [95 points]

### Part 1: JUnit Testing (20 points)

We provide two test files:

* `PublicTester.java`
    * This contains all the public test cases we will use to grade your MyHashMap and HashSet implementations (visible on Gradescope).
* `CustomTester.java`
    * Contains some of the headers and description to the hidden test cases we will use to grade your MyHashMap and MyHashSet implementations (hidden until the PA is graded). ⚠️There are hidden tests on Gradescope not described in the write-up. Make sure to write additional tests to verify your implementation's correctness! ⚠️

Your task: Implement the missing unit tests in `CustomTester.java` based on the description in the Tests table below.

* Your tests will be graded by checking if they pass on a good implementation and fail on a bad implementation. If a test fails on a good implementation, then the test is written incorrectly. If a test passes on a bad implementation, it may be written incorrectly or may be not be rigorous enough (try adding more asserts).
* Some of your tests will be run on several bad implementations. You will receive 2 pts for every bad implementation your test fails (if your test also passes on the good implementation).
* DO NOT CHANGE THE TEST HEADERS!


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
   	  <code>testMyHashMapConstructorInvalidCapacity</code>
   	</td>
   	<td>
        Call <code>MyHashMap</code> constructor in MyHashMap with an invalid value for the initial capacity.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashMapGetNullKey</code>
   	</td>
   	<td>
        Call <code>get</code> in MyHashMap with a null value for the key.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashMapGetKeyDoesNotExist</code>
   	</td>
   	<td>
        Call <code>get</code> in MyHashMap with a key that does not exist in the hash table.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
    <td>
      <code>testMyHashMapPutNullKey</code>
    </td>
    <td>
      Call <code>put</code> in MyHashMap with a null value for the key.
    </td>
    <td>2</td>
  </tr>

  <tr>
    <td>
      <code>testMyHashMapPutKeyAlreadyExists</code>
    </td>
    <td>
      Call <code>put</code> in MyHashMap with a key that already exists in the hash table.
    </td>
    <td>2</td>
  </tr>

  <tr>
    <td>
       <code>testMyHashMapRemoveNullKey</code>
    </td>
    <td>
      Call <code>remove</code> in MyHashMap with a null value for the key.
    </td>
    <td>2</td>
  </tr>
  
  <tr>
    <td>
       <code>testMyHashMapRemoveKeyDoesNotExist</code>
    </td>
    <td>
      Call <code>remove</code> in MyHashMap with a key that does not exist in the hash table.
    </td>
    <td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashMapGetHashNullKey</code>
   	</td>
   	<td>
        Call <code>getHash</code> in MyHashMap with a null value for the key.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashSetAddAlreadyExists</code>
   	</td>
   	<td>
        Call <code>add</code> in MyHashSet with an element that already exists in the set.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashSetRemoveDoesNotExist</code>
   	</td>
   	<td>
        Call <code>remove</code> in MyHashSet with an element that does not exist in the set.
   	</td>
   	<td>2</td>
  </tr>

</table>

#### **How to compile and run the public tester:**

Running the tester on UNIX based systems (including Mac): 
- Compile: `javac -cp ../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar:. PublicTester.java`
- Execute: `java -cp ../lib/junit-4.13.2.jar:../lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore PublicTester`

Running the tester on Windows systems:
- Compile: `javac -cp ".;..\lib\junit-4.13.2.jar;..\lib\hamcrest-core-1.3.jar" PublicTester.java`
- Execute: `java -cp ".;..\lib\junit-4.13.2.jar;..\lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore PublicTester`

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

⚠️**Your code must first compile in order to receive credit for the different test cases. You will receive a zero if your code doesn’t compile.** ⚠️

### Part 2: Implementation of HashMap and HashSet (60 points)

**What you will do:**

* Read through the method descriptions on the following table for the MyHashMap and MyHashSet classes. Understand well the behavior of each method.
* Implement these two classes with all of the following methods.

#### MyHashMap<K,V>

You will first complete the methods to implement the `MyHashMap` class in `MyHashMap.java`.

`MyHashMap` should use 2 generic types:
* K - the type of keys maintained by this map
* V - the type of mapped values

**Note**: `MyHashMap` objects will be used in the `MyHashSet` class.

**Note**: `MyHashMap` will **NOT** store null keys or null values.

#### Node Inner Class

In the starter code you can see that the `MyHashMap` class uses a nested class (that is, a class inside a class) to represent a node in your hash table. To accomplish this, you can’t declare a `Node` class as public, but you can include it in the same file (and even in the same class) as `MyHashMap`. This is necessary for us to store (key, value) pairs in the hash table since we will use separate chaining via a linked list (similar to PA3) for collision resolution. Please **do not use sentinel nodes** and keep in mind that this is a **singly** linked list. The node class has been implemented for you already.

#### MyHashMap<K,V>

|Instance Variable|Description|
|--- |--- |
|`Node[] hashTable`|The underlying array for the hash table.|
|`int size`|The number of (key, value) pairs currently in the hash table.|



You will be required to implement the following methods.



|Method Name|Description|Exceptions to Throw|
|--- |--- |--- |
|`public MyHashMap()`|Initialize the hash table with a default initial capacity of 5.||
|`public MyHashMap(int initialCapacity)`|Initialize the hash table with the initial capacity given.|Throw an `IllegalArgumentException` if initialCapacity is 0 or negative.|
|`public V get(K key)`|Returns the value to which the specified key is mapped, or null if this key is not in the hash map.|Throw a `NullPointerException` if key is null.|
|`public V put(K key, V value)`|Associates the specified value with the specified key in this map. At the start of this method, before attempting to add this mapping to the hash table, check if the hash table is at or above its maximum load of 80% of its capacity (i.e. if size is at least 80% of the current capacity). If so, expand the capacity to double its current capacity. Return the value previously mapped to this key or null if this key was not previously in the hash map. If hash collision happens, the node should be added to the end of the linked list.|Throw a `NullPointerException` if key is null or if value is null.|
|`public V remove(K key)`|Removes the mapping for the specified key from this map if present. Return the value previously mapped to this key or null if this key was not in the hash map. Adjust the Node linked list appropriately to reflect this removal.|Throw a `NullPointerException` if key is null.|
|`public int size()`|Return the number of (key, value) pairs in the hash map.||
|`public int getCapacity()`|Return the capacity of the hash map.||
|`public void clear()`|Removes all of the mappings from this hash map.||
|`public boolean isEmpty()`|If the map is empty, return `true`. Otherwise, return `false`.||
|`public void expandCapacity()`|Double the capacity of the hash table and rehash all (key, value) pairs into the new hash table. This method should only be called when the map is at or above its maximum load. You should rehash elements in ascending order from index 0 to index n-1 of `hashTable`. Refer to the [Appendix](#appendix) for an example of this behavior.||
|`public int getHash(K key, int capacity)`|Verify the given key and capacity are valid. Use the given hash function in the starter code to compute hash codes. You do not need to understand how it works. (This hash function ensures the hash code obtained is between 0 and capacity - 1.)|Throw a `NullPointerException` if key is null. Throw an `IllegalArgumentException` if capacity is 0 or negative.|



#### MyHashSet\<E\>

Now, you will complete the methods to implement the `MyHashSet` class in `MyHashSet.java`.

`MyHashSet` should be of generic type E.

The `MyHashSet` class is an implementation of the set abstract data type using a `MyHashMap` object. The `MyHashSet` object should have keys of type E (whatever type of element is stored in the hash set) and values of type Object, which will be used as a placeholder with a default value.

**Note**: `MyHashSet` will **NOT** store null elements.



|Instance Variable|Description|
|--- |--- |
|`public static final Object DEFAULT_OBJECT`|A default Object to use as a placeholder value in the underlying hashMap.|
|`MyHashMap hashMap`|The underlying hashMap for the hash set.|



You will be required to implement the following methods.



|Method Name|Description|Exceptions to Throw|
|--- |--- |--- |
|`public MyHashSet()`|Initialize the hash map with a default capacity of 5.||
|`public MyHashSet(int initialCapacity)`|Initialize the hash map with the initial capacity given.|Throw an `IllegalArgumentException` if initialCapacity is 0 or negative.|
|`public boolean add(E element)`|Adds the specified element to this set if it is not already present. Make sure to call MyHashMap put method in either case to ensure the value is replaced for the key if it is different than the current value and to ensure expandCapacity is called if needed. Return `true` if the set did not already contain the specified element or `false` otherwise.|Throw a `NullPointerException` if element is null.|
|`public boolean remove(E element)`|Removes the specified element from this set if it is present. Return `true` if the set contained the specified element or `false` otherwise.|Throw a `NullPointerException` if element is null.|
|`public boolean contains(E element)`|Returns true if this set contains the specified element.|Throw a `NullPointerException` if element is null.|
|`public int size()`|Returns the number of elements in this set (its cardinality).||
|`public void clear()`|Removes all of the elements from this set.||
|`public boolean isEmpty()`|Returns true if this set contains no elements.||

**NOTE**: The methods in `MyHashSet` should be relatively simple if you have implemented the methods in the `MyHashMap` class correctly.

### Part 3: MyAlgorithm Implementation (10 points)

In this part of the assignment, you will use `MyHashSet` and `MyHashMap` to solve a "challenge" programming problem. The goal of this section is to give you basic familiarity with the advantages/behavior of the data structure(s) you have just implemented. 

This time you are tasked with creating a fraud detection system for the credit card company you work at, Cao Express. You remember that your database has access to users' list of transactions, which are accompanied by a merchant ID. So, you come up with an idea to check if a merchant ID appears more than once within `certainty` steps apart. If this happens, you will flag the transactions as fraudulent. However, your boss has "kindly" recommended that you make this efficient, because that is a lot of data to check. Fortunately, your new `MyHashSet` and `MyHashMap` will come in handy!

Below is the spec you've prepared for your algorithm. To get full credit, your implementation needs to be O(n). You should use `MyHashSet` and/or `MyHashMap` to do this.

|Method Name|Description|Exceptions to Throw|
|-----------|-----------|-------------------|
|`public static boolean hasFraudulentTRs(int[] transactionIds, int certainty)`|You are given an array of integers. If there are two integers `i` and `j`, where `transactionsIds[i]==transactionIds[j]` and `i` and `j` are at most `certainty` spaces apart then return True. Otherwise, return False. You may assume `transactionIds` will be of any length and not null. You are allowed to use `Math.abs()` if it would be helpful. |If `certainty` <= 0 then throw `IllegalArgumentException`.| 

**NOTE:** If you would like to reuse or write additional tests for this part, write them in a different file so `MyAlgorithm` isn't accidentally flagged wrong by the autograder.<br>

#### Examples
<pre>
// Example 1
transactionIds  -> [1, 2, 3, 1]
certainty       -> 3

MyAlgorithm.hasFraudulentTRs(transactionIds, certainty) -> true
// Since there are multiple 1's, and the second 1 appears <b>certainty</b> spaces away from the first 1, we return true.

// Example 2
transactionIds  -> [1, 2, 3, 4, 3, 6]
certainty       -> 3

MyAlgorithm.hasFraudulentTRs(transactionIds, certainty) -> true
// Since there are multiple 3's, and the second 3 appears 2 <= <b>certainty</b> spaces away from the first 3, we return true.

// Example 3
transactionIds  -> [1, 3, 4, 1, 3, 4]
certainty       -> 2

MyAlgorithm.hasFraudulentTRs(transactionIds, certainty) -> false
// Since all numbers are more than <b>certainty</b> spaces away from their duplicate, we return false.
</pre>

### **Part 4: Coding Style (5 points)**

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


A full style guide can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/README.md) and a sample styled file can be found [here](https://github.com/CaoAssignments/guides/blob/main/resources/SampleFile.java). If you need any clarifications, feel free to ask on Piazza.

## Task 2 [5 points]

**Your task:** Fill out **PA5 Task 2** form on Gradescope. This will **NOT** be eligible for resubmission.

## Appendix

### **Example of MyHashMap Methods** 

We use an array of type `Node` as the underlying data structure. Assume all hashes are generated from a call to `getHash()`. We use separate chaining with a linked list for collisions (note that this is a singly linked list with no sentinel nodes, which is different from PA3).

**MyHashMap should look like after initialization:**

![ht init](https://i.imgur.com/NRVNJtJ.png)

After calling the constructor, `size` should be 0. All of the indices of our array should be `null`.

**After calling `put("A", 1)`:**

![ht put A](https://i.imgur.com/5456BgL.png)

`null` should be returned. `size` is 1.

**After calling `put("G", 7)`:**

![ht put G](https://i.imgur.com/M310TOB.png)

`null` should be returned. `size` is 2.

**After calling `put("B", 2)`:**

![ht put B](https://i.imgur.com/rHZUspO.png)


`null` should be returned. `size` is 3.

**After calling `remove("G")`:**

![ht remove G](https://i.imgur.com/sKVCTnh.png)


`7` should be returned. `size` is 2.

**After calling `put("F", 6)`:**

![ht put F](https://i.imgur.com/2CeDOnb.png)

`null` should be returned. `size` is 3.

**After calling `put("A", 2)`:**

![ht put A again](https://i.imgur.com/68UgfhG.png)

The value at node A is replaced. `1` should be returned. `size` is 3.

**After manually calling `expandCapacity()`:**

![ht expandCapacity](https://i.imgur.com/EnsoeXy.png)

All nodes, including nodes in the same chain, are re-hashed.
