package cs3500.animator.provider.view.enhancedview;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import cs3500.animator.model.Keyframe;

/**
 * Allows the animation to loop.
 */
public class EditKeyframeListener implements ActionListener {

  private EnhancedVisualView v;
  private boolean canEdit = true;
  private TextArea t;
  private Keyframe k;
  private JMenu j;
  private int i;
  private Map<String, Double> params = new HashMap<>();
  private JFrame window;

  /**
   * Constructs a loop handler.
   *
   * @param v is the EnhancedVisualView that will use this loopHandler
   * @param window is the window that wil appear
   */
  EditKeyframeListener(EnhancedVisualView v, Keyframe k, JMenu j, TextArea t, int i,
      JFrame window) {
    this.v = v;
    this.k = k;
    this.j = j;
    this.t  = t;
    this.i = i;
    this.window = window;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    params.put("t1", k.getT1());
    params.put("x1", k.getX1());
    params.put("y1", k.getY1());
    params.put("w1", k.getW1());
    params.put("h1", k.getH1());
    params.put("r1", k.getR1());
    params.put("g1", k.getG1());
    params.put("b1", k.getB1());
    params.put("t2", k.getT2());
    params.put("x2", k.getX2());
    params.put("y2", k.getY2());
    params.put("w2", k.getW2());
    params.put("h2", k.getH2());
    params.put("r2", k.getR2());
    params.put("g2", k.getG2());
    params.put("b2", k.getB2());

    String text = t.getText();
    System.out.println(t.getText());

    String[] textArray = text.split("\\s+");
    for (int i = 0; i < textArray.length; i += 2) {
      if (!textArray[i + 1].isEmpty()) {
        try {
          params.put(textArray[i], Double.parseDouble(textArray[i + 1]));
          System.out.println(textArray[i]);
        } catch (NumberFormatException nfe) {
          System.out.println("All params except name must be followed by a double");
        }
      }
    }

    for (Keyframe kf : v.getController().getModel().getKeyframes()) {
      if (k.getName().equals(kf.getName()) && (params.get("t1") >= kf.getT1() &&
          params.get("t1") <= kf.getT2()) && (params.get("t2") <= kf.getT2()
          && params.get("t2") >= kf.getT1())) {
        JOptionPane.showMessageDialog(window, "Shape cannot have multiple movements during "
            + "the same tick");
        canEdit = false;
        break;
      }
    }

    if (canEdit) {
      j.getItem(i).setText(" t: " + params.get("t1") + " x: " + params.get("x1") + " y: "
          + params.get("y1") + " w: " + params.get("w1") + " h: " + params.get("h1")
          + " col(" + params.get("r1") + ", " + params.get("g1") + ", " + params.get("b1") + ")");

      this.v.getController().editKeyframe(k,params.get("t1"),params.get("x1"),params.get("y1"),
          params.get("w1"),params.get("h1"),params.get("r1"),params.get("g1"),params.get("b1"),
          params.get("t2"),params.get("x2"),params.get("y2"),params.get("w2"),params.get("h2"),
          params.get("r2"),params.get("g2"),params.get("b2"));
    }

    window.dispose();
  }
}