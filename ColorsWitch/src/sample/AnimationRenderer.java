package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Fait le rendu d'un champignon sur l'écran en affichant image
 */
public class AnimationRenderer extends Renderer {

    private Image img;
    private Mushroom entity;
    public AnimationRenderer(String name, Mushroom e) {
        img = new Image("/images/" + name + ".png");
        this.entity = e;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double x = entity.getX();
        double y = Renderer.computeScreenY(level, entity.getY());

        context.drawImage(img, x - entity.getWidth() / 2, y - entity.getHeight() / 2, entity.getWidth(), entity.getHeight());
    }
}
