package engine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Rectangle Object.
 */
public class Rectangle extends UIElement {

  private GraphicsContext g;

  public Rectangle(String title, double x1, double y1, double x2, double y2,
                   String width, Color fillColor, Color strokeColor, GraphicsContext g) {

    super(title);
    g.setFill(fillColor);
    g.setStroke(strokeColor);
    g.setLineWidth(Double.parseDouble(width));
    g.strokeLine(x1, y1, x2, y2);
  }

  public onDraw() {}
}