package engine.UIElements;

import engine.UIElement;
import javafx.scene.canvas.GraphicsContext;

/**
 * Button Class
 */
public class Button extends UIElement {

  private GraphicsContext g;

  /**
   * A button UIElement object
   *
   * @param title the name of the button
   * @param shape the background shape of the button
   * @param text  the button label
   */
  public Button(String title, UIElement shape, Text text) {
    super(title);

  }
}
