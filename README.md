# Design Pattern Prototype - Clonage de Personnages

## Objectif

Implémenter le Design Pattern Prototype pour cloner rapidement des personnages dans un jeu vidéo de stratégie. Chaque personnage (Guerrier, Archer, Mage) possède des caractéristiques spécifiques et doit être capable de se cloner de manière totalement indépendante.

## Structure du Projet

```
Prototype/
├── pom.xml
├── Prototype_UML.txt
├── README.md
└── src/main/java/com/prototype/
    ├── MainApplication.java
    ├── character/
    │   ├── Character.java (interface)
    │   ├── BaseCharacter.java (classe abstraite)
    │   ├── Warrior.java
    │   ├── Archer.java
    │   └── Mage.java
    └── registry/
        └── CharacterRegistry.java
```

## Caractéristiques des Personnages

### Guerrier (Warrior)
- **Points de vie**: 100
- **Attaque**: 15
- **Défense**: 25
- **Capacité spéciale**: Coup de Rage

### Archer (Archer)
- **Points de vie**: 60
- **Attaque**: 25
- **Défense**: 8
- **Capacité spéciale**: Pluie de Flèches

### Mage (Mage)
- **Points de vie**: 45
- **Attaque**: 30
- **Défense**: 15
- **Capacité spéciale**: Boule de Feu

## Implémentation du Pattern Prototype

### 1. Interface Character
Définit le contrat pour tous les personnages:
- `clone()`: Clone le personnage
- `displayInfo()`: Affiche les infos
- `getType()`: Retourne le type

### 2. Classe Abstraite BaseCharacter
- Contient les attributs communs
- Contient un constructeur de copie pour le clone profond
- Implémente la méthode `displayInfo()`

### 3. Classes Concrètes (Warrior, Archer, Mage)
Chaque classe:
- Étend `BaseCharacter`
- Implémente `clone()` en retournant une nouvelle instance via le constructeur de copie
- Implémente `getType()` pour retourner son type

### 4. CharacterRegistry
- Enregistre les prototypes
- Crée des clones à la demande via `createClone()`

## Utilisation

```java
// Créer le registre
CharacterRegistry registry = new CharacterRegistry();

// Enregistrer les prototypes
Character warriorPrototype = new Warrior("Guerrier", 100, 15, 25, "Coup de Rage");
registry.register("guerrier", warriorPrototype);

// Cloner des personnages
Character guerrier1 = registry.createClone("guerrier");
guerrier1.setName("Conan");

Character guerrier2 = registry.createClone("guerrier");
guerrier2.setName("Aragorn");
```

## Avantages du Pattern Prototype

✓ **Création rapide**: Clonage instantané sans recréation complète
- **Économie de ressources**: Évite de recréer des objets complexes
✓ **Indépendance**: Les clones sont totalement indépendants du prototype
✓ **Flexibilité**: Facile d'ajouter de nouveaux types de personnages
✓ **Performances**: Idéal pour les jeux avec beaucoup d'objets similaires

## Pour Compiler et Exécuter

### Avec Maven

```bash
# Compiler le projet
mvn clean compile

# Exécuter l'application
mvn exec:java -Dexec.mainClass="com.prototype.MainApplication"
```

### Ou directement avec Java

```bash
# Compiler
javac -d bin src/main/java/com/prototype/**/*.java

# Exécuter
java -cp bin com.prototype.MainApplication
```

## Points Clés

1. **Clonage indépendant**: Chaque personnage peut être cloné sans affecter l'original
2. **Prototype Pattern**: Utilisation d'un registre pour gérer les prototypes
3. **Polymorphisme**: Différents types de personnages avec le même interface
4. **Encapsulation**: Les attributs sont protégés et accessibles via getters/setters

## Améliorations Possibles

- Ajouter d'autres types de personnages (Paladin, Voleur, etc.)
- Implémenter la sérialisation pour persister les prototypes
- Ajouter des niveaux d'expérience et d'évolution
- Créer une interface graphique pour visualiser les personnages

## Auteur

Projet réalisé dans le cadre de l'étude des Design Patterns en Java.
