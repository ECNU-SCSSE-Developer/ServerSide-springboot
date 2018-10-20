package com.tia.springbootserver.mapper;

import com.tia.springbootserver.entity.MatchRecruit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRecruitMapper {
    int deleteByPrimaryKey(@Param("matchId") Integer matchId, @Param("recruitId") Integer recruitId);

    int insert(MatchRecruit record);

    int insertSelective(MatchRecruit record);

    @Select("SELECT match_id FROM Match_Recruit\n" +
            "WHERE recruit_id = recruitId")
    int selectByRecruitId(Integer matchId);

    @Delete("DELETE FROM `Match_Recruit` WHERE recruit_id = #{recruitId}")
    int deleteByRecruitId(Integer recruitId);

}