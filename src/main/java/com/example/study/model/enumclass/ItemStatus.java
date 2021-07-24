package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemStatus {
    REGISTERED(0,"등록","상품등록상태"),
    UNREGISTERED(1,"해지","상품해지상태"),
    WAITING(2,"대기","상품검수상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
