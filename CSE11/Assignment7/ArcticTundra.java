///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Represents an Arctic tundra ecosystem and manages all creatures,
 * reproduction, predation, and weekly simulation updates
 *
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class ArcticTundra {

    private ArrayList<Creature> allCreatures;
    private int currentWeek;
    private Random rand;

    private static final double MAX_LOC_X = 5000.0;
    private static final double MAX_LOC_Y = 5000.0;
    private static final double FOX_STANDARD_MASS = 3000.0;
    private static final double LEMMING_STANDARD_MASS = 50.0;
    private static final double CROWBERRY_STANDARD_MASS = 1000.0;
    private static final double WILLOW_STANDARD_MASS = 80.0;
    private static final double TEN = 10.0;
    private static final int TWO = 2;



    /**
     * Returns the current week of the simulation
     *
     * @return Current week number
     */
    public int getCurrentWeek() {
        return currentWeek;
    }

    /**
     * Sets the current week of the simulation
     *
     * @param currentWeek New week value
     */
    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    /**
     * Creates an empty tundra with no creatures
     */
    public ArcticTundra() {
        allCreatures = new ArrayList<Creature>();
        currentWeek = 0;
        rand = new Random();
    }

    /**
     * Creates a tundra populated with the given number of each creature type
     *
     * @param numFox Number of foxes
     * @param numLemming Number of lemmings
     * @param numCrowberry Number of crowberries
     * @param numWillow Number of willows
     */
    public ArcticTundra(int numFox, int numLemming,
                        int numCrowberry, int numWillow) {

        allCreatures = new ArrayList<Creature>();
        currentWeek = 0;
        rand = new Random();

        for (int i = 0; i < numFox; i++) {
            Fox fox = new Fox(
                FOX_STANDARD_MASS,
                1.0,
                rand.nextDouble() * MAX_LOC_X,
                rand.nextDouble() * MAX_LOC_Y,
                false
            );
            allCreatures.add(fox);
        }

        for (int i = 0; i < numLemming; i++) {
            Lemming lemming = new Lemming(
                LEMMING_STANDARD_MASS,
                1.0,
                rand.nextDouble() * MAX_LOC_X,
                rand.nextDouble() * MAX_LOC_Y
            );
            allCreatures.add(lemming);
        }

        for (int i = 0; i < numCrowberry; i++) {
            Crowberry crowberry = new Crowberry(
                CROWBERRY_STANDARD_MASS,
                1.0,
                rand.nextDouble() * MAX_LOC_X,
                rand.nextDouble() * MAX_LOC_Y
            );
            allCreatures.add(crowberry);
        }

        for (int i = 0; i < numWillow; i++) {
            Willow willow = new Willow(
                WILLOW_STANDARD_MASS,
                1.0,
                rand.nextDouble() * MAX_LOC_X,
                rand.nextDouble() * MAX_LOC_Y
            );
            allCreatures.add(willow);
        }
    }

    /**
     * Computes the total mass of all foxes and their offspring
     *
     * @return Total fox mass
     */
    public double getTotalFoxMass() {
        double totalMass = 0.0;

        for (int i = 0; i < allCreatures.size(); i++) {
            Creature c = allCreatures.get(i);

            if (c instanceof Fox) {
                Fox fox = (Fox) c;

                totalMass += fox.getMass();

                ArrayList<Fox> babies = fox.getAllOffspring();
                for (int j = 0; j < babies.size(); j++) {
                    totalMass += babies.get(j).getMass();
                }
            }
        }

        return totalMass;
    }

    /**
     * Computes the total mass of all lemmings
     *
     * @return Total lemming mass
     */
    public double getTotalLemmingMass() {
        double totalMass = 0.0;

        for (int i = 0; i < allCreatures.size(); i++) {
            if (allCreatures.get(i) instanceof Lemming) {
                totalMass += allCreatures.get(i).getMass();
            }
        }

        return totalMass;
    }

    /**
     * Computes the total mass of all crowberries
     *
     * @return Total crowberry mass
     */
    public double getTotalCrowberryMass() {
        double totalMass = 0.0;

        for (int i = 0; i < allCreatures.size(); i++) {
            if (allCreatures.get(i) instanceof Crowberry) {
                totalMass += allCreatures.get(i).getMass();
            }
        }

        return totalMass;
    }

    /**
     * Computes the total mass of all willows
     *
     * @return Total willow mass
     */
    public double getTotalWillowMass() {
        double totalMass = 0.0;

        for (int i = 0; i < allCreatures.size(); i++) {
            if (allCreatures.get(i) instanceof Willow) {
                totalMass += allCreatures.get(i).getMass();
            }
        }

        return totalMass;
    }

    /**
     * Advances the simulation by the given number of weeks, updating all
     * creatures, handling reproduction, predation, and deaths
     *
     * @param n Number of weeks to simulate
     */
    public void advanceNWeeks(int n) {

        for (int i = 0; i < n; i++) {

            ArrayList<Creature> copyAllCreatures =
                new ArrayList<>(allCreatures);

            for (int j = 0; j < copyAllCreatures.size(); j++) {

                Creature creature = copyAllCreatures.get(j);

                try {
                    creature.advanceWeek();
                } catch (CreatureDeathException e) {
                    allCreatures.remove(creature); // ✅ FIXED
                    continue;
                }

                if (creature instanceof Fox) {
                    Fox fox = (Fox) creature;
                    if (fox.triggerReproduce()) {
                        fox.giveBirth();
                    }
                }

                if (creature instanceof Lemming) {
                    Lemming lemming = (Lemming) creature;
                    if (lemming.triggerReproduce()) {
                        try {
                            Lemming baby = lemming.clone();
                            allCreatures.add(baby);
                        } catch (CloneNotSupportedException e) {
                        }
                    }
                }

                if (creature instanceof Animal) {

                    Animal predator = (Animal) creature;

                    ArrayList<Creature> preyList =
                        new ArrayList<>(allCreatures);

                    for (int g = 0; g < preyList.size(); g++) {

                        Creature other = preyList.get(g);

                        if (other == creature) {
                            continue;
                        }

                        if (predator.distanceTo(other)
                            < predator.getActionRadius()) {

                            boolean success = predator.preyOn(other); // ✅ FIXED

                            if (success && !other.isAlive()) {

                                if (other instanceof Lemming) {
                                    allCreatures.remove(other); // ✅ FIXED
                                }
                                else if (other instanceof Willow) {

                                    Willow willow = (Willow) other;

                                    try {
                                        Willow clone = willow.clone();
                                        clone.setMass(TEN);

                                        allCreatures.remove(willow);
                                        allCreatures.add(clone);

                                    } catch (CloneNotSupportedException e) {
                                    }
                                }
                            }
                        }
                    }
                }
            }

            currentWeek++;
        }
    }

    /**
     * Saves all creatures in the tundra to a file in the required format
     *
     * @param filePath Path of the file to write
     */
    public void saveToFile(String filePath) {
        try {
            PrintWriter pw = new PrintWriter(new File(filePath));

            for (Creature c : allCreatures) {

                String species = c.getClass().getSimpleName();
                double mass = c.getMass();
                double health = c.getHealth();
                double locX = c.getLocX();
                double locY = c.getLocY();
                double off1 = -1.0;
                double off2 = -1.0;
                double off3 = -1.0;

                if (c instanceof Fox) {
                    Fox fox = (Fox) c;
                    ArrayList<Fox> babies = fox.getAllOffspring();

                    if (babies.size() > 0) {
                        off1 = babies.get(0).getMass();
                    }
                    if (babies.size() > 1) {
                        off2 = babies.get(1).getMass();
                    }
                    if (babies.size() > TWO) {
                        off3 = babies.get(TWO).getMass();
                    }
                }

                pw.println(
                    species + " " +
                    mass + " " +
                    health + " " +
                    locX + " " +
                    locY + " " +
                    off1 + " " +
                    off2 + " " +
                    off3
                );
            }

            pw.close();

        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    /**
     * Loads a tundra ecosystem from a file and reconstructs all creatures
     *
     * @param filePath Path of the file to read
     */
    public ArcticTundra(String filePath) {

        allCreatures = new ArrayList<Creature>();
        currentWeek = 0;
        rand = new Random();

        try {
            Scanner scan = new Scanner(new File(filePath));

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                Scanner lineScanner = new Scanner(line);

                String species = lineScanner.next();
                double mass = lineScanner.nextDouble();
                double health = lineScanner.nextDouble();
                double locX = lineScanner.nextDouble();
                double locY = lineScanner.nextDouble();

                double off1 = lineScanner.nextDouble();
                double off2 = lineScanner.nextDouble();
                double off3 = lineScanner.nextDouble();

                lineScanner.close();

                Creature creature = null;

                if (species.equals("Fox")) {

                    Fox fox = new Fox(mass, health, locX, locY, false);

                    if (off1 != -1.0) {
                        Fox b1 = new Fox(off1, 1.0, locX, locY, true);
                        fox.addOffspring(b1);
                    }
                    if (off2 != -1.0) {
                        Fox b2 = new Fox(off2, 1.0, locX, locY, true);
                        fox.addOffspring(b2);
                    }
                    if (off3 != -1.0) {
                        Fox b3 = new Fox(off3, 1.0, locX, locY, true);
                        fox.addOffspring(b3);
                    }

                    creature = fox;
                }
                else if (species.equals("Lemming")) {
                    creature = new Lemming(mass, health, locX, locY);
                }
                else if (species.equals("Crowberry")) {
                    creature = new Crowberry(mass, health, locX, locY);
                }
                else if (species.equals("Willow")) {
                    creature = new Willow(mass, health, locX, locY);
                }

                if (creature != null) {
                    allCreatures.add(creature);
                }
            }

            scan.close();

        } catch (Exception e) {
            System.out.println("Error loading file");
        }
    }

    /**
     * Runs basic unit tests to verify constructor calls, saving, loading,
     * and weekly advancement
     *
     * @return True if all tests complete without exceptions
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static boolean unitTests() {

        ArcticTundra t1 = new ArcticTundra();

        ArcticTundra t2 = new ArcticTundra(3, 5, 2, 4);

        t2.saveToFile("saveA.txt");
        t2.saveToFile("saveB.txt");
        t2.saveToFile("saveC.txt");

        ArcticTundra t3 = new ArcticTundra("saveA.txt");
        ArcticTundra t4 = new ArcticTundra("saveB.txt");
        ArcticTundra t5 = new ArcticTundra("saveC.txt");

        t1.advanceNWeeks(1);
        t2.advanceNWeeks(2);
        t3.advanceNWeeks(3);

        return true;
    }

    /**
     * Prints a message indicating whether unit tests passed
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        if (unitTests()) {
            System.out.println("Student: All unit tests passed.\n");
        } else {
            System.out.println("Student: ERROR: Failed test.\n");
            return;
        }
    }
}