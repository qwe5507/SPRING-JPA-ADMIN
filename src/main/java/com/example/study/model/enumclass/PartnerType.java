package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PartnerType {
    REGISTERED(0,"파트너등록상태"),
    UNREGISTERED(1,"파트너해지상태"),
    ;
    private Integer id;
    private String title;
}
