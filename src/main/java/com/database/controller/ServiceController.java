package com.database.controller;

import com.database.mapper.ServiceMapper;
import com.database.view.Project;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Tory on 4/25/17.
 */

@RestController
@RequestMapping("/service")
public class ServiceController {



    @RequestMapping("/init")
    public List<Project> init(){
        SqlSession session = DataBaseConfiguration.getInstance().openSession();
        ServiceMapper mapper = session.getMapper(ServiceMapper.class);
        List<Project> list = mapper.displayAllProjects();
        session.commit();
        session.close();
        return list;
    }

    @RequestMapping("/project/{email}")
    public List<Project> getProjectsByUser(@PathVariable("email") String email){
        SqlSession session = DataBaseConfiguration.getInstance().openSession();
        ServiceMapper mapper = session.getMapper(ServiceMapper.class);
        List<Project> list = mapper.displayProjectsByUser(email);
        session.commit();
        session.close();
        return list;
    }

    @RequestMapping("/project/{tag}")
    public List<Project> getProjectsByTag(@PathVariable("tag") String tag){
        SqlSession session = DataBaseConfiguration.getInstance().openSession();
        ServiceMapper mapper = session.getMapper(ServiceMapper.class);
        List<Project> list = mapper.displayProjectByTags(tag);
        session.commit();
        session.close();
        return list;
    }
    @RequestMapping(value = "/project/fuzzySearch",params = {"name","description","tag"})
    public List<Project> fuzzySearch(@RequestParam("name") String projectName,
                                     @RequestParam("description") String description,
                                     @RequestParam("tag") String tag){
        SqlSession session = DataBaseConfiguration.getInstance().openSession();
        ServiceMapper mapper = session.getMapper(ServiceMapper.class);
        List<Project> list = mapper.fuzzySearch(projectName, description, tag);
        session.commit();
        session.close();
        return list;
    }

    @RequestMapping(value = "project/creation",method = RequestMethod.POST)
    public boolean createProject(@RequestBody Map<String,String> map){
        if(!map.containsKey("userName") || !map.containsKey("projectName") ||
                !map.containsKey("minimum") || !map.containsKey("maximum") ||
                !map.containsKey("ednTime")){
            return false;
        }
        SqlSession session = DataBaseConfiguration.getInstance().openSession();
        ServiceMapper mapper = session.getMapper(ServiceMapper.class);
        boolean flag = mapper.createProject(map.get("userName"),map.get("projectName"),Integer.valueOf(map.get("minimum")),Integer.valueOf(map.get("maximum")),
                map.get("endTime"));
        session.commit();
        session.close();
        return flag;
    }

}
