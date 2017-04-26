package com.database.mapper;

import com.database.view.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Tory on 4/25/17.
 */
public interface ContributionMapper {

    String listCommentsByProject = "select * from comment where projectId = #{projectid}";

    String giveContribution = "insert into recordDetail(userName,projectId,fundedTime,fundedMoney,cardid) values " +
            "(#{userName},#{projectId},now(),#{amount},#{cardid},pending)";
    String rating = "insert into rate(userName,projectId,rate) values(#{userName},#{projectId},#{rate})";
    String liking = "insert into likes(userName，projectId) values (#{userName},#{projectId})";

    @Select(listCommentsByProject)
    public List<Comment> listCommentsByProject(@Param("projectId") int projectId);

    @Insert(giveContribution)
    public void giveContribution(@Param("userName") String userName,
                                 @Param("projectId") int projectId,
                                 @Param("amount") int amount,
                                 @Param("cardid") String cardid);
    @Insert(rating)
    public void rating(@Param("userName") String userName,
                       @Param("projectId") int projectId,
                       @Param("rate") int rate);
    @Insert(liking)
    public void liking(@Param("userName") String userName,
                       @Param("projectId") int projectId);
}
