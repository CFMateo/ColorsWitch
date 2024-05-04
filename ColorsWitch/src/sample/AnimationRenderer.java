package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Fait le rendu d'un champignon sur l'écran en affichant image
 */
public class AnimationRenderer extends Renderer {

    private Image img;
    private Mushroom entity;
    private String imageName;
    private int lastImageIndex = 26;
    private int nbTick = 10; // une nouvelle image de l'animation tous les nbTick
    
    private int imageIndex = 1;
    public AnimationRenderer(String name, Mushroom e) {
        //img = new Image("/images/" + name + ".png");
        imageIndex = 1;
        imageName = name;
        this.entity = e;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {//une nouvelle image de l'animation est dessinée à chaque appel de draw
        img = new Image("/images/" + imageName + "_animation" + imageIndex +".png");
        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        context.drawImage(img, x - entity.getWidth() / 2, y - entity.getHeight() / 2, entity.getWidth(), entity.getHeight());
        nbTick -=1;
        if (nbTick == 0){
            imageIndex +=1;
            if (imageIndex > lastImageIndex) imageIndex = 1;
            nbTick = 10;
        }
    }
}
