package cs3500.animator.provider.view.enhancedview;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.Keyframe;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.provider.view.IView;
import cs3500.animator.provider.view.VisualPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import cs3500.animator.model.motion.AnimatorModel;
import cs3500.animator.model.motion.AnimatorModelImpl;

/**
 * Represents an enhanced visual view that allows for manipulation of the animation playback.
 */
public class EnhancedVisualView extends JFrame implements IView, ActionListener {

  private final JFileChooser fc = new JFileChooser();

  private VisualPanel panel;

  private AnimatorModel model;

  private JButton play;
  private String playTitle;

  private JMenuItem add = new JMenuItem("Add shape");
  private JMenuItem openFile;
  private JMenuItem saveFile;
  private JMenu shapes = new JMenu("Shapes");


  private Controller c;

  /**
   * Constructs an instance of an EnhancedVisualView based on a model and a speed.
   */
  public EnhancedVisualView() {
    super();
    openFile = this.openFileWin();
    saveFile = this.saveFileWin();


  }

  private JMenuItem openFileWin() {
    return new JMenuItem(new AbstractAction("Open") {
      @Override
      public void actionPerformed(ActionEvent e) {
        int refValue = fc.showOpenDialog(openFile);

        if (refValue == JFileChooser.APPROVE_OPTION) {
          File file = fc.getSelectedFile();
          try {
            new AnimationReader().parseFile(new FileReader(file), new AnimatorModelImpl.Builder());
          } catch (IOException ioe) {
            ioe.printStackTrace();
          }
        }
      }
    });
  }

  private JMenuItem saveFileWin() {
    return new JMenuItem(new AbstractAction("Save") {
      @Override
      public void actionPerformed(ActionEvent e) {
        fc.showSaveDialog(saveFile);
      }
    });
  }

  private JMenuItem addKeyframe() {
    return new JMenuItem(new AbstractAction("Add keyframe") {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Add keyframe");

        panel.getTimer().stop();
        makeWindow("keyframe");

        panel.setTick(0);
        panel.getTimer().start();

      }
    });
  }

  void addOneKeyframe(Keyframe k) {
    for (int i = 0; i < shapes.getItemCount(); i++) {
      JMenuItem item = shapes.getItem(i);

      if (item.getText().equals(k.getName())) {

        JMenu kfMenu = new JMenu(" t: " + k.getT1() + " x: "
            + k.getX1() + " y: " + k.getY1() + " w: " + k.getW1() + " h: " + k.getH1()
            + " col(" + k.getR1() + ", " + k.getG1() + ", " + k.getB1() + ")");

        kfMenu.add(editKeyframe((JMenu) item, k, i));
        kfMenu.add(delKeyframe((JMenu) item, i, k));

        item.add(kfMenu);
      }
    }
  }

  private void makeWindow(String listenType) {
    // adds the frame
    JFrame window = new JFrame();
    window.setPreferredSize(new Dimension(500, 500));
    // creates panel
    JPanel window2 = new JPanel();
    window2.setPreferredSize(new Dimension(500, 500));
    // adding panel to frame
    window.add(window2);
    window.setVisible(true);
    window2.setVisible(true);
    // creates text box for user input
    TextArea type = new TextArea();
    window2.add(type);
    // creates submit button
    JButton submit = new JButton("Submit");
    window2.add(submit, BorderLayout.NORTH);
    ActionListener al = null;

    JLabel description = new JLabel();

    switch (listenType) {
      case "keyframe":
        al = new KeyframeButtonListener(this, type, window);
        description = new JLabel("name {value} t1 {value} x1 {value] y1 {value} w1 {value} "
            + "h1 {value} r1 {value} g1 {value} b1 {value} t2 {value} x2 {value] y2 {value} "
            + "w2 {value} h2 {value} r2 {value} g2 {value} b2 {value}");
        break;
      case "add shape":
        al = new ShapeButtonListener(this, type, window);
        description = new JLabel("Format: {name} {type}");
        break;
      case "edit keyframe":
        break;
      default:
        description = new JLabel("Please enter in correct format");
        break;
    }

    submit.addActionListener(al);

    // label

    window2.add(description);

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private JMenuItem deleteShape(String shapeToRemove, int i) {
    return new JMenuItem(new AbstractAction("Delete Shape") {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.getTimer().stop();
        System.out.println("Delete shape");
        c.removeShape(shapeToRemove);
        shapes.remove(i);
        panel.setTick(0);
        panel.getTimer().restart();
      }
    });
  }

  private JMenuItem addShape() {
    return new JMenuItem(new AbstractAction("Add shape") {
      @Override
      public void actionPerformed(ActionEvent e) {
        makeWindow("add shape");
        System.out.println("Add shape");
      }
    });
  }

  private JMenuItem editKeyframe(JMenu j, Keyframe k, int i) {
    return new JMenuItem(new AbstractAction("Edit") {
      @Override
      public void actionPerformed(ActionEvent e) {
        makeEditWindow(j, i, k);


      }
    });
  }

  private void makeEditWindow(JMenu j, int i, Keyframe k) {
    // adds the frame
    JFrame window = new JFrame();
    window.setPreferredSize(new Dimension(1000, 1000));
    // creates panel
    JPanel window2 = new JPanel();
    window2.setPreferredSize(new Dimension(1000, 1000));
    // adding panel to frame
    // adding panel to frame
    window.add(window2);
    window.setVisible(true);
    window2.setVisible(true);
    // creates text box for user input
    TextArea type = new TextArea();
    window2.add(type);
    // creates submit button
    JButton submit = new JButton("Submit");
    window2.add(submit, BorderLayout.NORTH);
    submit.addActionListener(new EditKeyframeListener(this, k, j, type, i, window));

    // label
    JLabel description = new JLabel("t1 {value} x1 {value] y1 {value} w1 {value} "
        + "h1 {value} r1 {value} g1 {value} b1 {value} t2 {value} x2 {value] y2 {value} "
        + "w2 {value} h2 {value} r2 {value} g2 {value} b2 {value}");
    window2.add(description);

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    System.out.println("Edit Keyframe");
  }

  private JMenuItem delKeyframe(JMenu s, int i, Keyframe k) {
    return new JMenuItem(new AbstractAction("Delete Keyframe") {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.getTimer().stop();
        System.out.println("Delete Keyframe");
        s.remove(i);
        c.deleteKeyframe(k);
        panel.setTick(0);
        panel.getTimer().restart();
      }
    });
  }

  private void addAllKeyframes(String s, JMenu t) {
    int i = 0;
    for (Keyframe k : model.getKeyframes()) {

      if (k.getName().equals(s)) {

        JMenu kfMenu = new JMenu(" t: " + k.getT1() + " x: "
            + k.getX1() + " y: " + k.getY1() + " w: " + k.getW1() + " h: " + k.getH1()
            + " col(" + k.getR1() + ", " + k.getG1() + ", " + k.getB1() + ")");

        kfMenu.add(editKeyframe(t, k, i));
        kfMenu.add(delKeyframe(t, i, k));

        t.add(kfMenu);
        i++;
      }
    }
  }

  void addShapeToMenu(String name) {
    JMenu newShape = new JMenu(name);
    newShape.add(addKeyframe());
    newShape.add(deleteShape(name, shapes.getItemCount()));
    shapes.add(newShape);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  VisualPanel getPanel() {
    return this.panel;
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    // needs to be overridden
  }

  @Override
  public void showErrorMessage(String error) {
    // needs to be overridden
  }

  @Override
  public void refresh() {
    // needs to be overridden
  }

  @Override
  public AnimatorModel getAnimatorModel() {
    return this.model;
  }

  @Override
  public void addController(Controller c) {
    this.c = c;

    this.setUpMenus();
  }

  private void setUpMenus() {
    this.model = c.getModel();

    this.panel = new VisualPanel(model, 3);
    JScrollPane scrollBar = new JScrollPane(this.panel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrollBar);

    JMenuBar menu = new JMenuBar();

    JMenu file = new JMenu("File");
    file.setMnemonic(KeyEvent.VK_C);
    file.getAccessibleContext().setAccessibleDescription("Blah");

    file.add(openFile);
    file.add(saveFile);

    JMenu edit = new JMenu("Edit");
    edit.setMnemonic(KeyEvent.VK_C);
    edit.getAccessibleContext().setAccessibleDescription("Blah");

    edit.add(shapes);

    menu.add(file);
    menu.add(edit);

    add.addActionListener(this);

    int i = 0;

    for (String s : model.getAllShapes().keySet()) {
      JMenu temp = new JMenu(s);
      addAllKeyframes(s, temp);
      temp.add(addKeyframe());
      temp.add(deleteShape(s, i));
      shapes.add(temp);
      i++;
    }

    shapes.add(addShape()); //displays, but can't add actionListener

    this.playTitle = "Press to pause";
    this.play = new JButton(this.playTitle);
    panel.add(this.play, BorderLayout.NORTH);
    this.play.addActionListener(new PlayHandler(this));

    JButton restart = new JButton("Press to restart");
    panel.add(restart, BorderLayout.NORTH);
    restart.addActionListener(new RestartHandler(this));

    JCheckBox loop = new JCheckBox("Toggle to loop");
    panel.add(loop, BorderLayout.NORTH);
    loop.addActionListener(new LoopHandler(this));

    JButton speedUp = new JButton("Press to speed up");
    panel.add(speedUp, BorderLayout.NORTH);
    speedUp.addActionListener(new SpeedUpHandler(this));

    JButton slowDown = new JButton("Press to slow down");
    panel.add(slowDown, BorderLayout.NORTH);
    slowDown.addActionListener(new SlowDownHandler(this));

    this.setJMenuBar(menu);

    this.setVisible(true);
    this.pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  @Override
  public void setTitle(String s) {
    this.play.setText(s);
    this.playTitle = s;
  }

  Controller getController() {
    return this.c;
  }
}
