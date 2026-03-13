package com.prototype.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import com.prototype.character.Archer;
import com.prototype.character.BaseCharacter;
import com.prototype.character.GameCharacter;
import com.prototype.character.Mage;
import com.prototype.character.Warrior;
import com.prototype.registry.CharacterRegistry;

/**
 * Interface graphique pour le Prototype Pattern
 * Démontre le clonage et la personnalisation de caractères
 */
public class PrototypeGUI extends JFrame {
    private CharacterRegistry registry;
    private JTextArea displayArea;
    private JComboBox<String> prototypeSelector;
    private JTextField nameField;
    private JSpinner healthSpinner;
    private JSpinner attackSpinner;
    private JSpinner defenseSpinner;
    private DefaultListModel<String> heroListModel;

    public PrototypeGUI() {
        setTitle("Prototype Pattern - Character Cloning System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        
        initializeRegistry();
        createUI();
    }

    private void initializeRegistry() {
        registry = new CharacterRegistry();
        
        // Register prototypes
        registry.register("Warrior", new Warrior("Guerrier", 100, 25, 20, "Coup Puissant"));
        registry.register("Archer", new Archer("Archer", 80, 30, 15, "Tir Rapide"));
        registry.register("Mage", new Mage("Mage", 60, 40, 10, "Lance Sort"));
    }

    private void createUI() {
        // Main panel with two columns
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Left panel - Selection and Cloning
        JPanel leftPanel = createLeftPanel();
        
        // Right panel - Display
        JPanel rightPanel = createRightPanel();
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        
        add(mainPanel);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Cloning Control"));
        
        // Prototype Selection
        JPanel protoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        protoPanel.add(new JLabel("Select Prototype:"));
        prototypeSelector = new JComboBox<>(new String[]{"Warrior", "Archer", "Mage"});
        protoPanel.add(prototypeSelector);
        panel.add(protoPanel);
        
        panel.add(Box.createVerticalStrut(10));
        
        // Character Customization
        JPanel customPanel = new JPanel();
        customPanel.setLayout(new BoxLayout(customPanel, BoxLayout.Y_AXIS));
        customPanel.setBorder(BorderFactory.createTitledBorder("Customize Clone"));
        
        // Name
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Hero Name:"));
        nameField = new JTextField(15);
        namePanel.add(nameField);
        customPanel.add(namePanel);
        
        // Health
        JPanel healthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        healthPanel.add(new JLabel("Health Points:"));
        healthSpinner = new JSpinner(new SpinnerNumberModel(100, 50, 200, 10));
        healthPanel.add(healthSpinner);
        customPanel.add(healthPanel);
        
        // Attack
        JPanel attackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        attackPanel.add(new JLabel("Attack Power:"));
        attackSpinner = new JSpinner(new SpinnerNumberModel(25, 10, 50, 5));
        attackPanel.add(attackSpinner);
        customPanel.add(attackPanel);
        
        // Defense
        JPanel defensePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        defensePanel.add(new JLabel("Defense:"));
        defenseSpinner = new JSpinner(new SpinnerNumberModel(20, 5, 40, 5));
        defensePanel.add(defenseSpinner);
        customPanel.add(defensePanel);
        
        panel.add(customPanel);
        panel.add(Box.createVerticalStrut(10));
        
        // Clone Button
        JButton cloneButton = new JButton("Clone & Create Hero");
        cloneButton.setFont(new Font("Arial", Font.BOLD, 14));
        cloneButton.addActionListener(e -> createHero());
        panel.add(cloneButton);
        
        panel.add(Box.createVerticalStrut(10));
        
        // Heroes List
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Created Heroes"));
        heroListModel = new DefaultListModel<>();
        JList<String> heroList = new JList<>(heroListModel);
        heroList.setFont(new Font("Monospaced", Font.PLAIN, 11));
        listPanel.add(new JScrollPane(heroList), BorderLayout.CENTER);
        panel.add(listPanel);
        
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Prototype Registry & Display"));
        
        // Display area
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        displayArea.setEditable(false);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        
        // Show Prototypes Button
        JPanel buttonPanel = new JPanel();
        JButton showPrototypesBtn = new JButton("Show Prototypes");
        showPrototypesBtn.addActionListener(e -> showPrototypes());
        buttonPanel.add(showPrototypesBtn);
        
        JButton clearBtn = new JButton("Clear Display");
        clearBtn.addActionListener(e -> displayArea.setText(""));
        buttonPanel.add(clearBtn);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }

    private void createHero() {
        String prototypeType = (String) prototypeSelector.getSelectedItem();
        String heroName = nameField.getText().trim();
        
        if (heroName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a hero name!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Clone from prototype registry
            GameCharacter clone = registry.createClone(prototypeType);
            
            // Customize
            BaseCharacter customClone = (BaseCharacter) clone;
            customClone.setName(heroName);
            customClone.setHealthPoints((Integer) healthSpinner.getValue());
            customClone.setAttack((Integer) attackSpinner.getValue());
            customClone.setDefense((Integer) defenseSpinner.getValue());
            
            // Add to display
            String heroInfo = String.format("✓ [%s] %s | HP: %d | ATK: %d | DEF: %d | Type: %s",
                    heroListModel.size() + 1,
                    heroName,
                    customClone.getHealthPoints(),
                    customClone.getAttack(),
                    customClone.getDefense(),
                    prototypeType);
            
            heroListModel.addElement(heroInfo);
            
            // Display in text area
            displayArea.append("\n" + heroInfo);
            displayArea.append("\n" + "─".repeat(70) + "\n");
            
            // Clear input fields
            nameField.setText("");
            
            JOptionPane.showMessageDialog(this, "Hero '" + heroName + "' created successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showPrototypes() {
        displayArea.setText("📚 PROTOTYPE REGISTRY\n");
        displayArea.append("═".repeat(70) + "\n\n");
        
        for (String type : new String[]{"Warrior", "Archer", "Mage"}) {
            try {
                GameCharacter proto = registry.createClone(type);
                BaseCharacter baseProto = (BaseCharacter) proto;
                displayArea.append("[" + type.toUpperCase() + " PROTOTYPE]\n");
                displayArea.append("Name: " + baseProto.getName() + "\n");
                displayArea.append("Health: " + baseProto.getHealthPoints() + " | ");
                displayArea.append("Attack: " + baseProto.getAttack() + " | ");
                displayArea.append("Defense: " + baseProto.getDefense() + "\n");
                displayArea.append("\n" + "─".repeat(70) + "\n\n");
            } catch (Exception e) {
                displayArea.append("Error loading " + type + ": " + e.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrototypeGUI frame = new PrototypeGUI();
            frame.setVisible(true);
        });
    }
}
