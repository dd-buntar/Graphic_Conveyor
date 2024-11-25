package ru.vsu.cs.konygina_d.obj_writer;

import ru.vsu.cs.konygina_d.math.Vector2f;
import ru.vsu.cs.konygina_d.math.Vector3f;
import ru.vsu.cs.konygina_d.model.Model;
import ru.vsu.cs.konygina_d.model.Polygon;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ObjWriter {
    private static final String OBJ_VERTEX_TOKEN = "v";
    private static final String OBJ_TEXTURE_TOKEN = "vt";
    private static final String OBJ_NORMAL_TOKEN = "vn";
    private static final String OBJ_FACE_TOKEN = "f";

    public void write(Model model, String filename) {
        File file = new File(filename);
        if (!createDir(file.getParentFile()))
            return;
        if (!createFile(file))
            return;
        try (PrintWriter writer = new PrintWriter(file)) {
            model.vertices.forEach(v -> writer.println(vertexToString(v)));
            model.textureVertices.forEach(v -> writer.println(textureVertexToString(v)));
            model.normals.forEach(v -> writer.println(normalToString(v)));
            model.polygons.forEach(v -> writer.println(polygonToString(v)));
        } catch (IOException e) {
            System.out.println("Error while writing file");
        }
    }

    private boolean createDir(File directory) {
        if (directory != null && !directory.exists() && !directory.mkdirs()) {
            System.out.println("Couldn't create dir: " + directory);
            return false;
        }
        return true;
    }

    private boolean createFile(File file) {
        try {
            if (!file.createNewFile())
                System.out.println("Warning: " + file.getName() + " already exists");
        } catch (IOException e) {
            System.out.println("Error while creating the file");
            return false;
        }
        return true;
    }

    protected String vertexToString(Vector3f vector) {
        return OBJ_VERTEX_TOKEN + " " + vector.getX() + " " + vector.getY() + " " + vector.getZ();
    }

    protected String textureVertexToString(Vector2f vector) {
        return OBJ_TEXTURE_TOKEN + " " + vector.getX() + " " + vector.getY();
    }

    protected String normalToString(Vector3f vector) {
        return OBJ_NORMAL_TOKEN + " " + vector.getX() + " " + vector.getY() + " " + vector.getZ();
    }

    protected String polygonToString(Polygon polygon) {
        StringBuilder stringBuilder = new StringBuilder(OBJ_FACE_TOKEN);
        List<Integer> vertexIndices = polygon.getVertexIndices();
        List<Integer> textureVertexIndices = polygon.getTextureVertexIndices();
        List<Integer> normalIndices = polygon.getNormalIndices();
        boolean hasTextures = textureVertexIndices.size() == vertexIndices.size();
        boolean hasNormals = normalIndices.size() == vertexIndices.size();
        for (int i = 0; i < vertexIndices.size(); i++) {
            stringBuilder.append(" ")
                    .append(getFormattedIndex(vertexIndices, i));
            if (hasNormals) {
                stringBuilder.append("/");
                if (hasTextures) {
                    stringBuilder.append(getFormattedIndex(textureVertexIndices, i))
                            .append("/")
                            .append(getFormattedIndex(normalIndices, i));
                } else {
                    stringBuilder.append("/")
                            .append(getFormattedIndex(normalIndices, i));
                }
            } else {
                if (hasTextures) {
                    stringBuilder.append("/")
                            .append(getFormattedIndex(textureVertexIndices, i));
                }
            }
        }
        return stringBuilder.toString();
    }

    private int getFormattedIndex(List<Integer> indices, int index) {
        return indices.get(index) + 1;
    }
}