package com.prototype.character;

/**
 * Classe représentant un Archer dans le jeu
 * Caractéristiques: Points de vie modérés, Très bonne attaque, Défense faible
 */
public class Archer extends BaseCharacter {

    public Archer(String name, int healthPoints, int attack, int defense, String specialAbility) {
        super(name, healthPoints, attack, defense, specialAbility);
    }

    /**
     * Constructeur de copie pour le clonage
     */
    private Archer(Archer original) {
        super(original);
    }

    @Override
    public GameCharacter clone() {
        return new Archer(this);
    }

    @Override
    public String getType() {
        return "ARCHER";
    }
}
