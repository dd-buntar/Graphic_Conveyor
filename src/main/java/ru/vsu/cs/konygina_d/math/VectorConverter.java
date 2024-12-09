package ru.vsu.cs.konygina_d.math;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class VectorConverter {
    public static Vector4f to4f(Vector3f v) {
        return new Vector4f(v.x, v.y, v.z, 1);
    }

    public static Vector3f to3f(Vector4f v) {
        return new Vector3f(v.x, v.y, v.z);
    }
}
