package fileChange;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class FileChange {
    public static void main(String[] args) throws IOException {
        String filePath = "./src/test/resource/蒋贝贝文档多语言.txt";
        Path path = Paths.get(filePath);
        File file = path.toFile();
        if (!file.exists()) {
            return;
        }
        List<String> contentList = Files.readAllLines(path);
        Set<Integer> labelIds = Sets.newHashSet();
        for (String s : contentList) {
            try {
                if (s.startsWith("#") || StringUtils.isBlank(s)) {
                    continue;
                }
                if (s.contains(" ")) {
                    String[] arr = s.split(" ");
                    for (String t: arr) {
                        try {
                            labelIds.add(Integer.parseInt(t));
                        } catch(NumberFormatException e) {

                        }
                    }
                } else {
                    System.out.println(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error:" + s);
            }
        }
        System.out.println("-------------------------------------");
        for (int labelId : labelIds) {
            System.out.println("insert into HtmlModuleLabel(type, moduleCode, indexId) values ('label', 'document', " + labelId + ")\nGO");
        }
    }
}
