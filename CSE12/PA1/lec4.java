public class lec4 {
    public class MyArrayList<E>{
        public Object[] data;
        private static final int DEFAULT_CAPACITY = 5;
        private int size;
        public MyArrayList (){
            //Initialize data to an array of Objects of size DEFAULT_CAPACITY
            this.data = new Object[DEFAULT_CAPACITY];    //data.length is the capacity
            this.size = 0;
        }

        public void append(E elem){
            if (size < data.length){
                data[size] = elem;
            } else {
                this.data = newObject[DAFAULT_CAPACITY * 2];
                data[size] = elem;
            }
        }

        /** Return the element at the specified index */
        public E get(int index) {
            if (index < 0 || index >= size){ 
                throw new ArrayIndexOutOfBoundsException();
            }
            E element = (E) data[index];
            return element;
        }

        /**
        * Remove and return the first element in the list
        * @return the first element in the array list
        */
        public E removeFirst() {    
            if (data == null || size <= 0){
                throw new Exception();
            }
            E firstElem = (E) data[0];
            Object[] newData = new Object[data.length];
            for (int i = 1; i < size; i++){
                newData[i - 1] = data[i];
            }
            data = newData;
            size--;
            return firstElem;
        }

        /**
        * Return a string representation of this arraylist.
        */
        public String toString(){
            // TODO: This method does not work. Fix it so that it does.
            String toReturn = new String("[");
            for (int i = 0; i < size; i++) {
                toReturn += this.data[i] + ", ";
            }
            if (size > 0) {
                toReturn += this.data[size];
            }
            toReturn += "]";
            return toReturn;
        }
        
}
