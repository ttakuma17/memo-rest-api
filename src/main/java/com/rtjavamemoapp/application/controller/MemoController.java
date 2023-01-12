package com.rtjavamemoapp.application.controller;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.application.resources.MemoResponse;
import com.rtjavamemoapp.domain.model.Memo;
import com.rtjavamemoapp.domain.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/memos")
    public List<MemoResponse> getMemos() {
        return this.memoService.findAll().stream().map(MemoResponse::new).toList();
    }

    @GetMapping("/memos/{id}")
    public Memo findById(@PathVariable int id) {
        return this.memoService.findById(id);
    }

    @PostMapping("/memos")
    public void createMemo(@RequestBody MemoForm form) {
        this.memoService.createMemo(form);
    }

    @DeleteMapping("memos/{id}")
    public void deleteMemo(@PathVariable int id) {
        this.memoService.deleteMemo(id);
    }

    @PatchMapping("memos/{id}")
    public void updateMemo(@PathVariable("id") int id, @RequestBody MemoForm form) {
        this.memoService.updateMemo(id, form);
    }
}

