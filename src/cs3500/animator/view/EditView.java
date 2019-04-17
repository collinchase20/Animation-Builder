package cs3500.animator.view;


import java.awt.*;
import javax.swing.*;

/**
 * Our EditView interface. Converts our visual animation into a visual animation that is able to be
 * used with a controller for editing the animation functionality.
 */
public class EditView extends JFrame implements IEditor {

  JButton play;
  JButton restart;
  JButton loop;
  JButton speedUp;
  JButton slowDown;
  JButton addShape;
  JButton removeShape;
  JButton keyFrameButtonR;
  JButton keyBA;
  JButton keyBM;
  JTextField shapeType;
  JTextField shapeName;
  JTextField shapeTypeRemove;
  JTextField keyFrameFieldR;
  JTextField keyTLA;
  JTextField keyTLM;
  private GraphicView visualView;
  private JFrame frame = new JFrame();


  /**
   * Constructor for a EditView.
   *
   * @param v an instance of our GraphicView
   */

  public EditView(GraphicView v) {
    visualView = v;

    frame.setVisible(true);
    frame.add(this.getFrame());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new FlowLayout());

    JPanel buttonPanel = new JPanel();

    play = new JButton("Play/Pause");
    restart = new JButton("Restart");
    loop = new JButton("Loop");
    speedUp = new JButton("Speed Up");
    slowDown = new JButton("Slow Down");
    loop.setBackground(Color.red);

    JPanel textPanel = new JPanel();

    JLabel shapeNameLabel = new JLabel("Name of shape to add");
    shapeName = new JTextField(8);

    JLabel shapeTypeLabel = new JLabel("Type of shape to add");
    shapeType = new JTextField(8);
    addShape = new JButton("Add Shape");

    JLabel shapeTypeLabelRemove = new JLabel("Name of shape to remove");
    shapeTypeRemove = new JTextField(8);
    removeShape = new JButton("Remove Shape");

    JLabel keyframeLabelR = new JLabel("KeyFrame to be removed.Format: name t " +
            "x y w h r g b");
    keyFrameFieldR = new JTextField(10);
    keyFrameButtonR = new JButton("Remove KeyFrame");

    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

    JLabel keyFLA = new JLabel("Shape and Time to add KeyFrame at. Format: name time");
    keyTLA = new JTextField(4);
    keyBA = new JButton("Add KeyFrame");

    JLabel keyFLM = new JLabel("Values to modify in KeyFrame. Format: name t x " +
            "y w h r g b");
    keyTLM = new JTextField(10);
    keyBM = new JButton("Modify KeyFrame");

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

  }

  @Override
  public JButton sendPlay() {
    return this.play;
  }

  @Override
  public JButton sendRestart() {
    return this.restart;
  }

  @Override
  public JButton sendLoop() {
    return this.loop;
  }

  @Override
  public JButton sendSlowDown() {
    return this.slowDown;
  }

  @Override
  public JButton sendSpeedUp() {
    return this.speedUp;
  }

  @Override
  public JButton sendAddShape() {
    return this.addShape;
  }

  @Override
  public JButton sendRemoveShape() {
    return this.removeShape;
  }

  @Override
  public JButton sendKeyFrameButtonR() {
    return this.keyFrameButtonR;
  }

  @Override
  public JButton sendKeyBA() {
    return this.keyBA;
  }

  @Override
  public JButton sendKeyBM() {
    return this.keyBM;
  }

  @Override
  public JTextField sendShapeName() {
    return this.shapeName;
  }

  @Override
  public JTextField sendShapeType() {
    return this.shapeType;
  }

  @Override
  public JTextField sendShapeTypeRemove() {
    return this.shapeTypeRemove;
  }

  @Override
  public JTextField sendKeyFrameFieldR() {
    return this.keyFrameFieldR;
  }

  @Override
  public JTextField sendKeyTLM() {
    return this.keyTLM;
  }

  @Override
  public JTextField sendKeyTLA() {
    return this.keyTLA;
  }

  @Override
  public void pauseAndResume() {
    visualView.pauseOrPlay();
  }


  @Override
  public void restart() {
    visualView.setTick(0);
  }

  @Override
  public void loopAnimation() {
    visualView.loopOrNoLoop();
  }


  @Override
  public void beginVisual() {
    visualView.startAnimation();
  }

  @Override
  public Component getFrame() {
    return visualView.jf;
  }

  @Override
  public void upSpeed() {
    visualView.increaseSpeed();
  }

  @Override
  public void downSpeed() {
    visualView.decreaseSpeed();
  }

}

