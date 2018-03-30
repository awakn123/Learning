package Base;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionTest {
    public String test() {
        return "wrong";
    }
    public static void main(String[] args) {
        try {
            throw new Exception("34333333333333\n444444");
//            ExceptionTest a = null;
//            a.test();
        } catch(Exception e) {
            String s = ExceptionTest.getErrorInfoFromException(e);
            System.out.println(s);
        }
    }


    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }
}
