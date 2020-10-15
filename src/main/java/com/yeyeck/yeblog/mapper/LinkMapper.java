package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.Link;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LinkMapper {

    @Insert("INSERT INTO `t_link`(`label`, `type`, `link`, `new_blank`, `order_num`, `create_time`, `update_time`) " +
            "VALUES (#{label}, #{type}, #{link}, #{newBlank}, #{orderNum}, NOW(), NOW())")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int add(Link link);

    @Update("UPDATE `t_link` SET `update_time` = NOW() WHERE id = #{id}")
    int activeId(Integer id);

    @Delete("DELETE FROM `t_link` WHERE `id` = #{id}")
    int deleteLink(Integer id);

    @Select("SELECT COUNT(`id`) FROM `t_link` WHERE `type` = #{type} ")
    int countByType(String type);

    @Update("UPDATE `t_link` SET `label` = #{label}, `link` = #{link}, `new_blank` = #{newBlank}, `update_time` = NOW() " +
            "WHERE id = #{id}")
    int update(Link link);

    @Select("SELECT `id`, `label`, `link`, `new_blank`, `order_num` FROM `t_link` WHERE `type` = #{type} ORDER BY `order_num`")
    List<Link> getLinksByType(String type);

    @Select("SELECT * FROM `t_link`")
    List<Link> getAll();

    @Delete("<script>" +
            "DELETE FROM `t_link` WHERE `id` IN (" +
            "<foreach collection='linkIds' item='id' index='index' separator=','>" +
            "#{id}" +
            "</foreach>" +
            ")" +
            "</script>")
    int deleteLinkByIds(@Param("linkIds") List<Integer> linkIds);

    @Update("UPDATE `t_link` SET `order_num` = #{orderNum} WHERE `id` = #{linkId}")
    int updateOrderNum(Integer linkId, Integer orderNum);

    @Select("SELECT * FROM `t_link` WHERE `id` = #{id}")
    Link getById(Integer id);

    @Update("UPDATE `t_link` SET `order_num` = `order_num` - 1 WHERE `order_num` > #{orderNum} AND `type` = #{type}")
    int lowerOrderNum(int orderNum, String type);

    @Update("UPDATE `t_link` SET `order_num` = `order_num` + 1 WHERE `order_num` >= #{start} " +
            "AND `order_num` < #{end} " +
            "AND`type` = #{type}")
    int rangeUpperOrderNum(int start, int end, String type);

    @Update("UPDATE `t_link` SET `order_num` = `order_num` - 1 WHERE `order_num` > #{start} " +
            "AND `order_num` <= #{end} " +
            "AND `type` = #{type}")
    int rangeLowerOrderNum(int start, int end, String type);

    @Select("SELECT `order_num` FROM `t_link` WHERE `id` = #{id}")
    int getOrderById(Integer id);
}
