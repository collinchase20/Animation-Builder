import org.junit.Assert;
import org.junit.Test;

import cs3500.animator.model.motion.AnimatorModel;
import cs3500.animator.model.motion.AnimatorModelImpl;
import cs3500.animator.model.motion.Motion;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for the SVG View SVGView.
 */
public class SVGViewTest {


  @Test
  public void SVGViewRectangles() {
    AnimatorModel testModel = new AnimatorModelImpl();

    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("R", "rectangle");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
                    0, 10, 50, 60, 200, 150, 0, 0, 50),
            "R");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200,
            0, 200, 11, 50, 60, 200, 150, 0, 0,
            50), "R");

    IView textualView = new SVGView(testModel);


    assertEquals("<svg width=\"360\" height=\"360\" version=\"1.1\" xmlns" +
            "=\"http://www.w3.org/2" +
            "000/svg\">\n" +
            "<rect id=\"R\" x=\"50\" y=\"50\" width=\"200\" height=\"200\" fill" +
            "=\"rgb(0,0,0)\" visibility=\"v" +
            "isible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribut" +
            "eName=\"x\" from=\"50\" to" +
            "=\"50\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribu" +
            "teName=\"y\" from=\"50" +
            "\" to=\"60\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribu" +
            "teName=\"width\" from" +
            "=\"200\" to=\"200\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribu" +
            "teName=\"height\" fro" +
            "m=\"200\" to=\"150\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribu" +
            "teName=\"fill\" fro" +
            "m=\"rgb(0,0,0)\" to=\"rgb(0,0,50)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attrib" +
            "uteName=\"x\" fro" +
            "m=\"60\" to=\"50\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attrib" +
            "uteName=\"y\" from=\"25\" to=\"6" +
            "0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attrib" +
            "uteName=\"width\" from=\"150\" to" +
            "=\"200\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attribut" +
            "eName=\"height\" from=\"150\" to" +
            "=\"150\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attribute" +
            "Name=\"fill\" from=\"rgb(200,0" +
            ",200)\" to=\"rgb(0,0,50)\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>", textualView.displayView());
  }

  @Test
  public void SVGViewEllipses() {
    AnimatorModel testModel = new AnimatorModelImpl();

    testModel.setCanvas(50, 50, 360, 360);
    testModel.addShape("E", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
                    0, 10, 50, 60, 200, 150, 0, 0, 50),
            "E");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200, 0,
            200, 11, 50, 60, 200, 150, 0, 0,
            50), "E");

    IView textualView = new SVGView(testModel);


    assertEquals("<svg width=\"360\" height=\"360\" version=\"1.1\" xml" +
            "ns=\"http://www.w3.org/2000/" +
            "svg\">\n" +
            "<ellipse id=\"E\" cx=\"50\" cy=\"50\" rx=\"200\" ry=\"200\" fill=\"r" +
            "gb(0,0,0)\" visibility=\"vi" +
            "sible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribute" +
            "Name=\"cx\" from=\"50\" to=\"" +
            "50\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribute" +
            "Name=\"cy\" from=\"50\" to=\"6" +
            "0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribute" +
            "Name=\"rx\" from=\"200\" to=\"2" +
            "00\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribute" +
            "Name=\"ry\" from=\"200\" to=\"15" +
            "0\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attribute" +
            "Name=\"fill\" from=\"rgb(0,0,0)" +
            "\" to=\"rgb(0,0,50)\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attribut" +
            "eName=\"cx\" from=\"60\" to" +
            "=\"50\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attribut" +
            "eName=\"cy\" from=\"25\" to" +
            "=\"60\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attribut" +
            "eName=\"rx\" from=\"150\" t" +
            "o=\"200\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attribut" +
            "eName=\"ry\" from=\"150\" t" +
            "o=\"150\" fill=\"remove\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attribute" +
            "Name=\"fill\"" +
            " from=\"rgb(200,0,200)\" to=\"rgb(0,0,50)\" fill=\"remove\" />\n" +
            "</ellipse>\n" +
            "</svg>", textualView.displayView());
  }

  @Test
  public void SVGTestEllipsesandRectangles() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new SVGView(testModel);


    testModel.setCanvas(100, 100, 50, 50);
    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0, 0,
                    10, 50, 60, 200, 150, 0, 0, 50),
            "C");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0, 0,
                    10, 50, 60, 200, 150, 0, 0, 50),
            "R");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200, 0, 200,
            11, 50, 60, 200, 150, 0, 0,
            50), "R");


    Assert.assertEquals("<svg width=\"50\" height=\"50\" version=\"1.1\" xmlns=\"http:" +
                    "//www.w3.org/200" +
                    "0/svg\">\n" +
                    "<rect id=\"R\" x=\"50\" y=\"50\" width=\"200\" height=\"200\" fill=\"rgb(" +
                    "0,0,0)\" visibili" +
                    "ty=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeName" +
                    "=\"x\" from=\"50\"" +
                    " to=\"50\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeName" +
                    "=\"y\" from=\"50\"" +
                    " to=\"60\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeName" +
                    "=\"width\" from=\"" +
                    "200\" to=\"200\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeNam" +
                    "e=\"height\" from=\"" +
                    "200\" to=\"150\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeNam" +
                    "e=\"fill\" from=\"r" +
                    "gb(0,0,0)\" to=\"rgb(0,0,50)\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attributeNa" +
                    "me=\"x\" from=\"60\"" +
                    " to=\"50\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attributeNa" +
                    "me=\"y\" from=\"25\"" +
                    " to=\"60\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attributeNa" +
                    "me=\"width\" from=\"" +
                    "150\" to=\"200\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attributeNa" +
                    "me=\"height\" from=\"" +
                    "150\" to=\"150\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attributeN" +
                    "ame=\"fill\" from=\"r" +
                    "gb(200,0,200)\" to=\"rgb(0,0,50)\" fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "<ellipse id=\"C\" cx=\"50\" cy=\"50\" rx=\"200\" ry=\"200\" fill=\"rgb" +
                    "(0,0,0)\" visibility" +
                    "=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeNa" +
                    "me=\"cx\" from=\"50\"" +
                    " to=\"50\" fill=\"remove\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeNa" +
                    "me=\"cy\" from=\"50\"" +
                    " to=\"60\" fill=\"remove\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeNa" +
                    "me=\"rx\" from=\"" +
                    "200\" to=\"200\" fill=\"remove\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeNa" +
                    "me=\"ry\" from" +
                    "=\"200\" to=\"150\" fill=\"remove\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attributeNa" +
                    "me=\"fill\" fro" +
                    "m=\"rgb(0,0,0)\" to=\"rgb(0,0,50)\" fill=\"remove\" />\n" +
                    "</ellipse>\n" +
                    "</svg>",
            textualView.displayView());

  }

  @Test
  public void removeShapeTestSVG() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new SVGView(testModel);


    testModel.setCanvas(100, 100, 50, 50);
    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0,
            0, 0, 10, 50, 60, 200, 150, 0, 0,
            50), "C");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0, 50),
            "R");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200, 0,
            200, 11, 50, 60, 200, 150, 0, 0, 50),
            "R");
    testModel.removeShape("C");


    assertEquals("<svg width=\"50\" height=\"50\" version=\"1.1\" xmln" +
                    "s=\"http://www.w3.org/2000/svg\">\n" +
                    "<rect id=\"R\" x=\"50\" y=\"50\" width=\"200\" height=\"2" +
                    "00\" fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500" +
                    "ms\" attributeName=\"x\" from=\"50\" to=\"50\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"50" +
                    "0ms\" attributeName=\"y\" from=\"50\" to=\"60\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"50" +
                    "0ms\" attributeName=\"width\" from=\"200\" to=\"200\" fil" +
                    "l=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"50" +
                    "0ms\" attributeName=\"height\" from=\"200\" to=\"150\" fill=\"fr" +
                    "eeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"50" +
                    "0ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" t" +
                    "o=\"rgb(0,0,50)\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"5" +
                    "0ms\" attributeName=\"x\" from=\"60\" to=\"50\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"5" +
                    "0ms\" attributeName=\"y\" from=\"25\" to=\"60\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"5" +
                    "0ms\" attributeName=\"width\" from=\"150\" to=\"200\" " +
                    "fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"5" +
                    "0ms\" attributeName=\"height\" from=\"150\" to=\"150\" f" +
                    "ill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"5" +
                    "0ms\" attributeName=\"fill\" from=\"rgb(200,0,20" +
                    "0)\" to=\"rgb(0,0,50)\" fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "</svg>",
            textualView.displayView());

  }

  @Test
  public void removeMotionTestSVG() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new SVGView(testModel);


    testModel.setCanvas(100, 100, 50, 50);
    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0, 0,
            10, 50, 60, 200, 150, 0, 0, 50), "C");
    testModel.addMotion(new Motion(10, 50, 50, 200, 150, 0, 0, 0,
            20, 50, 60, 200, 150, 0, 0, 50), "C");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0, 50), "R");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200, 0,
            200, 11, 50, 60, 200, 150, 0, 0, 50), "R");
    testModel.removeMotion(0, 50, 50, 200, 200, 0, 0, 0,
            10, 50, 60, 200, 150, 0, 0, 50);


    assertEquals("<svg width=\"50\" height=\"50\" version=\"1.1\" xmlns=\"htt" +
                    "p://www.w3.org/2000/svg\">\n" +
                    "<rect id=\"R\" x=\"50\" y=\"50\" width=\"200\" height=\"200\" fi" +
                    "ll=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attri" +
                    "buteName=\"x\" from=\"50\" to=\"50\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attrib" +
                    "uteName=\"y\" from=\"50\" to=\"60\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attrib" +
                    "uteName=\"width\" from=\"200\" to=\"200\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attrib" +
                    "uteName=\"height\" from=\"200\" to=\"150\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"500ms\" attrib" +
                    "uteName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(0,0,50)\" fill=\"fr" +
                    "eeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" attri" +
                    "buteName=\"x\" from=\"60\" to=\"50\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" att" +
                    "ributeName=\"y\" from=\"25\" to=\"60\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" at" +
                    "tributeName=\"width\" from=\"150\" to=\"200\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" a" +
                    "ttributeName=\"height\" from=\"150\" to=\"150\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50ms\" at" +
                    "tributeName=\"fill\" from=\"rgb(200,0,200)\" to=\"rgb(0,0,5" +
                    "0)\" fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "<ellipse id=\"C\" cx=\"50\" cy=\"50\" rx=\"200\" ry=\"150\" f" +
                    "ill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"500" +
                    "ms\" attributeName=\"cx\" from=\"50\" to=\"50\" fill=\"remove\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50" +
                    "0ms\" attributeName=\"cy\" from=\"50\" to=\"60\" fill=\"remove\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"50" +
                    "0ms\" attributeName=\"rx\" from=\"200\" to=\"200\" fill=\"remove\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"5" +
                    "00ms\" attributeName=\"ry\" from=\"150\" to=\"150\" fill" +
                    "=\"remove\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"5" +
                    "00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to" +
                    "=\"rgb(0,0,50)\" fill=\"remove\" />\n" +
                    "</ellipse>\n" +
                    "</svg>",
            textualView.displayView());

  }

  @Test(expected = IllegalArgumentException.class)
  public void removeMotionTestSVGExceptionNoMoreMotionsInElipse() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new SVGView(testModel);


    testModel.setCanvas(100, 100, 50, 50);
    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0, 0,
            10, 50, 60, 200, 150, 0, 0, 50), "C");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0, 0,
            0, 10, 50, 60, 200, 150, 0, 0, 50), "R");
    testModel.addMotion(new Motion(10, 60, 25, 150, 150, 200, 0,
            200, 11, 50, 60, 200, 150, 0, 0,
            50), "R");
    testModel.removeMotion(0, 50, 50, 200, 200, 0, 0, 0,
            10, 50, 60, 200, 150, 0, 0, 50);
    textualView.displayView();


  }

  @Test(expected = IllegalArgumentException.class)
  public void NotInitalizingCanvasBeforeTryingtoDisplayView() {
    AnimatorModel testModel = new AnimatorModelImpl();

    IView textualView = new SVGView(testModel);

    testModel.addShape("R", "rectangle");
    testModel.addShape("C", "ellipse");
    testModel.addMotion(new Motion(0, 50, 50, 200, 200, 0,
                    0, 0, 10, 50, 60, 200, 150,
                    0, 0, 50),
            "R");
    textualView.displayView();

  }

  @Test(expected = IllegalArgumentException.class)
  public void nullModelTest() {
    AnimatorModel testModel = null;
    IView view = new SVGView(testModel);
  }
}
