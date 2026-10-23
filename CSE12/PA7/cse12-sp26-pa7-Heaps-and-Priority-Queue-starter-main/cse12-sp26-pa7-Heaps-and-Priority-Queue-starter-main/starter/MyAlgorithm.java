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
import java.util.ArrayList;

/**
 * A utility class containing algorithms that operate an atom list
 */
public class MyAlgorithm {
    

    /**
     * Runs a sequence of reactions on a list of atoms until only one atom remains
     * in each reaction, the two atoms with the smallest types are removed:
     * If the two atoms have the same type, they combine into a new atom whose
     * type is the sum of the two.
     * If the two atoms have different types, they combine into a new atom whose
     * type is the absolute difference of the two
     * The process continues until only one atom remains
     *
     * @param atoms the list of atom types to process; must not be null
     * @return the type of the final remaining atom
     */
    public static Integer lastAtom(ArrayList<Integer> atoms){
        if (atoms == null){
            throw new NullPointerException();
        }
        MyPriorityQueue<Integer> atomsList = new MyPriorityQueue<>(atoms);
        while (atomsList.getLength() > 1) {
            int atom1 = atomsList.pop();
            int atom2 = atomsList.pop();

            int newAtom;
            if (atom1 == atom2) {
                newAtom = atom1 + atom2;
            } else {
                newAtom = Math.abs(atom1 - atom2);
            }
            atomsList.push(newAtom);
        }

        return atomsList.peek();
    }
}
