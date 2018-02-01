package Http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

	private static String sendGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "User-Agent");
//		System.out.println(con.getRequestProperties());

//		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'GET' request to URL : " + url);
//		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine).append("\n");
		}
		in.close();

		//print result
//		System.out.println(response.toString());
        return response.toString();
//		System.out.println(con.getRequestProperties());

	}
	public static void main(String[] args) throws Exception {
	    String url = "http://www.baidu.com/";
//        url = "http://zjhbdemo.gtja.net/newoa/gzqb_2017.nsf/0/2BF582DCA39A3F2E48258202003CBDDD?editdocument";
        System.out.println("start");
        String response = Main.sendGet(url);
        System.out.println("end, result:");
        System.out.println(response);
//		java.net.URL u = new java.net.URL("http://www.baidu.com");
//		try (java.util.Scanner s = new java.util.Scanner(u.openStream())) {
//			System.out.println(s.useDelimiter("\\A").next());
//		}
	}
}
