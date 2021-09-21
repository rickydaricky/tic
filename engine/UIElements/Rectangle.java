package engine.UIElements;

import engine.UIElement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Rectangle Class
 */
public class Rectangle implements UIElement {

  private double x1;
  private double y1;
  private double x2;
  private double y2;
  private double strokeWidth;
  private Color fillColor;
  private Color strokeColor;
  private String title;


  /**
   * Public Constructor for a Rectangle
   *
   * @param title       the name of the rectangle
   * @param x1          the x1 position
   * @param y1          the y1 position
   * @param x2          the x2 position
   * @param y2          the y2 position
   * @param strokeWidth       the width
   * @param fillColor   the color of the rectangle fill
   * @param strokeColor the color of the rectangle stroke
   */
  public Rectangle(String title, double x1, double y1, double x2, double y2,
                   double strokeWidth, Color fillColor, Color strokeColor) {

    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.strokeWidth = strokeWidth;
    this.fillColor = fillColor;
    this.strokeColor = strokeColor;
    this.title = title;
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
    g.strokeLine(x1, y1, x2, y2);
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
   * Updates based on a scale
   * @param xScale the x scale value
   * @param yScale the y scale value
   */
  public void update(double xScale, double yScale) {
    this.strokeWidth = strokeWidth * yScale;
    this.x1 = x1 * xScale;
    this.x2 = x2 * xScale;
    this.y1 = y1 * yScale;
    this.y2 = y2 * yScale;
  }

  /**
   * returns x1.
   * @return x1
   */
  public double getX1() {return x1;}

  /**
   * returns x2.
   * @return x2
   */
  public double getX2() {return x2;}

  /**
   * returns y1.
   * @return y1
   */
  public double getY1() {return y1;}

  /**
   * returns y2.
   * @return y2.
   */
  public double getY2() {return y2;}

  /**
   * Returns strokeWidth.
   * @return strokeWidth
   */
  public double getStrokeWidth() {return strokeWidth;}


}