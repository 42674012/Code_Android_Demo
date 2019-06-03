/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.vo;

import java.util.List;

import common.model.BaseResp;

/**
 * 业务(水、电、燃等)
 * Author: Gujin
 * Time: 16/9/3  18:04
 */
public class BusinessResp extends BaseResp {


    /**
     * status : 200
     * msg : 返回正常
     * data : {"services":[{"name":"电费","icon":"http://api.duocairen.com/img/icon/electricity.png","uri":"dcb://recharge/common?type=01","marked":"1","mark_icon":"http://api.duocairen.com/img/icon/marked_new.png","openStatus":{"isOpen":false,"msg":"该功能即将开放"},"maintenance":{"startTime":"1473411311351","endTime":"1573411311351","msg":"系统正在进行例行维护,3日10点恢复"},"extraData":[{"key":"xxxxxx","value":"xxxxxx"}]},{"name":"燃气费","icon":"http://api.duocairen.com/img/icon/gas.png","uri":"dcb://recharge/common?type=05","marked":"0","mark_icon":null,"openStatus":{"isOpen":true,"msg":""},"maintenance":{"startTime":"1473411311351","endTime":"1573411311351","msg":"系统正在进行例行维护,3日10点恢复"},"extraData":[{"extraA":"xxxxxx","extraB":"xxxxxx"}]},{"name":"水费","icon":"http://api.duocairen.com/img/icon/water.png","uri":"dcb://recharge/common?type=03","marked":"0","mark_icon":null,"openStatus":{"isOpen":true,"msg":""},"maintenance":{},"extraData":[]},{"name":"有线电视","icon":"http://api.duocairen.com/img/icon/tv.png","uri":"dcb://recharge/groups","marked":"0","mark_icon":null,"nodes":[{"name":"基本电视费","icon":"http://api.duocairen.com/img/icon/tv.png","uri":"dcb://recharge/tv","marked":"0","mark_icon":null,"openStatus":{"isOpen":true,"msg":""},"maintenance":{},"extraData":[]},{"name":"电视套餐","icon":"http://api.duocairen.com/img/icon/tv.png","uri":"dcb://recharge/tv","marked":"0","mark_icon":null,"openStatus":{"isOpen":true,"msg":""},"maintenance":{"startTime":"1473411311351","endTime":"1573411311351","msg":"系统正在进行例行维护,3日10点恢复"},"extraData":[{"extraA":"xxxxxx","extraB":"xxxxxx"}]}]},{"name":"手机充值","icon":"http://api.duocairen.com/img/icon/phone.png","uri":"dcb://phone","marked":"0","mark_icon":null,"openStatus":{"isOpen":true,"msg":""},"maintenance":{},"extraData":[]},{"name":"宽带话费","icon":"http://api.duocairen.com/img/icon/internet.png","uri":"dcb://internet","marked":"0","mark_icon":null,"openStatus":{"isOpen":true,"msg":""},"maintenance":{},"extraData":[]}],"ad":[{"img":"http://api.duocairen.com/img/ad/001.png","uri":"https://eapply.abchina.com/onlinetrade/CreditcardsInfo/CreditIndex"}],"extra":[{"icon":"http://api.duocairen.com/img/icon/guide.png","name":"缴费指南","uri":"http://m.duocairen.com/guide"},{"icon":"http://api.duocairen.com/img/icon/help.png","name":"帮助中心","uri":"http://m.duocairen.com/help"}]}
     */

    private int status;
    private String msg;
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


        private SupportBean support;

        /**
         * dcbIntroduction : http://api.duocairen.com/introduction
         * userAgreement : http://api.duocairen.com/user_agreement
         */

        private PageBean page;

        /**
         * originalUrl : https://m.kuaidi100.com/
         * script : http://api.gogpay.cn/js/appBrowser/express.js
         */

        private List<DynamicScriptsBean> dynamicScripts;

        /**
         * name : 电费
         * type : 01
         * miniIcon : http://220.197.219.192/img/tradeHistory/nav_screenmenu_electricity.png
         * icon : http://220.197.219.192/img/icon/electricity.png
         */

        private List<IconsBean> icons;


        /**
         * name : 电费
         * icon : http://api.duocairen.com/img/icon/electricity.png
         * uri : dcb://recharge/common?type=01
         * marked : 1
         * mark_icon : http://api.duocairen.com/img/icon/marked_new.png
         * openStatus : {"isOpen":false,"msg":"该功能即将开放"}
         * maintenance : {"startTime":"1473411311351","endTime":"1573411311351","msg":"系统正在进行例行维护,3日10点恢复"}
         * extraData : [{"key":"xxxxxx","value":"xxxxxx"}]
         */

        private List<ServicesBean> services;
        /**
         * img : http://api.duocairen.com/img/ad/001.png
         * uri : https://eapply.abchina.com/onlinetrade/CreditcardsInfo/CreditIndex
         */

        private List<AdBean> ad;
        /**
         * icon : http://api.duocairen.com/img/icon/guide.png
         * name : 缴费指南
         * uri : http://m.duocairen.com/guide
         */

        private List<ExtraBean> extra;

        public List<DynamicScriptsBean> getDynamicScripts() {
            return dynamicScripts;
        }

        public void setDynamicScripts(List<DynamicScriptsBean> dynamicScripts) {
            this.dynamicScripts = dynamicScripts;
        }

        public SupportBean getSupport() {
            return support;
        }

        public void setSupport(SupportBean support) {
            this.support = support;
        }

        public List<IconsBean> getIcons() {
            return icons;
        }

        public void setIcons(List<IconsBean> icons) {
            this.icons = icons;
        }

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ServicesBean> getServices() {
            return services;
        }

        public void setServices(List<ServicesBean> services) {
            this.services = services;
        }

        public List<AdBean> getAd() {
            return ad;
        }

        public void setAd(List<AdBean> ad) {
            this.ad = ad;
        }

        public List<ExtraBean> getExtra() {
            return extra;
        }

        public void setExtra(List<ExtraBean> extra) {
            this.extra = extra;
        }


        public static class IconsBean {
            private String name;
            private String type;
            private String miniIcon;
            private String icon;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMiniIcon() {
                return miniIcon;
            }

            public void setMiniIcon(String miniIcon) {
                this.miniIcon = miniIcon;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class ServicesBean {
            private String name;
            private String icon;
            private String uri;
            private String marked;
            private String mark_icon;
            /**
             * isOpen : false
             * msg : 该功能即将开放
             */

            private OpenStatusBean openStatus;
            /**
             * startTime : 1473411311351
             * endTime : 1573411311351
             * msg : 系统正在进行例行维护,3日10点恢复
             */

            private MaintenanceBean maintenance;
            /**
             * key : xxxxxx
             * value : xxxxxx
             */

            private List<ExtraDataBean> extraData;


            private List<ServicesBean> nodes;


            public List<ServicesBean> getNodes() {
                return nodes;
            }

            public void setNodes(List<ServicesBean> nodes) {
                this.nodes = nodes;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public String getMarked() {
                return marked;
            }

            public void setMarked(String marked) {
                this.marked = marked;
            }

            public String getMark_icon() {
                return mark_icon;
            }

            public void setMark_icon(String mark_icon) {
                this.mark_icon = mark_icon;
            }

            public OpenStatusBean getOpenStatus() {
                return openStatus;
            }

            public void setOpenStatus(OpenStatusBean openStatus) {
                this.openStatus = openStatus;
            }

            public MaintenanceBean getMaintenance() {
                return maintenance;
            }

            public void setMaintenance(MaintenanceBean maintenance) {
                this.maintenance = maintenance;
            }

            public List<ExtraDataBean> getExtraData() {
                return extraData;
            }

            public void setExtraData(List<ExtraDataBean> extraData) {
                this.extraData = extraData;
            }

            public static class OpenStatusBean {
                private boolean isOpen;
                private String msg;

                public boolean isIsOpen() {
                    return isOpen;
                }

                public void setIsOpen(boolean isOpen) {
                    this.isOpen = isOpen;
                }

                public String getMsg() {
                    return msg;
                }

                public void setMsg(String msg) {
                    this.msg = msg;
                }
            }

            public static class MaintenanceBean {
                private String startTime;
                private String endTime;
                private String msg;

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public String getMsg() {
                    return msg;
                }

                public void setMsg(String msg) {
                    this.msg = msg;
                }
            }

            public static class ExtraDataBean {
                private String key;
                private String value;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }


        public static class SupportBean {
            private String tel;
            private String desc;

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

        }
        public static class DynamicScriptsBean {
            private String originalUrl;
            private String script;

            public String getOriginalUrl() {
                return originalUrl;
            }

            public void setOriginalUrl(String originalUrl) {
                this.originalUrl = originalUrl;
            }

            public String getScript() {
                return script;
            }

            public void setScript(String script) {
                this.script = script;
            }
        }
        public static class PageBean {
            private String weatherDetail;
            private String dcbIntroduction;
            private String userAgreement;
            private String helpCenter;

            public String getWeatherDetail() {
                return weatherDetail;
            }

            public void setWeatherDetail(String weatherDetail) {
                this.weatherDetail = weatherDetail;
            }

            public String getHelpCenter() {
                return helpCenter;
            }

            public void setHelpCenter(String helpCenter) {
                this.helpCenter = helpCenter;
            }

            public String getDcbIntroduction() {
                return dcbIntroduction;
            }

            public void setDcbIntroduction(String dcbIntroduction) {
                this.dcbIntroduction = dcbIntroduction;
            }

            public String getUserAgreement() {
                return userAgreement;
            }

            public void setUserAgreement(String userAgreement) {
                this.userAgreement = userAgreement;
            }
        }

        public static class AdBean {
            private String img;
            private String uri;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }
        }

        public static class ExtraBean {
            private String icon;
            private String name;
            private String uri;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }
        }
    }
}
