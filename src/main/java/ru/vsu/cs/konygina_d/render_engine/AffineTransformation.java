package ru.vsu.cs.konygina_d.render_engine;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;

public interface AffineTransformation {
    Matrix4f getMatrix();

    Vector4f transform(Vector4f v);
}
