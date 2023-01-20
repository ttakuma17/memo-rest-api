package com.rtjavamemoapp.domain.service;

import com.rtjavamemoapp.application.resources.MemoForm;
import com.rtjavamemoapp.domain.exception.ResourceNotFoundException;
import com.rtjavamemoapp.domain.model.Memo;
import com.rtjavamemoapp.infrastructure.mapper.MemoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemoServiceImplTest {

    @InjectMocks
    MemoServiceImpl memoServiceImpl;

    @Mock
    MemoMapper memoMapper;
    List memoList = List.of(
            new Memo(1,"第1回課題", "Java", "Hello World","2022/12/31",1),
            new Memo(2,"第2回課題", "Java", "オリジナルクラスの実装","2023/01/01",1),
            new Memo(3,"第3回課題", "Java", "ListとMapの練習","2023/01/02",1),
            new Memo(4, "第4回課題", "Java", "Streamをお試し","2023/01/09",0));
    Memo memo = new Memo(1,"第1回課題", "Java", "Hello World","2022/12/31",1);

    @Test
    void 全てのメモを取得できること() {
        doReturn(memoList).when(memoMapper).findAll();

        List<Memo> actual = memoServiceImpl.findAll();
        assertThat(actual).isEqualTo(memoList);
    }

    @Test
    void 存在するメモのIDを指定した時に正常にメモが返されること() throws Exception {
        doReturn(Optional.of(memo)).when(memoMapper).findById(1);

        Memo actual = memoServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Memo(1,"第1回課題", "Java", "Hello World","2022/12/31",1));
    }

    @Test
    void メモを作成できること() {
        doNothing().when(memoMapper).createMemo(any(MemoForm.class));

        memoServiceImpl.createMemo(new MemoForm());
        verify(memoMapper).createMemo(any(MemoForm.class));
    }

    @Test
    void 指定したIDのメモを削除できること() {
        doReturn(Optional.of(memo)).when(memoMapper).findById(1);

        memoServiceImpl.deleteMemo(1);
        verify(memoMapper).deleteMemo(1);
    }

    @Test
    void 指定したIDのメモを更新できること() {
        doReturn(Optional.of(memo)).when(memoMapper).findById(1);

        memoServiceImpl.updateMemo(1, new MemoForm());
        verify(memoMapper).updateMemo(eq(1), any(MemoForm.class));
    }

    @Test
    void 存在しないメモのIDを指定した時にResourceNotFoundExceptionが発生すること(){
        doReturn(Optional.empty()).when(memoMapper).findById(1);

        assertThatThrownBy(()-> memoServiceImpl.findById(1))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void 存在しないメモのIDを指定して更新した時にResourceNotFoundExceptionが発生すること(){
        doReturn(Optional.empty()).when(memoMapper).findById(1);

        assertThatThrownBy(()-> memoServiceImpl.updateMemo(1, new MemoForm()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void 存在しないメモのIDを指定して削除した時にResourceNotFoundExceptionが発生すること(){
        doReturn(Optional.empty()).when(memoMapper).findById(1);

        assertThatThrownBy(()-> memoServiceImpl.deleteMemo(1))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
