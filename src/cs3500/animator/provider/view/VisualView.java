package cs3500.animator.provider.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import cs3500.animator.model.motion.AnimatorModel;

/**
 * Visual view. Draws and runs the animation in a window.
 */
public class VisualView extends AbstractViewClass implements IView {

  /**
   * Constructs and runs a visual view. This view will run the animation in a new window.
   *
   * @param speed is the speed in ticks/sec that this animation will play at
   */
  public VisualView(int speed) {
    super();
    if (speed <= 0) {
      throw new IllegalArgumentException();
    }
    if (m == null) {
      throw new IllegalArgumentException();
    }
    int winX = m.getWidth() + m.getLeftX();
    int winY = m.getHeight() + m.getTopY();
    this.setTitle("Animation");
    this.setSize(winX / 2, winY / 2);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    super.speed = speed;

    this.setLayout(new BorderLayout());

    VisualPanel vp = new VisualPanel(m, speed);
    vp.setPreferredSize(new Dimension(winX / 2, winY / 2));
    JScrollPane scrollPane = new JScrollPane(vp);
    this.add(scrollPane, BorderLayout.CENTER);
    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);

  }
}