package ru.vsu.cs.konygina_d.model;

import ru.vsu.cs.konygina_d.math.Vector2f;
import ru.vsu.cs.konygina_d.math.Vector3f;

import java.util.ArrayList;
import java.util.Objects;



public class Model {

    public ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<Vector2f>();
    public ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
    public ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    public static class ModelParams {
        private float Sx = 1.0F, Sy = 1.0F, Sz = 1.0F;  // scale
        private int a = 0, b = 0, c = 0; // rotate
        private float Tx = 0.0F, Ty = 0.0F, Tz = 0.0F; // translate

        public float getSx() {
            return Sx;
        }

        public void setSx(float sx) {
            Sx = sx;
        }

        public float getSy() {
            return Sy;
        }

        public void setSy(float sy) {
            Sy = sy;
        }

        public float getSz() {
            return Sz;
        }

        public void setSz(float sz) {
            Sz = sz;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public float getTx() {
            return Tx;
        }

        public void setTx(float tx) {
            Tx = tx;
        }

        public float getTy() {
            return Ty;
        }

        public void setTy(float ty) {
            Ty = ty;
        }

        public float getTz() {
            return Tz;
        }

        public void setTz(float tz) {
            Tz = tz;
        }
    }

    private ModelParams params;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model model)) return false;
        return Objects.equals(vertices, model.vertices) && Objects.equals(textureVertices, model.textureVertices) && Objects.equals(normals, model.normals) && Objects.equals(polygons, model.polygons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, textureVertices, normals, polygons);
    }
}

