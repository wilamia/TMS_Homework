package duel_game.players;

import duel_game.armor.Armor;
import duel_game.dice.Dice;
import duel_game.weapon.Weapon;

import java.util.Random;

public abstract class Hero {
    private String name;
    private int hp;
    private Armor armor;
    private Weapon weapon;
    private int countOfCriticalAttacks = 0;
    private int countOfDefaultAttacks = 3;

    Hero(String name, int hp, Armor armor, Weapon weapon) {
        this.name = name;
        this.hp = hp;
        this.armor = armor;
        this.weapon = weapon;
    }

    public void getDamage(int damage) {
        int finalDamage = armor.reduceDamage(damage);
        System.out.printf("Герой %s получил урон %d, оставшееся hp: %d\n", name, finalDamage, getHp());
        this.hp -= finalDamage;
    }

    public int causeDamage(int diceResult) {
        int finalDamage = weapon.getDamage() + diceResult;
        System.out.printf("Результат кубика игрока %s: %d, нанесенный урон: %d\n", name, diceResult, finalDamage);
        return finalDamage;
    }

    public int causeCriticalDamage(int diceResult) {
        int finalDamage = (weapon.getDamage() + diceResult) + weapon.getDamage() * diceResult / 100;
        System.out.printf("Результат кубика игрока %s: %d, нанесенный урон: %d\n", name, diceResult, finalDamage);
        return finalDamage;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getCountOfCriticalAttacks() {
        return countOfCriticalAttacks;
    }

    public void changeCountOfCriticalAttacks() {
        countOfCriticalAttacks++;
    }

    public void resetCountOfCriticalAttacks() {
        countOfCriticalAttacks = 0;
    }

    public int getCountOfDefaultAttacks() {
        return countOfDefaultAttacks;
    }

    public void changeCountOfDefaultAttacks() {
        countOfDefaultAttacks++;
    }

    public void resetCountOfDefaultAttacks() {
        countOfDefaultAttacks = 0;
    }

    public int getHp() {
        return Math.max(hp, 0);
    }

    public void getStats() {
        System.out.printf("Имя: %s\nЗдоровье: %d\nБроня: рейтинг %d, прочность %d\nУрон оружия: %d\n",
                name, getHp(), armor.getArmorRating(), armor.getDurability(), weapon.getWeaponDamage());
    }

}
