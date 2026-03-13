package com.prototype;

import com.prototype.character.*;
import com.prototype.registry.CharacterRegistry;

/**
 * Application principale démontrant le Design Pattern Prototype
 * Clonage de personnages dans un jeu vidéo de stratégie
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║       DESIGN PATTERN PROTOTYPE - CLONAGE DE PERSONNAGES  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        // Créer le registre de prototypes
        CharacterRegistry registry = new CharacterRegistry();

        // Créer les prototypes de base
        GameCharacter warriorPrototype = new Warrior("Guerrier de Base", 100, 15, 25, "Coup de Rage");
        GameCharacter archerPrototype = new Archer("Archer Rapide", 60, 25, 8, "Pluie de Flèches");
        GameCharacter magePrototype = new Mage("Mage Sage", 45, 30, 15, "Boule de Feu");

        // Enregistrer les prototypes
        registry.register("guerrier", warriorPrototype);
        registry.register("archer", archerPrototype);
        registry.register("mage", magePrototype);

        registry.displayRegisteredPrototypes();

        // Afficher les prototypes de base
        System.out.println("\n=== PROTOTYPES ORIGINAUX ===\n");
        warriorPrototype.displayInfo();
        archerPrototype.displayInfo();
        magePrototype.displayInfo();

        // Cloner des personnages à partir des prototypes
        System.out.println("\n=== CLONAGE DE PERSONNAGES ===\n");
        System.out.println("Création de clones via le registre de prototypes...\n");

        // Créer des clones de guerrier
        GameCharacter guerrier1 = registry.createClone("guerrier");
        guerrier1.setName("Conan le Barbare");

        GameCharacter guerrier2 = registry.createClone("guerrier");
        guerrier2.setName("Aragorn le Roi");
        ((BaseCharacter) guerrier2).setAttack(20);

        // Créer des clones d'archer
        GameCharacter archer1 = registry.createClone("archer");
        archer1.setName("Legolas aux Cheveux d'Or");

        GameCharacter archer2 = registry.createClone("archer");
        archer2.setName("Robin des Bois");
        ((BaseCharacter) archer2).setHealthPoints(70);

        // Créer des clones de mage
        GameCharacter mage1 = registry.createClone("mage");
        mage1.setName("Gandalf le Gris");

        GameCharacter mage2 = registry.createClone("mage");
        mage2.setName("Merlin l'Enchanteur");
        ((BaseCharacter) mage2).setAttack(35);
        ((BaseCharacter) mage2).setDefense(20);

        // Afficher les clones créés
        System.out.println("\n=== PERSONNAGES CLONÉS ===\n");
        System.out.println("─── GUERRIERS ───");
        guerrier1.displayInfo();
        guerrier2.displayInfo();

        System.out.println("─── ARCHERS ───");
        archer1.displayInfo();
        archer2.displayInfo();

        System.out.println("─── MAGES ───");
        mage1.displayInfo();
        mage2.displayInfo();

        // Démonstration de l'indépendance des clones
        System.out.println("\n=== DÉMONSTRATION D'INDÉPENDANCE ===\n");
        System.out.println("Avant modification du prototype guerrier:");
        System.out.println("→ Guerrier original: " + ((BaseCharacter) warriorPrototype).getName());
        System.out.println("→ Conan (clone): " + guerrier1.getName());

        ((BaseCharacter) warriorPrototype).setName("Guerrier Modifié");
        ((BaseCharacter) warriorPrototype).setAttack(50);
        ((BaseCharacter) warriorPrototype).setHealthPoints(200);

        System.out.println("\nAprès modification du prototype guerrier:");
        System.out.println("→ Guerrier original: " + ((BaseCharacter) warriorPrototype).getName());
        System.out.println("→ Guerrier original - Attaque: " + ((BaseCharacter) warriorPrototype).getAttack());
        System.out.println("→ Conan (clone) - Nom: " + guerrier1.getName());
        System.out.println("→ Conan (clone) - Attaque: " + ((BaseCharacter) guerrier1).getAttack());
        System.out.println("\n✓ Les clones sont indépendants du prototype original!");

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║        FIN DE LA DÉMONSTRATION DU PATTERN PROTOTYPE    ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }
}
