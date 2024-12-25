package ru.vsu.cs.konygina_d.render_engine;

import io.github.alphameo.linear_algebra.mat.Mat4;
import io.github.alphameo.linear_algebra.mat.Mat4Math;
import io.github.alphameo.linear_algebra.mat.Matrix4;
import io.github.alphameo.linear_algebra.mat.Matrix4Col;
import io.github.alphameo.linear_algebra.mat.Matrix4Row;

import static io.github.alphameo.linear_algebra.mat.Matrix4Col.*;
import static io.github.alphameo.linear_algebra.mat.Matrix4Row.*;
import io.github.alphameo.linear_algebra.vec.Vec3;
import io.github.alphameo.linear_algebra.vec.Vector3;
import java.util.Objects;

public class Scaling implements AffineTransformation {
    private final Matrix4 scaleMatrix;

    public Scaling(float sx, float sy, float sz) {
        this.scaleMatrix = new Mat4(
                sx, 0, 0, 0,
                0, sy, 0, 0,
                0, 0, sz, 0,
                0, 0, 0, 1);
    }

    public Scaling() {
        this.scaleMatrix = Mat4Math.unitMat();
    }

    @Override
    public Matrix4 getMatrix() {
        return scaleMatrix;
    }

    @Override
    public Vector3 transform(Vector3 v) {
        return new Vec3(
                scaleMatrix.get(R0, C0) * v.x(),
                scaleMatrix.get(R1, C1) * v.y(),
                scaleMatrix.get(R2, C2) * v.z());
    }

    public void set(float newSX, float newSY, float newSZ) {
        scaleMatrix.set(Matrix4Row.R0, Matrix4Col.C0, newSX);
        scaleMatrix.set(Matrix4Row.R1, Matrix4Col.C1, newSY);
        scaleMatrix.set(Matrix4Row.R2, Matrix4Col.C2, newSZ);
    }

    public void setRelative(float dTX, float dTY, float dTZ) {
        set(
                scaleMatrix.get(Matrix4Row.R0, Matrix4Col.C0) * dTX,
                scaleMatrix.get(Matrix4Row.R1, Matrix4Col.C1) * dTY,
                scaleMatrix.get(Matrix4Row.R2, Matrix4Col.C2) * dTZ);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Scaling scaling = (Scaling) o;
        return Objects.equals(scaleMatrix, scaling.scaleMatrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scaleMatrix);
    }
}
