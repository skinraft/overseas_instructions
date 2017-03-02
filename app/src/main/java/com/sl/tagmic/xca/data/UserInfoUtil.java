package com.sl.tagmic.xca.data;

import android.content.Context;

import com.sl.tagmic.xca.data.LSP;

public class UserInfoUtil {
    /*
     * 用户是否已经登录
     */
    public static String SWICH = "SWICH";

    /*
     * 设置用户是否已经登录
     */
    public static String getLang(Context context) {
        String lang= LSP.getString(context, SWICH, SWICH);
        if ("".equals(lang))
            lang="1";
        return lang;
    }

    /*
     * 获取用户登录状态
     */
    public static void setLang(Context context, String lang) {
        LSP.putString(context, SWICH, SWICH, lang);
    }


}
