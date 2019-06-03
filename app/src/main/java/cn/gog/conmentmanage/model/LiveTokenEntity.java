package cn.gog.conmentmanage.model;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/12/5.
 */

public class LiveTokenEntity implements Serializable {
    String access_token;
    String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
