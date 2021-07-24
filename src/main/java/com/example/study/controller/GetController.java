package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @GetMapping("/header")
    public Header getHeader(){
        // { "resultCode" : "OK" , "description" : "OK" }
        return Header.builder().resultCode("OK").description("OK").build();
    }

    @RequestMapping(method = RequestMethod.GET,path = "/getMethod")
    public String getRequest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam(name = "pass") String password){
        System.out.println("id" +" "+"pass");

        return id +" "+password;
    }

    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter (SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage()+2);
        return searchParam;
    }

}
