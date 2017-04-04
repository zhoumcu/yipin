package com.hunter.fastandroid.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ymn on 2017/4/2.
 */
public class Goods implements Serializable {


    /**
     * product_id : 22223626
     * supplier_id : 22223624
     * cid : 46
     * catcode : 00046
     * brand_id : 3
     * photo_id : 0
     * name : sdfsdfsdfsf
     * subtitle : sdfsdffsdfsdf
     * code : sdfds
     * spec :
     * summary :
     * gift :
     * cost : 0.00
     * price1 : 0.00
     * price2 : 0.00
     * price3 : 0.00
     * price : 0.00
     * miaosd : 0
     * miaoed : 0
     * points : 0
     * send_points : 0
     * sales : 0
     * comments : 0
     * listorder : 99
     * hits : 0
     * stick : 0
     * has_banner : 0
     * order_sday : 0
     * order_eday : 0
     * discountx : 0
     * is_shipping : 0
     * created : 1448025381
     * updated : 1448025381
     * logo : http://domain/xxx.jpg
     * photo : http://domain/xxx.jpg
     * brand : 商品品牌名称
     * content :
     * fenqi :分期数
     * fenqi_price:每期价格
     * attrs : []

     product_id:产品ID
     supplier_id:供应商ID
     cid:类别ID
     catcode:类别编码
     brand_id:品牌ID
     name:产品名称
     subtitle:副标题
     code:产品编码
     summary:产品简介
     cost:成本价
     price1:市场价
     price2:零售价
     price:最终价（销售时价格）
     stick:推荐值，大于0表示推荐
     is_shipping: 是否包邮
     logo: 产品缩略图
     photo: 产品大图
     brand: 商品品牌名称
     content:详情
     attrs:扩展属性，数组
     "depcities":"上海;武汉;重庆;成都;西安;广州;深圳;昆明;香港"
     */

    private String product_id;
    private String supplier_id;
    private String cid;
    private String catcode;
    private String brand_id;
    private String photo_id;
    private String name;
    private String subtitle;
    private String code;
    private String spec;
    private String summary;
    private String gift;
    private String cost;
    private String price1;
    private String price2;
    private String price3;
    private String price;
    private String miaosd;
    private String miaoed;
    private String points;
    private String send_points;
    private String sales;
    private String comments;
    private String listorder;
    private String hits;
    private String stick;
    private String has_banner;
    private String order_sday;
    private String order_eday;
    private String discountx;
    private String is_shipping;
    private String created;
    private String updated;
    private String logo;
    private String photo;
    private String brand;
    private String content;
    private String fenqi;
    private String fenqi_price;
    private List<?> attrs;
    /**
     * freight : 0.00
     * rating_count : 0
     * rating : 0.0
     * color_attr_id : 0
     * sku_attr :
     * sku_unit :
     */

    private String freight;
    private int rating_count;
    private String rating;
    private int color_attr_id;
    private String sku_attr;
    private String sku_unit;
    private String depcities;

    public int getIs_instalment() {
        return is_instalment;
    }

    public void setIs_instalment(int is_instalment) {
        this.is_instalment = is_instalment;
    }

    private int is_instalment;

    public String getShoufu() {
        return shoufu;
    }

    public void setShoufu(String shoufu) {
        this.shoufu = shoufu;
    }

    private String shoufu;

    public String getFenqi_price() {
        return fenqi_price;
    }

    public void setFenqi_price(String fenqi_price) {
        this.fenqi_price = fenqi_price;
    }

    public String getFenqi() {
        return fenqi;
    }

    public void setFenqi(String fenqi) {
        this.fenqi = fenqi;
    }

    public String getDepcities() {
        return depcities;
    }

    public void setDepcities(String depcities) {
        this.depcities = depcities;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setCatcode(String catcode) {
        this.catcode = catcode;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setMiaosd(String miaosd) {
        this.miaosd = miaosd;
    }

    public void setMiaoed(String miaoed) {
        this.miaoed = miaoed;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setSend_points(String send_points) {
        this.send_points = send_points;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setListorder(String listorder) {
        this.listorder = listorder;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public void setStick(String stick) {
        this.stick = stick;
    }

    public void setHas_banner(String has_banner) {
        this.has_banner = has_banner;
    }

    public void setOrder_sday(String order_sday) {
        this.order_sday = order_sday;
    }

    public void setOrder_eday(String order_eday) {
        this.order_eday = order_eday;
    }

    public void setDiscountx(String discountx) {
        this.discountx = discountx;
    }

    public void setIs_shipping(String is_shipping) {
        this.is_shipping = is_shipping;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAttrs(List<?> attrs) {
        this.attrs = attrs;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public String getCid() {
        return cid;
    }

    public String getCatcode() {
        return catcode;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public String getName() {
        return name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getCode() {
        return code;
    }

    public String getSpec() {
        return spec;
    }

    public String getSummary() {
        return summary;
    }

    public String getGift() {
        return gift;
    }

    public String getCost() {
        return cost;
    }

    public String getPrice1() {
        return price1;
    }

    public String getPrice2() {
        return price2;
    }

    public String getPrice3() {
        return price3;
    }

    public String getPrice() {
        return price;
    }

    public String getMiaosd() {
        return miaosd;
    }

    public String getMiaoed() {
        return miaoed;
    }

    public String getPoints() {
        return points;
    }

    public String getSend_points() {
        return send_points;
    }

    public String getSales() {
        return sales;
    }

    public String getComments() {
        return comments;
    }

    public String getListorder() {
        return listorder;
    }

    public String getHits() {
        return hits;
    }

    public String getStick() {
        return stick;
    }

    public String getHas_banner() {
        return has_banner;
    }

    public String getOrder_sday() {
        return order_sday;
    }

    public String getOrder_eday() {
        return order_eday;
    }

    public String getDiscountx() {
        return discountx;
    }

    public String getIs_shipping() {
        return is_shipping;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getLogo() {
        return logo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getBrand() {
        return brand;
    }

    public String getContent() {
        return content;
    }

    public List<?> getAttrs() {
        return attrs;
    }

    public void setFreight (String freight) {
        this.freight = freight;
    }

    public void setRating_count (int rating_count) {
        this.rating_count = rating_count;
    }

    public void setRating (String rating) {
        this.rating = rating;
    }

    public void setColor_attr_id (int color_attr_id) {
        this.color_attr_id = color_attr_id;
    }

    public void setSku_attr (String sku_attr) {
        this.sku_attr = sku_attr;
    }

    public void setSku_unit (String sku_unit) {
        this.sku_unit = sku_unit;
    }

    public String getFreight () {
        return freight;
    }

    public int getRating_count () {
        return rating_count;
    }

    public String getRating () {
        return rating;
    }

    public int getColor_attr_id () {
        return color_attr_id;
    }

    public String getSku_attr () {
        return sku_attr;
    }

    public String getSku_unit () {
        return sku_unit;
    }
}