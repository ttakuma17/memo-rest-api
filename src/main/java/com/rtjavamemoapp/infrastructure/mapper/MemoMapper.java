package com.rtjavamemoapp.infrastructure.mapper;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.domain.model.Memo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemoMapper {

    @Select("SELECT * FROM memos")
    List<Memo> findAll();

    @Select("SELECT * FROM memos WHERE id=#{id}")
    Optional<Memo> findById(int id);

    @Insert("INSERT INTO memos(title, category, description, date, mark_div) VALUES(#{title}, #{category}, #{description}, #{date}, #{mark_div})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createMemo(MemoForm form);

    @Delete("DELETE from memos WHERE id=#{id}")
    void deleteMemo(int id);

    @Update("UPDATE memos SET title=#{form.title}, category=#{form.category}, description=#{form.description}, date=#{form.date},mark_div=#{form.mark_div} WHERE id=#{id}")
    void updateMemo(int id, MemoForm form);
}
