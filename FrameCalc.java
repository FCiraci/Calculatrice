import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe FrameCalc - Une calculatrice graphique simple.
 */
public class FrameCalc extends JFrame implements ActionListener {

    // Composants de l'interface
    private JPanel buttonPanel; // Panneau contenant les boutons de la calculatrice
    private JTextField display; // Champ de texte pour l'affichage des chiffres et du résultat
    private String operator; // Stocke l'opérateur sélectionné (+, -, *, /)
    private double firstOperand; // Premier opérande pour l'opération
    private double secondOperand; // Deuxième opérande pour l'opération
    private double result; // Résultat de l'opération
    private boolean isOperatorSelected; // Indique si un opérateur a été sélectionné

    /**
     * Constructeur de FrameCalc.
     * Initialise l'interface utilisateur et les variables de l'application.
     */
    public FrameCalc() {
        // Configuration de la fenêtre principale
        setTitle("Calculatrice");
        setLocation(50, 50);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialiser le champ d'affichage
        display = new JTextField(50);
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setHorizontalAlignment(JTextField.RIGHT); // Alignement du texte à droite

        // Initialiser le panneau des boutons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10)); // Grille 5x4 avec espaces

        // Liste des étiquettes de boutons
        String[] buttonLabels = {
            "C", "<-", "(", ")", // Ligne des opérations supplémentaires
            "7", "8", "9", "÷",  // Ligne des chiffres et de la division
            "4", "5", "6", "×",  // Ligne des chiffres et de la multiplication
            "1", "2", "3", "-",  // Ligne des chiffres et de la soustraction
            "0", ".", "=", "+"   // Ligne du zéro, du point, de l'égalité et de l'addition
        };

        // Créer les boutons et les ajouter au panneau
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this); // Ajouter un écouteur d'action à chaque bouton
            buttonPanel.add(button); // Ajouter le bouton au panneau
        }

        // Initialisation des variables
        operator = "";
        isOperatorSelected = false;
        result = firstOperand = secondOperand = 0.0;

        // Ajouter les composants à la fenêtre principale
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    /**
     * Méthode appelée lorsqu'un bouton est cliqué.
     * Gère les entrées numériques, les opérateurs et le calcul.
     * @param e l'événement d'action qui a déclenché cet appel.
     */
    public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand(); // Récupère le label du bouton cliqué

    // Si le bouton est un chiffre ou un point décimal
    if (action.matches("[0-9\\.]")) {
        if (isOperatorSelected) {
            display.setText(action); // Remplace le texte affiché par le nouveau chiffre
            isOperatorSelected = false;
        } else {
            display.setText(display.getText() + action); // Ajoute le chiffre à l'affichage
        }
    }
    // Si le bouton est "C" (Clear)
    else if (action.equals("C")) {
        display.setText(""); // Efface l'affichage
        firstOperand = secondOperand = result = 0.0; // Réinitialise les opérandes et le résultat
        operator = ""; // Réinitialise l'opérateur
    }
    // Si le bouton est "<-" (Backspace)
    else if (action.equals("<-")) {
        String currentText = display.getText();
        if (currentText.length() > 0) {
            display.setText(currentText.substring(0, currentText.length() - 1)); // Supprime le dernier caractère
        }
    }
    // Si le bouton est une parenthèse
    else if (action.equals("(") || action.equals(")")) {
        display.setText(display.getText() + action); // Ajoute la parenthèse à l'affichage
    }
    // Si le bouton est le bouton "="
    else if (action.equals("=")) {
        secondOperand = Double.parseDouble(display.getText()); // Convertit l'affichage en double pour le deuxième opérande
        switch (operator) {
            case "+" -> result = firstOperand + secondOperand; // Addition
            case "-" -> result = firstOperand - secondOperand; // Soustraction
            case "×" -> result = firstOperand * secondOperand; // Multiplication
            case "÷" -> result = firstOperand / secondOperand; // Division
        }
        display.setText(String.valueOf(result)); // Affiche le résultat
        operator = ""; // Réinitialise l'opérateur
    }
    // Si le bouton est un opérateur
    else {
        if (!operator.isEmpty() && !operator.equals("=")) {
            secondOperand = Double.parseDouble(display.getText()); // Convertit l'affichage en double pour le deuxième opérande
            switch (operator) {
                case "+" -> result = firstOperand + secondOperand; // Addition
                case "-" -> result = firstOperand - secondOperand; // Soustraction
                case "×" -> result = firstOperand * secondOperand; // Multiplication
                case "÷" -> result = firstOperand / secondOperand; // Division
            }
            display.setText(String.valueOf(result)); // Affiche le résultat intermédiaire
            firstOperand = result; // Stocke le résultat pour les prochaines opérations
        } else {
            firstOperand = Double.parseDouble(display.getText()); // Stocke le premier opérande
        }
        operator = action; // Stocke l'opérateur
        isOperatorSelected = true; // Indique qu'un opérateur a été sélectionné
    }
}

    /**
     * Point d'entrée principal de l'application.
     * Lance l'application en créant une instance de FrameCalc.
     * @param args Les arguments de ligne de commande.
     */
    public static void main(String[] args) {
        new FrameCalc();
    }
}

