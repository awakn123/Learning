package Base;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class Base64Test {

    public static void main(String[] args) {
        String image = "./src/test/resource/image.jpg";
        String imageWrong = "./src/test/resource/image_wrong.jpg";
        String imageStr = getImageStr(image);
        String imageWrongStr = getImageStr(imageWrong);
//        System.out.println("image:" + imageStr);
//        System.out.println("imageWrong:" + imageWrongStr);
        System.out.println("----------------------------");
        generateImage(imageStr);
        System.out.println("----------------------------");
        generateImage(imageWrongStr);
    }

    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static boolean generateImage(String imgStr) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                System.out.print(b[i] + ",");
//                if (b[i] < 0) {
//                    b[i] += 256;
//                }
            }
//            OutputStream out = new FileOutputStream(path);
//            out.write(b);
//            out.flush();
//            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
