package ru.vsu.cs.konygina_d.render_engine;

import javax.vecmath.*;
import java.util.Objects;

public class Scaling implements AffineTransformation {
    private final float Sx, Sy, Sz;
    private boolean isDefault;

    public Scaling(float sx, float sy, float sz) {
        Sx = sx;
        Sy = sy;
        Sz = sz;
        isDefault = false;
    }

    public Scaling() {
        Sx = 1;
        Sy = 1;
        Sz = 1;
        isDefault = true;
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
        return new Matrix4f(
                Sx, 0, 0, 0,
                0, Sy, 0, 0,
                0, 0, Sz, 0,
                0, 0, 0, 1);
    }

    @Override
    public Vector4f transform(Vector4f v) {
        return new Vector4f(
                Sx * v.x,
                Sy * v.y,
                Sz * v.z,
                v.w);
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
