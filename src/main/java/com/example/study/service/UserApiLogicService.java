package com.example.study.service;

import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagenation;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.repository.UserRepository;
import ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse,User> {

    @Autowired
    OrderGroupApiLogicService orderGroupApiLogicService;
    @Autowired
    ItemApiLogicService itemApiLogicService;

    public Header<List<UserApiResponse>> search(Pageable pageable){
        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList()); // List 로 변환

        Pagenation pagenation = Pagenation.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponseList,pagenation);
    }

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1.request data
        UserApiRequest userApiRequest = request.getData();
        // 2. User 생성
        User user = User.builder()
                            .account(userApiRequest.getAccount())
                            .password(userApiRequest.getPassword())
                            .status(UserStatus.REGISTERED)
                            .phoneNumber(userApiRequest.getPhoneNumber())
                            .email(userApiRequest.getEmail())
                            .registeredAt(LocalDateTime.now())
                            .build();
        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터  -> UserApiResponse return
        return Header.OK(response(newUser));
    }
    private UserApiResponse response (User user){
        // user - > userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())  // todo 암호화 , 길이
                 .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
        // Header + data return
        return userApiResponse;
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id - > repository getOne, getById
        Optional<User> optional = baseRepository.findById(id);

        // user -> userApiResponse return
       return optional.map(user -> Header.OK(response(user)) )
                    .orElseGet( () -> Header.ERROR("데이터 없음") );
                // orElseGet() null일때만 동작

    }
    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1.data
            UserApiRequest userApiRequest = request.getData();
        //2. id -> user  데이터를 찾고
            Optional<User> optional = baseRepository.findById(userApiRequest.getId());
            return optional.map(user -> {
                //3. update
                user.setAccount(userApiRequest.getAccount())
                        .setPassword(userApiRequest.getPassword())
                        .setPhoneNumber(userApiRequest.getPhoneNumber())
                        .setStatus(userApiRequest.getStatus())
                        .setEmail(userApiRequest.getEmail())
                        .setRegisteredAt(userApiRequest.getRegisteredAt())
                        .setUnregisteredAt(userApiRequest.getUnregisteredAt());
                    return user;
            })
                    .map(user -> baseRepository.save(user))                 //update -> newUser
                    .map(newUser -> response(newUser))                      // userApiResponse
                    .map(Header::OK)
                    .orElseGet(()->Header.ERROR("데이터 없음."));
    }
    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
            Optional<User> optional = baseRepository.findById(id);
        // 2. repository -> delete
        // 3. response return
            return optional.map(user -> {
                baseRepository.delete(user);
                    return Header.OK();
            }).orElseGet(() ->  Header.ERROR("데이터 없음") );
     }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        //User
        User user = baseRepository.getById(id);
        UserApiResponse userApiResponse = response(user);

        //orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroups();

        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();

                    // item api response
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                   return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();
                return Header.OK(userOrderInfoApiResponse);
    }
}
