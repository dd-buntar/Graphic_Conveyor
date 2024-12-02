package ru.vsu.cs.konygina_d.render_engine;

import javax.vecmath.*;
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
    public Matrix4f getMatrix() {
        return new Matrix4f(
                1, 0, 0, Tx,
                0, 1, 0, Ty,
                0, 0, 1, Tz,
                0, 0, 0, 1);
    }

    @Override
    public Vector4f transform(Vector4f v) {
        return new Vector4f(
                Tx + v.x,
                Ty + v.y,
                Tz + v.z,
                v.w);
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

