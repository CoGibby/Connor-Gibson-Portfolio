///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

/**
 * This will provide tests for each of our classes
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class Assignment6 {
    /**
    * This tests all the classes
    *
    * @return A boolean value representing if 
    * the tests passed or failed
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT REMOVE THIS !!!
    public static boolean unitTests() {
        RegistrationProcessor proc = new RegistrationProcessor();

        Registration[] registrationsToAdd = {
            new LeagueRegistration("Dragonite", 3.0,
            4444555566667777L, 5, 1,
            "Kanto", 100),
            new BeginnerRegistration("Squirtle", 4.77,
            4444555566667777L, 200, 25)
        };
        proc.addToRegistrationList(registrationsToAdd);

        // TEST CASE 1: applyExperienceIncrease
        double[] originalExp = new double[proc.getRegistrationList().size()];
        for (int i = 0; i < proc.getRegistrationList().size(); i++) {
            originalExp[i]
                    = proc.getRegistrationList().get(i).getExperienceLevel();
        }

        double increaseRate = 1.5;
        int increaseIndex = proc.applyExperienceIncrease(increaseRate);

        for (int i = 0; i < proc.getRegistrationList().size(); i++) {
            Registration registration = proc.getRegistrationList().get(i);
            if (i != increaseIndex || increaseRate < 1) {
                if (registration.getExperienceLevel() != originalExp[i]) {
                    System.out.println(
                            "applyExperienceIncrease 1 - Registration "
                            + "unexpectedly changed at index: " + i);
                    System.out.println(proc.getRegistrationList());
                    return false;
                }
            } else {
                double actualExp = originalExp[i] * increaseRate;
                if (registration.getExperienceLevel() != actualExp) {
                    System.out.println(
                            "applyExperienceIncrease 1 - Registration "
                            + "does not have expected increased experience");
                    System.out.println(registration.getExperienceLevel());
                    System.out.println(actualExp);
                    System.out.println(proc.getRegistrationList());
                    return false;
                }
            }
        }
        // TEST CASE 2: comparePower

        Registration a = new BeginnerRegistration("A", 5.0, 1L, 0, 0);
        Registration b = new BeginnerRegistration("B", 5.0, 2L, 0, 0);
        Registration c = new BeginnerRegistration("C", 10.0, 3L, 0, 0);
        if (RegistrationProcessor.comparePower(a, b) != 0) {
            System.out.println("comparePower - expected equal power");
            return false;
        }

        if (RegistrationProcessor.comparePower(a, c) != -1) {
            System.out.println("comparePower - expected a < c");
            return false;
        }

        if (RegistrationProcessor.comparePower(c, a) != 1) {
            System.out.println("comparePower - expected c > a");
            return false;
        }
        // TEST CASE 3: processRegistration

        Registration begValid = 
            new BeginnerRegistration("B1", 5.0, 1L, 900, 20);
        Registration begInvalid = 
            new BeginnerRegistration("B2", 5.0, 1L, 900, 5);

        if (!RegistrationProcessor.processRegistration(begValid)) {
            System.out.println("processRegistration - " 
                + "beginner should be valid");
            return false;
        }
        if (RegistrationProcessor.processRegistration(begInvalid)) {
            System.out.println("processRegistration - "
                + "beginner should be invalid");
            return false;
        }

        Registration compValid = new CompetitiveRegistration(
                "C1", 5.0, 1L, 100, 20, 5, 20, 10);
        Registration compInvalid = new CompetitiveRegistration(
                "C2", 5.0, 1L, 100, 20, 1, 1, 1);

        if (!RegistrationProcessor.processRegistration(compValid)) {
            System.out.println("processRegistration - "
                + "competitive should be valid");
            return false;
        }
        if (RegistrationProcessor.processRegistration(compInvalid)) {
            System.out.println("processRegistration - "
                + "competitive should be invalid");
            return false;
        }

        Registration leagueValid = new LeagueRegistration(
                "L1", 5.0, 1L, 5, 1, "Kanto", 50);
        Registration leagueInvalid = new LeagueRegistration(
                "L2", 5.0, 1L, 5, 1, "Kanto", 500);

        if (!RegistrationProcessor.processRegistration(leagueValid)) {
            System.out.println("processRegistration - league should be valid");
            return false;
        }
        if (RegistrationProcessor.processRegistration(leagueInvalid)) {
            System.out.println("processRegistration - "
                + "league should be invalid");
            return false;
        }  
        return true;
    }
    /**
    * Main method for unit tests
    *
    * @param args The argument passed in
    */
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("Failed test.\n");
        }
    }
}
