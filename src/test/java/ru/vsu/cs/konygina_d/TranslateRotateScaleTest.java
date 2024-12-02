package ru.vsu.cs.konygina_d;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.konygina_d.render_engine.*;

import javax.vecmath.*;

public class TranslateRotateScaleTest {
    @Test
    public void testScale() {
        Vector4f expectedVec = new Vector4f(2, 3, 5, 1);

        AffineTransformation affineTransformation = new Scaling(2, 3, 5);
        Vector4f vec = new Vector4f(1, 1, 1, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testRotate() {
        Vector4f expectedVec = new Vector4f(1, 1, -1, 1);

        AffineTransformation affineTransformation = new Rotator(90, 90, 90);
        Vector4f vec = new Vector4f(1, 1, 1, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testTranslate() {
        Vector4f expectedVec = new Vector4f(9, -5, 2, 1);

        AffineTransformation affineTransformation = new Translator(9, -5, 2);
        Vector4f vec = new Vector4f(0, 0, 0, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testDefaultTransform() {
        Vector4f expectedVec = new Vector4f(9, -5, 2, 1);

        AffineTransformation affineTransformation = new Transformation();
        Vector4f vec = new Vector4f(9, -5, 2, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testScaleTransform() {
        Vector4f expectedVec = new Vector4f(9, -5, 2, 1);

        AffineTransformation affineTransformation = new Transformation(new Scaling(9, -5, 2));
        Vector4f vec = new Vector4f(1, 1, 1, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testRotateTransform() {
        Vector4f expectedVec = new Vector4f(1, 1, -1, 1);

        AffineTransformation affineTransformation = new Transformation(new Rotator(90, 90, 90));
        Vector4f vec = new Vector4f(1, 1, 1, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }

    @Test
    public void testTranslateTransform() {
        Vector4f expectedVec = new Vector4f(9, -5, 2, 1);

        AffineTransformation affineTransformation = new Transformation(new Translator(9, -5, 2));
        Vector4f vec = new Vector4f(0, 0, 0, 1);

        Assertions.assertTrue(expectedVec.epsilonEquals(affineTransformation.transform(vec), 0.00001F));
    }
}
