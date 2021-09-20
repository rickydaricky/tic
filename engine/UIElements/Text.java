package engine.UIElements;

import engine.UIElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Text object
 */
public class Text extends UIElement {

  private String content;
  private String title;
  private Color fillColor;
  private double x;
  private double y;

  /**
   * A text UIElement object
   *
   * @param title   the name of the text object
   * @param content the actual words in the text
   */
  public Text(String title, double x, double y, String content, Color fillColor) {
    super(title);
    this.title = title;
    this.content = content;
    this.fillColor = fillColor;
    this.x = x;
    this.y = y;
  }

  /**
   * Inserts the parameters into the GraphicsContext
   * @param g input graphicsContext
   * @return new graphicsContext
   */
  @Override
  public GraphicsContext convert(GraphicsContext g) {
    g.setFill(fillColor);
    g.fillText(content, x, y);
    return g;
  }


}
