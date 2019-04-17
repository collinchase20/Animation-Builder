import org.junit.Test;

import cs3500.animator.model.motion.IAnimator;
import cs3500.animator.model.motion.IAnimatorImpl;
import cs3500.animator.model.motion.Motion;
import cs3500.animator.view.IView;
import cs3500.animator.view.TView;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for the the EditorView and
 * methods that go with it.
 */
public class EditorTest {

  @Test
  public void testBasicRemoveKeyFrame() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");
    testModel.addMotion(new Motion(0, 10, 10, 50, 50, 255, 0,
                    0, 20, 10, 10, 50, 50, 0, 255, 0),
            "R");
    testModel.addMotion(new Motion(20, 10, 10, 50, 50, 0,
            255, 0, 40, 10, 10, 50, 50, 0, 0,
            255), "R");
    IView visual = new TView(testModel);
    testModel.removeKeyFrame("R", 20, 10, 10, 50, 50, 0, 255, 0);
    assertEquals(visual.displayView(), "canvas 50 50 360 360 \n" +
            "shape R rectangle\n" +
            "motion R 0 10 10 50 50 255 0 0 40 10 10 50 50 0 0 255\n");
  }

  @Test
  public void testKeyFrameBeforeMotion() {
    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");
    testModel.addMotion(new Motion(10, 10, 10, 01, 01, 10, 0, 0,
            15, 0, 0, 3, 3, 5, 5, 5),"R");
    testModel.addKeyFrame("R",0);
    IView text = new TView(testModel);
    assertEquals("canvas 50 50 360 360 \n" +
            "shape R rectangle\n" +
            "motion R 0 0 0 0 0 0 0 0 10 10 0 3 3 5 5 5\n" +
            "motion R 10 10 10 1 1 10 0 0 15 0 0 3 3 5 5 5\n",text.displayView());
  }


  @Test
  public void testAddNewKeyFrame() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");

    IView visual = new TView(testModel);

    testModel.addKeyFrame("R", 0);
    assertEquals("canvas 50 50 360 360 \n" +
            "shape R rectangle\n" +
            "motion R 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n", visual.displayView());
  }

  @Test
  public void testAddAndModifyNewKeyFrame() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");

    IView visual = new TView(testModel);

    testModel.addKeyFrame("R", 0);
    testModel.modifyKeyFrame("R", 0, 20, 20, 20, 20, 0, 0, 255);
    assertEquals("canvas 50 50 360 360 \n" +
            "shape R rectangle\n" +
            "motion R 0 20 20 20 20 0 0 255 0 20 20 20 20 0 0 255\n", visual.displayView());
  }


  @Test
  public void testAddAndModifyMultipleKeyFrames() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");

    IView visual = new TView(testModel);

    testModel.addKeyFrame("R", 0);
    testModel.modifyKeyFrame("R", 0, 20, 20, 20, 20, 0, 0, 255);
    testModel.addKeyFrame("R", 10);
    testModel.modifyKeyFrame("R", 10, 40, 40, 40, 40, 0, 255, 0);
    assertEquals("canvas 50 50 360 360 \n" +
            "shape R rectangle\n" +
            "motion R 0 20 20 20 20 0 0 255 0 20 20 20 20 0 0 255\n" +
            "motion R 0 20 20 20 20 0 0 255 10 40 40 40 40 0 255 0\n", visual.displayView());
  }

  @Test
  public void testBasicAddKeyFrame() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");
    testModel.addMotion(new Motion(0, 10, 10, 30, 30, 255, 0, 0, 40, 50, 50, 70, 70, 0, 0, 255),
            "R");
    IView visual = new TView(testModel);
    testModel.addKeyFrame("R", 10);
    assertEquals(visual.displayView(), "canvas 50 50 360 360 \n"
            + "shape R rectangle\n"
            + "motion R 0 10 10 30 30 255 0 0 10 20 20 40 40 191 0 64\n"
            + "motion R 10 20 20 40 40 191 0 64 40 50 50 70 70 0 0 255\n");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveKeyFrameInvalidName() {
    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");
    testModel.addMotion(new Motion(0, 10, 10, 50, 50, 255, 0,
                    0, 20, 10, 10, 50, 50, 0, 255, 0),
            "R");
    testModel.addMotion(new Motion(20, 10, 10, 50, 50, 0,
            255, 0, 40, 10, 10, 50, 50, 0, 0,
            255), "R");
    IView visual = new TView(testModel);
    testModel.removeKeyFrame("B", 20, 10, 10, 50, 50, 0, 255, 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveKeyFrameInvalidTime() {
    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");
    testModel.addMotion(new Motion(0, 10, 10, 50, 50, 255, 0,
                    0, 20, 10, 10, 50, 50, 0, 255, 0),
            "R");
    testModel.addMotion(new Motion(20, 10, 10, 50, 50, 0,
            255, 0, 40, 10, 10, 50, 50, 0, 0,
            255), "R");
    IView visual = new TView(testModel);
    testModel.removeKeyFrame("R", 15, 10, 10, 50, 50, 0, 255, 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyFrameWhereThereAlreadyIsOne() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");

    IView visual = new TView(testModel);

    testModel.addKeyFrame("R", 0);
    testModel.addKeyFrame("R", 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddKeyFrameInvalidName() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");

    IView visual = new TView(testModel);

    testModel.addKeyFrame("B", 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testModifyKeyFrameNonExistentShape() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");

    IView visual = new TView(testModel);

    testModel.addKeyFrame("R", 0);
    testModel.modifyKeyFrame("C", 0, 20, 20, 20, 20, 0, 0, 255);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testModifyKeyFrameNonExistentTime() {

    IAnimator testModel = new IAnimatorImpl();
    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");

    IView visual = new TView(testModel);

    testModel.addKeyFrame("R", 0);
    testModel.modifyKeyFrame("R", 10, 20, 20, 20, 20, 0, 0, 255);

  }
}
