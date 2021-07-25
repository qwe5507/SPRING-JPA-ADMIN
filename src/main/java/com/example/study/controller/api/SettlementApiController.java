package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Settlement;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.SettlementApiRequest;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.SettlementApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
CREATE TABLE `study`.`settlement` (
  `user_id` BIGINT NOT NULL,
  `price` DECIMAL(12,4) NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_USER_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `study`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
 */
@Slf4j
@RestController
@RequestMapping("/api/settlement")
public class SettlementApiController extends CrudController<SettlementApiRequest, SettlementApiResponse, Settlement> {

        //CRUD
}
