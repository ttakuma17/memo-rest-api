package com.rtjavamemoapp.infrastructure.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.domain.model.Memo;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemoMapperTest {

  @Autowired
  MemoMapper memoMapper;

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 全てのメモを取得できること() {
    List<Memo> memos = memoMapper.findAll();
    assertThat(memos)
        .hasSize(4)
        .contains(
            new Memo(1, "第1回課題", "Java", "Hello World", "2022/12/31", 1),
            new Memo(2, "第2回課題", "Java", "オリジナルクラスの実装", "2023/01/01", 1),
            new Memo(3, "第3回課題", "Java", "ListとMapの練習", "2023/01/02", 1),
            new Memo(4, "第4回課題", "Java", "Streamをお試し", "2023/01/09", 0)
        );
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 指定したIDのメモを取得できること() {
    Optional<Memo> memo = memoMapper.findById(1);
    assertThat(memo).isEqualTo(
        Optional.of(new Memo(1, "第1回課題", "Java", "Hello World", "2022/12/31", 1)));
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void メモを作成できること() {
    MemoForm form = new MemoForm();
    form.setTitle("第5回課題");
    form.setCategory("Java");
    form.setDescription("プルリクエストの作成");
    form.setDate("2023/01/16");
    form.setMark_div(0);

    memoMapper.createMemo(form);
    assertThat(memoMapper.findAll()).hasSize(5);
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 指定したIDのメモを削除できること() {
    memoMapper.deleteMemo(1);
    List<Memo> memos = memoMapper.findAll();
    assertThat(memos).hasSize(3);
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 指定したIDのメモを更新できること() {
    MemoForm form = new MemoForm();
    form.setTitle("第5回課題");
    form.setCategory("Java");
    form.setDescription("プルリクエストの作成");
    form.setDate("2023/01/16");
    form.setMark_div(0);

    memoMapper.updateMemo(1, form);
    assertThat(memoMapper.findById(1).map(Memo::getTitle).orElse("")).isEqualTo("第5回課題");
  }
}

