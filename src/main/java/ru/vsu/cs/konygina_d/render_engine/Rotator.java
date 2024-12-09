package ru.vsu.cs.konygina_d.render_engine;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;
import java.util.Objects;

public class Rotator implements AffineTransformation {
    private final float alpha, beta, gamma;
    private boolean isDefault;

    public Rotator(int alpha, int beta, int gamma) {
        this.alpha = (float) Math.toRadians(alpha);
        this.beta = (float) Math.toRadians(beta);
        this.gamma = (float) Math.toRadians(gamma);
        isDefault = false;
    }

    public Rotator() {
        this.alpha = 0;
        this.beta = 0;
        this.gamma = 0;
        isDefault = true;
    }

    public Rotator(float alpha, float beta, float gamma) {
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        isDefault = false;
    }

    @Override
    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public Matrix4f getMatrix() {
        float cosA = (float) Math.cos(alpha);
        float sinA = (float) Math.sin(alpha);
        float cosB = (float) Math.cos(beta);
        float sinB = (float) Math.sin(beta);
        float cosG = (float) Math.cos(gamma);
        float sinG = (float) Math.sin(gamma);

        Matrix3f xRotateMatrix = new Matrix3f(
                1, 0, 0,
                0, cosA, -sinA,
                0, sinA, cosA);

        Matrix3f yRotateMatrix = new Matrix3f(
                cosB, 0, -sinB,
                0, 1, 0,
                sinB, 0, cosB);

        Matrix3f zRotateMatrix = new Matrix3f(
                cosG, -sinG, 0,
                sinG, cosG, 0,
                0, 0, 1);

        zRotateMatrix.mul(yRotateMatrix);
        zRotateMatrix.mul(xRotateMatrix);

        return matrix3ftoMatrix4f(zRotateMatrix);
    }

    @Override
    public Vector4f transform(Vector4f v) {
        Matrix4f m = getMatrix();
        return prod(m, v);
    }

    private static Matrix4f matrix3ftoMatrix4f(Matrix3f m) {
        return new Matrix4f(
                m.m00, m.m01, m.m02, 0,
                m.m10, m.m11, m.m12, 0,
                m.m20, m.m21, m.m22, 0,
                0, 0, 0, 1);
    }

    public static Vector4f prod(final Matrix4f m, final Vector4f v) {

        float[] result = new float[]{1, 1, 1, 1};
        for (int i = 0; i < 4; i++) {
            float value = 0;
            value += m.getElement(i, 0) * v.x;
            value += m.getElement(i, 1) * v.y;
            value += m.getElement(i, 2) * v.z;
            value += m.getElement(i, 3) * v.w;

            result[i] = value;
        }

        return new Vector4f(result[0], result[1], result[2], result[3]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rotator rotator = (Rotator) o;
        return Float.compare(alpha, rotator.alpha) == 0 && Float.compare(beta, rotator.beta) == 0 && Float.compare(gamma, rotator.gamma) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alpha, beta, gamma);
    }
}

