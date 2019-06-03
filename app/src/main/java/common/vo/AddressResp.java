package common.vo;

import common.model.BaseResp;

/**
 * Created by Administrator on 2016/10/10.
 */

public class AddressResp extends BaseResp {


    /**
     * msg : 返回正常
     * data : {"country":"中国","adcode":"520112","city":"贵阳市","street":"长岭南路辅路","district":"观山湖区"}
     * status : 200
     */

    private String msg;
    /**
     * country : 中国
     * adcode : 520112
     * city : 贵阳市
     * street : 长岭南路辅路
     * district : 观山湖区
     */

    private DataBean data;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        private String country;
        private String adcode;
        private String city;
        private String street;
        private String district;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }
    }
}
