package com.prototype.character;

/**
 * Classe représentant un Mage dans le jeu
 * Caractéristiques: Points de vie faibles, Attaque magique très élevée, Défense modérée
 */
public class Mage extends BaseCharacter {

    public Mage(String name, int healthPoints, int attack, int defense, String specialAbility) {
        super(name, healthPoints, attack, defense, specialAbility);
    }

    /**
     * Constructeur de copie pour le clonage
     */
    private Mage(Mage original) {
        super(original);
    }

    @Override
    public GameCharacter clone() {
        return new Mage(this);
    }

    @Override
    public String getType() {
        return "MAGE";
    }
}
