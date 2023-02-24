package com.pts.Controller.site;

import com.pts.DAO.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChangePasswordController {
    @Autowired
    AccountDAO accountDAO;
    @RequestMapping("/change-password")
    public String changepassword(){
        return "site/ChangePassword";
    }

}
