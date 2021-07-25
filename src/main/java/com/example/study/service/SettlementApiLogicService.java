package com.example.study.service;

import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.Partner;
import com.example.study.model.entity.Settlement;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagenation;
import com.example.study.model.network.request.SettlementApiRequest;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.*;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SettlementApiLogicService extends BaseService<SettlementApiRequest, SettlementApiResponse, Settlement> {

    @Autowired
    UserRepository userRepository;
    @Override
    public Header<SettlementApiResponse> read(Long id) {
        Optional<User> user = userRepository.findById(id);

        return baseRepository.findById(user.get().getId())
                .map(settlement -> response(settlement))
                .orElseGet(() -> Header.ERROR("데이터 없음"));

//       List<Settlement> settlements =  user.get().getSettlements();
//
//        BigDecimal totalPrice = BigDecimal.ZERO;
//        for(Settlement value : settlements) {
//            System.out.println(value);
//            totalPrice = totalPrice.add(value.getPrice());
//        }

//        SettlementApiResponse settlementApiResponse = SettlementApiResponse.builder()
//                .userId(user.get().getId())
//                .price(totalPrice)
    }
    private Header<SettlementApiResponse> response(Settlement settlement){
        SettlementApiResponse body = SettlementApiResponse.builder()
                .userId(settlement.getUser().getId())
                .price(settlement.getPrice())

                .build();
        return Header.OK(body);
    }

    @Override
    public Header<SettlementApiResponse> create(Header<SettlementApiRequest> req) {
        return null;
    }


    @Override
    public Header<SettlementApiResponse> update(Header<SettlementApiRequest> req) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    @Override
    public Header<List<SettlementApiResponse>> search(Pageable pageable) {
        return null;
    }
}
