package ru.vsu.cs.konygina_d.render_engine;

import ru.vsu.cs.konygina_d.math.MatMath;
import ru.vsu.cs.konygina_d.math.VectorConverter;

import javax.vecmath.*;
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
    public Matrix4f getMatrix() {
        Matrix4f result = new Matrix4f(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );

        for (AffineTransformation at : affineTransformations) {
            result.mul(at.getMatrix());
        }

        return result;
    }

    @Override
    public Vector3f transform(Vector3f v) {
        Vector4f resVertex = MatMath.prod(getMatrix(), VectorConverter.to4f(v));
        return VectorConverter.to3f(resVertex);
    }
}
