package engine.UIElements;

import engine.UIElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Text object
 */
public class Text implements UIElement {

  private String content;
  private String title;
  private Color fillColor;
  private double x;
  private double y;
  private double fontSize;

  /**
   * A text UIElement object
   *
   * @param title   the name of the text object
   * @param content the actual words in the text
   */
  public Text(String title, double x, double y, String content, Color fillColor, double fontSize) {
    this.title = title;
    this.content = content;
    this.fillColor = fillColor;
    this.x = x;
    this.y = y;
    this.fontSize = fontSize;
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
    g.setFont(new Font(fontSize));
    return g;
  }

  /**
   * Returns the title.
   *
   * @return the title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Updates for resizing
   *
   * @param xScale the x scale
   * @param yScale the y scale
   */
  @Override
  public void update(double xScale, double yScale) {
    this.x = x * xScale;
    this.y = y * yScale;
  }

  /**
   * Changes the content in the Text
   * @param newContent new words
   */
  public void setContent(String newContent) {
    content = newContent;
  }


}
