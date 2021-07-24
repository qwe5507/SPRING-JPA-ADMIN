package com.example.study.controller;

import com.example.study.model.network.Header;
import com.example.study.service.BaseService;
import ifs.CrudInterface;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController <Req,Res>implements CrudInterface<Req,Res> {

    protected BaseService<Req,Res> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> req) {
        return baseService.create(req);
    }
    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable(name ="id") Long id) {
        return baseService.read(id);
    }
    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> req) {
        return baseService.update(req);
    }
    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable(name = "id") Long id) {
        return baseService.delete(id);
    }
}
