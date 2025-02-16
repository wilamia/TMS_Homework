package duel_game.armor;

public class HardArmor extends Armor {
    public HardArmor(int armorRating, int durability) {
        super(armorRating, durability);
    }

    @Override
    public int reduceDamage(int damage) {
        return super.reduceDamage(damage);
    }
}
