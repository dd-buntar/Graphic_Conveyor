package ru.vsu.cs.konygina_d.render_engine;

import ru.vsu.cs.konygina_d.math.MatMath;
import ru.vsu.cs.konygina_d.math.VectorConverter;

import javax.vecmath.*;
import java.util.Objects;

public class Rotator implements AffineTransformation {
    private final float angle;
    private final Axis axis;

    public enum Axis {
        X, Y, Z;
    }

    public Rotator(int dangle, Rotator.Axis axis) {
        this.angle = (float) Math.toRadians(dangle);
        this.axis = axis;
    }

    public Rotator(float rangle, Rotator.Axis axis) {
        this.angle = rangle;
        this.axis = axis;
    }

    @Override
    public Matrix4f getMatrix() {
        float cosA = (float) Math.cos(angle);
        float sinA = (float) Math.sin(angle);

        switch (axis) {
            case X -> {
                return new Matrix4f(
                        1, 0, 0, 0,
                        0, cosA, -sinA, 0,
                        0, sinA, cosA, 0,
                        0, 0, 0, 1);
            }
            case Y -> {
                return new Matrix4f(
                        cosA, 0, sinA, 0,
                        0, 1, 0, 0,
                        -sinA, 0, cosA, 0,
                        0, 0, 0, 1);
            }
            case Z -> {
                return new Matrix4f(
                        cosA, -sinA, 0, 0,
                        sinA, cosA, 0, 0,
                        0, 0, 1, 0,
                        0, 0, 0, 1);
            }
            default -> {
                return new Matrix4f(
                        1, 0, 0, 0,
                        0, 1, 0, 0,
                        0, 0, 1, 0,
                        0, 0, 0, 1);
            }
        }
    }

    @Override
    public Vector3f transform(Vector3f v) {
        Vector4f resVertex = MatMath.prod(getMatrix(), VectorConverter.to4f(v));
        return VectorConverter.to3f(resVertex);
    }

    private static Matrix4f matrix3ftoMatrix4f(Matrix3f m) {
        return new Matrix4f(
                m.m00, m.m01, m.m02, 0,
                m.m10, m.m11, m.m12, 0,
                m.m20, m.m21, m.m22, 0,
                0, 0, 0, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rotator rotator = (Rotator) o;
        return Float.compare(angle, rotator.angle) == 0 && axis == rotator.axis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(angle, axis);
    }
}

