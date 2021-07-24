package com.example.study.service;

import com.example.study.model.network.Header;
import ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public abstract class  BaseService <req,res,Entity> implements CrudInterface<req,res> {

    @Autowired(required = false)
    protected JpaRepository<Entity,Long> baseRepository;

}
