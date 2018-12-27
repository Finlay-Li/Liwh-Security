package com.liwh.domain;

import lombok.Data;

/**
 * @author: Liwh
 * @ClassName: SimpleRespone
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-27 4:32 PM
 */
@Data
public class SimpleRespone {

    private int code;
    private String msg;

    public SimpleRespone(String msg) {
        this.msg = msg;
    }

    public SimpleRespone(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
