package com.rtjavamemoapp.domain.service;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.domain.model.Memo;
import com.rtjavamemoapp.infrastructure.mapper.MemoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {
    
    private MemoMapper memoMapper;

    public MemoServiceImpl(MemoMapper memoMapper) {
        this.memoMapper = memoMapper;
    }

    @Override
    public List<Memo> findAll() {
        return memoMapper.findAll();
    }

    @Override
    public Memo findById(int id) {
        return memoMapper.findById(id);
    }

    @Override
    public void createMemo(MemoForm form) {
        memoMapper.createMemo(form);
    }

    @Override
    public void deleteMemo(int id) {
        memoMapper.deleteMemo(id);
    }

    @Override
    public void updateMemo(int id, MemoForm form) {
        //update処理がうまくいかない要因を確認
        // formのIDが0になってしまい、PathVariableの入力値を渡せていないことでデータの更新がされない
        memoMapper.findById(id);
        memoMapper.updateMemo(form);
    }
}
