package ru.vsu.cs.konygina_d.render_engine;

import javax.vecmath.*;

public interface AffineTransformation {
    Matrix4f getMatrix();

    Vector4f transform(Vector4f v);
}
