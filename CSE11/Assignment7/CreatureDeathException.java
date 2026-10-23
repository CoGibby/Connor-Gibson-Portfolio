///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * Models a crowberry plant with weekly mass growth and capped health
 * recovery
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class CreatureDeathException extends Exception {

    /**
     * Creates a new creature death exception with the given message
     *
     * @param message Description of the death event
     */
    public CreatureDeathException(String message) {
        super(message);
    }
}