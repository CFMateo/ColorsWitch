package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;

public class Menu extends VBox {

    // Interface fonctionnelle pour écouter les options sélectionnées dans le menu
    public interface OptionSelectedListener {
        void onOptionSelected(String option);
    }

    private OptionSelectedListener optionSelectedListener;

    public Menu() {

        // Ajout d'un remplissage supérieur pour dégager de l'espace entre le titre et les boutons
        setPadding(new Insets(40, 0, 0, 0));

    	
        // Création des boutons du menu pour chaque niveau et le bouton Exit
        Button level1Button = createButton("Level 1");
        Button level2Button = createButton("Level 2");
        Button level3Button = createButton("Level 3");
        Button level4Button = createButton("Level 4");
        Button exitButton = createButton("Exit");

        // Ajout des écouteurs d'événements aux boutons
        level1Button.setOnAction(event -> notifyOptionSelected("Level 1"));
        level2Button.setOnAction(event -> notifyOptionSelected("Level 2"));
        level3Button.setOnAction(event -> notifyOptionSelected("Level 3"));
        level4Button.setOnAction(event -> notifyOptionSelected("Level 4"));
        exitButton.setOnAction(event -> notifyOptionSelected("Exit"));

        // Configuration du layout du menu
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.rgb(30, 30, 30), CornerRadii.EMPTY, Insets.EMPTY)));

        // Ajout des boutons au menu
        getChildren().addAll(level1Button, level2Button, level3Button, level4Button, exitButton);
    }

    // Crée un bouton avec des propriétés visuelles personnalisées
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setPrefWidth(150);
        button.setPrefHeight(40);
        button.setBackground(new Background(new BackgroundFill(Color.rgb(70, 70, 70), new CornerRadii(5), Insets.EMPTY)));
        button.setOnMouseEntered(event -> button.setEffect(new DropShadow(10, Color.WHITE)));
        button.setOnMouseExited(event -> button.setEffect(null));
        return button;
    }

    // Méthode pour notifier l'écouteur lorsque une option est sélectionnée
    private void notifyOptionSelected(String option) {
        if (optionSelectedListener != null) {
            optionSelectedListener.onOptionSelected(option);
        }
    }

    // Méthode pour définir un écouteur pour les options sélectionnées dans le menu
    public void setOnOptionSelected(OptionSelectedListener listener) {
        this.optionSelectedListener = listener;
    }
}

