package ru.vsu.cs.konygina_d;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.konygina_d.model.Model;
import ru.vsu.cs.konygina_d.obj_reader.ObjReader;
import ru.vsu.cs.konygina_d.obj_writer.ObjWriter;
import ru.vsu.cs.konygina_d.render_engine.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class AppTest {

    @Test
    public void testApp() {
        Path fileName = Path.of("D:\\Graphic_Conveyor\\src\\test\\models\\cube.obj");
        String fileContent = null;
        try {
            fileContent = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Model mesh = ObjReader.read(fileContent);

        AffineTransformation transformation = new Transformation(
                new Translator(5, 6.5f, -7),
                new Rotator(-60, Rotator.Axis.Z),
                new Rotator(120, Rotator.Axis.Y),
                new Rotator(20, Rotator.Axis.X),
                new Scaling(1.5f, 6, 1));

        mesh.vertices = new ArrayList<>(transformation.transform(mesh.vertices));

        ObjWriter objWriter = new ObjWriter();
        objWriter.write(mesh, "D:\\Graphic_Conveyor\\src\\test\\models\\testCube.obj");
        Assertions.assertTrue(true);
    }
}
