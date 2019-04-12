import org.junit.Test;

import cs3500.animator.model.motion.AnimatorModel;
import cs3500.animator.model.motion.AnimatorModelImpl;
import cs3500.animator.model.motion.Motion;
import cs3500.animator.view.IView;
import cs3500.animator.view.TView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Textual View TView.
 */
public class TViewTest {

  @Test
  public void TViewTestMultipleAnimationsAndShapes() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);


    testModel.setCanvas(100, 100, 50, 50);
    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0,
            0, 0, 10, 50, 60, 200, 150, 0, 0,
            50), "C");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0,
            0, 0, 10,
            50, 60, 200, 150, 0, 0, 50), "R");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200,
            0, 200,
            11, 50, 60, 200, 150, 0, 0, 50), "R");


    assertEquals("canvas 100 100 50 50 \n" +
                    "shape R rectangle\n" +
                    "motion R 0 50 50 200 200 0 0 0 10 50 60 200 150 0 0 50\n" +
                    "motion R 10 60 25 150 150 200 0 200 11 50 60 200 150 0 0 50\n" +
                    "shape C ellipse\n" +
                    "motion C 0 50 50 200 200 0 0 0 10 50 60 200 150 0 0 50\n",
            textualView.displayView());

  }

  @Test
  public void removeShapeTest() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);


    testModel.setCanvas(100, 100, 50, 50);
    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0,
            0, 0, 10, 50, 60, 200, 150, 0,
            0, 50), "C");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0,
            50), "R");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200, 0,
            200, 11, 50, 60, 200, 150, 0, 0,
            50), "R");
    testModel.removeShape("C");


    assertEquals("canvas 100 100 50 50 \n" +
                    "shape R rectangle\n" +
                    "motion R 0 50 50 200 200 0 0 0 10 50 60 200 150 0 0 50\n" +
                    "motion R 10 60 25 150 150 200 0 200 11 50 60 200 150 0 0 50\n",
            textualView.displayView());

  }

  @Test
  public void removeMotionTest() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);


    testModel.setCanvas(100, 100, 50, 50);
    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0,
                    0, 0,
            10, 50, 60, 200, 150, 0, 0, 50),
            "C");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0,
            50), "R");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200, 0,
            200, 11, 50, 60, 200, 150, 0, 0,
            50), "R");
    testModel.removeMotion(0, 50, 50, 200, 200, 0, 0, 0,
            10, 50, 60, 200, 150, 0, 0, 50);


    assertEquals("canvas 100 100 50 50 \n" +
                    "shape R rectangle\n" +
                    "motion R 0 50 50 200 200 0 0 0 10 50 60 200 150 0 0 50\n" +
                    "motion R 10 60 25 150 150 200 0 200 11 50 60 200 150 0 0 50\n" +
                    "shape C ellipse\n",
            textualView.displayView());

  }

  @Test(expected = IllegalArgumentException.class)
  public void removeShapeThatDoesNotExist() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);

    testModel.setCanvas(100, 100, 50, 50);

    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0, 50), "R");
    testModel.removeShape("notAShape");

  }

  @Test(expected = IllegalArgumentException.class)
  public void removeMotionThatDoesNotExist() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);

    testModel.setCanvas(100, 100, 50, 50);

    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0, 50), "R");
    testModel.removeMotion(0, 100, 150, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0, 0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void NotInitalizingCanvasBeforeTryingtoDisplayView() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);

    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200,
            0, 0,
            0, 10, 50, 60, 200, 150, 0, 0,
            50), "R");
    textualView.displayView();

  }


  @Test(expected = IllegalArgumentException.class)
  public void InvalidShapeTest() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);

    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200,
            0,
            0, 0, 10, 50, 60, 200, 150,
            0, 0, 50), "O");

  }


  @Test(expected = IllegalArgumentException.class)
  public void EndTimeLessThanStartTime() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);

    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(5, 50, 50, 200, 200,
            0, 0,
            0, 4, 50, 60, 200, 150, 0, 0,
            50), "R");

  }

  @Test(expected = IllegalArgumentException.class)
  public void PreviousEndTimeGreaterThanCurrentStartTime() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);

    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0,
            0, 0, 10, 50, 60, 200, 150, 0,
            0, 50), "R");
    testModel.addMotion(new Motion(9, 50, 50, 200, 200, 0,
            0, 0, 15, 50, 60, 200, 150, 0,
            0, 50), "R");

  }

  @Test(expected = IllegalArgumentException.class)
  public void NegativeTime() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(testModel);

    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(-5, 50, 50, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0,
            50), "R");
    testModel.addMotion(new Motion(10, 50, 50, 200, 200, 0, 0,
            0, 15, 50, 60, 200, 150, 0, 0,
            50), "R");

  }

  @Test(expected = IllegalArgumentException.class)
  public void NullModelException() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new TView(null);

  }
}
