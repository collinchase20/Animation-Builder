package cs3500.animator.view;


import javax.swing.JFrame;
import java.awt.Component;

/**
 * Our EditView interface. Converts our visual animation into a visual animation that
 * is able to be used with a controller for editing the animation functionality.
 */
public class EditView extends JFrame implements IEditor {

  private GraphicView visualView;


  /**
   * Constructor for a EditView.
   * @param v an instance of our GraphicView
   */

  public EditView(GraphicView v) {
    visualView = v;

  }

  @Override
  public void pauseAndResume() {
    visualView.pauseOrPlay();
  }


  @Override
  public void restart() {
    visualView.setTick(0);
  }

  @Override
  public void loopAnimation() {
    visualView.loopOrNoLoop();
  }


  @Override
  public void beginVisual() {
    visualView.startAnimation();
  }

  @Override
  public Component getFrame() {
    return visualView.jf;
  }

  @Override
  public void upSpeed() {
    visualView.increaseSpeed();
  }

  @Override
  public void downSpeed() {
    visualView.decreaseSpeed();
  }

}
