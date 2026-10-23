///////////////////////////////////////////////////////////////////////////////
// Author:            Connor Gibson
// Email:             cogibson@ucsd.edu
// Class:             CSE 11 Winter 2026
// Instructor's Name: Ben Ochoa
///////////////////////////////////////////////////////////////////////////////

//
// NOTE: YOU SHOULD NOT HAVE TO IMPORT ANY PACKAGES TO COMPLETE THIS
// ASSIGNMENT.
// 
// If you add extraneous packages (intentionally or not), then your
// code might fail Gradescope compilation.
//

/**
 * Automating the damage calculation of an RPG game
 * using four methods
 * Bugs: None
 *
 * @author Connor Gibson
 */
public class GamePlay {

    // Feel free to create extra variables if necessary.
    // Hint: you would lose style points if you use magic numbers 
    // (e.g., 2, 3, 4, etc.) in the methods.
    // To avoid this, create variables below and use them in the methods,
    // or create variables in the methods.

    /* Variables used for calculateDamage */
    private static final int DICE_MIN = 0;
    private static final int MODIFIER_LOW = -5;
    private static final int MODIFIER_HIGH = 10;
    private static final int CRIT_MULTIPLIER = 3;
    private static final int HEAVY_WEAPON_ADD = 4;
    private static final int NOT_HEAVY_WEAPON_ADD = -1;
    private static final int INVALID_DAMAGE_RETURN = -1;

    /* Variables used for computeStatusBonus */
    private static final int MAX_LEVEL = 9;
    private static final int MIN_LEVEL = 1;
    private static final String STATUS_CODE_BURN = "BURN";
    private static final String STATUS_CODE_POISON = "POISON";
    private static final int BURNY_VALUE = 2; 
    private static final double POISON_VALUE = 1.5; 
    private static final int STUN_VALUE = 4;
    private static final int LOWEST_SCORE_POSSIBLE = 0;
    private static final int HIGHEST_SCORE_POSSIBLE = 20;

    
    // TODO: we omit the rest of variables for this method and the next two
    // methods. Try to add them and use them in the methods!


    // TODO: add methods below
    /**
    * Calculates the damage dealt based on a series of attributes
    *
    * @param diceRoll An integer representing the roll of the dice
    * @param modifier An integer that adds damage to the total
    * @param isCriticalHit A boolean that determines if there is
    * a critical hit (damage * 3)
    * @param isHeavyWeapon A boolean that determines if there it is
    * a heavy weapon (+4 true or -1 false from damage)
    * @return Integer of the total damage
    */
    public static int calculateDamage(int diceRoll, int modifier, 
        boolean isCriticalHit, boolean isHeavyWeapon){ 
        int damage = 0;
        if (diceRoll < DICE_MIN || modifier > MODIFIER_HIGH 
            || modifier < MODIFIER_LOW){ 
            return INVALID_DAMAGE_RETURN;
        }
        if (isCriticalHit == true){
            damage = diceRoll * CRIT_MULTIPLIER;
        } else {
            damage = diceRoll;
        }
        if (isHeavyWeapon == true){
            damage += HEAVY_WEAPON_ADD;
        } else {
            damage += NOT_HEAVY_WEAPON_ADD;
        }
        damage += modifier;
        if (damage < LOWEST_SCORE_POSSIBLE){ 
            return LOWEST_SCORE_POSSIBLE;
        } else { 
            return damage; 
        }
    }
    /**
    * Parses a status-effect code and returns a bonus damage 
    * based on the effect, the level, and the target type
    *
    * @param statusCode A string that determines the status effect and level
    * @param isBoss A boolean variable that determines if the effect
    * is against a boss or not
    * @return Integer representing the total status bonus
    */
    public static int computeStatusBonus(String statusCode, boolean isBoss){
        int plusIdx = statusCode.indexOf('+');
        if (plusIdx == INVALID_DAMAGE_RETURN){
            return INVALID_DAMAGE_RETURN; 
        }
        String statusEffect = 
            statusCode.substring(0, plusIdx).toLowerCase().trim();
        String level = statusCode.substring(plusIdx + 1).trim();
        if (statusEffect.length() == DICE_MIN){
            return DICE_MIN;
        }
        if (level.length() != 1){
            return INVALID_DAMAGE_RETURN;
        }
        char levelChar = level.charAt(0);
        if (levelChar < '1' || levelChar > '9'){
            return -1;
        }
        int levelInt = levelChar - '0';
        int statusBonusDamage = LOWEST_SCORE_POSSIBLE;

        if (statusEffect.equals("burn")){
            statusBonusDamage = BURNY_VALUE * levelInt;
        } else if (statusEffect.equals("poison")){
            statusBonusDamage = (int) Math.ceil(POISON_VALUE * levelInt);
        } else if (statusEffect.equals("freeze")){
            if (isBoss){ 
                statusBonusDamage = LOWEST_SCORE_POSSIBLE;
            } else {
                statusBonusDamage = CRIT_MULTIPLIER * levelInt;
            }
        } else if (statusEffect.equals("stun")){
            if (isBoss){
                if (levelInt > STUN_VALUE){
                    statusBonusDamage = levelInt;
                } else {
                    statusBonusDamage = STUN_VALUE;
                }
            } else {
                statusBonusDamage = levelInt * CRIT_MULTIPLIER; 
            }
        } else {
            return -1;
        }

        if (statusBonusDamage > HIGHEST_SCORE_POSSIBLE){
            statusBonusDamage = HIGHEST_SCORE_POSSIBLE;
        } else if (statusBonusDamage < LOWEST_SCORE_POSSIBLE){
            statusBonusDamage = LOWEST_SCORE_POSSIBLE;
        }
        return statusBonusDamage;
    } 
    /**
    * Reusing the previous two methods to apply 
    * player’s perks to the final damage
    *
    * @param diceRoll Integer that represents the roll of dice
    * @param modifier Integer used to modify the damage (+/-)
    * @param perk String that updates variables of other methods
    * @param statusCode A string that determines the status effect and level
    * @param isBoss Whether it is against a boss or not; same as other method
    * @return Integer representing the final damage, adding previous methods
    */
    public static int applyPerkAndAttack(int diceRoll, int modifier, 
        String perk, String statusCode, boolean isBoss){
        int damage = 0;
        boolean isCriticalHit = false; 
        boolean isHeavyWeapon = false;
        String statusCodeUpdated = "";
        int statusBonus = LOWEST_SCORE_POSSIBLE;
        if (perk.equals("LUCKY") || perk.equals("WEAPONMASTER") 
            || perk.equals("HELLCURSED")){
            if (perk.equals("LUCKY")){
                if (diceRoll % BURNY_VALUE == LOWEST_SCORE_POSSIBLE){
                    isCriticalHit = true;
                }
            } else if (diceRoll == MODIFIER_HIGH && !perk.equals("LUCKY")){
                isCriticalHit = true;
            }
            if (perk.equals("WEAPONMASTER")){
                isHeavyWeapon = true; 
            } else if (perk.equals("HELLCURSED")){
                statusCodeUpdated = "BURN+9";
            } 
        } else {
            System.out.print("Error: Invalid perk\n");
            return -1;
        }
        int baseDamage = calculateDamage(diceRoll, modifier, 
            isCriticalHit, isHeavyWeapon);
        if (statusCodeUpdated.equals("BURN+9")) {
            statusBonus = computeStatusBonus(statusCodeUpdated, isBoss);
        } else {
            statusBonus = computeStatusBonus(statusCode, isBoss);
        }
        if (baseDamage == -1){ 
            System.out.print("Error: Invalid damage calculation\n");
            return -1;
        } else if (statusBonus == -1){ 
            System.out.print("Error: Invalid status code\n");
            return -1;
        }
        return baseDamage + statusBonus;
    }
    /**
    * This method ties everything together: it calls applyPerkAndAttack,
    * and returns a multi-line summary string
    *
    * @param diceRoll Same as in other methods
    * @param modifier Same as in other methods
    * @param perk Same as in other methods, reused
    * @param statusCode reused from other method
    * @param isBoss reused from other method 
    * @param targetHP Integer that determines whether a target 
    * is alive or defeated
    * @return A string that displays the perk, target type,
    * final damage, and the target life status
    */
    public static String resolveAttack(int diceRoll, int modifier, 
        String perk, String statusCode, 
        boolean isBoss, int targetHP){ 
        if (!perk.equals("LUCKY") && !perk.equals("WEAPONMASTER") 
            && !perk.equals("HELLCURSED")){
            return "Error: Invalid perk\n";
        }
        int finalDamage = applyPerkAndAttack(diceRoll, modifier, perk, 
            statusCode, isBoss);
        if (finalDamage == -1){
            return "Error: Invalid attack\n";
        }
        String targetType = "";
        String targetLife = "";
        if (isBoss){ 
            targetType = "Boss";
        } else {
            targetType = "Normal";
        }
        if (targetHP > finalDamage){
            targetLife = "Alive";
        } else { 
            targetLife = "Defeated";
        }
           
        return "Attention! The player attacked!\nPerk applied: " + perk +
            "\nTarget type: " + targetType + "\nFinal damage: " + 
            finalDamage + "\nTarget is now: " + targetLife + "\n"; 
    }
    // TODO: Add more unit tests to ensure correctness of methods.
    // TODO: Add method headers for unitTests
    /**
    * Tests cases for each of the three methods
    * 
    * @return A boolean value representing whether the tests pass or fail
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE
    public static boolean unitTests() {
        System.out.println();

        // Test(s) for calculateDamage
        // Test case 1: diceRoll = 3, modifier = 1, 
        // isCriticalHit = true, isHeavyWeapon = false
        int calcDice1 = 3;
        int calcMod1 = 1;
        boolean calcIsCrit1 = true;
        boolean isHeavyWeapon1 = false;
        int expectedCalcDamage1 = 9;
        int actualCalcDamage1 = calculateDamage(calcDice1, calcMod1, 
                calcIsCrit1, isHeavyWeapon1);
        System.out.println("Expected calculateDamage Output 1: " + 
                            expectedCalcDamage1);
        System.out.println("-----------------");
        System.out.println("Actual calculateDamage Output 1: " + 
                            actualCalcDamage1);
        if (actualCalcDamage1 != expectedCalcDamage1) {
            System.out.println("FAILED: calculateDamage 1");
            return false;
        }
        System.out.println();
        System.out.println();

        // Test case 2: diceRoll = 2, modifier = -5, 
        // isCriticalHit = false, isHeavyWeapon = false
        int actualCalcDamage2 = calculateDamage(2, -5, 
                false, false);
        System.out.println("Expected calculateDamage Output 2: " + 
                            0);
        System.out.println("-----------------");
        System.out.println("Actual calculateDamage Output 2: " + 
                            actualCalcDamage2);
        if (actualCalcDamage2 != 0) {
            System.out.println("FAILED: calculateDamage 2");
            return false;
        }
        System.out.println();
        System.out.println();

        // Test case 3: diceRoll = 1, modifier = 6, 
        // isCriticalHit = true, isHeavyWeapon = true
        int actualCalcDamage3 = calculateDamage(1, 6, 
                true, true);
        System.out.println("Expected calculateDamage Output 3: " + 
                            13);
        System.out.println("-----------------");
        System.out.println("Actual calculateDamage Output 3: " + 
                            actualCalcDamage3);
        if (actualCalcDamage3 != 13) {
            System.out.println("FAILED: calculateDamage 3");
            return false;
        }
        System.out.println();
        System.out.println();

        // Test(s) for computeStatusBonus
        // Test case 1: statusCode = BURN+2, isBoss = false, 
        String statusCodeComputeStatusBonus1 = "BURN+2";
        boolean isBossComputeStatusBonus1 = false;
        int expectedStatusBonus1 = 4;
        int actualBonus1 = computeStatusBonus(statusCodeComputeStatusBonus1, 
                                                isBossComputeStatusBonus1);
        System.out.println("Expected computeStatusBonus Output 1: "
                            + expectedStatusBonus1);
        System.out.println("-----------------");
        System.out.println("Actual computeStatusBonus Output 1: " 
                            + actualBonus1 + "\n");
        if (actualBonus1 != expectedStatusBonus1) {
            System.out.println("FAILED: computeStatusBonus 1");
            return false;
        }
        // Test case 2: statusCode = freeze+5, isBoss = true, 
        int actualBonus2 = computeStatusBonus("freeze+5", 
                                                true);
        System.out.println("Expected computeStatusBonus Output 2: "
                            + 0);
        System.out.println("-----------------");
        System.out.println("Actual computeStatusBonus Output 2: " 
                            + actualBonus2 + "\n");
        if (actualBonus2 != 0) {
            System.out.println("FAILED: computeStatusBonus 2");
            return false;
        }
        // Test case 3: statusCode = ignite+2, isBoss = false, 
        int actualBonus3 = computeStatusBonus("ignite+2", 
                                                false);
        System.out.println("Expected computeStatusBonus Output 3: "
                            + -1);
        System.out.println("-----------------");
        System.out.println("Actual computeStatusBonus Output 3: " 
                            + actualBonus3 + "\n");
        if (actualBonus3 != -1) {
            System.out.println("FAILED: computeStatusBonus 3");
            return false;
        }

        // Test(s) for applyPerkAndAttack
        // Test case 1: diceRoll = 4, modifier = 1, 
        // perk = "LUCKY", statusCode = "BURN+2", isBoss = false
        // Remove the TWO lines tagged "REMOVE THIS LINE" to test the method.
        int applyPerkAndAttackDiceRoll1 = 4;
        int applyPerkAndAttackModifier1 = 1;
        String applyPerkAndAttackPerk1 = "LUCKY";
        String applyPerkAndAttackStatusCode1 = "BURN+2";
        boolean applyPerkAndAttackIsBoss1 = false;
        int expectedApplyPerkAndAttack1 = 16;
        int actualApplyPerkAndAttack1 = applyPerkAndAttack(
            applyPerkAndAttackDiceRoll1, applyPerkAndAttackModifier1, 
            applyPerkAndAttackPerk1, applyPerkAndAttackStatusCode1, 
            applyPerkAndAttackIsBoss1);
        System.out.println("Expected applyPerkAndAttack Output 1: " 
                            + expectedApplyPerkAndAttack1);
        System.out.println("-----------------");
        System.out.println("Actual applyPerkAndAttack Output 1: " 
                            + actualApplyPerkAndAttack1);
        if (actualApplyPerkAndAttack1 != expectedApplyPerkAndAttack1) {
            System.out.println("FAILED: applyPerkAndAttack 1");
            return false;
        }
        System.out.println();
        System.out.println();

        // Test case 2: diceRoll = 4, modifier = 1, 
        // perk = "WEAPONMASTER", statusCode = "BURN+2", isBoss = false
        // Remove the TWO lines tagged "REMOVE THIS LINE" to test the method.
        int applyPerkAndAttackDiceRoll2 = 4;
        int applyPerkAndAttackModifier2 = 1;
        String applyPerkAndAttackPerk2 = "WEAPONMASTER";
        String applyPerkAndAttackStatusCode2 = "BURN+2";
        boolean applyPerkAndAttackIsBoss2 = false;
        int expectedApplyPerkAndAttack2 = 13;
        int actualApplyPerkAndAttack2 = applyPerkAndAttack(
            applyPerkAndAttackDiceRoll2, applyPerkAndAttackModifier2, 
            applyPerkAndAttackPerk2, applyPerkAndAttackStatusCode2, 
            applyPerkAndAttackIsBoss2);
        System.out.println("Expected applyPerkAndAttack Output 2: " 
                            + expectedApplyPerkAndAttack2);
        System.out.println("-----------------");
        System.out.println("Actual applyPerkAndAttack Output 2: " 
                            + actualApplyPerkAndAttack2);
        if (actualApplyPerkAndAttack2 != expectedApplyPerkAndAttack2) {
            System.out.println("FAILED: applyPerkAndAttack 2");
            return false;
        }
        System.out.println();
        System.out.println();

        // Test case 3: diceRoll = 10, modifier = 1, 
        // perk = "WEAPONMASTER", statusCode = "BURN+2", isBoss = false
        // Remove the TWO lines tagged "REMOVE THIS LINE" to test the method.
        int applyPerkAndAttackDiceRoll3 = 10;
        int applyPerkAndAttackModifier3 = 1;
        String applyPerkAndAttackPerk3 = "WEAPONMASTER";
        String applyPerkAndAttackStatusCode3 = "BURN+2";
        boolean applyPerkAndAttackIsBoss3 = false;
        int expectedApplyPerkAndAttack3 = 39;
        int actualApplyPerkAndAttack3 = applyPerkAndAttack(
            applyPerkAndAttackDiceRoll3, applyPerkAndAttackModifier3, 
            applyPerkAndAttackPerk3, applyPerkAndAttackStatusCode3, 
            applyPerkAndAttackIsBoss3);
        System.out.println("Expected applyPerkAndAttack Output 3: " 
                            + expectedApplyPerkAndAttack3);
        System.out.println("-----------------");
        System.out.println("Actual applyPerkAndAttack Output 3: " 
                            + actualApplyPerkAndAttack3);
        if (actualApplyPerkAndAttack3 != expectedApplyPerkAndAttack3) {
            System.out.println("FAILED: applyPerkAndAttack 3");
            return false;
        }
        System.out.println();
        System.out.println();
        // Test(s) for resolveAttack
        // Test case 1: diceRoll = 4, modifier = 1, perk = "LUCKY", 
        // statusCode = "BURN+2", isBoss = false, targetHP = 7
        int diceRollResolveAttack1 = 4;
        int modifierResolveAttack1 = 1;
        String perkResolveAttack1 = "LUCKY";
        String statusCodeResolveAttack1 = "BURN+2";
        boolean isBossResolveAttack1 = false;
        int targetHPResolveAttack1 = 7;

        String expectedResolveAttack1 = "Attention! The player attacked!\n"
                                        + "Perk applied: LUCKY\n"
                                        + "Target type: Normal\n"
                                        + "Final damage: 16\n"
                                        + "Target is now: Defeated\n";
        String actualResolveAttack1 = resolveAttack(diceRollResolveAttack1, 
                                            modifierResolveAttack1, 
                                            perkResolveAttack1, 
                                            statusCodeResolveAttack1, 
                                            isBossResolveAttack1,
                                            targetHPResolveAttack1);
        System.out.println("Expected resolveAttack Output 1:\n" 
                            + expectedResolveAttack1);
        System.out.println("-----------------");
        System.out.println("Actual resolveAttack Output 1:\n" 
                            + actualResolveAttack1);
        if (!actualResolveAttack1.equals(expectedResolveAttack1)) {
            System.out.println("FAILED: resolveAttack 1");
            return false;
        }
        System.out.println();
        System.out.println();

        // Test case 2: diceRoll = 4, modifier = 11, perk = "LUCKY", 
        // statusCode = "BURN+2", isBoss = false, targetHP = 7
        int diceRollResolveAttack2 = 4;
        int modifierResolveAttack2 = 11;
        String perkResolveAttack2 = "LUCKY";
        String statusCodeResolveAttack2 = "BURN+2";
        boolean isBossResolveAttack2 = false;
        int targetHPResolveAttack2 = 7;

        String expectedResolveAttack2 = "Error: Invalid attack\n";
        String actualResolveAttack2 = resolveAttack(diceRollResolveAttack2, 
                                            modifierResolveAttack2, 
                                            perkResolveAttack2, 
                                            statusCodeResolveAttack2, 
                                            isBossResolveAttack2,
                                            targetHPResolveAttack2);
        System.out.println("Expected resolveAttack Output 2:\n" 
                            + expectedResolveAttack2);
        System.out.println("-----------------");
        System.out.println("Actual resolveAttack Output 2:\n" 
                            + actualResolveAttack2);
        if (!actualResolveAttack2.equals(expectedResolveAttack2)) {
            System.out.println("FAILED: resolveAttack 2\n");
            return false;
        }
        System.out.println();
        System.out.println();

        // Test case 3: diceRoll = 4, modifier = 0, perk = "NOTLUCKY", 
        // statusCode = "BURN+2", isBoss = false, targetHP = 7
        int diceRollResolveAttack3 = 4;
        int modifierResolveAttack3 = 0;
        String perkResolveAttack3 = "NOTLUCKY";
        String statusCodeResolveAttack3 = "BURN+2";
        boolean isBossResolveAttack3 = false;
        int targetHPResolveAttack3 = 7;

        String expectedResolveAttack3 = "Error: Invalid perk\n";
        String actualResolveAttack3 = resolveAttack(diceRollResolveAttack3, 
                                            modifierResolveAttack3, 
                                            perkResolveAttack3, 
                                            statusCodeResolveAttack3, 
                                            isBossResolveAttack3,
                                            targetHPResolveAttack3);
        System.out.print("Expected resolveAttack Output 3:\n" 
                            + expectedResolveAttack3);
        System.out.println("-----------------");
        System.out.println("Actual resolveAttack Output 3:\n" 
                            + actualResolveAttack3);
        if (!actualResolveAttack3.equals(expectedResolveAttack3)) {
            System.out.println("FAILED: resolveAttack 3");
            return false;
        }
        System.out.println();
        System.out.println();

        // All test cases passed
        return true;
    }

    // TODO: Complete the method header
    /**
    * Checks to see if unit tests is true or false, and prints 
    * a corresponding statement
    *
    * @param args Conventional value for arguments
    */
    @SuppressWarnings("checkstyle:MagicNumber") // DO NOT CHANGE THIS LINE
    public static void main(String[] args) {
        if (unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("ERROR: Failed test.\n");
            return;
        }
    }
}