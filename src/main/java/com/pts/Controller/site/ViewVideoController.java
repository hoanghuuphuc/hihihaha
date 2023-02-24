package com.pts.Controller.site;


import com.pts.DAO.ChapterDAO;
import com.pts.DAO.ContentDAO;
import com.pts.DAO.CourseDAO;
import com.pts.DAO.OrderDAO;
import com.pts.entity.Chapter;
import com.pts.entity.Content;
import com.pts.entity.Course;
import com.pts.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class ViewVideoController {
    @Autowired
    CourseDAO courseDAO;
    @Autowired
    ChapterDAO chapterDAO;
    @Autowired
    ContentDAO contentDAO;

    @Autowired
    OrderDAO orderDAO;
    @RequestMapping("/learning/{id}")
    public String viewvideo(Model m, @PathVariable("id") int id, HttpServletRequest request) {
        String username=request.getRemoteUser();
        Order or=orderDAO.kiemtraCrouse(id,username);
        if(or ==null){
            return "redirect:/";
        }else {
            Course course = courseDAO.findById(id);
            String name =course.getTps_Name();
            String firstLink = null;
            String title =null;

            for (Chapter chapter : course.getChapters()) {
                for (Content content : chapter.getContents()) {
                    if (content.getTps_linkytb() != null) {
                        firstLink = content.getTps_linkytb();
                        title = content.getTps_title();
                        break;
                    }
                }
                if (firstLink != null) {
                    break;
                }
            }

            m.addAttribute("ListMycourse", course);
            m.addAttribute("firstLink", firstLink);
            m.addAttribute("name", name);
            m.addAttribute("title", title);

            return "site/viewvideo";
        }

    }
    @RequestMapping("/learningg/{content}/{course}")
    public String ct(Model m, @PathVariable("content") int contentId, @PathVariable("course") int courseId,HttpServletRequest request) {
        String username = request.getRemoteUser();
        Order or = orderDAO.kiemtraCrouse(courseId, username);
        if (or == null) {
            return "redirect:/";
        } else {
            Course course = courseDAO.findById(courseId);
            Content contents = contentDAO.connn(contentId);
            String name = course.getTps_Name();
            String firstLink = null;
            String title = null;

            for (Chapter chapter : course.getChapters()) {
                for (Content content : chapter.getContents()) {
                    if (content.getTps_linkytb() != null) {
                        firstLink = contents.getTps_linkytb();
                        title = contents.getTps_title();
                        break;
                    }
                }
                if (firstLink != null) {
                    break;
                }
            }

            m.addAttribute("ListMycourse", course);
            m.addAttribute("firstLink", firstLink);
            m.addAttribute("name", name);
            m.addAttribute("title", title);

            return "site/viewvideo";


        }
    }


}
