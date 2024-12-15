package ru.vsu.cs.konygina_d.render_engine;

import io.github.alphameo.linear_algebra.mat.Matrix4;
import io.github.alphameo.linear_algebra.vec.Vector3;

public interface AffineTransformation {
    Matrix4 getMatrix();

    Vector3 transform(Vector3 v);
}
