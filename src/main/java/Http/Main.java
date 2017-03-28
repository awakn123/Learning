package Http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.net.HttpHeaders.USER_AGENT;

public class Main {

	private static void sendGet() throws Exception {

		String url = "http://www.baidu.com/";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		System.out.println(con.getRequestProperties());

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		System.out.println(con.getRequestProperties());

	}
	public static void main(String[] args) throws Exception {
		Main.sendGet();
//		java.net.URL u = new java.net.URL("http://www.baidu.com");
//		try (java.util.Scanner s = new java.util.Scanner(u.openStream())) {
//			System.out.println(s.useDelimiter("\\A").next());
//		}
	}
}
