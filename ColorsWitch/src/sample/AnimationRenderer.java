package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Fait le rendu d'une animation en affichant une série d'images.
 */
public class AnimationRenderer extends Renderer {

    private Image img;
    private Mushroom entity;
    private String imageName;
    private int lastImageIndex = 26;
    private int nbTick = 10; // une nouvelle image de l'animation tous les nbTick
    
    private int imageIndex = 1;

    /**
     * Crée un nouveau rendu d'animation pour l'entité donnée.
     *
     * @param name  le nom de l'image de l'animation
     * @param entity l'entité associée à ce rendu
     */
    public AnimationRenderer(String name, Mushroom entity) {
        this.imageIndex = 1;
        this.imageName = name;
        this.entity = entity;
    }

    /**
     * Dessine l'animation actuelle de l'entité sur le contexte graphique.
     *
     * @param level   le niveau actuel
     * @param context le contexte graphique sur lequel dessiner
     */
    @Override
    public void draw(Level level, GraphicsContext context) {
        // Charge l'image correspondant à l'index actuel
        img = new Image("/images/" + imageName + "_animation" + imageIndex +".png");

        // Obtient les coordonnées de rendu de l'entité sur l'écran
        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        // Dessine l'image sur le contexte graphique
        context.drawImage(img, x - entity.getWidth() / 2, y - entity.getHeight() / 2, entity.getWidth(), entity.getHeight());

        // Met à jour l'index de l'image pour l'animation
        nbTick -=1;
        if (nbTick == 0){
            imageIndex +=1;
            if (imageIndex > lastImageIndex) imageIndex = 1;
            nbTick = 10;
        }
    }
}
