package com.cnbeta.cnbetaone.entity;

import lombok.Data;

@Data
public class CnbetaBaseResponse<T> {

    private int code;
    private String msg;
    private String status;
    private T result;

}
