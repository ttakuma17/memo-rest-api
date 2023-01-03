package com.rtjavamemoapp.infrastructure.mapper;

import com.rtjavamemoapp.domain.model.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemoMapper {

    @Select("SELECT * FROM memos")
    List<Memo> findAll();

}
