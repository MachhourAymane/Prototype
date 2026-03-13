package com.prototype.character;

/**
 * Interface Cloneable personnalisée pour les personnages
 * Définit le contrat pour le clonage de personnages
 */
public interface GameCharacter extends Cloneable {
    /**
     * Clone le personnage actuel
     * @return une copie profonde du personnage
     */
    public GameCharacter clone();
    
    /**
     * Affiche les informations du personnage
     */
    void displayInfo();
    
    /**
     * Obtient le type du personnage
     */
    String getType();
    
    /**
     * Définit le nom du personnage
     */
    void setName(String name);
    
    /**
     * Obtient le nom du personnage
     */
    String getName();
}
