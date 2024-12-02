package ru.vsu.cs.konygina_d.render_engine;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;

public class Transformation implements AffineTransformation {
    private static final AffineTransformation defaultScale = new Scaling();
    private static final AffineTransformation defaultRotate = new Rotator();
    private static final AffineTransformation defaultTranslate = new Translator();


    private final AffineTransformation scale, rotation, translation;

    public Transformation(Scaling scale, Rotator rotation, Translator translation) {
        this.scale = scale;
        this.rotation = rotation;
        this.translation = translation;
    }

    public Transformation(Scaling scale) {
        this.scale = scale;
        this.rotation = defaultRotate;
        this.translation = defaultTranslate;
    }

    public Transformation(Rotator rotation) {
        this.scale = defaultScale;
        this.rotation = rotation;
        this.translation = defaultTranslate;
    }

    public Transformation(Translator translation) {
        this.scale = defaultScale;
        this.rotation = defaultRotate;
        this.translation = translation;
    }

    public Transformation() {
        this.scale = defaultScale;
        this.rotation = defaultRotate;
        this.translation = defaultTranslate;
    }

    @Override
    public Matrix4f getMatrix() {
        Matrix4f result = new Matrix4f(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );

        if (!translation.equals(defaultTranslate)) {
            result.mul(translation.getMatrix());
        }

        if (!rotation.equals(defaultRotate)) {
            result.mul(rotation.getMatrix());
        }

        if (!scale.equals(defaultScale)) {
            result.mul(scale.getMatrix());
        }

        return result;
    }

    @Override
    public Vector4f transform(Vector4f v) {
        return Rotator.prod(getMatrix(), v);
    }
}
