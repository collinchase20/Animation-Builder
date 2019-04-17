package cs3500.animator.provider.view.enhancedview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows an animation to restart.
 */
public class RestartHandler implements ActionListener {

  private EnhancedVisualView v;

  /**
   * Constructs a restartHandler.
   *
   * @param v is the EnhancedVisualView that will use this restartHandler
   */
  public RestartHandler(EnhancedVisualView v) {
    this.v = v;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    this.v.getPanel().setTick(0);
  }
}
