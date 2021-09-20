package engine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class UIElement extends Screen {

  private GraphicsContext g;

  /**
   * Matches the first constructor of FXFrontEnd
   *
   * @param title the title of the screen
   */
  public UIElement(String title) {
    super(title);
  }

  /**
   * Called periodically and meant to draw graphical components.
   *
   * @param g a {@link GraphicsContext} object used for drawing.
   */
  @Override
  protected void onDraw(GraphicsContext g) {
    g.setFill(Color.RED);
    g.setStroke(Color.BLUE);
    g.setLineWidth(3);
    g.strokeLine(40, 40, 10, 40);
  }

  public GraphicsContext convert(GraphicsContext g) {
    return g;
  }

}









