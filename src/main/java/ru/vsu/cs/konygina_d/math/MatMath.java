package ru.vsu.cs.konygina_d.math;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;

public class MatMath {
    public static Vector4f prod(final Matrix4f m, final Vector4f v) {

        float[] result = new float[]{1, 1, 1, 1};
        for (int i = 0; i < 4; i++) {
            float value = 0;
            value += m.getElement(i, 0) * v.x;
            value += m.getElement(i, 1) * v.y;
            value += m.getElement(i, 2) * v.z;
            value += m.getElement(i, 3) * v.w;

            result[i] = value;
        }

        return new Vector4f(result[0], result[1], result[2], result[3]);
    }
}
