package cs3500.animator.controller;


import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;


import cs3500.animator.model.motion.AnimatorModel;
import cs3500.animator.view.IEditor;

/**
 * Implementation of our controller.
 * This controller simply holds an instance of our model, editor view, and a Jframe.
 * With those it can relay the methods written from our editor view to the JFrame its
 * producing and adding functionality with buttons to call those methods.
 */
public class Controller extends JFrame implements IController {

  private IEditor eView;
  private JFrame frame = new JFrame();
  private boolean switchBool = true;
  private AnimatorModel a;

  /**
   * Constructor for a controller.
   *
   * @param eView an Instance of our EditView
   * @param a an Instance of our model
   */

  public Controller(IEditor eView, AnimatorModel a) {
    this.eView = eView;
    this.a = a;
  }

  @Override
  public void beginController() {
    frame.setVisible(true);
    frame.add(eView.getFrame());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new FlowLayout());

    JPanel buttonPanel = new JPanel();

    JButton play = new JButton("Play/Pause");
    JButton restart = new JButton("Restart");
    JButton loop = new JButton("Loop");
    JButton speedUp = new JButton("Speed Up");
    JButton slowDown = new JButton("Slow Down");
    loop.setBackground(Color.red);

    JPanel textPanel = new JPanel();

    JLabel shapeNameLabel = new JLabel("Name of shape to add");
    JTextField shapeName = new JTextField(8);

    JLabel shapeTypeLabel = new JLabel("Type of shape to add");
    JTextField shapeType = new JTextField(8);
    JButton addShape = new JButton("Add Shape");

    JLabel shapeTypeLabelRemove = new JLabel("Name of shape to remove");
    JTextField shapeTypeRemove = new JTextField(8);
    JButton removeShape = new JButton("Remove Shape");

    JLabel keyframeLabelR = new JLabel("KeyFrame to be removed.Format: name t " +
            "x y w h r g b");
    JTextField keyFrameFieldR = new JTextField(10);
    JButton keyFrameButtonR = new JButton("Remove KeyFrame");

    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

    JLabel keyFLA = new JLabel("Shape and Time to add KeyFrame at. Format: name time");
    JTextField keyTLA = new JTextField(4);
    JButton keyBA = new JButton("Add KeyFrame");

    JLabel keyFLM = new JLabel("Values to modify in KeyFrame. Format: name t x " +
            "y w h r g b");
    JTextField keyTLM = new JTextField(10);
    JButton keyBM = new JButton("Modify KeyFrame");


    play.addActionListener(e -> eView.pauseAndResume());
    restart.addActionListener(e -> eView.restart());
    loop.addActionListener(e -> eView.loopAnimation());
    loop.addActionListener(e -> this.switchColor(loop));
    speedUp.addActionListener(e -> eView.upSpeed());
    slowDown.addActionListener(e -> eView.downSpeed());
    addShape.addActionListener(e -> a.addShape(shapeName.getText(), shapeType.getText()));

    try {
      keyFrameButtonR.addActionListener(e -> {
        if (keyFrameFieldR.getText().split(" ").length == 9) {
          a.removeKeyFrame(keyFrameFieldR.getText().split(" ")[0],
                  Integer.parseInt(keyFrameFieldR.getText().split(" ")[1]),
                  Integer.parseInt(keyFrameFieldR.getText().split(" ")[2]),
                  Integer.parseInt(keyFrameFieldR.getText().split(" ")[3]),
                  Integer.parseInt(keyFrameFieldR.getText().split(" ")[4]),
                  Integer.parseInt(keyFrameFieldR.getText().split(" ")[5]),
                  Integer.parseInt(keyFrameFieldR.getText().split(" ")[6]),
                  Integer.parseInt(keyFrameFieldR.getText().split(" ")[7]),
                  Integer.parseInt(keyFrameFieldR.getText().split(" ")[8]));
        } else {
          throw new IllegalArgumentException("Must have 9 space separated values.");
        }
      });
    } catch (Exception e) {
      System.out.print("Bad input");
    }

    removeShape.addActionListener(e -> a.removeShape(shapeTypeRemove.getText()));

    try {
      keyBA.addActionListener(e -> a.addKeyFrame(keyTLA.getText().split(" ")[0],
              Integer.parseInt(keyTLA.getText().split(" ")[1])));
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.print("Bad input");
    }

    try {
      keyBM.addActionListener(e -> {
        if (keyTLM.getText().split(" ").length == 9) {
          a.modifyKeyFrame(keyTLM.getText().split(" ")[0],
                  Integer.parseInt(keyTLM.getText().split(" ")[1]),
                  Integer.parseInt(keyTLM.getText().split(" ")[2]),
                  Integer.parseInt(keyTLM.getText().split(" ")[3]),
                  Integer.parseInt(keyTLM.getText().split(" ")[4]),
                  Integer.parseInt(keyTLM.getText().split(" ")[5]),
                  Integer.parseInt(keyTLM.getText().split(" ")[6]),
                  Integer.parseInt(keyTLM.getText().split(" ")[7]),
                  Integer.parseInt(keyTLM.getText().split(" ")[8]));
        } else {
          throw new IllegalArgumentException("Must have 9 space separated values.");
        }
      });
    } catch (Exception e) {
      System.out.print("Bad input");
    }

    buttonPanel.add(play);
    buttonPanel.add(restart);
    buttonPanel.add(loop);
    buttonPanel.add(slowDown);
    buttonPanel.add(speedUp);
    buttonPanel.add(speedUp);
    buttonPanel.add(slowDown);
    textPanel.add(shapeNameLabel);
    textPanel.add(shapeName);
    textPanel.add(shapeTypeLabel);
    textPanel.add(shapeType);
    textPanel.add(addShape);
    textPanel.add(shapeTypeLabelRemove);
    textPanel.add(shapeTypeRemove);
    textPanel.add(removeShape);
    textPanel.add(keyframeLabelR);
    textPanel.add(keyFrameFieldR);
    textPanel.add(keyFrameButtonR);

    textPanel.add(keyFLA);
    textPanel.add(keyTLA);
    textPanel.add(keyBA);

    textPanel.add(keyFLM);
    textPanel.add(keyTLM);
    textPanel.add(keyBM);

    frame.add(buttonPanel);
    frame.add(textPanel);

    frame.pack();
    eView.beginVisual();
  }

  @Override
  public AnimatorModel getModel() {
    return this.a;
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
