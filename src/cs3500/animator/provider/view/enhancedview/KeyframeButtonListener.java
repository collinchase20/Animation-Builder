package cs3500.animator.provider.view.enhancedview;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Keyframe;

/**
 * Listens for when the keyframe is added.
 */
public class KeyframeButtonListener implements ActionListener {

  private EnhancedVisualView v;
  private boolean canAdd = true;
  private TextArea t;
  private JFrame window;
  private Map<String, Double> params = new HashMap<>();

  /**
   * Listener that adds a new keyframe to the specified shape. Based off of text input.
   *
   * @param v is the view that is being used
   * @param t the text that is being parsed
   * @param window is the window frame
   */
  KeyframeButtonListener(EnhancedVisualView v, TextArea t, JFrame window) {
    this.v = v;
    this.t = t;
    this.window = window;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    String name = "";
    params.put("t1", 0.0);
    params.put("x1", 0.0);
    params.put("y1", 0.0);
    params.put("w1", 0.0);
    params.put("h1", 0.0);
    params.put("r1", 0.0);
    params.put("g1", 0.0);
    params.put("b1", 0.0);
    params.put("t2", 0.0);
    params.put("x2", 0.0);
    params.put("y2", 0.0);
    params.put("w2", 0.0);
    params.put("h2", 0.0);
    params.put("r2", 0.0);
    params.put("g2", 0.0);
    params.put("b2", 0.0);
    // when button is clicked mutate the shapes in the model to add the key frame
    String text = t.getText();

    String[] textArray = text.split("\\s+");
    try {
      for (int i = 0; i < textArray.length; i += 2) {
        if (textArray[i].equals("name") && !textArray[i + 1].isEmpty()) {
          name = textArray[i + 1];
          //System.out.println("name set");
        } else if (!textArray[i].equals("name") && !textArray[i + 1].isEmpty()) {
          try {
            params.put(textArray[i], Double.parseDouble(textArray[i + 1]));
            //System.out.println("value set");
          } catch (NumberFormatException nfe) {
            System.out.println("All params except name must be followed by a double");
          }
        }
      }
    } catch (ArrayIndexOutOfBoundsException blah) {
      JOptionPane.showMessageDialog(window, "Invalid format");
      canAdd = false;
    }

    if (canAdd) {
      Keyframe kf = new Keyframe(name, params.get("t1"), params.get("x1"), params.get("y1"),
          params.get("w1"), params.get("h1"), params.get("r1"), params.get("g1"), params.get("b1"),
          params.get("t2"), params.get("x2"), params.get("y2"), params.get("w2"), params.get("h2"),
          params.get("r2"), params.get("g2"), params.get("b2"));
      v.getController().addKeyframe(kf);
      v.addOneKeyframe(kf);
      System.out.println("Added frame");
    }

    window.dispose();
  }
}