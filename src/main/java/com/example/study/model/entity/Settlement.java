package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
@DiscriminatorValue("settlement")
public class Settlement extends User {
    private BigDecimal price;
// test22
}
