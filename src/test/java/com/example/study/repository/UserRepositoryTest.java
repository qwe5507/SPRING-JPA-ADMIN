package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

//    @Test
//    public void create(){
//        //String sql = insert into user(%s, %s, %d) value (account, email, age)
//        User user = new User();
////        user.setId(20L);
//        user.setAccount("Testuser05");
//        user.setEmail("Testuser05@gmail.com");
//        user.setPhoneNumber("010-1155-1124");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy("admin4");
//        //user 테이블에 맞춰서 notnull 인것은 필수적으로 기입했다.
//        User newUser = userRepository.save(user);
//        System.out.println(newUser);
//        System.out.println("야래야래");
//    }

        @Test
    public void create() {
            String account = "Test01";
            String password = "Test01";
            String status = "REGISTERED";
            String email = "Test01@gmail.com";
            String phoneNumber = "010-1111-2222";
            LocalDateTime registeredAt = LocalDateTime.now();
            LocalDateTime createdAt = LocalDateTime.now();
            String createdBy = "AdminServer";

            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setStatus(status);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setRegisteredAt(registeredAt);

//            user.setAccount().setCreatedAt().setEmail();

            User u = User.builder()
                        .account("홍순이")
                        .password("1234")
                        .build();

            User newUser = userRepository.save(user);
            Assertions.assertNotNull(newUser);

        }

    @Test
    @Transactional
    public void read(){
            User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

            if(user != null) {
                user.getOrderGroups().stream().forEach(orderGroup -> {
                    System.out.println("----------- 주문 묶음 ----------- ");
                    System.out.println("수령인 : " + orderGroup.getRevName());
                    System.out.println("수령지 : " + orderGroup.getRevAddress());
                    System.out.println("총 금액 : " + orderGroup.getTotalPrice());
                    System.out.println("총 수량 : " + orderGroup.getTotalQuantity());
                    System.out.println("----------- 주문 상세 ----------- ");
                    orderGroup.getOrderDetailList().stream().forEach((orderDetail -> {
                                System.out.println("파트너사 이름 : "+ orderDetail.getItem().getPartner().getName());
                                System.out.println("파트너사 카테코리 : "+ orderDetail.getItem().getPartner().getCategory().getTitle());
                                System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                                System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
                                System.out.println("주문의 상태 : "+orderDetail.getStatus()) ;
                                System.out.println("도착 예정 일자 : "+orderDetail.getArrivalDate()) ;
                    })
                    );
                });
            }

            Assertions.assertNotNull(user);

//        //select * from user where id = ?
//        Optional<User> user = userRepository.findByAccount("Testuser05");
//
//        user.ifPresent( selectUser ->{
//            selectUser.getOrderDetailList().stream().forEach( orderDetail -> {
//                Item item = orderDetail.getItem();
//                System.out.println(item);
//
//            } );
//        });
    }


    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent( selectUser ->{
                selectUser.setAccount("pppp");
                selectUser.setUpdatedAt(LocalDateTime.now());
                selectUser.setUpdatedBy("이진강");

                userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(2L);

        assertTrue(user.isPresent());

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        } );

        Optional<User> deleteUser = userRepository.findById(2L);

        assertFalse(deleteUser.isPresent());

//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재" + deleteUser.get()) ;
//        }else {
//            System.out.println("데이터 삭제됨");
//        }
    }
}
