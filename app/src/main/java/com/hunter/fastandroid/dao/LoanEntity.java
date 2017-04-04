package com.hunter.fastandroid.dao;

/**
 * Created by ymn on 2017/4/4.
 */
public class LoanEntity {

    /**
     * "loan": { //该商品对应的贷款产品对象
     "id": 3, //贷款ID
     "name": "贷款产品3",
     "instalment_type": 3,
     "instalments": "1,2,3,4,5,6,7,8,9,10,11,12", //支持的分期数
     "rate": "0.1900", //月利率
     "manage_rate": "0.0030", //管理费率
     "handling_rate": "0.0040", //手续费率
     "xd_id": "3", //对应小贷ID
     "listorder": 0,
     "description": "",
     "created": 1470047454,
     "updated": 1470054848
     }
     */
    /** 贷款ID */
    private String id;
    /** 贷款产品 */
    private String name;
    /** 默认贷款期数 */
    private String instalment_type;
    /** 支持的分期数 */
    private String instalments;
    /** 月利率 */
    private String rate;
    /** 管理费率 */
    private String manage_rate;
    /** 手续费率 */
    private String handling_rate;
    /** 对应小贷ID */
    private String xd_id;

    public String getXd_id() {
        return xd_id;
    }

    public void setXd_id(String xd_id) {
        this.xd_id = xd_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstalment_type() {
        return instalment_type;
    }

    public void setInstalment_type(String instalment_type) {
        this.instalment_type = instalment_type;
    }

    public String getInstalments() {
        return instalments;
    }

    public void setInstalments(String instalments) {
        this.instalments = instalments;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getManage_rate() {
        return manage_rate;
    }

    public void setManage_rate(String manage_rate) {
        this.manage_rate = manage_rate;
    }

    public String getHandling_rate() {
        return handling_rate;
    }

    public void setHandling_rate(String handling_rate) {
        this.handling_rate = handling_rate;
    }
}