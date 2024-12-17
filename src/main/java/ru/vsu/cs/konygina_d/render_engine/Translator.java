package ru.vsu.cs.konygina_d.render_engine;

import io.github.alphameo.linear_algebra.mat.Mat4;
import io.github.alphameo.linear_algebra.mat.Matrix4;
import static io.github.alphameo.linear_algebra.mat.Matrix4Col.*;
import static io.github.alphameo.linear_algebra.mat.Matrix4Row.*;
import io.github.alphameo.linear_algebra.vec.Vec3;
import io.github.alphameo.linear_algebra.vec.Vector3;
import java.util.Objects;

public class Translator implements AffineTransformation {
    private final Matrix4 translateMatrix;

    public Translator(float tx, float ty, float tz) {
        this.translateMatrix = new Mat4(
                1, 0, 0, tx,
                0, 1, 0, ty,
                0, 0, 1, tz,
                0, 0, 0, 1);
    }

    public Translator() {
        this.translateMatrix = new Mat4(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
    }

    @Override
    public Matrix4 getMatrix() {
        return translateMatrix;
    }

    @Override
    public Vector3 transform(Vector3 v) {
        return new Vec3(
                translateMatrix.get(R3, C0) + v.x(),
                translateMatrix.get(R3, C1) + v.y(),
                translateMatrix.get(R3, C2) + v.z());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translator that = (Translator) o;
        return Objects.equals(translateMatrix, that.translateMatrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translateMatrix);
    }
}

