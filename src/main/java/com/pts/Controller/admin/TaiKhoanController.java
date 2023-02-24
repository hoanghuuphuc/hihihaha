package com.pts.Controller.admin;

import com.pts.DAO.AccountDAO;
import com.pts.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TaiKhoanController {
    @Autowired
    AccountDAO accountDAO;
    @RequestMapping("admin")
    public String qltk(Model m){

        List<Account> acc = accountDAO.findAll();
        m.addAttribute("acc",acc);


        return "Admin/qltk";
    }
}
