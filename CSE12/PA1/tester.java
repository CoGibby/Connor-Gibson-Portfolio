public class tester {
    @Test
    public void testDefaultCtor() {
        Person ref = new Person();
        assertEquals(0, ref.age);
        assertEquals(null, ref.name);
        assertEquals(null, ref.height);

    }

    @Test
    public void testCtor1(){
        boolean exception = false; 
        try {
            Person ref = new Person(-15, "cse12", "5'11")
        }
        catch (Exception e){
            exception = true;
        }
        assertEquals("Wrong parameters!", true, exception);
    }

    @Test 
    public void testCtor3() {
        Person ref = new Person(20, "cse12", "100'55");

    }

    @Test 
    public void testGetNameLength(){
        boolean exception = false; 
        try {
            Person ref = new Person(20, "cse", "5'03");
            ref.name = null;
            ref.setNameLength();
        }
        catch (Exception e) {
            exception = true; 

        }
        assertEquals(message, expect, actual)
        //aka assertEquals("Check getNameLength for exception", true, exception)

    }
    
}