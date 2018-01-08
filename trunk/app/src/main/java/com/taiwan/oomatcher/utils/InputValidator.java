package com.taiwan.oomatcher.utils;

import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2018/1/2 17:48
 */

public class InputValidator {
    public static final String REGEXP_ACCOUNT_NAME = "^([a-zA-Z0-9-_\\.]|[\\u4E00-\\u9FA5]){2,15}$";
    public static final String REGEXP_CHAR = "^[一-龥0-9a-zA-Z]+$";
    public static final String REGEXP_CHINESE_STR = "^[一-龥]+$";
    public static final String REGEXP_COMPANY_NAME = "^[\\u4E00-\\u9FA5]{4,50}$";
    public static final String REGEXP_EMAIL = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
    public static final String REGEXP_ENGLISH_STR = "^[a-z|A-Z]+$";
    public static final String REGEXP_IDCARD_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    public static final String REGEXP_IDCARD_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
    public static final String REGEXP_MOBILE = "^1[345789]{1}\\d{9}$";
    public static final String REGEXP_MULTI_PHONE = "^(((\\d{11})|((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})))([；、;，、,]?))+$";
    public static final String REGEXP_NUMBER = "^-?\\d+$";
    public static final String REGEXP_PASSWORD = "^[\\W_a-zA-z0-9-]{6,20}$";
    public static final String REGEXP_PHONE = "((^\\d{11}$)|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
    public static final Pattern REG_ACCOUNT_NAME;
    public static final Pattern REG_CHAR;
    public static final Pattern REG_CHINESE_STR;
    public static final Pattern REG_COMPANY_NAME;
    public static final Pattern REG_EMAIL;
    public static final Pattern REG_ENGLISH_STR;
    public static final Pattern REG_IDCARD_15;
    public static final Pattern REG_IDCARD_18;
    public static final Pattern REG_MOBILE;
    public static final Pattern REG_MULTI_PHONE;
    public static final Pattern REG_NUMBER = Pattern.compile("^-?\\d+$");
    public static final Pattern REG_PASSWORD;
    public static final Pattern REG_PHONE;

    static {
        REG_EMAIL = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
        REG_MOBILE = Pattern.compile("^1[345789]{1}\\d{9}$");
        REG_CHINESE_STR = Pattern.compile("^[一-龥]+$");
        REG_ENGLISH_STR = Pattern.compile("^[a-z|A-Z]+$");
        REG_IDCARD_15 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
        REG_IDCARD_18 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$");
        REG_CHAR = Pattern.compile("^[一-龥0-9a-zA-Z]+$");
        REG_MULTI_PHONE = Pattern.compile("^(((\\d{11})|((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})))([；、;，、,]?))+$");
        REG_PHONE = Pattern.compile("(^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$)");
        REG_COMPANY_NAME = Pattern.compile("^[\\u4E00-\\u9FA5]{4,50}$");
        REG_ACCOUNT_NAME = Pattern.compile("^([a-zA-Z0-9-_\\.]|[\\u4E00-\\u9FA5]){2,15}$");
        //    REG_PASSWORD = Pattern.compile("^[\\W_a-zA-z0-9-]{6,12}$");
        //密码：6-12任意字符组合
        REG_PASSWORD = Pattern.compile("^\\w{6,12}$");
    }

    public static boolean isAge(String paramString) {
        return ((!(isNumber(paramString))) || (Integer.parseInt(paramString) > 100) || (Integer.parseInt(paramString) <= 0));
    }

    public static boolean isChar(String paramString) {
        return isMatch(REG_CHAR, paramString);
    }

    public static boolean isChineseString(String paramString) {
        return isMatch(REG_CHINESE_STR, paramString);
    }

    public static boolean isEmail(String paramString) {
        return isMatch(REG_EMAIL, paramString);
    }

    public static boolean isEmpty(EditText paramEditText) {
        boolean flag = ((paramEditText != null) && (paramEditText.getText() != null) && (!(paramEditText.getText().toString().trim().equals(""))));
        return !flag;
    }

    public static boolean isEmpty(TextView paramTextView) {
        boolean flag = ((paramTextView != null) && (paramTextView.getText() != null) && (!(paramTextView.getText().equals(""))));
        return !flag;
    }

    public static boolean isEmpty(String paramString) {
        boolean flag = ((paramString != null) && (!(paramString.trim().equals(""))));
        return !flag;
    }

    public static boolean isEnglishString(String paramString) {
        return isMatch(REG_ENGLISH_STR, paramString);
    }

    public static boolean isFind(Pattern paramPattern, String paramString) {
        if (isEmpty(paramString))
            return false;
        return paramPattern.matcher(paramString).find();
    }

    public static boolean isMatch(String paramString1, String paramString2) {
        return isMatch(Pattern.compile(paramString1), paramString2);
    }

    public static boolean isEquals(String str1, String str2) {
        if(str1 == null || str2 == null){
            return false;
        }
        return str2.trim().equals(str1.trim());
    }

    public static boolean isMatch(Pattern paramPattern, String paramString) {
        if (isEmpty(paramString))
            return false;
        return paramPattern.matcher(paramString).matches();
    }

    public static boolean isMobile(String paramString) {
        return isMatch(REG_MOBILE, paramString);
    }

    public static boolean isMultiPhone(String paramString) {
        return isMatch(REG_MULTI_PHONE, paramString);
    }

    public static boolean isNumber(String paramString) {
        return isMatch(REG_NUMBER, paramString);
    }

    public static boolean isPhone(String paramString) {
        return isMatch(REG_PHONE, paramString);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
		/*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (!isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    public static boolean isRegisterAccountNameValid(String paramString) {
        return isMatch(REG_ACCOUNT_NAME, paramString);
    }

    public static boolean isRegisterCompanyNameValid(String paramString) {
        return isMatch(REG_COMPANY_NAME, paramString);
    }

    public static boolean isRegisterPasswordValid(String paramString) {
        return isMatch(REG_PASSWORD, paramString);
    }
}
