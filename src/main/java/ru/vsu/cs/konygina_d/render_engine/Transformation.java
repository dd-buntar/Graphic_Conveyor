package ru.vsu.cs.konygina_d.render_engine;

import io.github.alphameo.linear_algebra.mat.Mat4;
import io.github.alphameo.linear_algebra.mat.Mat4Math;
import io.github.alphameo.linear_algebra.mat.Matrix4;
import io.github.alphameo.linear_algebra.vec.Vec3;
import io.github.alphameo.linear_algebra.vec.Vec3Math;
import io.github.alphameo.linear_algebra.vec.Vector3;
import io.github.alphameo.linear_algebra.vec.Vector4;
import java.util.ArrayList;
import java.util.List;

public class Transformation implements AffineTransformation {
    private final List<AffineTransformation> affineTransformations = new ArrayList<>();

    public Transformation(AffineTransformation... ats) {
        for (AffineTransformation at : ats) {
            affineTransformations.add(at);
        }
    }

    public Transformation() { }

    @Override
    public Matrix4 getMatrix() {
        Matrix4 result = new Mat4(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );

        for (AffineTransformation at : affineTransformations) {
            result = Mat4Math.prod(result, at.getMatrix());
        }

        return result;
    }

    @Override
    public Vector3 transform(Vector3 v) {
        Vector4 resVertex = Mat4Math.prod(getMatrix(), Vec3Math.toVec4(v));
        return new Vec3(resVertex.x(), resVertex.y(), resVertex.z());
    }
}
