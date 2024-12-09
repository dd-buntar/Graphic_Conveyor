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

       /* AffineTransformation transformation = new Transformation(
                new Scaling(1.920F, 1.590F, 0.640F),
                new Rotator(45, 45, 45),
                new Translator(3, -2, 0)
        );*/

        AffineTransformation transformation = new Transformation(
                new Rotator(0, 0, -60)
        );

        /*AffineTransformation transformation = new Transformation(
                new Scaling(3.5F, 2, 0.5F)
        );*/

        for (int i = 0; i < mesh.vertices.size(); i++) {
            mesh.vertices.set(i, transformation.transform(mesh.vertices.get(i)));
        }

        ObjWriter objWriter = new ObjWriter();
        objWriter.write(mesh, "D:\\Graphic_Conveyor\\src\\test\\models\\myOnlyRCube.obj");
    }

}