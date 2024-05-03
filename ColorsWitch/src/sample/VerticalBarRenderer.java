	package sample;

import javafx.scene.canvas.GraphicsContext;

/**
 * Fait le rendu d'un Square en dessinant un carré coloré sur l'écran.
 */
public class VerticalBarRenderer extends Renderer {

    private VerticalBar bar;

    public VerticalBarRenderer(VerticalBar v) {
        this.bar = v;
    }

    @Override
    public void draw(Level level, GraphicsContext context) {

        double canvasY = Renderer.computeScreenY(level, bar.getY());

        context.setFill(Renderer.convertColor(bar.getColor()));

        
        context.fillRect(
                bar.getX() - bar.getWidth() / 2,
                canvasY - bar.getHeight() / 2,
                bar.getWidth(),
                bar.getHeight());
    }
}
