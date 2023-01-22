package com.rtjavamemoapp.infrastructure.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.rtjavamemoapp.domain.model.Memo;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemoMapperTest {

    @Autowired
    MemoMapper memoMapper;

    @Test
    @Sql(
        scripts = {"classpath:/sqlannotation/delete-memos.sql", "classpath:/sqlannotation/insert-memos.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void 全てのメモを取得できること() {
        List<Memo> memos = memoMapper.findAll();
        assertThat(memos)
            .hasSize(4)
            .contains(
                new Memo(1,"第1回課題", "Java", "Hello World","2022/12/31",1),
                new Memo(2, "第2回課題", "Java", "オリジナルクラスの実装","2023/01/01",1),
                new Memo(3, "第3回課題", "Java", "ListとMapの練習","2023/01/02",1),
                new Memo(4, "第4回課題", "Java", "Streamをお試し","2023/01/09",0)
            );
    }

    @Test
    void 指定したIDのメモを取得できこと() {
        Optional<Memo> memo = memoMapper.findById(1);

        assertThat(memo).contains(new Memo(1,"第1回課題", "Java", "Hello World","2022/12/31",1));
        // テスト対象が1つの値なので、isEqualToで通るか検証 // 参照値が違いうので通らないとは思うが。。。
        assertThat(memo).isEqualTo(new Memo(1,"第1回課題", "Java", "Hello World","2022/12/31",1));
    }

    @Test
    void メモを作成できること() {
    }

    @Test
    void 指定したIDのメモを削除できること() {
    }

    @Test
    void 指定したIDのメモを更新できること() {
    }
}
