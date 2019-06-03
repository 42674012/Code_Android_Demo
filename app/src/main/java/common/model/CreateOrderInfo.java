package common.model;

/**
 * <p>
 * <p>
 * <p>
 * Author: Zk
 * Time:2016/12/17  10:56
 */
public class CreateOrderInfo extends BaseResp {


    /**
     * type : 03
     * unitOrgCode : 0307001
     * orgCode : 0307000
     * areaCode : 520400
     * consNo : 11120065
     * amt : 20.20
     * payAmt : 0.00
     * discountedAmt : 0.00
     * source : 01
     * payType : 04
     * queryTranNo : 43B3598D059743858EF08C6F86741B6F
     */

    private String type;
    private String unitOrgName;
    private String unitOrgCode;
    private String orgCode;
    private String areaCode;
    private String consNo;
    private String amt;
    private String payAmt;
    private String discountedAmt;
    private String source;
    private String payType;
    private String queryTranNo;
    private String quantity;


    public String getUnitOrgName() {
        return unitOrgName;
    }

    public void setUnitOrgName(String unitOrgName) {
        this.unitOrgName = unitOrgName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitOrgCode() {
        return unitOrgCode;
    }

    public void setUnitOrgCode(String unitOrgCode) {
        this.unitOrgCode = unitOrgCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(String payAmt) {
        this.payAmt = payAmt;
    }

    public String getDiscountedAmt() {
        return discountedAmt;
    }

    public void setDiscountedAmt(String discountedAmt) {
        this.discountedAmt = discountedAmt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getQueryTranNo() {
        return queryTranNo;
    }

    public void setQueryTranNo(String queryTranNo) {
        this.queryTranNo = queryTranNo;
    }
}
