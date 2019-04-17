package cs3500.animator.provider.view;

import java.awt.Dimension;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.motion.AnimatorModel;

/**
 * Abstract class for views. Has common methods.
 */
public class AbstractViewClass extends JFrame implements IView {

  protected AnimatorModel m;
  protected Controller c;
  Consumer<String> commandCallback;
  int speed;

  public AbstractViewClass() {
    JFrame frame = new JFrame();
    frame.setPreferredSize(new Dimension(500,500));
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    commandCallback = callback;
  }


  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public AnimatorModel getAnimatorModel() {
    return m;
  }

  @Override
  public void addController(Controller c) {
    this.c = c;
  }
}
