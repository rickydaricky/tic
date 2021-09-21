package engine.UIElements;

import engine.UIElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Button Class
 */
public class Button implements UIElement {

  private String title;
  private Rectangle shape;
  private Text text;

  private GraphicsContext g;

  /**
   * A button UIElement object
   *
   * @param title the name of the button
   * @param shape the background shape of the button
   * @param text  the button label
   */
  public Button(String title, Rectangle shape, Text text) {

    this.title = title;
    this.shape = shape;
    this.text = text;
  }

  /**
   * Inserts the parameters into the GraphicsContext
   *
   * @param g input graphicsContext
   * @return new graphicsContext
   */
  @Override
  public GraphicsContext convert(GraphicsContext g) {
    g = shape.convert(g);
    g = text.convert(g);
    return g;
  }

  /**
   * Returns shape.
   *
   * @return the shape
   */
  public Rectangle getShape() {
    return shape;
  }

  /**
   * Returns title.
   *
   * @return the title
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
    this.shape.update(xScale, yScale);
    this.text.update(xScale, yScale);
  }

  /**
   * Changes the button's background color.
   *
   * @param c the new color
   */
  public void changeBackgroundColor(Color c) {
    shape.setColor(c);
  }


}
