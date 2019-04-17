package cs3500.animator.controller;

import java.awt.*;
import javax.swing.*;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.motion.AnimatorModel;
import cs3500.animator.model.motion.IAnimator;
import cs3500.animator.view.IEditor;

/**
 * Implementation of our controller. This controller simply holds an instance of our model, editor
 * view, and a Jframe. With those it can relay the methods written from our editor view to the
 * JFrame its producing and adding functionality with buttons to call those methods.
 */
public class Controller extends JFrame implements IController {

  private IEditor eView;
  private boolean switchBool = true;
  private IAnimator model;
  private AnimatorModel adapt;


  /**
   * Constructor for a controller.
   *
   * @param eView an Instance of our EditView
   * @param adapt a new instance of our model using the adapter pattern
   */

  public Controller(IEditor eView, AnimatorModel adapt, IAnimator model) {
    this.eView = eView;
    this.model = model;
    this.adapt = adapt;
    this.beginController();
  }

  @Override
  public void beginController() {

    eView.sendPlay().addActionListener(e -> eView.pauseAndResume());
    eView.sendRestart().addActionListener(e -> eView.restart());
    eView.sendLoop().addActionListener(e -> eView.loopAnimation());
    eView.sendLoop().addActionListener(e -> this.switchColor(eView.sendLoop()));
    eView.sendSpeedUp().addActionListener(e -> eView.upSpeed());
    eView.sendSlowDown().addActionListener(e -> eView.downSpeed());
    eView.sendAddShape().addActionListener(e -> model.addShape(eView.sendShapeName().getText(),
            eView.sendShapeType().getText()));
    eView.sendRemoveShape().addActionListener(e -> model.removeShape(eView.sendShapeTypeRemove().getText()));


    eView.sendKeyFrameButtonR().addActionListener(e -> {
      if (eView.sendKeyFrameFieldR().getText().split(" ").length == 9) {
        model.removeKeyFrame(eView.sendKeyFrameFieldR().getText().split(" ")[0],
                Integer.parseInt(eView.sendKeyFrameFieldR().getText().split(" ")[1]),
                Integer.parseInt(eView.sendKeyFrameFieldR().getText().split(" ")[2]),
                Integer.parseInt(eView.sendKeyFrameFieldR().getText().split(" ")[3]),
                Integer.parseInt(eView.sendKeyFrameFieldR().getText().split(" ")[4]),
                Integer.parseInt(eView.sendKeyFrameFieldR().getText().split(" ")[5]),
                Integer.parseInt(eView.sendKeyFrameFieldR().getText().split(" ")[6]),
                Integer.parseInt(eView.sendKeyFrameFieldR().getText().split(" ")[7]),
                Integer.parseInt(eView.sendKeyFrameFieldR().getText().split(" ")[8]));
      } else {
        throw new IllegalArgumentException("Must have 9 space separated values.");
      }
    });


    eView.sendKeyBA().addActionListener(e -> model.addKeyFrame(eView.sendKeyTLA().getText().split(
            " ")[0],
            Integer.parseInt(eView.sendKeyTLA().getText().split(" ")[1])));

    eView.sendKeyBM().addActionListener(e -> {
      if (eView.sendKeyTLM().getText().split(" ").length == 9) {
        model.modifyKeyFrame(eView.sendKeyTLM().getText().split(" ")[0],
                Integer.parseInt(eView.sendKeyTLM().getText().split(" ")[1]),
                Integer.parseInt(eView.sendKeyTLM().getText().split(" ")[2]),
                Integer.parseInt(eView.sendKeyTLM().getText().split(" ")[3]),
                Integer.parseInt(eView.sendKeyTLM().getText().split(" ")[4]),
                Integer.parseInt(eView.sendKeyTLM().getText().split(" ")[5]),
                Integer.parseInt(eView.sendKeyTLM().getText().split(" ")[6]),
                Integer.parseInt(eView.sendKeyTLM().getText().split(" ")[7]),
                Integer.parseInt(eView.sendKeyTLM().getText().split(" ")[8]));
      } else {
        throw new IllegalArgumentException("Must have 9 space separated values.");
      }
    });

    eView.beginVisual();

  }


  @Override
  public AnimatorModel getModel() {
    return this.adapt;
  }


  @Override
  public void addShape(String name, String type) {
    model.addShape(name, type);
  }

  @Override
  public void removeShape(String name) {
    model.removeShape(name);
  }

  @Override
  public void editKeyframe(Keyframe k, Double t1, Double x1, Double y1, Double w1, Double h1,
                           Double r1, Double g1, Double b1, Double t2, Double x2, Double y2,
                           Double w2, Double h2, Double r2, Double g2, Double b2) {
    model.modifyKeyFrame(k.getName(), (int) k.getT1(), (int) k.getX1(), (int) k.getY1(),
            (int) k.getW1(), (int) k.getH1(), (int) k.getR1(), (int) k.getG1(), (int) k.getB1());

  }

  @Override
  public void addKeyframe(Keyframe kf) {
    model.addKeyFrame(kf.getName(), (int) kf.getT1());
  }

  @Override
  public void deleteKeyframe(Keyframe k) {
    model.removeKeyFrame(k.getName(), (int) k.getT1(), (int) k.getX1(), (int) k.getY1(),
            (int) k.getW1(), (int) k.getH1(), (int) k.getR1(), (int) k.getG1(), (int) k.getB1());
  }

  private void switchColor(JButton loop) {
    if (this.switchBool) {
      loop.setBackground(Color.green);
      this.switchBool = false;
    } else {
      loop.setBackground(Color.red);
      this.switchBool = true;
    }
  }
}
