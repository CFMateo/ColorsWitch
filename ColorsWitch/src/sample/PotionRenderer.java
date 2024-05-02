package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Fait le rendu d'une potion
 */
public class PotionRenderer extends Renderer {

    private Potion potion;
    private Image img;

    public PotionRenderer(Potion p) {
        img = new Image("/images/" + "potion" + ".png");
        this.potion = p;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, potion.getY());

        //context.setFill(Renderer.convertColor(potion.getColor()));
        //****
        
        double x = potion.getX();
        double y = Renderer.computeScreenY(level, potion.getY());

        context.drawImage(img, x - potion.getWidth() / 2, y - potion.getHeight() / 2, potion.getWidth(), potion.getHeight());
        //*****/
        //context.fillRect(
          //      potion.getX() - potion.getWidth() / 2,
            //    canvasY - potion.getWidth() / 2,
              //  potion.getWidth(),
                //potion.getWidth());
    }
}

