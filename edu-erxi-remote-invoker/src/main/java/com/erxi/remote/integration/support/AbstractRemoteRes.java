package com.erxi.remote.integration.support;

import java.io.Serializable;

/**
 * @author qingyin
 * @date 2016/8/27
 */
public abstract class AbstractRemoteRes implements Serializable{
    private static final long serialVersionUID = -4115791655799368714L;

    private String            respCode;
    private String            respDesc;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    @Override
    public String toString() {
        return "AbstractRemoteRes{" +
                "respCode='" + respCode + '\'' +
                ", respDesc='" + respDesc + '\'' +
                '}';
    }
}
