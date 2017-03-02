package com.sl.tagmic.xca.data;

import java.io.Serializable;

/**
 * Created by techssd on 2017/1/4.
 */

public class XLangEntity implements Serializable {
    String lang;
    String name;

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "XLangEntity{" +
                "lang='" + lang + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
