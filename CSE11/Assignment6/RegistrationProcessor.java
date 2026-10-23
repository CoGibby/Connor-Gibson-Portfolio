///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;
/**
 * This processes a registration to make sure it 
 * is valid
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class RegistrationProcessor {

    private ArrayList<Registration> registrationList;
    public static final double EPSILON = 1e-9;

    private static final int EXPERIENCE_INCREASE_FLAT = 10;
    private static final double MIN_INCREASE_RATE = 1.0;
    /**
     * A no arg constructor for registration processor
     */
    public RegistrationProcessor() {
        registrationList = new ArrayList<Registration>();
    }

    /**
     * Adds a registration to the registration list
     *
     * @param registration The passed in registration to add to the list
     */
    public void addToRegistrationList(Registration registration) {
        registrationList.add(registration);
    }

    /**
     * Adds multiple registrations to registration list
     *
     * @param registrations An array of registrations
     */
    public void addToRegistrationList(Registration[] registrations) {
        for (int i = 0; i < registrations.length; i++) {
            registrationList.add(registrations[i]);
        }
    }

    /**
     * Validates if a given registration name is in the list
     *
     * @param registrationName The passed in registration name to validate
     * @return Boolean representing if it is in the list or not
     */
    public boolean hasRegistration(String registrationName) {
        for (int i = 0; i < registrationList.size(); i++) {
            if (registrationList.get(i).getRegistrationName()
                .equals(registrationName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a registration, given it is in the list
     *
     * @param registrationName The name to remove from the list
     * @return The registration removed
     */
    public Registration getAndRemoveRegistration(
        String registrationName
    ) {
        for (int i = 0; i < registrationList.size(); i++) {
            Registration r = registrationList.get(i);

            if (r.getRegistrationName().equals(registrationName)) {
                registrationList.remove(i);
                return r;
            }
        }
        return null;
    }

    /**
     * Compares the powers of two registrations, using class Registration
     *
     * @param registrationOne First registration passed in
     * @param registrationTwo Second registration passsed in
     * @return An integer representing how the powers compare
     */
    public static int comparePower(
        Registration registrationOne,
        Registration registrationTwo
    ) {
        double power1 = registrationOne.getPower();
        double power2 = registrationTwo.getPower();
        boolean equal = Math.abs(power1 - power2) < EPSILON;

        if (equal) {
            return 0;
        }
        if (power1 < power2) {
            return -1;
        }
        return 1;
    }

    /**
     * Increases the experience level of a random registration in the list
     *
     * @param increaseRate The rate of which experience will be increased
     * @return An integer representing the index that changed
     */
    public int applyExperienceIncrease(double increaseRate) {

        if (registrationList.isEmpty()) {
            return -1;
        }

        int randomness = (int) (Math.random() * registrationList.size());
        Registration r = registrationList.get(randomness);

        double current = r.getExperienceLevel();

        if (increaseRate > MIN_INCREASE_RATE) {
            r.setExperienceLevel(current * increaseRate);
        }
        else if (Math.abs(increaseRate - MIN_INCREASE_RATE) < EPSILON) {
            r.setExperienceLevel(current + EXPERIENCE_INCREASE_FLAT);
        }

        return randomness;
    }



    /**
     * Makes sure the registration passes validation check
     *
     * @param registration The passed in registration to check
     * @return A boolean representing if it is valid or not
     */
    public static boolean processRegistration(
        Registration registration
    ) {
        if (registration == null) {
            return false;
        }
        if (registration.isValidRegistration()) {
            return true;
        }
        return false;
    }

    /**
     * Simple getter for registrationList
     *
     * @return The registration list
     */
    public ArrayList<Registration> getRegistrationList() {
        return registrationList;
    }

    /**
     * Prints the registration list
     */
    public void printRegistrationList() {
        System.out.println("[");
        for (int i = 0; i < registrationList.size(); i++) {
            System.out.print("  " + registrationList.get(i));
            System.out.println(",");
        }
        System.out.println("]");
    }
}