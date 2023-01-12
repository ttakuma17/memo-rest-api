package com.rtjavamemoapp.infrastructure.mapper;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.domain.model.Memo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemoMapper {

    @Select("SELECT * FROM memos")
    List<Memo> findAll();

    @Select("SELECT * FROM memos WHERE id=#{id}")
    Memo findById(int id);

    @Insert("INSERT INTO memos(title, category, description, date, mark_div) VALUES(#{title}, #{category}, #{description}, #{date}, #{mark_div})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createMemo(MemoForm form);

}
