package Base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONTest {
    public static void main(String[] args) {
        String text = "[[1,2,1],[1,\"\",1]]";
        JSONArray ja = JSONObject.parseArray(text);
        System.out.println(ja);
    }
}
