package ru.vsu.cs.konygina_d;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.konygina_d.render_engine.*;

import javax.vecmath.Matrix4f;

public class MatrixTest {

    @Test
    public void testScaleMatrix() {
        Matrix4f expectedMatrix = new Matrix4f(
                2, 0, 0, 0,
                0, 3, 0, 0,
                0, 0, 5, 0,
                0, 0, 0, 1);

        Scaling s = new Scaling(2, 3, 5);
        Assertions.assertTrue(expectedMatrix.epsilonEquals(s.getMatrix(), 0.00001F));
    }

    @Test
    public void testRotateMatrix() {
        Matrix4f expectedMatrix = new Matrix4f(
                0, 0, 1, 0,
                0, -1, 0, 0,
                1, 0, 0, 0,
                0, 0, 0, 1);

        AffineTransformation affineTransformation = new Transformation(
                new Rotator(90, Rotator.Axis.X),
                new Rotator(90, Rotator.Axis.Y),
                new Rotator(90, Rotator.Axis.Z)
        );
        Assertions.assertTrue(expectedMatrix.epsilonEquals(affineTransformation.getMatrix(), 0.00001F));
    }

    @Test
    public void testTranslateMatrix() {
        Matrix4f expectedMatrix = new Matrix4f(
                1, 0, 0, 5,
                0, 1, 0, -2,
                0, 0, 1, 3,
                0, 0, 0, 1);

        Translator t = new Translator(5, -2, 3);
        Assertions.assertTrue(expectedMatrix.epsilonEquals(t.getMatrix(), 0.00001F));
    }
}
