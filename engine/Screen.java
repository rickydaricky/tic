package engine;

import engine.support.FXFrontEnd;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Screen class
 */
public class Screen extends FXFrontEnd {

  private boolean active = false;
  private String title = "";
  private long timeElapsed = 0;
  private List<UIElement> elements = new ArrayList<>();

  /**
   * Matches the first constructor of FXFrontEnd
   * @param title the title of the screen
   */
  public Screen(String title) {
    super(title);
    this.title = title;
  }

  /**
   * Matches the second constructor of FXFrontEnd
   * @param title the title of the screen
   */
  public Screen(String title, Vec2d windowSize, boolean debugMode, boolean fullscreen, List<UIElement> elements) {
    super(title, windowSize, debugMode, fullscreen);
    this.title = title;
    this.elements = elements;
  }

  /**
   * Called at a regular interval set by {@link #setTickFrequency(long)}. Use to update any state
   * that changes over time.
   *
   * @param nanosSincePreviousTick approximate number of nanoseconds since the previous call
   *                               to onTick
   */
  @Override
  protected void onTick(long nanosSincePreviousTick) {

    timeElapsed += (0.000000001 * nanosSincePreviousTick);
  }

  @Override
  protected void onLateTick() {

  }

  /**
   * Called when the screen needs to be redrawn. This is at least once per tick, but possibly
   * more frequently (for example, when the window is resizing).
   * <br><br>
   * Note that the entire drawing area is cleared before each call to this method. Furthermore,
   * {@link #onResize} is guaranteed to be called before the first invocation of onDraw.
   *
   * @param g a {@link GraphicsContext} object used for drawing.
   */
  @Override
  protected void onDraw(GraphicsContext g) {

  }

  /**
   * @param e an FX {@link KeyEvent} representing the input event.
   */
  @Override
  protected void onKeyTyped(KeyEvent e) {

  }

  /**
   * @param e an FX {@link KeyEvent} representing the input event.
   */
  @Override
  protected void onKeyPressed(KeyEvent e) {

  }

  /**
   * @param e an FX {@link KeyEvent} representing the input event.
   */
  @Override
  protected void onKeyReleased(KeyEvent e) {

  }

  /**
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseClicked(MouseEvent e) {

  }

  /**
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMousePressed(MouseEvent e) {

  }

  /**
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseReleased(MouseEvent e) {

  }

  /**
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseDragged(MouseEvent e) {

  }

  /**
   * @param e an FX {@link MouseEvent} representing the input event.
   */
  @Override
  protected void onMouseMoved(MouseEvent e) {

  }

  /**
   * @param e an FX {@link ScrollEvent} representing the input event.
   */
  @Override
  protected void onMouseWheelMoved(ScrollEvent e) {

  }

  /**
   * @param newVal a boolean representing the new focus state
   */
  @Override
  protected void onFocusChanged(boolean newVal) {

  }

  /**
   * Called when the size of the drawing area changes. Any subsequent calls to onDraw should note
   * the new size and be sure to fill the entire area appropriately. Guaranteed to be called
   * before the first call to onDraw.
   *
   * @param newSize the new size of the drawing area.
   */
  @Override
  protected void onResize(Vec2d newSize) {

  }

  /**
   * Called before the window closes by user input. Not guaranteed to be called if the program
   * terminates due to an exception or a System.exit(...) / Platform.close call.
   */
  @Override
  protected void onShutdown() {

  }

  /**
   * Called after the window is initialized by engine.support code, but before it appears. Guaranteed
   * to be called before all events, ticks, resizes, and draw calls.
   */
  @Override
  protected void onStartup() {

  }

  /**
   * Turns the screen off.
   */
  public void setInactive() {
    this.active = false;
  }

  public void addUIElement(UIElement ele) {
    elements.add(ele);
  }

  /**
   * Returns elements
   * @return a list of UIElements
   */
  public List<UIElement> getElements() {
    return elements;
  }



}
