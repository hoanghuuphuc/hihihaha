package com.pts.Controller.site;

import com.pts.DAO.CourseDAO;
import com.pts.DAO.OrderDAO;
import com.pts.entity.Course;
import com.pts.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;


@Controller
public class CheckoutController {
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    OrderDAO orderDAO;
    @RequestMapping("/checkout/{id}")
    private String index(Model m, @PathVariable("id")int id){
        Course course =courseDAO.findById(id);
        m.addAttribute("checkout",course);
        return "/site/checkout";

    }
    @GetMapping("/add-course/{id}")
    public String themkhoahoc(Model m, @PathVariable("id")int id, HttpServletRequest request){
       
        Course course=courseDAO.findById(id);
        String username=request.getRemoteUser();
        int gia =course.getTps_Price();
        if(gia !=0) {
        	return "redirect:/";
        }else {
        	Order order = new Order();
            order.setTps_username(username);
            order.setCourseor(course);
            order.setTps_statuscode(false);
            orderDAO.save(order);
            return "redirect:/learning/{id}";
        	
        }
        
    }
}
