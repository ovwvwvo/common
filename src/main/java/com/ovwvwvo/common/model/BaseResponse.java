package com.ovwvwvo.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 16/8/15.
 */
public class BaseResponse {

    @SerializedName("code")
    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
