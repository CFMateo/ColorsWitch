package sample;



/*
 TODO: - TACHES OBLIGATOIRES - 
 0: Ajouter un mode de test  = VALIDE
 1: Créer trois nouveaux types d’Obstacles = VALIDE
 2: Créer un nouvel Item = VALIDE
 3: Créer 4 niveaux = VALIDE
 4: Corriger les collisions entre le cercle (Player) et le reste = VALIDE
 5: Animer le champignon = VALIDE
 6: Afficher un message lorsqu’on gagne ou perd dans un niveau = VALIDE
 */


import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Classe principale de l'application, responsable de l'initialisation et de la gestion des scènes du menu et du jeu.
 */
public class ColorsWitch extends Application {

    // Constantes pour la taille de la fenêtre
    public static final double WIDTH = 320, HEIGHT = 480;

    // Variable indiquant si la touche TAB est enfoncée
    private boolean pressTab;

    // Scène du menu
    private Scene menuScene;

    // Contexte graphique pour dessiner sur le canevas
    private GraphicsContext context;

    // Contrôleur pour gérer le jeu
    private Controller controller = new Controller();
    
    // Dernier instant de temps enregistré
    private long lastTime = 0;
    
    // AnimationTimer actuel
    private AnimationTimer animationTimer;

    /**
     * Méthode principale pour lancer l'application.
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Méthode appelée au démarrage de l'application.
     * @param primaryStage La fenêtre principale de l'application
     * @throws Exception En cas d'erreur lors du démarrage de l'application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création du menu
        Menu menu = new Menu();

        // Initialisation du contrôleur
        controller = new Controller(); // Utilisation d'une instance unique de Controller

        // Écoute des actions de l'utilisateur dans le menu
        menu.setOnOptionSelected((option) -> {
            if (!option.equals("Exit")) {
                controller.setLevelSelected(option);
                showGameScene(primaryStage); // Affichage de la scène de jeu
            } else {
                primaryStage.close(); // Fermeture de l'application
            }
        });

        // Création de la scène du menu
        menuScene = new Scene(menu, WIDTH, HEIGHT);

        // Affichage de la scène du menu dans la fenêtre principale
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Colors Witch - Menu");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Affiche la scène de jeu dans la fenêtre principale.
     * @param primaryStage La fenêtre principale de l'application
     */
    private void showGameScene(Stage primaryStage) {
        // Création de la scène de jeu
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        Pane root = new Pane(canvas);
        context = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Ajout des gestionnaires d'événements à la scène de jeu
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                controller.spaceTyped();
            } else if (event.getCode() == KeyCode.TAB) {
                controller.togglePressTab(); // Active ou désactive le mode de test
            } else if (event.getCode() == KeyCode.ESCAPE) {
                if (animationTimer != null) {
                    animationTimer.stop(); // Arrêt de l'animation
                }
                primaryStage.setScene(menuScene); // Retour au menu principal
                primaryStage.setTitle("Colors Witch - Menu");
                lastTime = 0; // Réinitialisation de lastTime
            }
        });

        // Démarrage de l'animation du jeu
        startAnimation(controller);

        // Affichage de la scène de jeu dans la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Colors Witch");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Démarre l'animation du jeu en utilisant un AnimationTimer.
     * @param controller Le contrôleur du jeu
     */
    private void startAnimation(Controller controller) {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                controller.tick((now - lastTime) * 1e-9);
                context.setFill(Color.BLACK);
                context.fillRect(0, 0, WIDTH, HEIGHT);
                List<Entity> entities = controller.getEntities();
                for (Entity e : entities) {
                    e.getRepresentation().draw(controller.getCurrentLevel(), context);
                }
                lastTime = now; // Mise à jour de lastTime

                // Dessiner le message (si présent)
                if (controller.getGame().isGameOver() || controller.getGame().hasWon()) {
                    controller.getGame().drawMessage(context);
                }
            }
        };
        animationTimer.start();
    }
}