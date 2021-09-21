package engine.UIElements;

import engine.UIElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Circle class
 */
public class Circle implements UIElement {

  private String title;
  private double strokeWidth;
  private Color fillColor;
  private Color strokeColor;
  private double x;
  private double y;
  private double w;
  private double h;


  /**
   * Circle constructor
   * @param title the name of the circle
   */
  public Circle(String title, double x, double y, double w, double h,
                double strokeWidth, Color fillColor, Color strokeColor) {

    this.title = title;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.strokeWidth = strokeWidth;
    this.fillColor = fillColor;
    this.strokeColor = strokeColor;
  }

  /**
   * Inserts the parameters into the GraphicsContext
   * @param g input graphicsContext
   * @return new graphicsContext
   */
  @Override
  public GraphicsContext convert(GraphicsContext g) {
    g.setFill(fillColor);
    g.setStroke(strokeColor);
    g.setLineWidth(strokeWidth);
    g.strokeOval(x, y, w, h);
    return g;
  }

  /**
   * Returns the title.
   *
   * @return the title.
   */
  @Override
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
    this.w = w * xScale;
    this.h = h * yScale;
  }

}
