package com.database.mapper;

import com.database.view.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Tory on 4/25/17.
 */
public interface ServiceMapper {

    //Query Operation
    String displayAllProjects = "select * from projectRequest where pstatus = 'live'";
    String displayProjectsByUser = "select * from projectRequest where userName = #{userName}";
    String displayProjectByTags = "select * from tag where tag = #{tag}";
    String fuzzySearch = "<script> select * from projectRequest natarual join tag" +
            "<set>" +
                "<if test=\"description != null\"> description like %#{description}%,</if>"+
                "<if test=\"projectName != null\"> projectName like %#{projectName}%,</if>"+
                "<if test=\"tag != null\"> tag like %#{tag}%,</if>"+
            "</script>";
    String userSearchHistory = "select * from projectRequest where projectId in " +
            "(select projectId from history natural join projectRequest where userName = #{userName})";

    //Insertion Operation
    String createProject = "insert into projectRequest(userName,projectName,minimum,maximum,postTime,endTime) values" +
            "(#{userName},#{projectName},#{minimum},#{maximum},now(),#{endTime}})";
    String buildTag = "insert into tag(projectId,tag) values (#{projectId},#{tag})";
    String insertHistory = "insert into history(userName,projectId) values (#{userName},#{projectId})";

    @Select(displayAllProjects)
    public List<Project> displayAllProjects();
    @Select(displayProjectsByUser)
    public List<Project> displayProjectsByUser(@Param("userName") String userName);
    @Select(displayProjectByTags)
    public List<Project> displayProjectByTags(@Param("tag") String tag);
    @Select(fuzzySearch)
    public List<Project> fuzzySearch(@Param("projectName") String projectName,
                                     @Param("description") String description,
                                     @Param("tag") String tag);
    @Select(userSearchHistory)
    public List<Project> userSearchHistory(@Param("userName") String userName);

    @Insert(createProject)
    public boolean createProject(@Param("userName") String userName,
                              @Param("projectName") String projectName,
                              @Param("minimum") int minimum,
                              @Param("maximum") int maximum,
                              @Param("endTime") String endTime);
    @Insert(buildTag)
    public void buildTag(@Param("projectId") int projectId,
                         @Param("tag") String tag);
    @Insert(insertHistory)
    public void insertHistory(@Param("userName") String userName,
                              @Param("projectId") int projectId);

}
