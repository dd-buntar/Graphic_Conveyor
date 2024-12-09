package ru.vsu.cs.konygina_d;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.konygina_d.render_engine.*;

import javax.vecmath.*;

public class TranslateRotateScaleTest {
    @Test
    public void testScale() {
        Vector3f expectedVec = new Vector3f(2, 3, 5);

        AffineTransformation affineTransformation = new Scaling(2, 3, 5);
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testRotate() {
        Vector3f expectedVec = new Vector3f(1, -1, 1);

        AffineTransformation affineTransformation = new Rotator(90, 90, 90);
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testTranslate() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        AffineTransformation affineTransformation = new Translator(9, -5, 2);
        Vector3f vec = new Vector3f(0, 0, 0);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testDefaultTransform() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        AffineTransformation affineTransformation = new Transformation();
        Vector3f vec = new Vector3f(9, -5, 2);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testScaleTransform() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        AffineTransformation affineTransformation = new Transformation(new Scaling(9, -5, 2));
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testRotateTransform() {
        Vector3f expectedVec = new Vector3f(1, -1, 1);

        AffineTransformation affineTransformation = new Transformation(new Rotator(90, 90, 90));
        Vector3f vec = new Vector3f(1, 1, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testTranslateTransform() {
        Vector3f expectedVec = new Vector3f(9, -5, 2);

        AffineTransformation affineTransformation = new Transformation(new Translator(9, -5, 2));
        Vector3f vec = new Vector3f(0, 0, 0);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }
}
