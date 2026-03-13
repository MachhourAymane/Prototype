package com.prototype.character;

/**
 * Classe représentant un Guerrier dans le jeu
 * Caractéristiques: Points de vie élevés, Attaque modérée, Très bonne défense
 */
public class Warrior extends BaseCharacter {

    public Warrior(String name, int healthPoints, int attack, int defense, String specialAbility) {
        super(name, healthPoints, attack, defense, specialAbility);
    }

    /**
     * Constructeur de copie pour le clonage
     */
    private Warrior(Warrior original) {
        super(original);
    }

    @Override
    public GameCharacter clone() {
        return new Warrior(this);
    }

    @Override
    public String getType() {
        return "GUERRIER";
    }
}
