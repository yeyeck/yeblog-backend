package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Link;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LinkMapper {

    @Insert("insert into t_link(label, type, link, new_blank, order_num, create_time, update_time) " +
            "values (#{label}, #{type}, #{link}, #{newBlank}, #{orderNum}, now(), now())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Link link);

    @Delete("delete from t_link where id = #{id}")
    int deleteLink(Integer id);

    @Select("select count(id) from t_link where type = #{type} ")
    int countByType(String type);

    @Update("update t_link set label = #{label}, link = #{link}, new_blank = #{newBlank}, update_time = now() " +
            "where id = #{id}")
    int update(Link link);

    @Select("select id, label, link, new_blank, order_num from t_link where type = #{type} order by order_num")
    List<Link> getLinksByType(String type);

    @Select("select * from t_link")
    List<Link> getAll();

    @Delete("<script>" +
            "delete from t_link where id IN (" +
            "<foreach collection='linkIds' item='id' index='index' separator=','>" +
            "#{id}" +
            "</foreach>" +
            ")" +
            "</script>")
    int deleteLinkByIds(@Param("linkIds") List<Integer> linkIds);

    @Update("update t_link set order_num = #{orderNum} where id = #{linkId}")
    int updateOrderNum(Integer linkId, Integer orderNum);

    @Select("select * from t_link where id = #{id}")
    Link getById(Integer id);

    @Update("update t_link set order_num = order_num - 1 where order_num > #{orderNum} and type = #{type}")
    int lowerOrderNum(int orderNum, String type);

    @Update("update t_link set order_num = order_num + 1 where order_num >= #{start} " +
            "and order_num < #{end} " +
            "andtype = #{type}")
    int rangeUpperOrderNum(int start, int end, String type);

    @Update("update t_link set order_num = order_num - 1 where order_num > #{start} " +
            "and order_num <= #{end} " +
            "and type = #{type}")
    int rangeLowerOrderNum(int start, int end, String type);

    @Select("select order_num from t_link where id = #{id}")
    int getOrderById(Integer id);
}
