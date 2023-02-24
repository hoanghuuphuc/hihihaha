package com.pts.Controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoiMatKhauController {
    @GetMapping("/doi-mat-khau")
    public String doimatkhau(){
        return "/site/doimatKhau";
    }
}
