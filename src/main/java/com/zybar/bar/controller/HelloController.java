package com.zybar.bar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 刘佳昇
 * @Date 2019/7/24 22:04
 */

@Controller
public class HelloController {
    @GetMapping("/")
    public String hello(@RequestParam(name = "name")String name, Model model){

        model.addAttribute("name",name);

        return "index";

    }
}

