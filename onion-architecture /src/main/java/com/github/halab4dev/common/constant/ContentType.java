package com.github.halab4dev.common.constant;

import lombok.Getter;

/*
 *
 * @author halab
 */
@Getter
public enum ContentType {

    APPLICATION_JSON_UTF8("application/json;charset=UTF-8"),
    APPLICATION_X_FORM_URL_ENCODED("application/x-www-form-urlencoded");

    private String value;

    ContentType(String value) {
        this.value = value;
    }
}
