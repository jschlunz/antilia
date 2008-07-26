/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.util;

public class StringUtils {
    
	/**
	 * counts the number of occurrences of character c in a string.
	 * 
	 * @param str
	 * @param c
	 * @return int
	 */
	public static int countChar(String str, char c) {
		int start = -1;
		int count = 0;
		while (true) {
			if ((start = str.indexOf(c, start + 1)) == -1)
				return (count);
			count++;
		}
	}

	/**
	 * Add delimiters to a string.
	 * If the string itself contains the delimiter character, 
	 * the character will be escaped by repeating the delimitter character.  
	 *  
	 * @return The delimited String
	 * @param str The String to delimit 	
	 * @param delimiter
	 */
	public static String delimit(String str, char delimiter) {
		if (delimiter == 0)
			return (str);

		StringBuffer buffer = new StringBuffer();
		buffer.append(delimiter);
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == delimiter)
					buffer.append(delimiter);
				buffer.append(str.charAt(i));
			}
		}
		buffer.append(delimiter);
		return (buffer.toString());
	}
	
	public static String getFirstToken(final String path, final String separator)
	{
		if (path == null) 
			return null;
		
		final int index = path.indexOf(separator);

		if (index == -1)
			return path;
		
		return path.substring(0, index);
	}
	
	public static String getLastToken(final String path, final String separator)
	{
		if (path == null)
			return null;
		
		final int index = path.lastIndexOf(separator);

		if (index == -1)
			return path;
		
		if (index+1>=path.length())
			return "";
		
		return path.substring(index+1);
	}

	/**
	 * Checks if a String is empty, i.e. if the String is null or is the String
	 * containing white spaces characters.
	 * 
	 * @param str The String to check.
	 * @return Returns <code>true</code> is the string is empty otherwise
	 *         return <code>false</code>.
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}
	
    /**
     * <p>Converts a String to lower case</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.lowerCase(null)  = null
     * StringUtils.lowerCase("")    = ""
     * StringUtils.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str  the String to lower case, may be null
     * @return the lower cased String, <code>null</code> if null String input
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

	/**
	 * Returns a new string resulting from replacing all occurrences of oldStr in this string with newStr.
	 * 
	 * @param str
	 * @param oldStr The old string
	 * @param newStr the new string
	 * @return
	 */
	public static String replace(String str, String oldStr, String newStr) {
        if (str == null || str.length() == 0) {
            return str;
        }

		StringBuffer buffer = new StringBuffer();
		int pos = 0;
		int oldPos = 0;
		while ((pos = str.indexOf(oldStr, oldPos)) != -1) {
			buffer.append(str.substring(oldPos, pos));
			buffer.append(newStr);
			oldPos = pos + oldStr.length();
		}

		buffer.append(str.substring(oldPos, str.length()));

		return buffer.toString();
	}					
}