package jtwig;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("./src/test/template/example.twig");
        File file = path.toFile();
        JtwigTemplate template = JtwigTemplate.fileTemplate(file);
        JtwigModel model = JtwigModel.newModel().with("var", "World");
        template.render(model, System.out);
        System.out.println("success");
    }
}
