package duel_game.armor;

import java.util.Random;

public class LightArmor extends Armor {
    private final Random rand;

    public LightArmor(int armorRating, int durability) {
        super(armorRating, durability);
        rand = new Random();
    }

    @Override
    public int reduceDamage(int damage) {
        return Math.max(super.reduceDamage(damage) - rand.nextInt(0, 2), 0);
    }
}
