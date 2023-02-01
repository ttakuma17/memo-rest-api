package com.rtjavamemoapp.application.controller;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.application.resources.MemoResponse;
import com.rtjavamemoapp.domain.model.Memo;
import com.rtjavamemoapp.domain.service.MemoService;
import java.net.URI;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    
    @GetMapping("/memos")
    public List<MemoResponse> getMemos() {
        return memoService.findAll().stream().map(MemoResponse::new).toList();
    }

    @GetMapping("/memos/{id}")
    public Memo findById(@PathVariable int id) throws Exception {
        return memoService.findById(id);
    }

    @PostMapping("/memos")
    public ResponseEntity<Map<String, String>> createMemo(@RequestBody @Validated MemoForm form) {
        memoService.createMemo(form);
        URI url = UriComponentsBuilder.fromUriString("").path("/memos/id").build().toUri();

        return ResponseEntity.created(url)
            .body(Map.of("message", "memo has been successfully created"));
    }

    @DeleteMapping("/memos/{id}")
    public ResponseEntity<Map<String, String>> deleteMemo(@PathVariable int id) {
        memoService.deleteMemo(id);
        return ResponseEntity.ok(Map.of("message", "memo has been successfully deleted"));

    }

    @PatchMapping("/memos/{id}")
    public ResponseEntity<Map<String, String>> updateMemo(@PathVariable int id,
        @RequestBody @Validated MemoForm form) {
        memoService.updateMemo(id, form);
        return ResponseEntity.ok(Map.of("message", "memo has been successfully updated"));
    }
}
