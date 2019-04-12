package cs3500.animator.provider.view.enhancedview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows the animation to play.
 */
public class PlayHandler implements ActionListener {

  private EnhancedVisualView v;

  /**
   * Constructs a playHandler.
   *
   * @param v is the EnhancedVisualView that will use this playHandler
   */
  public PlayHandler(EnhancedVisualView v) {
    this.v = v;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (v.getPanel().getIsPlay()) {
      v.setTitle("Press to play");
    } else {
      v.setTitle("Press to pause");
    }
    this.v.getPanel().setTimer();
  }
}
