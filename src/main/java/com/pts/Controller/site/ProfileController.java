package com.pts.Controller.site;


import com.pts.DAO.AccountDAO;
import com.pts.entity.Account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
@Controller
public class ProfileController {
    @Autowired
    AccountDAO accountDAO;
    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model m){
        String us=request.getRemoteUser();
        Account acc =accountDAO.laytk(us);
        m.addAttribute("acc",acc);
        return "/site/profile";
    }
    @PostMapping("/edit-name")
    public String editname(Model m,@RequestParam("fullname")String fullname,HttpServletRequest request) {
    	String username=request.getRemoteUser();
        Account acc =accountDAO.laytk(username);
        acc.setTps_Fullname(fullname);
        accountDAO.save(acc);
    	return "redirect:/profile";
    }
    
    //sulyanh
    @PostMapping("/edit-photo")
    public String editphoto(Model m,@RequestParam("mulimage") MultipartFile file
    		,HttpServletRequest request)throws IOException{
    	String username=request.getRemoteUser();
        Account acc =accountDAO.laytk(username);
    	String uniqueFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
		File filePath = new File("src/main/webapp/image");
		file.transferTo(new File(filePath.getAbsolutePath() + "/" + uniqueFileName));
		acc.setTps_Photo(uniqueFileName);
		accountDAO.save(acc);
    	return "redirect:/profile";
    }
}
