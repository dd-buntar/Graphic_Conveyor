package ru.vsu.cs.konygina_d.render_engine;

import ru.vsu.cs.konygina_d.math.MatMath;
import ru.vsu.cs.konygina_d.math.VectorConverter;

import javax.vecmath.*;

public class Transformation implements AffineTransformation {
    private final AffineTransformation scale, rotation, translation;
    private boolean isDefault;

    public Transformation(Scaling scale, Rotator rotation, Translator translation) {
        this.scale = scale;
        this.rotation = rotation;
        this.translation = translation;
        isDefault = false;
    }

    public Transformation(Scaling scale) {
        this.scale = scale;
        this.rotation = new Rotator();
        this.translation = new Translator();
        isDefault = false;
    }

    public Transformation(Rotator rotation) {
        this.scale = new Scaling();
        this.rotation = rotation;
        this.translation = new Translator();
        isDefault = false;
    }

    public Transformation(Translator translation) {
        this.scale = new Scaling();
        this.rotation = new Rotator();
        this.translation = translation;
        isDefault = false;
    }

    public Transformation() {
        this.scale = new Scaling();
        this.rotation = new Rotator();
        this.translation = new Translator();
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
        Matrix4f result = new Matrix4f(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );

        if (isDefault()) {
            return result;
        }

        if (!translation.isDefault()) {
            result.mul(translation.getMatrix());
        }

        if (!rotation.isDefault()) {
            result.mul(rotation.getMatrix());
        }

        if (!scale.isDefault()) {
            result.mul(scale.getMatrix());
        }

        return result;
    }

    @Override
    public Vector3f transform(Vector3f v) {
        Vector4f resVertex = MatMath.prod(getMatrix(), VectorConverter.to4f(v));
        return VectorConverter.to3f(resVertex);
    }
}
