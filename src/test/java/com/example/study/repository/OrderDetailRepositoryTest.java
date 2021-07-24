package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderDetailRepositoryTest extends StudyApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("준비중");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));  //현재 일자로 부터 2일 뒤
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));

//        orderDetail.setOrderGroupId(1L);        //  Long -> OrderGroup  //어떠한 장바구니에
//        orderDetail.setItemId(1L);                      //어떠한 상품

        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        assertNotNull(newOrderDetail);

    }
//    @Test
//    public void create(){
//        OrderDetail orderDetail = new OrderDetail();
//
//        orderDetail.setOrderAt(LocalDateTime.now());
//        //어떤 사람에 대한 인덱스 id
////        orderDetail.setUserId(8L);
//        //어떤 상품에 대한 인덱스 id
////        orderDetail.setItemId(1L);
//
//        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
//
//        assertNotNull(newOrderDetail);
//
//    }
}
