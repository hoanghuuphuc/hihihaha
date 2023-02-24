package com.pts.DAO;

import com.pts.entity.Chapter;
import com.pts.entity.Content;
import com.pts.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentDAO extends JpaRepository<Content,String> {
    @Query("select p from Content p WHERE p.chapter.course.tps_id =?1")
    List<Content>findId(int id);
    @Query("select p from Content p where p.tps_id=?1 ")
    Content connn(int id);


}
