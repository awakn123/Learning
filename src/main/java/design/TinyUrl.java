package design;

import java.security.MessageDigest;

/**
 * Created by 曹云 on 2020/11/25.
 */
public class TinyUrl {
	public static void main(String[] args) {
		String sLongUrl = "https://www.github.com"; // 长链接
		String[] aResult = shortUrl(sLongUrl);
		// 打印出结果
		for (int i = 0; i < aResult.length; i++) {
			System.out.println("[" + i + "]:::" + aResult[i]);
		}
	}

	/**
	 理论基础：
	 我们共有62个字符可用，计算可知62^6 = 568亿，使用6位即可。7位时为3万5千亿。
	 所以将长链接转为一个6*62的一组数据即可。62不方便计算，我们将其转为6*64的数据，然后忽略掉2位。
	 64位的数据为2^6，考虑到0，需要5位数。所以我们需要5*6 = 一组30位数。
	 将长链接转为MD5串后，可获得一个32位的16进制串。
	 8位16进制可以转为32位2进制（8^16 = 2^32)
	 所以我们将MD5串分成4组，可以形成4组短链接。

	 核心思路：
	 1. 将长链接MD5加密并转为16进制串(32位)，分为4组，一组8位，最长为2^32。
	 2. 与2^31 - 1 作与运算，去掉头部两位，保留后30位，方便计算
	 3. 将30位分为5组，一组6位，与0x3D作与运算，计算0~61，不对，这样算不出来二位为1的，应该对61取余更好。2^6 = 64，所以也无所谓，相当于舍弃了第二位为1的。
	 4. 将之后的值转为0-61对应的具体字符。
	 * @param url
	 * @return
	 */
	public static String[] shortUrl(String url) {

		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z"
		};

		String key = "test";                               // 可以自定义生成 MD5 加密字符传前的混合加密key
		String sMD5EncryptResult = CMyEncrypt.md5(key + url);       // 对传入网址进行 MD5 加密，key是加密字符串
		String hex = sMD5EncryptResult;

		String[] resUrl = new String[4];
		// 32位md5值转为4组8位
		for (int i = 0; i < 4; i++) {
			// 把加密字符按照8位一组16进制与0x3FFFFFFF进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);

			// 这里需要使用 long 型来转换，因为 Inteter.parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
			// 1个F4个1，这里是30个1。另一个思路，7位16进制代表4*16^7=2^(2+4*7) = 2%30次方。
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			for (int j = 0; j < 6; j++) {
				long index = 0x0000003D & lHexLong;     // 把得到的值与 0x0000003D(61) 进行位与运算，取得字符数组 chars 索引
				outChars += chars[(int) index];         // 把取得的字符相加
				lHexLong = lHexLong >> 5;             // 每次循环按位右移 5 位
			}
			resUrl[i] = outChars;                       // 把字符串存入对应索引的输出数组
		}
		return resUrl;
	}
}


class CMyEncrypt {
	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	/** 把inputString加密 */
	public static String md5(String inputStr) {
		return encodeByMD5(inputStr);
	}

	/**
	 * 验证输入的密码是否正确
	 *
	 * @param password 真正的密码（加密后的真密码）
	 * @param inputString 输入的字符串
	 * @return 验证结果，boolean类型
	 */
	public static boolean authenticatePassword(String password, String inputString) {
		if (password.equals(encodeByMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	/** 对字符串进行MD5编码 */
	private static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md5.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String result = byteArrayToHexString(results);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 轮换字节数组为十六进制字符串
	 *
	 * @param b 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	// 将一个字节转化成十六进制形式的字符串
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static void main(String[] args) {
		CMyEncrypt.md5("https://blog.mimvp.com/about");
	}

}
