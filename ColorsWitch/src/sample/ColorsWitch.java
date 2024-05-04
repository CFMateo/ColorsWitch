package sample;

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


public class ColorsWitch extends Application {

    public static final double WIDTH = 320, HEIGHT = 480;
    public boolean pressTab;
    private Scene menuScene; // Déclaration de menuScene comme une variable accessible globalement
    private GraphicsContext context;
    private Controller controller = new Controller();
    
    // Déclaration de lastTime comme une variable globale
    private long lastTime = 0;
    // Déclaration de l'AnimationTimer actuel
    private AnimationTimer animationTimer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Création du menu
        Menu menu = new Menu();

        // Initialisation du contrôleur
        controller = new Controller(); // Utilisez une instance unique de Controller	

        // Écoute des actions de l'utilisateur dans le menu
        menu.setOnOptionSelected((option) -> {
            if (!option.equals("Exit")) {
                controller.setLevelSelected(option);
                showGameScene(primaryStage); // Appel de showGameScene sans passer de controller
            } else {
                // Mettez ici le code pour quitter l'application
                primaryStage.close();
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

    private void showGameScene(Stage primaryStage	) {
        // Création de la scène principale
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        Pane root = new Pane(canvas);
        context = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Ajout des gestionnaires d'événements à la scène principale
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                controller.spaceTyped();
            } else if (event.getCode() == KeyCode.TAB) {
                controller.togglePressTab(); // Active ou désactive le mode de test
            } else if (event.getCode() == KeyCode.ESCAPE) {
                if (animationTimer != null) {
                    animationTimer.stop();
                }
                primaryStage.setScene(menuScene); // Revenir au menu principal
                primaryStage.setTitle("Colors Witch - Menu");
                // Réinitialiser les valeurs de temps
                lastTime = 0;
            }
        });


        // Démarrage de l'animation
        startAnimation(controller);

        // Affichage de la scène principale dans la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Colors Witch");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


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
            }
        };
        animationTimer.start();
    }
}
