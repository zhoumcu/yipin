package com.hunter.fastandroid.dao;

import java.util.List;

/**
 * author：Administrator on 2017/3/31 16:21
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ShoppingVip {

    /**
     * name : A
     * space_id : 23528734
     * img_src : http://img1.imgtn.bdimg.com/it/u=1538368858,4289171815&fm=11&gp=0.jpg
     * product : [{"name":"innisfree/悦诗风吟乳液 浆果美白提亮保湿乳液","product_id":23528865,"fenqi_num":"1","fenqi_per":"111.00","picture_url":"http://tourdev.darlingwallet.com/upfile/product/pic/201604/21/5718b4c7dc850729.png"},{"name":"innisfree/悦诗风吟眼霜 浆果美白提亮温和淡化黑眼圈细纹","product_id":23528863,"fenqi_num":"2","fenqi_per":"333.00","picture_url":"http://tourdev.darlingwallet.com/upfile/product/pic/201604/21/5718b4bee8e31757.png"},{"name":"innisfree/悦诗风吟面膜 毛孔清洁紧实火山岩泥面膜 100ml","product_id":23528859,"fenqi_num":"4","fenqi_per":"231.00","picture_url":"http://tourdev.darlingwallet.com/upfile/product/pic/201604/21/5718b3e052711790.png"}]
     */

    private String name;
    private int ad_id;
    private String title;
    private String linkurl;
    private String imgsrc;
    /**
     * name : innisfree/悦诗风吟乳液 浆果美白提亮保湿乳液
     * product_id : 23528865
     * fenqi_num : 1
     * fenqi_per : 111.00
     * picture_url : http://tourdev.darlingwallet.com/upfile/product/pic/201604/21/5718b4c7dc850729.png
     */

    private List<ProductEntity> products;

    public void setName(String name) {
        this.name = name;
    }

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }



    public static class ProductEntity {
        private int product_id;
        private String fenqi_num;
        private String fenqi_per;
        private String photo;
        private String name;


        public void setName(String name) {
            this.name = name;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public void setFenqi_num(String fenqi_num) {
            this.fenqi_num = fenqi_num;
        }

        public void setFenqi_per(String fenqi_per) {
            this.fenqi_per = fenqi_per;
        }


        public String getName() {
            return name;
        }

        public int getProduct_id() {
            return product_id;
        }

        public String getFenqi_num() {
            return fenqi_num;
        }

        public String getFenqi_per() {
            return fenqi_per;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
