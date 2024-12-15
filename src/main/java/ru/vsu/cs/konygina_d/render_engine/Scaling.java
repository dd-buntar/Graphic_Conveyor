package ru.vsu.cs.konygina_d.render_engine;

import io.github.alphameo.linear_algebra.mat.Mat4;
import io.github.alphameo.linear_algebra.mat.Matrix4;
import io.github.alphameo.linear_algebra.vec.Vec3;
import io.github.alphameo.linear_algebra.vec.Vector3;
import java.util.Objects;

public class Scaling implements AffineTransformation {
    private final float Sx, Sy, Sz;

    public Scaling(float sx, float sy, float sz) {
        Sx = sx;
        Sy = sy;
        Sz = sz;
    }

    public Scaling() {
        Sx = 1;
        Sy = 1;
        Sz = 1;
    }

    @Override
    public Matrix4 getMatrix() {
        return new Mat4(
                Sx, 0, 0, 0,
                0, Sy, 0, 0,
                0, 0, Sz, 0,
                0, 0, 0, 1);
    }

    @Override
    public Vector3 transform(Vector3 v) {
        return new Vec3(
                Sx * v.x(),
                Sy * v.y(),
                Sz * v.z());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scaling scaling = (Scaling) o;
        return Float.compare(Sx, scaling.Sx) == 0 && Float.compare(Sy, scaling.Sy) == 0 && Float.compare(Sz, scaling.Sz) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Sx, Sy, Sz);
    }
}
