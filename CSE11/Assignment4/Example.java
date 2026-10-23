public abstract class Pieces{
    protected int x, y;
    
    // Step 1. Declare the abstract boolean move(int dx, int dy) method here.
    abstract boolean move(int dx, int dy);
    // The class should take in the change in x (dx) and change in y (dy).
    // Since the method is abstract, you should only need to write one line.
    // CHECKPOINT 1: After Step 1, you should pass testMoveIsAbstract() test.
    // hint: you can still move to next steps if you can compile, but failed this test.
    
    // Step 2a. Implement the Pieces constructor here.
    // It should intialize x and y to be 1.
    // [Strongly suggested] jump to Step 2b. to implement the Rook constructor.
    Pieces () {
        x = 1;
        y = 1;
    }
    
    // Step 3. Implement checkRange here.
    // this method takes whether or not a position is legal, i.e. inside the board.
    // Note: Chess board is 8x8, so x and y may span from 1 to 8
    // Returns true if the piece remains inside the board, false otherwise.
    // CHECKPOINT 3: After Steps 2abc and 3, you should pass the testCheckRange() test.
    // You should move on to Step 4.
    static boolean checkRange(int x, int y){
        if (x >= 1 && x < 9){
            if (y >= 1 && y < 9){
                return true;
            }
        }
        return false;
    }
    
}


class Rook extends Pieces {
    
    // Step 2b. Implement the Rook default constructor here.
    // It should work exactly the same as the Pieces constructor.
    // [Strongly suggested] move on to Step 2c. to implement the parameterized constructor.
    Rook () {
        super();
    }
    
    // Step 2c. Implement the Rook parameterized constructor here.
    // It should take in a coordinate x and y
    // CHECKPOINT 2: After Steps 2abc, you should pass the testConstructor() test.
    // You should jump back to Pieces class for Step 3.
    Rook (int x, int y) {
        this.x = x;
        this.y =y;
    }
    
    
    // Step 4. Implement the concrete move method for Rook here.
    // This method takes in the change in x (dx) and change in y (dy) and checks if it is a valid move.
    // A valid move is one where at most one of dx or dy is nonzero, and after the move the piece is still inside the board.
    // The function returns true and changes the position of the piece if the move is valid.
    // The function returns false and keep the original positions if the move is invalid.
    // CHECKPOINT 4: You should pass all tests upon successful implementation.
    boolean move(int dx, int dy){
        

        // Rook must move in a straight line
        boolean straightMove = (dx == 0 && dy != 0) || (dy == 0 && dx != 0);
        if (!straightMove) {
            return false;
        }

        int newX = x + dx;
        int newY = y + dy;

        // Must stay on the board
        if (!checkRange(newX, newY)) {
            return false;
        }

        // Valid move → update position
        x = newX;
        y = newY;
        return true;
    }
}
