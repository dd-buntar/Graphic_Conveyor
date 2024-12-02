package ru.vsu.cs.konygina_d.render_engine;

import ru.vsu.cs.konygina_d.model.Model;
import ru.vsu.cs.konygina_d.obj_reader.ObjReader;
import ru.vsu.cs.konygina_d.obj_writer.ObjWriter;

import javax.vecmath.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GraphicConveyor {

    public static void main(String[] args) {
        Path fileName = Path.of("D:\\Graphic_Conveyor\\src\\test\\models\\simpleCube.obj");
        String fileContent = null;
        try {
            fileContent = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Model mesh = ObjReader.read(fileContent);

        AffineTransformation transformation = new Transformation(
                new Scaling(-1.920F, 1.590F, 0.640F),
                new Rotator(45, -45, -45),
                new Translator(3, -2, 0)
        );

        List<Vector4f> vertexList = new ArrayList<>();
        for (Vector3f v : mesh.vertices) {
            vertexList.add(new Vector4f(v.x, v.y, v.z, 1));
        }

        List<Vector4f> transformedVertices = new ArrayList<>();
        for (Vector4f v : vertexList) {
            transformedVertices.add(transformation.transform(v));
        }

        mesh.vertices.clear();
        for (Vector4f v : transformedVertices) {
            mesh.vertices.add(new Vector3f(v.x, v.y, v.z));
        }

        ObjWriter objWriter = new ObjWriter();
        objWriter.write(mesh, "D:\\Graphic_Conveyor\\src\\test\\models\\myTransCube.obj");
    }

}