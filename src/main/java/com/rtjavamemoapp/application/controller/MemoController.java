package com.rtjavamemoapp.application.controller;

import com.rtjavamemoapp.domain.model.Memo;
import com.rtjavamemoapp.domain.service.MemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/memos")
    public List<MemoResponse> getMemos() {
        return memoService.findAll().stream().map(MemoResponse::new).toList();
    }

    @GetMapping("/memos/{id}")
    public Optional<Memo> findById(@PathVariable int id) {
        return memoService.findById(id);
    }
}
