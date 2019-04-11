package cs3500.animator.provider.view;

import cs3500.animator.view.enhancedview.EnhancedVisualView;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.AnimatorModel;
import model.Keyframe;

/**
 * Defines a panel that will be drawn onto.
 */
public class VisualPanel extends JPanel implements ActionListener {

  private Map<String, String> shapes;
  private ArrayList<Keyframe> ak;
  private double tick;
  private AnimatorModel m;
  private boolean loop;
  private Timer timer;
  private boolean isPlay;
  private int speed;
  private EnhancedVisualView v;

  /**
   * Constructs a shape panel object and sets the background to white.
   */
  public VisualPanel(AnimatorModel m, int speed) {
    super();
    this.m = m;
    this.speed = speed;
    this.tick = 0;
    this.ak = m.getKeyframes();
    timer = new Timer(1000 / speed, this);
    //this.ak = m.interpolate(tick);
    this.shapes = m.getAllShapes();
    this.setBackground(Color.WHITE);
    loop = false;
    isPlay = true;
    timer.start();
  }

  @Override // this should simply draw an animation at any moment
  protected void paintComponent(Graphics g) {
    //never forget to call super.paintComponent!
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    AffineTransform originalTransform = g2d.getTransform();

    g2d.translate(1, this.getPreferredSize().getHeight());
    g2d.scale(0.5, 0.5);

    for (String s : shapes.keySet()) {
      for (Keyframe k : ak) {
        try {
          if (tick >= k.getT1() && tick <= k.getT2()) {
            g2d.setColor(new Color((int) k.getR1(), (int) k.getG1(), (int) k.getB1()));
          }
          if (shapes.get(k.getName()).equals("rectangle") && s.equals(k.getName())) {
            g2d.fillRect((int) k.getX1(), (int) k.getY1(), (int) k.getW1(), (int) k.getH1());
          }
          if (shapes.get(k.getName()).equals("ellipse") && s.equals(k.getName())) {
            g2d.fillOval((int) k.getX1(), (int) k.getY1(), (int) k.getW1(), (int) k.getH1());
          }
        } catch (NullPointerException npe) {
          System.out.println("Item has been deleted");
          return;
        }
      }
    }

    g2d.setTransform(originalTransform);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    tick++;
    this.ak = this.m.interpolate(tick);
    if (tick >= m.getLargestTick() && loop) {
      tick = 0;
    }
    if (tick >= m.getLargestTick() && !loop) {
      this.ak = m.interpolate(this.m.getLargestTick());
      tick = m.getLargestTick();
      tick = m.getLargestTick();
    }

    repaint();
  }

  /**
   * Sets the tick of the animation.
   *
   * @param tick is the new tick the animation will play from
   */
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * Sets whether to loop the animation.
   *
   * @param b is whether the animation will loop
   */
  public void setLoop(boolean b) {
    this.loop = b;
  }

  /**
   * Gets whether the animation will loop.
   *
   * @return whether the animation will loop
   */
  public boolean getLoop() {
    return this.loop;
  }

  /**
   * Sets a timer for this animation.
   */
  public void setTimer() {
    if (this.isPlay) {
      timer.stop();
      this.isPlay = !this.isPlay;
    } else {
      timer.start();
      this.isPlay = !this.isPlay;
    }
  }

  public Timer getTimer() {
    return this.timer;

  }

  /**
   * Changes the speed of the animation.
   *
   * @param b the boolean representing whether or not the animation is changing speed
   */
  public void changeSpeed(boolean b) {

    if (b) {
      this.speed = this.speed + 10;
    } else  {
      if (this.speed - 10 > 0) {
        this.speed = this.speed - 10;
      }
    }
    System.out.println(this.speed);
    timer.stop();
    timer = new Timer(1000 / this.speed, this);
    timer.start();
    this.isPlay = true;

  }

  /**
   * Gets the status of the animation.
   *
   * @return whether or not the animation is playing
   */
  public boolean getIsPlay() {
    return this.isPlay;
  }


}
