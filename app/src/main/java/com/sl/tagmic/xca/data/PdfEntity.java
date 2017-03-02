package com.sl.tagmic.xca.data;

import java.io.Serializable;

/**
 * Created by techssd on 2017/1/3.
 */

public class PdfEntity implements Serializable {
    String lang;
    String pdf_url;
    String code;
    String date;
    String pic;
    String right_conner;
    String name;

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getPic() {
        return pic;
    }

    public String getRight_conner() {
        return right_conner;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setRight_conner(String right_conner) {
        this.right_conner = right_conner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public String getLang() {
        return lang;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    @Override
    public String toString() {
        return "PdfEntity{" +
                "lang='" + lang + '\'' +
                ", pdf_url='" + pdf_url + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", pic='" + pic + '\'' +
                ", right_conner='" + right_conner + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
