import org.junit.*;
import static org.junit.Assert.*;
import java.lang.Comparable;

public class DemoArrayImplTester {

	DemoArrayImpl<Integer> intArray;
	DemoArrayImpl<Character> charArray;
	DemoArrayImpl<Course> courseArray;
	DemoArrayImpl<String> emptyArray;
	DemoArrayImpl<String> nullArray;

	Course[] cseCourses = {new Course(12, "Data Structure"), new Course(11, "Intro to OOP")};

	@Before
	public void setUp(){
		intArray =  new DemoArrayImpl<>(new Integer[]{7, 3, 16, 2, 5});

		charArray =  new DemoArrayImpl<>(new Character[]{'r', 'b', 'f', 'p', 'k', 'h'});

		courseArray = new DemoArrayImpl<>(cseCourses);

		emptyArray = new DemoArrayImpl<>(new String[0]);

		nullArray = new DemoArrayImpl<>(null);
	}


	@Test
	public void testNullArray(){
		assertNull(nullArray.min());
		assertNull(nullArray.get(0));
	}

	@Test
	public void TestEmptyArray(){
		assertNull(emptyArray.min());
		assertNull(emptyArray.get(0));
	}

	// TODO1: Testing min method
	@Test
	public void testMin(){
		assertNull()
	}


	// TODO2: Testing get method with valid index
	@Test
	public void testGetValid(){
	}
    
}

class Course implements Comparable<Course>{
	int id;
	String name;
	public Course(int id, String name){
		this.id = id;
		this.name = name;
	}

	@Override
	public int compareTo(Course o) {
		return this.id - o.id; 
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(obj.getClass() != this.getClass()) return false;
		Course other = (Course) obj;
		if(other.id == this.id && other.name.equals(this.name)){
			return true;
		}
		return false;
	}
}