package ru.vsu.cs.konygina_d;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.konygina_d.model.Model;
import ru.vsu.cs.konygina_d.obj_reader.ObjReader;
import ru.vsu.cs.konygina_d.obj_writer.ObjWriter;
import ru.vsu.cs.konygina_d.render_engine.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

       /* AffineTransformation transformation = new Transformation(
                new Scaling(1.920F, 1.590F, 0.640F),
                new Rotator(45, 45, 45),
                new Translator(3, -2, 0)
        );*/

        AffineTransformation transformation = new Transformation(
                new Translator(5, 6.5f, -7),
                new Rotator(-60, Rotator.Axis.Z),
                new Rotator(120, Rotator.Axis.Y),
                new Rotator(20, Rotator.Axis.X),
                new Scaling(1.5f, 6, 1)
        );

        /*AffineTransformation transformation = new Transformation(
                new Scaling(3.5F, 2, 0.5F)
        );*/

        for (int i = 0; i < mesh.vertices.size(); i++) {
            mesh.vertices.set(i, transformation.transform(mesh.vertices.get(i)));
        }

        ObjWriter objWriter = new ObjWriter();
        objWriter.write(mesh, "D:\\Graphic_Conveyor\\src\\test\\models\\testCube.obj");
        Assertions.assertTrue(true);
    }
}
