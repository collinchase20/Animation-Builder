package cs3500.animator.provider.view.enhancedview;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Allows the animation to play.
 */
public class ShapeButtonListener implements ActionListener {

  private EnhancedVisualView v;
  private TextArea t;
  private JFrame window;
  private boolean canAdd = true;

  /**
   * Constructs a playHandler.
   *
   * @param v is the EnhancedVisualView that will use this playHandler
   * @param t is the text that will be parsed
   * @param window is the new window that will appear
   */
  ShapeButtonListener(EnhancedVisualView v, TextArea t, JFrame window) {
    this.v = v;
    this.t = t;
    this.window = window;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    String name = "";
    String type = "";

    String text = t.getText();
    Scanner s = new Scanner(text);

    while (s.hasNext()) {
      String temp = s.next();
      if (name.equals("")) {
        name = temp;
      } else if (type.equals("")) {
        type = temp;
      }
    }

    for (String sc : v.getController().getModel().getAllShapes().keySet()) {
      if (sc.equals(name)) {
        JOptionPane.showMessageDialog(window, "Cannot have two shapes with same name");
        canAdd = false;
        break;
      } else {
        canAdd = true;
      }
    }

    if (canAdd) {
      v.getController().addShape(name, type);
      v.addShapeToMenu(name);
    }

    window.dispose();
  }
}