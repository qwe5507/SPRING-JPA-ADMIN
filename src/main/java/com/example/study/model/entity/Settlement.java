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
@ToString(exclude = {"user"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Settlement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private BigDecimal price;
    @OneToOne
    private User user;

}
