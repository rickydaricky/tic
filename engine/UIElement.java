package engine;

import javafx.scene.canvas.GraphicsContext;

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
   * Calls onDraw for the current element.
   */
  public void draw() {
    onDraw(g);
  }

}









