package cs3500.animator.provider.view.enhancedview;

import cs3500.animator.view.enhancedview.EnhancedVisualView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows the animation to loop.
 */
public class LoopHandler implements ActionListener {

  private EnhancedVisualView v;

  /**
   * Constructs a loop handeler.
   *
   * @param v is the EnhancedVisualView that will use this loopHandler
   */
  public LoopHandler(EnhancedVisualView v) {
    this.v = v;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    this.v.getPanel().setLoop(!this.v.getPanel().getLoop());
  }
}
