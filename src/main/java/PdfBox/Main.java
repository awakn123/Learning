package PdfBox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        PDDocument document = null;
        try {
            File pdfFile = new File("./src/test/resource/pdf.pdf");
            String filePath = "./src/test/resource/";
            document = PDDocument.load(pdfFile);
            /*java.util.List pages = document.getDocumentCatalog().getAllPages();

            System.out.print("getAllPages==============="+pages.size());
            Iterator iter = pages.iterator();
            int i = 1;
            String name = null;

            while (iter.hasNext()) {
                PDPage page = (PDPage) iter.next();
                PDResources resources = page.getResources();
                Map pageImages = resources.getImages();
                if (pageImages != null) {
                    Iterator imageIter = pageImages.keySet().iterator();
                    while (imageIter.hasNext()) {
                        String key = (String) imageIter.next();
                        PDXObjectImage image = (PDXObjectImage) pageImages.get(key);
                        image.write2file("d:\\Image" + i);
                        //image.get
                        i++;

                    }
                }
            }*/
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

}
