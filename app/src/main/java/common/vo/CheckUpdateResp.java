package common.vo;

import common.model.BaseResp;

/**
 * Created by Administrator on 2016/10/4.
 */

public class CheckUpdateResp  extends BaseResp {


    /**
     * status : 200
     * msg : 返回正常
     * data : {"forcible":true,"version":"2.0.0","versionCode":2,"download":"http://www.gogpay.cn/latest.apk","description":"多彩宝 2.0 全新上线啦！","detail":"全新的 UI 设计\n支持更多的机构"}
     */

    private int status;
    private String msg;
    /**
     * forcible : true
     * version : 2.0.0
     * versionCode : 2
     * download : http://www.gogpay.cn/latest.apk
     * description : 多彩宝 2.0 全新上线啦！
     * detail : 全新的 UI 设计
     支持更多的机构
     */

    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public static class DataBean {
        private boolean forcible;
        private String version;
        private int versionCode;
        private String download;
        private String description;
        private String detail;

        public boolean isForcible() {
            return forcible;
        }

        public void setForcible(boolean forcible) {
            this.forcible = forcible;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
