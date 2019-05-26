package com.example.demo.mapper;

import com.example.demo.bean.Detailed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DetailedMapper {

    List<Detailed> getByDetaileds(@Param("langid") long langid, @Param("catid") long catid);
}
