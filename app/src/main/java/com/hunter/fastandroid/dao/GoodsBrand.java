package com.hunter.fastandroid.dao;

/**
 * author：Administrator on 2017/4/1 10:29
 * company: xxxx
 * email：1032324589@qq.com
 */
public class GoodsBrand {
    /**
     * brand_id : 1
     * name : Asobio/傲鸶
     * logo : http://112.74.114.103/upfile/brand/logo/201511/13/5645bec0304fd972.jpg
     * desc :
     * site : http://#
     * listorder : 50
     * is_show : 1
     * stick : 0
     * created : 0
     * updated : 1447411392


     参看汽车品牌列表接口，注意区别
     */

    private String brand_id;
    private String name;
    private String logo;
    private String desc;
    private String site;
    private String listorder;
    private String is_show;
    private String stick;
    private String created;
    private String updated;

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setListorder(String listorder) {
        this.listorder = listorder;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public void setStick(String stick) {
        this.stick = stick;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public String getDesc() {
        return desc;
    }

    public String getSite() {
        return site;
    }

    public String getListorder() {
        return listorder;
    }

    public String getIs_show() {
        return is_show;
    }

    public String getStick() {
        return stick;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }
}
