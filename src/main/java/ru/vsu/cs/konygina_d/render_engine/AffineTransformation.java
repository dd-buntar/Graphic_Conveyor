package ru.vsu.cs.konygina_d.render_engine;

import javax.vecmath.*;

public interface AffineTransformation {
    Matrix4f getMatrix();

    Vector3f transform(Vector3f v);

    boolean isDefault();
}
