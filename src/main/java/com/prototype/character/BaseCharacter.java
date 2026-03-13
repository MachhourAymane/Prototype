package com.prototype.character;

/**
 * Classe abstraite de base pour tous les personnages
 * Implémente la logique commune de clonage
 */
public abstract class BaseCharacter implements GameCharacter {
    protected String name;
    protected int healthPoints;
    protected int attack;
    protected int defense;
    protected String specialAbility;

    public BaseCharacter(String name, int healthPoints, int attack, int defense, String specialAbility) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attack = attack;
        this.defense = defense;
        this.specialAbility = specialAbility;
    }

    /**
     * Constructeur de copie pour le clone profond
     */
    protected BaseCharacter(BaseCharacter original) {
        this.name = original.name;
        this.healthPoints = original.healthPoints;
        this.attack = original.attack;
        this.defense = original.defense;
        this.specialAbility = original.specialAbility;
    }

    /**
     * Déclare que clone() est public dans cette classe
     */
    @Override
    public abstract GameCharacter clone();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public void setSpecialAbility(String specialAbility) {
        this.specialAbility = specialAbility;
    }

    @Override
    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║ " + getType());
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Nom: " + name);
        System.out.println("║ Points de vie: " + healthPoints);
        System.out.println("║ Attaque: " + attack);
        System.out.println("║ Défense: " + defense);
        System.out.println("║ Capacité spéciale: " + specialAbility);
        System.out.println("╚════════════════════════════════════════╝");
    }
}
