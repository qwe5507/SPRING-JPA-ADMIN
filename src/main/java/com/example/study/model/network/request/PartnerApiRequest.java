package com.example.study.model.network.request;

import com.example.study.model.enumclass.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiRequest {
    private Long id;
    private String name;
    private PartnerType status;
    private String address;
    private String callCenter;
    private String partnerNumber; //파트너 담당자의 전화번호
    private String businessNumber; //사업자 번호
    private String ceoName;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt; //우리랑 계약을 끊은 날짜
    private Long categoryId;
}
