package org.nlpcn.commons.lang.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class StringUtil {

	private static final char DY = '\'';
	private static final char DH = ',';
	private static int[] filter = new int[128];
	private static int[] filterEnd = new int[128];
	private static final String EMPTY = "";
	private static final String NULL = "null";

	static {
		filter['<'] = Integer.MAX_VALUE / 2;
		filterEnd['<'] = '>';

		filter['&'] = 10;
		filterEnd['&'] = ';';

		filter[';'] = -1;
		filter['\n'] = -1;

		filter['\r'] = -1;
		filter['\t'] = -1;
		filter[' '] = 1;
		filter['*'] = 1;
		filter['-'] = 1;
		filter['.'] = 1;
		filter['#'] = 1;

	}

	/**
	 * 去除html标签
	 *
	 * @param input
	 * @return
	 */
	public static String rmHtmlTag(String input) {
		if (isBlank(input)) {
			return "";
		}
		int length = input.length();
		int tl = 0;
		StringBuilder sb = new StringBuilder();
		char c = 0;
		for (int i = 0; i < length; i++) {
			c = input.charAt(i);

			if (c > 127) {
				sb.append(c);
				continue;
			}

			switch (filter[c]) {
			case -1:
				break;
			case 0:
				sb.append(c);
				break;
			case 1:
				if (sb.length() > 0 && sb.charAt(sb.length() - 1) != c)
					sb.append(c);
				do {
					i++;
				} while (i < length && input.charAt(i) == c);

				if (i < length || input.charAt(length - 1) != c)
					i--;
				break;
			default:
				tl = filter[c] + i;
				int tempOff = i;
				boolean flag = false;
				char end = (char) filterEnd[c];
				for (i++; i < length && i < tl; i++) {
					c = input.charAt(i);
					if (c > 127)
						continue;
					if (c == end) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					i = tempOff;
					sb.append(input.charAt(i));
				}
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否为空
	 *
	 * @param cs
	 * @return
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否不为空
	 *
	 * @param cs
	 * @return
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !isBlank(cs);

	}

	public static String makeSqlInString(String str) {
		String[] strs = str.split(",");
		StringBuilder sb = new StringBuilder();
		String field = null;
		for (int i = 0; i < strs.length; i++) {
			field = strs[i].trim();
			if (isNotBlank(field)) {
				sb.append(DY);
				sb.append(field);
				sb.append(DY);
				if (i < strs.length - 1) {
					sb.append(DH);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将一个字符串.转换成排序后的字符数组
	 *
	 * @param str
	 * @return
	 */
	public static char[] sortCharArray(String str) {
		char[] chars = str.toCharArray();
		Arrays.sort(chars);
		return chars;
	}

	public static String joiner(int[] ints, String split) {

		if (ints.length == 0) {
			return EMPTY;
		}

		StringBuilder sb = new StringBuilder(String.valueOf(ints[0]));

		for (int i = 1; i < ints.length; i++) {
			sb.append(split);
			sb.append(ints[i]);
		}

		return sb.toString();
	}
	
	public static String joiner(double[] doubles, String split) {

		if (doubles.length == 0) {
			return EMPTY;
		}

		StringBuilder sb = new StringBuilder(String.valueOf(doubles[0]));

		for (int i = 1; i < doubles.length; i++) {
			sb.append(split);
			sb.append(doubles[i]);
		}

		return sb.toString();
	}
	

	public static String joiner(float[] floats, String split) {

		if (floats.length == 0) {
			return EMPTY;
		}

		StringBuilder sb = new StringBuilder(String.valueOf(floats[0]));

		for (int i = 1; i < floats.length; i++) {
			sb.append(split);
			sb.append(floats[i]);
		}

		return sb.toString();
	}
	

	public static String joiner(long[] longs, String split) {

		if (longs.length == 0) {
			return EMPTY;
		}

		StringBuilder sb = new StringBuilder(String.valueOf(longs[0]));

		for (int i = 1; i < longs.length; i++) {
			sb.append(split);
			sb.append(longs[i]);
		}

		return sb.toString();
	}


	public static String toString(Object obj) {
		if (obj == null) {
			return NULL;
		} else {
			return obj.toString();
		}
	}

	public static String joiner(Collection<?> c, String split) {

		Iterator<?> iterator = c.iterator();

		if (!iterator.hasNext()) {
			return EMPTY;
		}

		StringBuilder sb = new StringBuilder(iterator.next().toString());

		while (iterator.hasNext()) {
			sb.append(split);
			sb.append(toString(iterator.next()).toString());
		}

		return sb.toString();
	}

	public static boolean isBlank(char[] chars) {
		// TODO Auto-generated method stub
		int strLen;
		if (chars == null || (strLen = chars.length) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(chars[i]) == false) {
				return false;
			}
		}
		return true;
	}
}