package pers.yangs.business.controller;

import pers.yangs.winterframework.web.annotation.Controller;
import pers.yangs.winterframework.web.annotation.RequestMapping;

/**
 * Created by Ytadp on 2018/4/22.
 */
@Controller(value = "abc")
public class TestController {

    @RequestMapping("test")
    public String test(){
        return "test";
    }
}
