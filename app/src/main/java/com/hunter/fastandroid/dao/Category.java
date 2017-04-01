package com.hunter.fastandroid.dao;

/**
 * author：Administrator on 2017/4/1 09:43
 * company: xxxx
 * email：1032324589@qq.com
 */
public class Category {

    /**
     * cid : 46
     * parent_id : 0
     * photo_id : 4
     * counts : 5
     * depth : 1
     * is_leaf : 0
     * name : 底盘件
     * item_count : 0
     * disord : 1
     * created : 1411195666
     * updated : 1447315471
     *

     cid: 类别ID
     parent_id: 父类ID
     name: 类别名称
     depth: 分类层次（第几级）
     is_leaf: 是否叶子结点
     disord: 排序值（同级分类序号）
     其他参数不用关注

     */

    private String cid;
    private String parent_id;
    private String photo_id;
    private String counts;
    private String depth;
    private String is_leaf;
    private String name;
    private String item_count;
    private String disord;
    private String created;
    private String updated;

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    private boolean isFirst;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    private boolean isShow;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setIs_leaf(String is_leaf) {
        this.is_leaf = is_leaf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }

    public void setDisord(String disord) {
        this.disord = disord;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCid() {
        return cid;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public String getCounts() {
        return counts;
    }

    public String getDepth() {
        return depth;
    }

    public String getIs_leaf() {
        return is_leaf;
    }

    public String getName() {
        return name;
    }

    public String getItem_count() {
        return item_count;
    }

    public String getDisord() {
        return disord;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }
}
