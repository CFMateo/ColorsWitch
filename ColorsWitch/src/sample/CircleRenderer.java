package sample;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un Square en dessinant un carré coloré sur l'écran.
 */
public class CircleRenderer extends Renderer {

    private Circle growc;

    public CircleRenderer(Circle gc) {
        this.growc = gc;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, growc.getY());

        context.setFill(Renderer.convertColor(growc.getColor()));

        
        context.fillOval(
            growc.getX() - growc.getRadius(),
                canvasY - growc.getRadius() / 2,
                2 * growc.getRadius(),
                2 * growc.getRadius());
    }
}

