package com.hunter.fastandroid.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：Administrator on 2017/4/1 17:14
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ShoppingCartGoods implements Parcelable{
    /**
     * item_id : 22223555
     * cart_id : 22223551
     * product_id : 22223515
     * cid : 46
     * user_id : 0
     * name : sdfsdfsdfsf
     * subtitle : sdfsdffsdfsdf
     * thumb : http://cyp.dev/upfile/product/pic/201512/04/5660756d0775c353_m.jpg
     * quantity : 3
     * old_price : 0.00
     * price : 2000.00
     * expiryd : 0
     * created : 1449811048
     * updated : 1449811048
     * summary :简述
     *
     * shoufu : 27.26
     * monthpay : 10.128187658459
     * instalments : 9
     */

    /**
     *  说明：
     *  item_id: 明细ID
     * cart_id: 购物车ID
     * product_id: 产品ID
     * user_id: 用户ID
     * cid: 产品类别ID
     * name: 商品名称
     * subtitle: 副标题（可能用不上）
     * thumb: 商品缩略图
     * quantity: 数量
     * old_price: 旧的价格（用不上）
     * price: 价格
     * summary :简述
     *
     * shoufu : 首付
     * monthpay : 月供
     * instalments : 分期数
     */

    private String item_id;
    private String cart_id;
    private String product_id;
    private String cid;
    private String user_id;
    private String name;
    private String subtitle;
    private String thumb;
    private String quantity;
    private String old_price;
    private String price;
    private String expiryd;
    private String created;
    private String updated;
    private String summary;

    private String shoufu;

    private int instalments;
    private double monthpay;

    private String load_id;

    private String loan_id; //分期时使用这个id，上面这个load_id不知道是不是谁写错了，暂时保留

    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public String getShoufu() {
        return shoufu;
    }

    public void setShoufu(String shoufu) {
        this.shoufu = shoufu;
    }

    public int getInstalments() {
        return instalments;
    }

    public void setInstalments(int instalments) {
        this.instalments = instalments;
    }

    public String getLoad_id() {
        return load_id;
    }

    public void setLoad_id(String load_id) {
        this.load_id = load_id;
    }

    public double getmonthpay() {
        return monthpay;
    }

    public void setmonthpay(double monthpay) {
        this.monthpay = monthpay;
    }

    /**
     * sku_id : 23524296
     */

    private int sku_id;

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setExpiryd(String expiryd) {
        this.expiryd = expiryd;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getCid() {
        return cid;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getThumb() {
        return thumb;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getOld_price() {
        return old_price;
    }

    public String getPrice() {
        return price;
    }

    public String getExpiryd() {
        return expiryd;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {

        return summary;
    }


    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeString(this.item_id);
        dest.writeString(this.cart_id);
        dest.writeString(this.product_id);
        dest.writeString(this.cid);
        dest.writeString(this.user_id);
        dest.writeString(this.name);
        dest.writeString(this.subtitle);
        dest.writeString(this.thumb);
        dest.writeString(this.quantity);
        dest.writeString(this.old_price);
        dest.writeString(this.price);
        dest.writeString(this.expiryd);
        dest.writeString(this.created);
        dest.writeString(this.updated);
        dest.writeString(this.summary);
    }

    public ShoppingCartGoods () {
    }

    protected ShoppingCartGoods (Parcel in) {
        this.item_id = in.readString();
        this.cart_id = in.readString();
        this.product_id = in.readString();
        this.cid = in.readString();
        this.user_id = in.readString();
        this.name = in.readString();
        this.subtitle = in.readString();
        this.thumb = in.readString();
        this.quantity = in.readString();
        this.old_price = in.readString();
        this.price = in.readString();
        this.expiryd = in.readString();
        this.created = in.readString();
        this.updated = in.readString();
        this.summary = in.readString();
    }

    public static final Parcelable.Creator<ShoppingCartGoods> CREATOR = new Parcelable.Creator<ShoppingCartGoods>() {
        public ShoppingCartGoods createFromParcel (Parcel source) {
            return new ShoppingCartGoods(source);
        }

        public ShoppingCartGoods[] newArray (int size) {
            return new ShoppingCartGoods[size];
        }
    };

    public void setSku_id (int sku_id) {
        this.sku_id = sku_id;
    }

    public int getSku_id () {
        return sku_id;
    }
}
