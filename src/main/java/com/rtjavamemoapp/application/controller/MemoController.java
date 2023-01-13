package com.rtjavamemoapp.application.controller;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.application.resources.MemoResponse;
import com.rtjavamemoapp.domain.exception.ResourceNotFoundException;
import com.rtjavamemoapp.domain.model.Memo;
import com.rtjavamemoapp.domain.service.MemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

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
    public void createMemo(@RequestBody MemoForm form) {
        memoService.createMemo(form);
    }

    @DeleteMapping("/memos/{id}")
    public void deleteMemo(@PathVariable int id) {
        memoService.deleteMemo(id);
    }

    @PatchMapping("/memos/{id}")
    public void updateMemo(@PathVariable int id, @RequestBody MemoForm form) {
        memoService.updateMemo(id, form);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI()
        );
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }
}

