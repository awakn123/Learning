package leetcode.easy.string;

import leetcode.util.ResultCheck;

import java.util.*;

/**
 * Created by 曹云 on 2020/8/6.
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger {

	public int myAtoi(String str) {
		if (str == null) return 0;
		boolean positive = true, start = false;
		int n = 0;
		for (int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				int cn = Character.digit(c, 10);
				if (positive && (n>Integer.MAX_VALUE/10 || (n==Integer.MAX_VALUE/10 && cn >7)))
					return Integer.MAX_VALUE;
				else if (!positive && (-n < Integer.MIN_VALUE/10 || (-n == Integer.MIN_VALUE/10 && cn>8)))
					return Integer.MIN_VALUE;
				n *= 10;
				n += cn;
				start = true;
			} else if (start) {
				break;
			} else if (c == '-') {
				positive = false;
				start = true;
			} else if (c == '+') {
				positive = true;
				start = true;
			} else if (c != ' ') {
				return 0;
			}
		}
		return positive ? n : -n;
	}

	/**
	 * see and reimplement.
	 * use state machine.
	 * @param str
	 * @return
	 */
	public int myAtoi2(String str) {
		class StateMachine {
			String state = "start";// start, signal, digit, end.
			boolean positive = true;
			int num = 0;
			String str;
			Map<String, List<String>> stateMap = new HashMap<>();
			public StateMachine(String str){
				stateMap.put("start", Arrays.asList("start", "signal", "digit", "end"));
				stateMap.put("signal", Arrays.asList("end", "end", "digit", "end"));
				stateMap.put("digit", Arrays.asList("end", "end", "digit", "end"));
				stateMap.put("end", Arrays.asList("end", "end", "end", "end"));
				this.str = str;
			}

			/**
			 *         blank  signal  digit  other
			 * start   start  signal  digit  end
			 * signal  end    end     digit  end
			 * digit   end    end     digit  end
			 * end     end    end     end    end
			 * @param c
			 */
			public int getStateIdx(char c) {
				if (c == ' ')
					return 0;
				if (c == '+' || c == '-')
					return 1;
				if (Character.isDigit(c))
					return 2;
				return 3;
			}
			public void calc(char c) {
				this.state = this.stateMap.get(this.state).get(this.getStateIdx(c));
				if ("start".equals(this.state) || "end".equals(this.state))
					return;
				if ("signal".equals(this.state)) {
					this.positive = '+' == c;
					return;
				}
				int cn = Character.digit(c, 10);
				if (positive && (this.num>Integer.MAX_VALUE/10 || (this.num==Integer.MAX_VALUE/10 && cn >7))) {
					this.state = "end";
					this.num = Integer.MAX_VALUE;
				} else if (!positive && (-this.num < Integer.MIN_VALUE/10 || (-this.num == Integer.MIN_VALUE/10 && cn>8))) {
					this.state = "end";
					this.num = Integer.MIN_VALUE;
					this.positive = true;
				} else {
					this.num = this.num * 10 + cn;
				}

			}
			public void run() {
				this.num = 0;
				for (int i=0;i< this.str.length();i++) {
					this.calc(this.str.charAt(i));
				}
				if (!positive) {
					this.num = - this.num;
				}
			}
		}

		StateMachine sm = new StateMachine(str);
		sm.run();
		return sm.num;
	}

	public static void main(String[] args){
		StringToInteger main = new StringToInteger();
		String strI = "42";
		int answerI = 42;
		int resultI = main.myAtoi2(strI);
		ResultCheck.check(resultI, answerI);
		String strII = "       -42";
		int answerII = -42;
		int resultII = main.myAtoi2(strII);
		ResultCheck.check(resultII, answerII);
		String strIII = "4193 with words";
		int answerIII = 4193;
		int resultIII = main.myAtoi2(strIII);
		ResultCheck.check(resultIII, answerIII);
		String strIV = "words and 987";
		int answerIV = 0;
		int resultIV = main.myAtoi2(strIV);
		ResultCheck.check(resultIV, answerIV);
		String strV = "-91283472332";
		int answerV = Integer.MIN_VALUE;
		int resultV = main.myAtoi2(strV);
		ResultCheck.check(resultV, answerV);

	}
}
