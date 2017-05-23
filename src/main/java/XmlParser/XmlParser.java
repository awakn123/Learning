package XmlParser;

import SqlToMysql.util.ListUtils;
import com.google.common.collect.Sets;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class XmlParser {
	public static void main(String[] args) throws IOException, DocumentException {
		String rootPath = "./src/test/xml/workflow_wcc.xml";
		List<String> xmlTexts = Files.readAllLines(Paths.get(rootPath));
		Document document = DocumentHelper.parseText(ListUtils.toString(xmlTexts, "\n"));
		Element element = document.getRootElement();
		List elist = element.elements();
		Set<String> typeSet = Sets.newHashSet();
		for(Object e: elist) {
			DefaultElement tmp = (DefaultElement) e;
			typeSet.add(tmp.attributeValue("type"));
		}
		System.out.println(typeSet);
	}
}
