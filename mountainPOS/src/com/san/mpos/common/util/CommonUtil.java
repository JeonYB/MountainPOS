package com.san.mpos.common.util;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.*;
import java.lang.String;
/**
 * 
 * <pre> 
 *  서브시스템 : 공통 유틸
 *       설명 : 공통 Utility 클래스
 *   최초 작성 : LYM (2009. 03. 25) 
 *   변경 작성 :  
 * </pre>
a
 */
public class CommonUtil {
    public CommonUtil() {
        
    }
    
    /**
     * CLOB Type의 Data를 Table에서 읽어 온다.
     * @param reader ResultSet에서 읽어온 Clob 객체 ( rs.getCharacterStream(int index) )
     * @return CLOB Data
     */
    public static String getClob(Reader rd)
    	throws Exception{
    	 // CLOB column에 대한 스트림을 얻는다.
		StringBuffer sb = new StringBuffer();
        char[] buf = new char[1024];
        int readcnt;
        
        if (rd != null) {
	        while ((readcnt = rd.read(buf, 0, 1024)) != -1) {
				// 스트림으로부터 읽어서 스트링 버퍼에 넣는다.
				sb.append(buf, 0, readcnt);
	        }
	        rd.close();
        }
        
        return sb.toString();
    }
    
    /**
     * 캐리지 리턴을 br 태그로 replace한다.
     * @param String 문자열
     * @return br태그로 변경된 문자열
     */	    
    public static synchronized String N2Br(String s)
    {
        return replaceString("\n", "<BR>", s);
    }
    
	public static String getValueChgCarr(Object value, String defaultValue) {
		if(value == null || 
				value.toString().equals("null") ||
				value.toString().equals("NULL") ||
				value.toString().equals("")) {
			return defaultValue;	
		} else {
			return value.toString().replaceAll("\r\n", "<BR>").replaceAll("\n", "<BR>").replaceAll("'", "");
		}	
	}
	public static String getValueChgCarr(String value, String defaultValue) {
		if(value == null || 
				value.toString().equals("null") ||
				value.toString().equals("NULL") ||
				value.toString().equals("")) {
			return defaultValue;	
		} else {
			return value.toString().replaceAll("\r\n", "<BR>").replaceAll("\n", "<BR>").replaceAll("'", "‘");
		}	
	}
	
	
	
    public static synchronized String convertFlashString(String s) {
        String temp = s;
        temp = replaceString(" ", "%20", temp);
        temp = replaceString("!", "%21", temp);
        temp = replaceString("\"", "%22", temp);
        temp = replaceString("#", "%23", temp);
        temp = replaceString("$", "%24", temp);
        temp = replaceString("%", "%25", temp);
        temp = replaceString("&", "%26", temp);        
        
        return temp;
    }
    public static synchronized String replaceString(String s, String s1, String s2)
    {
        int i = 0;
        int j = 0;
        StringBuffer stringbuffer = new StringBuffer();
        
        while((j = s2.indexOf(s, i)) >= 0) 
        {
            stringbuffer.append(s2.substring(i, j));
            stringbuffer.append(s1);
            i = j + s.length();
        }
        
        stringbuffer.append(s2.substring(i));
        
        return stringbuffer.toString();
    }
    
    /**
     * 넘어온 휴대전화번호를 쪼개준다.
     * @param 문자열
     * @return 변경된 문자열
     */	
    public static String[] divideMPhone(String phone) {
        String phoneNumber = phone.replaceAll("-","");
               phoneNumber = phoneNumber.replaceAll(" ","");
        String[] mphone = { "", "", ""} ;
        if ( phoneNumber.length() != 0 )
        {
            if (phoneNumber.length() == 11) {
                if ( phoneNumber.substring(0, 3).equals("013") || phoneNumber.substring(0, 3).equals("050") ) {
                    mphone[0] = phoneNumber.substring(0, 4);
                    mphone[1] = phoneNumber.substring(4, 7);
                    mphone[2] = phoneNumber.substring(7, 11);
                }
                else {
                    mphone[0] = phoneNumber.substring(0, 3);
                    mphone[1] = phoneNumber.substring(3, 7);
                    mphone[2] = phoneNumber.substring(7, 11);
                }
            } else if (phoneNumber.length() == 10) {
                mphone[0] = phoneNumber.substring(0, 3);
                mphone[1] = phoneNumber.substring(3, 6);
                mphone[2] = phoneNumber.substring(6, 10);
            }
            else if  (phoneNumber.length() == 12)  {
                mphone[0] = phoneNumber.substring(0, 4);
                mphone[1] = phoneNumber.substring(4, 8);
                mphone[2] = phoneNumber.substring(8, 12);
            }
        }
        return mphone;
    }
    /**
     * 넘어온 전화번호를 쪼개준다.
     * @param 문자열
     * @return 변경된 문자열
     */	
    public static String[] divideHPhone(String hphone) {
        String phoneNumber = hphone.replaceAll("-","");
               phoneNumber = phoneNumber.replaceAll(" ","");
        String[] phone = { "", "", "" } ;
        if ( phoneNumber.length() != 0 )
        {
            if ( phoneNumber.substring(0, 2).equals("02") ) {
                if ( phoneNumber.length() == 10 )
                {
                    phone[0] = phoneNumber.substring(0, 2);
                    phone[1] = phoneNumber.substring(2, 6);
                    phone[2] = phoneNumber.substring(6, 10);
                }
                else if ( phoneNumber.length() == 9 )
                {
                    phone[0] = phoneNumber.substring(0, 2);
                    phone[1] = phoneNumber.substring(2, 5);
                    phone[2] = phoneNumber.substring(5, 9);
                }
            }
            if ( phoneNumber.length() == 12 )
            {
                phone[0] = phoneNumber.substring(0, 4);
                phone[1] = phoneNumber.substring(4, 8);
                phone[2] = phoneNumber.substring(8, 12);
            }
            else if ( phoneNumber.length() == 11 )
            {
                if ( phoneNumber.substring(0, 3).equals("013") || phoneNumber.substring(0, 3).equals("050") )
                {
                        phone[0] = phoneNumber.substring(0, 4);
                        phone[1] = phoneNumber.substring(4, 7);
                        phone[2] = phoneNumber.substring(7, 11);
                }
                else if ( !phoneNumber.substring(0, 3).equals("013") || !phoneNumber.substring(0, 3).equals("050"))
                {
                        phone[0] = phoneNumber.substring(0, 3);
                        phone[1] = phoneNumber.substring(3, 7);
                        phone[2] = phoneNumber.substring(7, 11);
                }
            }
            else if ( !phoneNumber.substring(0, 2).equals("02") && phoneNumber.length() == 10 )
            {
                phone[0] = phoneNumber.substring(0, 3);
                phone[1] = phoneNumber.substring(3, 6);
                phone[2] = phoneNumber.substring(6, 10);
            }
        }
        return phone;
    }
     /**
     * yyyyMMddhh24miss 를 영문요일로 변경하여 리턴한다.
     * @param 문자열
     * @return 변경된 문자열
     */	        
    public static String convertDate3() {
            Calendar now   = Calendar.getInstance();
            
            int day = now.get(Calendar.DAY_OF_WEEK);
            now.set(Calendar.YEAR,Calendar.MONTH,Calendar.DATE);
            String   today = "";
            switch(day) {
	            case 1: today = "SUN"; break;
	            case 2: today = "MON"; break;
	            case 3: today = "TUE"; break;
	            case 4: today = "WED"; break;
	            case 5: today = "THU"; break;
	            case 6: today = "FRI"; break;
	            case 7: today = "SAT";
            }
            return today;
    }
    
    /**
     * 복수의 파일 삭제
     * @param String path 	삭제할 파일의 경로 
     * @param String[] fileName 삭제할 파일명
     */    
    public static void deleteFiles(String path, String[] fileName) {
	    for (int i=0 ; i < fileName.length; i++) {
	        deleteFiles(path, fileName[i]);
	    }
    }
    /**
     * 파일 삭제
     * @param String path 	삭제할 파일의 경로 
     * @param String fileName 삭제할 파일명
     */    
    public static void deleteFiles(String path, String fileName) {
        File file = new File(path+File.separator+fileName);
        file.delete();
    }    
      
    
	public static String formatNumber(long number, String format) {
		DecimalFormat formatter = new DecimalFormat(format);
		return formatter.format(number);
	}
	/**
     * 문자를 ###,###,###,### format으로 변환
     * @param 문자열
     * @return 변경된 문자열
     */	      
	public static String commaFormat(String number) {
		return formatNumber(Long.parseLong(number), "###,###,###,###");
	}
	/**
     * 문자를 특정 format으로 변환
     * @param 문자열
     * @param format
     * @return 변경된 문자열
     */	      
	public static String commaFormat(String number, String format) {
		return formatNumber(Long.parseLong(number), format);
	}
	
	/**
     * null 값이 아닌 string을 주어진 길이 만큼 space 문자를 채운후 반환
     * @param 문자열
     * @param 길이
     * @return 변경된 문자열
     */	      	
	public static String fixString( String str, int len )
	{	
		String 		temp1;
		StringBuffer temp;
		int	i,j;
		
		i = str.length();
		temp = new StringBuffer( str );
		if ( i < len ){
			for ( j = 1; j <= ( len - i ); j ++ ){
				temp.append(" ");
			}
			temp1 = new String(temp);
		} else { 
			if ( i > len ) {
				temp1 = str.substring( 0, len );
			} else	temp1 =  str;
		}
		
		return temp1;
	}
   /**
    * 금액데이타 123,345,567 형식으로 보여주기
    * @param n
    * @return
    */
    public static String moneyFormValue(String n)
    {
        boolean nFlag=true;
        String o     = "";
        String p     = "";
        String minus = ""; 
        if ( n.substring(0,1).equals("-") ) {
            minus = n.substring(0,1);
            n     = n.substring(1);
        }
        if ( n.indexOf(".")>0 ) {
            o = n.substring(0, n.indexOf("."));
            p = n.substring(n.indexOf(".")+1, n.length());
        }
        else    {
            o = n;
        }
        o = CommonUtil.replace(o," ","");
        o = CommonUtil.replace(o,",","");
        o = CommonUtil.replace(o,"+","");
        int tLen = o.length();
        String tMoney = "";
        for(int i=0;i<tLen;i++){
            if (i!=0 && ( i % 3 == tLen % 3) ) tMoney += ",";
            if(i < tLen ) tMoney += o.charAt(i);
        }
        if ( p.length()>0 )     tMoney += "."+p;
        if ( nFlag == false )   tMoney = "-"+tMoney;
        if ( minus.equals("-") ) {
            tMoney = minus + tMoney;
        }
        return tMoney;
    }
    /**
     * 문자열대 문자열로 바꿔준다.
     * @param line
     * @param oldString
     * @param newString
     * @return
     */
    public static String replace(String line, String oldString, String newString)
    {   
        line = isNull(line);
        for(int index = 0; (index = line.indexOf(oldString, index)) >= 0; index += newString.length())
            line = line.substring(0, index) + newString + line.substring(index + oldString.length());
        return line;
    }
    /**
     * 문자열을 받아서 null이면 공백 문자열로 리턴
     * @param str
     * @return 
    */
    public static String isNull(String str)
    {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
            return "";
        else
            return str;
    }
    public static boolean isNull1(String str)
    {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
            return true;
        else
            return false;
    }    
    
    public static String getCookie(HttpServletRequest httpservletrequest, String s) throws Exception {
	    Cookie acookie[] = httpservletrequest.getCookies();
	    String s1 = "";
	    
	    if(acookie != null) {
	        for(int i = 0; i < acookie.length; i++) {
	            Cookie cookie = acookie[i];
	            
	            if(cookie.getName().equals(s))
	                return URLDecoder.decode(cookie.getValue(), "KSC5601");
	        }
	    }
	    
	    return s1;
	}
    
	public static String getTime(String format)
	{
		if ( format == null || format.equals("") == true )
			format = "yyyyMMddHHmmss";
		
		TimeZone tz = TimeZone.getDefault();
		tz.setRawOffset((60*60*1000) * 9);
		TimeZone.setDefault(tz);
		Calendar cal = Calendar.getInstance(tz);
		Date date = cal.getTime();
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String timestamp = formater.format(date);
		
		return timestamp;
	}    
	
	public static String right(String str, int length) {
		String rtnStr = "";
		if (!isNull1(str) && length > 0 && (str.length() - length >= 0)) {
			rtnStr = str.substring(str.length() - length);
		}
		return rtnStr;
	}
	
	/**
	 * html 코딩을 Encoding 한다.
	 * @param htmlstr
	 * @return
	 */
	public static String convertHtmlEncode(String htmlstr)
	{
		String convert = new String();
		convert = htmlstr.replaceAll("<", "&lt;");
		convert = convert.replaceAll(">", "&gt;");
		convert = convert.replaceAll("\"", "&quot;");
		convert = convert.replaceAll("&nbsp;", "&amp;nbsp;");
		return convert;
	}
	/**
	 * html 코딩을 Decoding 한다.
	 * @param htmlstr
	 * @return
	 */
	public static String convertHtmlDecode(String htmlstr)
	{
		String convert = new String();
		convert = htmlstr.replaceAll("&lt;", "<");
		convert = convert.replaceAll("&gt;", ">");
		convert = convert.replaceAll("&quot;", "\"");
		convert = convert.replaceAll("&amp;nbsp;", "&nbsp;");
		return convert;
	}
	public static boolean startWith(String str, String chr) {
		if (str.indexOf(chr) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * 입력받은 문자열이 null 이거나 NULL 이거나 "" 이면 원하는 문자열로 변환 
	 * @param value 입력받은 문자열
	 * @param defaultValue 변환할 문자열
	 * @return 변환처리된 문자열 
	 */
	public static String getValue(String value) {
		if(value == null || 
				value.equals("null") ||
				value.equals("NULL") ||
				value.equals("")) {
			return "";	
		} else {
			return value;
		}	
	}
	
	public static String getValueUpper(String value) {
		if(value == null || 
				value.equals("null") ||
				value.equals("NULL") ||
				value.equals("")) {
			return "";	
		} else {
			return value.toUpperCase();
		}	
	}
	
	public static String getValue(String value, String defaultValue) {
		if(value == null ||
				value.equals("null") ||
				value.equals("NULL") ||
				value.equals("")) {
			return defaultValue;	
		} else {
			return value;
		}	
	}
	
	/**
	 * 입력 받은 Object 가 NULL 인지 판단.
	 * @param value
	 * @return
	 */
	public static String getValue(Object value) {
		if(value == null || 
				value.toString().equals("null") ||
				value.toString().equals("NULL") ||
				value.toString().equals("")) {
			return "";	
		} else {
			return value.toString();
		}	
	}
	public static String getValue(Object value, String defaultValue) {
		if(value == null || 
				value.toString().equals("null") ||
				value.toString().equals("NULL") ||
				value.toString().equals("")) {
			return defaultValue;	
		} else {
			return value.toString();
		}	
	}
	
	
	
	/**
	 * 입력받은 문자열이 NULL, 0이면 넘어온 숫자값으로 대체 한다.
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static int getIntValue(String value) {
		if( value == null ||
			value.equals("null") ||
			value.equals("NULL") ||
			value.equals("") )
			return 0;
		else{
			return Integer.parseInt(value.toString());
		}
	}
	public static int getIntValue(Object value) {
		if (value == null ||
			value.toString().equals("null") ||
			value.toString().equals("NULL") ||
			value.toString().equals("") )
			return 0;
		else{
			return Integer.parseInt(value.toString());
		}
	}
	public static int getIntValue(String value, int defaultValue ) {
		if (value == null ||
			value.equals("null") ||
			value.equals("NULL") ||
			value.equals("") ||
			value.equals("0"))
			return defaultValue;
		else{
			return Integer.parseInt(value.toString());
		}
	}
	public static int getIntValue(Object value, int defaultValue ) {
		if (value == null ||
			value.equals("null") ||
			value.equals("NULL") ||
			value.equals("") ||
			value.equals("0"))
			return defaultValue;
		else{
			return Integer.parseInt(value.toString());
		}
	}
	
	public static int getIntValue(int value, int defaultValue ) {
		if (value == 0)
			return defaultValue;
		else{
			return value;
		}
	}
	
	/**
	 * 현재의 주소를 가져온다. (파라미터 정보를 포함하여 반환한다.)
	 * @param request
	 * @return
	 */
	public static String getNowUrl(HttpServletRequest request) throws IOException {
		String parameterList = ""; 
	   	String ret_url = "http://" + request.getHeader("Host") + request.getRequestURI();// No Parameter URL 
	
	   	int k1=0; 
	   	for (Enumeration e = request.getParameterNames(); e.hasMoreElements() ;k1++) { 
			String name = (String) e.nextElement(); 
			String[] value = request.getParameterValues(name); 
			if (k1 == 0) 
				ret_url = ret_url + "?";
			else if (k1>0)
				ret_url = ret_url + "&";
			parameterList = parameterList + "&";
			
			for (int q1 = 0; q1 < value.length; q1++){
				if (q1 > 0) {
					ret_url = ret_url + "&";
					parameterList = parameterList + "&";
				}
				ret_url = ret_url + name + "=" + value[q1]; 
				parameterList = parameterList + name + "=" + value[q1]; 
			}
	    }
	    return URLEncoder.encode(ret_url, "UTF-8");
	}
	/**
	 * 입력받은 첨부 화일명을 pkid_index.ext 형태로 변환
	 * @param no 일련번호
	 * @param seq 순번
	 * @param path 경로
	 * @param baseFile 원본 화일명
	 * @return 변환된 화일
	 */
	public static File getFileRename(String no, String seq, String path){
		File returnFile = null;
		String newFileName = "";
		//String fileName = baseFile.getName();
		/**
		String ext = "txt";
		StringTokenizer st =new StringTokenizer(fileName, ".");
		int tokenLength = st.countTokens();
		for(int a=0;a<tokenLength;a++){
			ext = st.nextToken();
		}
		*/
		newFileName = no+"_"+seq;
		returnFile = new File(path + "/" + newFileName);
		return returnFile;
	}
	
	public static File getFileRename2(String path, String fileNm){
		File returnFile = null;
		String newFileName = "";
		//String fileName = baseFile.getName();
		/**
		String ext = "txt";
		StringTokenizer st =new StringTokenizer(fileName, ".");
		int tokenLength = st.countTokens();
		for(int a=0;a<tokenLength;a++){
			ext = st.nextToken();
		}
		*/
		newFileName = fileNm;
		returnFile = new File(path + newFileName);
		
		System.out.println("==" + path + newFileName);
		return returnFile;
	}
	
	
	
    /**
     * 문자형 한자리 숫자에 0 + 숫자 리턴한다.
     * @param 문자열
     * @return 변경된 문자열
     */
    public static String plusZero(String number) {
    	
		if(number == null || 
				number.equals("null") ||
				number.equals("NULL") ||
				number.equals("") ||
				number.length() == 0) {
			return "";	
		} else {
			
	        if (number.length() == 1) {
	            return "0"+number;
	        } else {
	            return number;
	        }
		}
    }
    /**
     * 특정 자릿수 만큼 0을 더하여 리턴
     * @param number
     * @param length
     * @return
     * 
     * @author LYM
     */
    public static String plusZero(String number, int length) {
    	
		if(number == null || 
				number.equals("null") ||
				number.equals("NULL") ||
				number.equals("") ||
				number.length() == 0) {
			return "";	
		} else {
			
	        if (number.length() == length) {
	        	return number;
	        } else {
	        	
	        	String tmpStr = "";
	        	for(int i=0; i< length-number.length();i++)
	        		tmpStr += "0";
	        	return tmpStr+number;
	        }
		}
    }
    /**
     * 문자형 한자리 숫자에 0 + 숫자 리턴한다.
     * @param int
     * @return 변경된 문자열
     */
    public static String plusZero(int number) {
        String temp = String.valueOf(number);
        if (temp.length() == 1) {
            return "0"+temp;
        } else {
            return temp;
        }
    }
    
    /**
	 * 한글 포함 문자열의 길이를 구한다.
	 * 
	 * @param String
	 * @return 문자열 길이
	 */
	public static final int getByteSizeToComplex(String str) {
		int byteLength = 0;
		char[] string = str.toCharArray();
		for (int j = 0; j < string.length; j++) {
			if (string[j] >= 'A' && string[j] <= 'z') {
				byteLength +=1;
			} else if (string[j] >= '\uAC00' && string[j] <= '\uD7A3') {
				byteLength +=2;
			} else {
				byteLength +=1;
			}
		}
		return byteLength;
	}
    
    
}