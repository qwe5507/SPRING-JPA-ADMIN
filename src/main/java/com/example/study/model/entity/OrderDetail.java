package com.example.study.model.entity;

import javassist.runtime.Inner;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = {"user","item"})
@ToString(exclude = {"orderGroup","item"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

//    private Long itemId;
//    private Long orderGroupId;

    //OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;

    //OrderDetail N : 1 Item
    @ManyToOne
    private Item item;
//    // N : 1
//    @ManyToOne
//    private User user;
//    // N : 1
//    @ManyToOne
//    private Item item;


}
