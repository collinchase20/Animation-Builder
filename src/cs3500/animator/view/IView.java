package cs3500.animator.view;

/**
 * Our View interface. Works in conjunction with the model in order to create either a plaintext,
 * svg, or visual view.
 */
public interface IView {

  /**
   * Displays the view as a text, either in svg format or plaintext.
   * @return a string representing the animation.
   */
  String displayView();


  /**
   * Starts the visual animation. Suppressed in TView and SVGView.
   */
  void startAnimation();

  /**
   * Sets the speed of an animation. Suppressed in TView.
   *
   * @param speed the speed of the animation
   */
  void setSpeed(int speed);

  /**
   * Starts the visual animation independent of the editor, by creating it's own JFrame and
   * displaying it.
   */
  void startVisualView();


}
