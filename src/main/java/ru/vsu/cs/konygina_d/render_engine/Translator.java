package ru.vsu.cs.konygina_d.render_engine;

import io.github.alphameo.linear_algebra.mat.Mat4;
import io.github.alphameo.linear_algebra.mat.Matrix4;
import io.github.alphameo.linear_algebra.vec.Vec3;
import io.github.alphameo.linear_algebra.vec.Vector3;
import java.util.Objects;

public class Translator implements AffineTransformation {
    private final float Tx, Ty, Tz;

    public Translator(float tx, float ty, float tz) {
        Tx = tx;
        Ty = ty;
        Tz = tz;
    }

    public Translator() {
        Tx = 0;
        Ty = 0;
        Tz = 0;
    }

    @Override
    public Matrix4 getMatrix() {
        return new Mat4(
                1, 0, 0, Tx,
                0, 1, 0, Ty,
                0, 0, 1, Tz,
                0, 0, 0, 1);
    }

    @Override
    public Vector3 transform(Vector3 v) {
        return new Vec3(
                Tx + v.x(),
                Ty + v.y(),
                Tz + v.z());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translator that = (Translator) o;
        return Float.compare(Tx, that.Tx) == 0 && Float.compare(Ty, that.Ty) == 0 && Float.compare(Tz, that.Tz) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Tx, Ty, Tz);
    }
}

