package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JFrame;
import cs3500.animator.model.motion.AnimatorModel;
import cs3500.animator.model.motion.Motion;
import cs3500.animator.model.shape.Shape;


/**
 * Our graphicView interface. Converts our animation model with Java Swing into a graphical
 * animation.
 */
public class GraphicView extends JPanel implements IView {

  private AnimatorModel a;
  private int tick = 0;
  private double tickSpeed = 20;
  JPanel jf = new JPanel();
  private boolean paused; //set to true by default later to enable start functionality
  private boolean loop;

  /**
   * Constructor for a graphic motion.
   *
   * @param a an instance of our model.
   */
  GraphicView(AnimatorModel a) {

    if (a == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.a = a;
    jf.add(this);
  }

  @Override
  public void startAnimation() {
    jf.setVisible(true);
    while (true) {

      while (paused) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      try {
        Thread.sleep((long) (1000 / tickSpeed));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (loop) {
        int stop = a.getLargestTick();
        if (tick == stop) {
          tick = 0;
        }
      }
      this.tick++;
      this.repaint();
    }
  }

  @Override
  public void setSpeed(int speed) {
    this.tickSpeed = speed;
  }


  void setTick(int tick) {
    this.tick = tick;
  }


  void increaseSpeed() {
    this.tickSpeed = (this.tickSpeed / 2) + this.tickSpeed;

  }

  void decreaseSpeed() {
    this.tickSpeed = this.tickSpeed - (this.tickSpeed / 2);

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    for (Map.Entry<Shape, ArrayList<Motion>> entry : a.sendAnimation().entrySet()) {
      Shape key = entry.getKey();
      ArrayList<Motion> value = entry.getValue();

      if (key.getType().equals("rectangle")) {
        for (Motion m : value) {
          if (this.tick >= m.getT1() && this.tick <= m.getT2()) {
            int curX = a.tween(this.tick, m.getX1(), m.getX2(), m.getT1(), m.getT2());
            int curY = a.tween(this.tick, m.getY1(), m.getY2(), m.getT1(), m.getT2());
            int curH = a.tween(this.tick, m.getH1(), m.getH2(), m.getT1(), m.getT2());
            int curW = a.tween(this.tick, m.getW1(), m.getW2(), m.getT1(), m.getT2());
            int curR = a.tween(this.tick, m.getR1(), m.getR2(), m.getT1(), m.getT2());
            int curG = a.tween(this.tick, m.getG1(), m.getG2(), m.getT1(), m.getT2());
            int curB = a.tween(this.tick, m.getB1(), m.getB2(), m.getT1(), m.getT2());
            Rectangle2D r1 = new Rectangle2D.Double(curX + a.returnCanvas()[0],
                    curY + a.returnCanvas()[1], curW, curH);
            g2.setColor(new Color(curR, curG, curB));
            g2.fill(r1);
          }
        }
      }
      if (key.getType().equals("ellipse")) {
        for (Motion m : value) {
          if (this.tick >= m.getT1() && this.tick <= m.getT2()) {
            int curX = a.tween(this.tick, m.getX1(), m.getX2(), m.getT1(), m.getT2());
            int curY = a.tween(this.tick, m.getY1(), m.getY2(), m.getT1(), m.getT2());
            int curH = a.tween(this.tick, m.getH1(), m.getH2(), m.getT1(), m.getT2());
            int curW = a.tween(this.tick, m.getW1(), m.getW2(), m.getT1(), m.getT2());
            int curR = a.tween(this.tick, m.getR1(), m.getR2(), m.getT1(), m.getT2());
            int curG = a.tween(this.tick, m.getG1(), m.getG2(), m.getT1(), m.getT2());
            int curB = a.tween(this.tick, m.getB1(), m.getB2(), m.getT1(), m.getT2());
            Ellipse2D e1 = new Ellipse2D.Double(curX + a.returnCanvas()[0],
                    curY + a.returnCanvas()[1], curW, curH);
            g2.setColor(new Color(curR, curG, curB));
            g2.fill(e1);
          }
        }
      }
    }
  }

  @Override
  public String displayView() {
    throw new UnsupportedOperationException("Can only be called on svg or text");
  }

  void pauseOrPlay() {
    if (paused) {
      paused = false;
    }
    else {
      paused = true;
    }
  }

  void loopOrNoLoop() {
    if (loop) {
      loop = false;
    }
    else {
      loop = true;
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(a.returnCanvas()[2] + 10, a.returnCanvas()[3] + 10);
  }

  @Override
  public void startVisualView() {
    JFrame frame = new JFrame();
    frame.setSize(a.returnCanvas()[2], a.returnCanvas()[3] + 10);
    frame.setVisible(true);
    frame.add(jf);
    this.startAnimation();
  }



}


