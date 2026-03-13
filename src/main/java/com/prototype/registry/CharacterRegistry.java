package com.prototype.registry;

import com.prototype.character.GameCharacter;
import java.util.HashMap;
import java.util.Map;

/**
 * Registre de prototypes
 * Permet de stocker et de cloner des personnages existants
 */
public class CharacterRegistry {
    private Map<String, GameCharacter> characterPrototypes = new HashMap<>();

    /**
     * Enregistre un personnage comme prototype
     * @param name le nom du prototype
     * @param prototype le personnage prototype
     */
    public void register(String name, GameCharacter prototype) {
        characterPrototypes.put(name, prototype);
        System.out.println("✓ Prototype '" + name + "' enregistré dans le registre");
    }

    /**
     * Crée un clone d'un prototype enregistré
     * @param prototypeKey le clé du prototype à cloner
     * @return un clone du prototype
     */
    public GameCharacter createClone(String prototypeKey) {
        GameCharacter prototype = characterPrototypes.get(prototypeKey);
        if (prototype != null) {
            return prototype.clone();
        }
        throw new IllegalArgumentException("Prototype '" + prototypeKey + "' non trouvé!");
    }

    /**
     * Affiche tous les prototypes enregistrés
     */
    public void displayRegisteredPrototypes() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     PROTOTYPES ENREGISTRÉS");
        System.out.println("╠════════════════════════════════════════╣");
        characterPrototypes.forEach((key, character) -> {
            System.out.println("║ [" + key + "] -> " + character.getType());
        });
        System.out.println("╚════════════════════════════════════════╝");
    }
}
