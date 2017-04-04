package com.hunter.fastandroid.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ymn on 2017/4/4.
 */
public class GoodDetail implements Serializable {

    /**
     * product_id : 22223515
     * supplier_id : 22223624
     * cid : 46
     * catcode : 00046
     * brand_id : 3
     * photo_id : 3
     * name : sdfsdfsdfsf
     * subtitle : sdfsdffsdfsdf
     * code : sdfds
     * spec :
     * summary : sdfsdfsdf
     * gift :
     * cost : 1000.00
     * price1 : 1500.00
     * price2 : 2000.00
     * price3 : 0.00
     * price : 2000.00
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
     * is_shipping : 1
     * sku_attr :
     * sku_unit :
     * created : 1448025744
     * updated : 1449222941
     * logo : http://112.74.114.103/upfile/product/pic/201512/04/56616312e0a6e132_m.jpg
     * photo : http://112.74.114.103/upfile/product/pic/201512/04/56616312e0a6e132.jpg
     * brand : 鏈垫媺鑺
     * content : sdfsdfsdf
     * attrs : []
     * photos : [{"url":"http://112.74.114.103/upfile/product/pic/201512/04/56616312e0a6e132.jpg","is_rec":"1","listorder":"100"}]
     * <p>
     * <p>
     * <p>
     * 说明：
     * 参看商品列表接口
     * photos: 商品所有图片数组
     * url: 图片地址
     * is_rec: 是否推荐
     * listorder: 图片顺序
     * **
     * **
     * ---------------------------------------------------------------------
     * 2015.12.24新增
     * ---------------------------------------------------------------------
     * colors: 颜色数组
     * attribute_id： 属性ID，
     * value_id: 属性值ID
     * name: 属性值名称
     * thumb: 缩略图
     * pic: 大图
     * ---------------------------------------------------------------------
     * xattributes: 除了colors外的属性，多维数组
     * attribute: 属性对象
     * values: 属性值数组，由属性值对象组成
     * ----------------------------------------------------------------------
     * skus: 商品sku数组
     * sku: 商品sku标签，格式是：属性ID:属性值ID;属性ID:属性值ID.....
     * attrnames: 属性名称标签， 格式是：属性名:属性值名;属性名:属性值名......
     * price: 商品sku对应价格
     * stock: 商品sku对应库存
     * ---------------------------------------------------------------------
     * 20160425新增
     * "fenqi":[{"fenqi":"2","fenqi_price":"333"}]分期，期数加每期价格
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
    private String sku_attr;
    private String sku_unit;
    private String created;
    private String updated;
    private String logo;
    private String photo;
    private String brand;
    private String content;
    private LoanEntity loan;

    //库存
    private String stock;
    //最低首付比率
    private double shoufu_min_ratio;

    public double getShoufu_max_ratio() {
        return shoufu_max_ratio;
    }

    public void setShoufu_max_ratio(double shoufu_max_ratio) {
        this.shoufu_max_ratio = shoufu_max_ratio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public double getShoufu_min_ratio() {
        return shoufu_min_ratio;
    }

    public void setShoufu_min_ratio(double shoufu_min_ratio) {
        this.shoufu_min_ratio = shoufu_min_ratio;
    }

    //最高首付比率
    private double shoufu_max_ratio;


    public LoanEntity getLoan() {
        return loan;
    }

    public void setLoan(LoanEntity loan) {
        this.loan = loan;
    }



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
    private List<?> attrs;
    /**
     * fenqi : 2
     * fenqi_price : 333
     */

    private List<FenqiEntity> fenqi;
    /**
     * url : http://112.74.114.103/upfile/product/pic/201512/04/56616312e0a6e132.jpg
     * is_rec : 1
     * listorder : 100
     */

    private List<PhotosEntity> photos;
    /**
     * attribute_id : ​6
     * value_id : ​40
     * name : 红
     * thumb : http://cyp.dev/upfile/product/pic/201512/23/567ab58c31786783_s.jpg
     * pic : http://cyp.dev/upfile/product/pic/201512/23/567ab58c31786783.jpg
     */

    private List<ColorsEntity> colors;
    /**
     * attribute : {"attribute_id":"\u200b21","name":"尺码"}
     * values : [{"value_id":"\u200b32","name":"大码"},{"value_id":"\u200b34","name":"中码"}]
     */

    private List<XattributesEntity> xattributes;
    /**
     * sku : 6: 40;21: 32
     * attrnames : 颜色: 红;尺码: 大码
     * price : 500.00
     * stock : ​5
     */

    private List<SkusEntity> skus;

    public void setProduct_id (String product_id) {
        this.product_id = product_id;
    }

    public void setSupplier_id (String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setCid (String cid) {
        this.cid = cid;
    }

    public void setCatcode (String catcode) {
        this.catcode = catcode;
    }

    public void setBrand_id (String brand_id) {
        this.brand_id = brand_id;
    }

    public void setPhoto_id (String photo_id) {
        this.photo_id = photo_id;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setSubtitle (String subtitle) {
        this.subtitle = subtitle;
    }

    public void setCode (String code) {
        this.code = code;
    }

    public void setSpec (String spec) {
        this.spec = spec;
    }

    public void setSummary (String summary) {
        this.summary = summary;
    }

    public void setGift (String gift) {
        this.gift = gift;
    }

    public void setCost (String cost) {
        this.cost = cost;
    }

    public void setPrice1 (String price1) {
        this.price1 = price1;
    }

    public void setPrice2 (String price2) {
        this.price2 = price2;
    }

    public void setPrice3 (String price3) {
        this.price3 = price3;
    }

    public void setPrice (String price) {
        this.price = price;
    }

    public void setMiaosd (String miaosd) {
        this.miaosd = miaosd;
    }

    public void setMiaoed (String miaoed) {
        this.miaoed = miaoed;
    }

    public void setPoints (String points) {
        this.points = points;
    }

    public void setSend_points (String send_points) {
        this.send_points = send_points;
    }

    public void setSales (String sales) {
        this.sales = sales;
    }

    public void setComments (String comments) {
        this.comments = comments;
    }

    public void setListorder (String listorder) {
        this.listorder = listorder;
    }

    public void setHits (String hits) {
        this.hits = hits;
    }

    public void setStick (String stick) {
        this.stick = stick;
    }

    public void setHas_banner (String has_banner) {
        this.has_banner = has_banner;
    }

    public void setOrder_sday (String order_sday) {
        this.order_sday = order_sday;
    }

    public void setOrder_eday (String order_eday) {
        this.order_eday = order_eday;
    }

    public void setDiscountx (String discountx) {
        this.discountx = discountx;
    }

    public void setIs_shipping (String is_shipping) {
        this.is_shipping = is_shipping;
    }

    public void setSku_attr (String sku_attr) {
        this.sku_attr = sku_attr;
    }

    public void setSku_unit (String sku_unit) {
        this.sku_unit = sku_unit;
    }

    public void setCreated (String created) {
        this.created = created;
    }

    public void setUpdated (String updated) {
        this.updated = updated;
    }

    public void setLogo (String logo) {
        this.logo = logo;
    }

    public void setPhoto (String photo) {
        this.photo = photo;
    }

    public void setBrand (String brand) {
        this.brand = brand;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public void setAttrs (List<?> attrs) {
        this.attrs = attrs;
    }

    public void setFenqi(List<FenqiEntity> fenqi) {
        this.fenqi = fenqi;
    }

    public void setPhotos (List<PhotosEntity> photos) {
        this.photos = photos;
    }

    public String getProduct_id () {
        return product_id;
    }

    public String getSupplier_id () {
        return supplier_id;
    }

    public String getCid () {
        return cid;
    }

    public String getCatcode () {
        return catcode;
    }

    public String getBrand_id () {
        return brand_id;
    }

    public String getPhoto_id () {
        return photo_id;
    }

    public String getName () {
        return name;
    }

    public String getSubtitle () {
        return subtitle;
    }

    public String getCode () {
        return code;
    }

    public String getSpec () {
        return spec;
    }

    public String getSummary () {
        return summary;
    }

    public String getGift () {
        return gift;
    }

    public String getCost () {
        return cost;
    }

    public String getPrice1 () {
        return price1;
    }

    public String getPrice2 () {
        return price2;
    }

    public String getPrice3 () {
        return price3;
    }

    public String getPrice () {
        return price;
    }

    public String getMiaosd () {
        return miaosd;
    }

    public String getMiaoed () {
        return miaoed;
    }

    public String getPoints () {
        return points;
    }

    public String getSend_points () {
        return send_points;
    }

    public String getSales () {
        return sales;
    }

    public String getComments () {
        return comments;
    }

    public String getListorder () {
        return listorder;
    }

    public String getHits () {
        return hits;
    }

    public String getStick () {
        return stick;
    }

    public String getHas_banner () {
        return has_banner;
    }

    public String getOrder_sday () {
        return order_sday;
    }

    public String getOrder_eday () {
        return order_eday;
    }

    public String getDiscountx () {
        return discountx;
    }

    public String getIs_shipping () {
        return is_shipping;
    }

    public String getSku_attr () {
        return sku_attr;
    }

    public String getSku_unit () {
        return sku_unit;
    }

    public String getCreated () {
        return created;
    }

    public String getUpdated () {
        return updated;
    }

    public String getLogo () {
        return logo;
    }

    public String getPhoto () {
        return photo;
    }

    public String getBrand () {
        return brand;
    }

    public String getContent () {
        return content;
    }

    public List<?> getAttrs () {
        return attrs;
    }

    public List<FenqiEntity> getFenqi() {
        return fenqi;
    }

    public List<PhotosEntity> getPhotos () {
        return photos;
    }

    public void setColors (List<ColorsEntity> colors) {
        this.colors = colors;
    }

    public void setXattributes (List<XattributesEntity> xattributes) {
        this.xattributes = xattributes;
    }

    public void setSkus (List<SkusEntity> skus) {
        this.skus = skus;
    }

    public List<ColorsEntity> getColors () {
        return colors;
    }

    public List<XattributesEntity> getXattributes () {
        return xattributes;
    }

    public List<SkusEntity> getSkus () {
        return skus;
    }

    public static class FenqiEntity implements  Serializable {
        private String fenqi;
        private String fenqi_price;

        public void setFenqi(String fenqi) {
            this.fenqi = fenqi;
        }

        public void setFenqi_price(String fenqi_price) {
            this.fenqi_price = fenqi_price;
        }

        public String getFenqi() {
            return fenqi;
        }

        public String getFenqi_price() {
            return fenqi_price;
        }
    }

    public static class PhotosEntity implements  Serializable{
        private String url;
        private String is_rec;
        private String listorder;

        public void setUrl (String url) {
            this.url = url;
        }

        public void setIs_rec (String is_rec) {
            this.is_rec = is_rec;
        }

        public void setListorder (String listorder) {
            this.listorder = listorder;
        }

        public String getUrl () {
            return url;
        }

        public String getIs_rec () {
            return is_rec;
        }

        public String getListorder () {
            return listorder;
        }
    }

    public static class ColorsEntity implements  Serializable{
        private String attribute_id;
        private String value_id;
        private String name;
        private String thumb;
        private String pic;

        public void setAttribute_id (String attribute_id) {
            this.attribute_id = attribute_id;
        }

        public void setValue_id (String value_id) {
            this.value_id = value_id;
        }

        public void setName (String name) {
            this.name = name;
        }

        public void setThumb (String thumb) {
            this.thumb = thumb;
        }

        public void setPic (String pic) {
            this.pic = pic;
        }

        public String getAttribute_id () {
            return attribute_id;
        }

        public String getValue_id () {
            return value_id;
        }

        public String getName () {
            return name;
        }

        public String getThumb () {
            return thumb;
        }

        public String getPic () {
            return pic;
        }
    }

    public static class XattributesEntity implements  Serializable{
        /**
         * attribute_id : ​21
         * name : 尺码
         */

        private AttributeEntity attribute;
        /**
         * value_id : ​32
         * name : 大码
         */

        private List<ValuesEntity> values;

        public void setAttribute (AttributeEntity attribute) {
            this.attribute = attribute;
        }

        public void setValues (List<ValuesEntity> values) {
            this.values = values;
        }

        public AttributeEntity getAttribute () {
            return attribute;
        }

        public List<ValuesEntity> getValues () {
            return values;
        }

        public static class AttributeEntity {
            private String attribute_id;
            private String name;

            public void setAttribute_id (String attribute_id) {
                this.attribute_id = attribute_id;
            }

            public void setName (String name) {
                this.name = name;
            }

            public String getAttribute_id () {
                return attribute_id;
            }

            public String getName () {
                return name;
            }
        }

        public static class ValuesEntity {
            private String value_id;
            private String name;

            public void setValue_id (String value_id) {
                this.value_id = value_id;
            }

            public void setName (String name) {
                this.name = name;
            }

            public String getValue_id () {
                return value_id;
            }

            public String getName () {
                return name;
            }
        }
    }

    public static class SkusEntity implements  Serializable{
        private String sku_id;
        private String sku;
        private String attrnames;
        private String price;
        private String stock;

        public String getSku_id () {
            return sku_id;
        }

        public void setSku_id (String sku_id) {
            this.sku_id = sku_id;
        }

        public void setSku (String sku) {
            this.sku = sku;
        }

        public void setAttrnames (String attrnames) {
            this.attrnames = attrnames;
        }

        public void setPrice (String price) {
            this.price = price;
        }

        public void setStock (String stock) {
            this.stock = stock;
        }

        public String getSku () {
            return sku;
        }

        public String getAttrnames () {
            return attrnames;
        }

        public String getPrice () {
            return price;
        }

        public String getStock () {
            return stock;
        }
    }
}
