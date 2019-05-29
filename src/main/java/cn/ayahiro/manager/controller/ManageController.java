package cn.ayahiro.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManageController {

    @RequestMapping(path = {"/management"}, method = RequestMethod.GET)
    public String management() {
        return "management";
    }
}
