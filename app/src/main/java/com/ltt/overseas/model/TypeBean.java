package com.ltt.overseas.model;

import com.ltt.overseas.base.BaseBean;

public class TypeBean extends BaseBean {

    private String type_id;
    private String type_name;
    private String icon_image;
    private String banner_image;
    private String seo_name;
    private String logo;

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getSeo_name() {
        return seo_name;
    }

    public void setSeo_name(String seo_name) {
        this.seo_name = seo_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "TypeBean{" +
                "type_id='" + type_id + '\'' +
                ", type_name='" + type_name + '\'' +
                ", icon_image='" + icon_image + '\'' +
                ", banner_image='" + banner_image + '\'' +
                ", seo_name='" + seo_name + '\'' +
                ", logo='" + logo + '\'' +
                "} " + super.toString();
    }
}
