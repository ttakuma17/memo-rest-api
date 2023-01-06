package com.rtjavamemoapp.infrastructure.mapper;

import com.rtjavamemoapp.domain.model.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemoMapper {

    @Select("SELECT * FROM memos")
    List<Memo> findAll();

    @Select("SELECT * FROM memos WHERE id=#{id}")
    Optional<Memo> findById(int id);
}
