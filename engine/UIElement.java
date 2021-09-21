package engine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface UIElement {

  /**
   * Adds to graphicsContext
   * @param g graphicsContext
   * @return graphicsContext
   */
  GraphicsContext convert(GraphicsContext g);

  /**
   * Returns the title.
   * @return the title.
   */
  String getTitle();

  /**
   * Updates for resizing
   * @param xScale the x scale
   * @param yScale the y scale
   */
  void update(double xScale, double yScale);
}









