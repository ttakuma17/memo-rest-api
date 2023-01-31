package com.rtjavamemoapp.integrationtest;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtjavamemoapp.domain.object.MemoDTO;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
    scripts = {"classpath:/sqlannotation/delete-memos.sql",
        "classpath:/sqlannotation/insert-memos.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Transactional
public class MemoRestApiIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void メモを全件取得できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/memos"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("""
            [
              {
                "id": 1,
                "title": "第1回課題",
                "category": "Java",
                "description": "Hello World",
                "date": "2022/12/31",
                "markDiv": 1
              },
              {
                "id": 2,
                "title": "第2回課題",
                "category": "Java",
                "description": "オリジナルクラスの実装",
                "date": "2023/01/01",
                "markDiv": 1
              },
              {
                "id": 3,
                "title": "第3回課題",
                "category": "Java",
                "description": "ListとMapの練習",
                "date": "2023/01/02",
                "markDiv": 1
              },
              {
                "id": 4,
                "title": "第4回課題",
                "category": "Java",
                "description": "Streamをお試し",
                "date": "2023/01/09",
                "markDiv": 0
              }
            ]
            """
        , response, JSONCompareMode.STRICT);

    List<MemoDTO> listMemo = objectMapper
        .readValue(response, new TypeReference<List<MemoDTO>>() {
        });

    assertThat(listMemo.size()).isEqualTo(4);
  }

  @Test
  void 指定したIDに紐づくメモを取得できること() throws Exception {
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/memos/1")).
        andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals(
        """
            {
              "id": 1,
              "title": "第1回課題",
              "category": "Java",
              "description": "Hello World",
              "date": "2022/12/31",
              "markDiv": 1
            }
            """, response, JSONCompareMode.STRICT);

    MemoDTO memo = objectMapper.readValue(response, MemoDTO.class);
    assertThat(memo.getId()).isEqualTo(1);
  }

  @Test
  void 存在しないID指定してメモを取得するとNotFoundを返すこと() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/memos/100"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void メモを新規作成できること() throws Exception {

    String countDbBeforeCreate = mockMvc.perform(MockMvcRequestBuilders.get("/memos"))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    List<MemoDTO> beforeCreateMemo = objectMapper
        .readValue(countDbBeforeCreate, new TypeReference<List<MemoDTO>>() {
        });

    mockMvc.perform(MockMvcRequestBuilders.post("/memos")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
                "title": "第100回課題",
                "category": "Java",
                "description": "100回目の課題",
                "date": "2025/01/30",
                "markDiv": 1
            }
            """)
    ).andExpect(MockMvcResultMatchers.status().isOk());

    String countDbAfterCreate = mockMvc.perform(MockMvcRequestBuilders.get("/memos"))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    List<MemoDTO> afterCreateMemo = objectMapper
        .readValue(countDbAfterCreate, new TypeReference<List<MemoDTO>>() {
        });
    assertThat(beforeCreateMemo.size()).isLessThan(afterCreateMemo.size());
  }

  @Test
  void メモを更新できること() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.patch("/memos/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
                "title": "第100回課題",
                "category": "Java",
                "description": "100回目の課題",
                "date": "2025/01/30",
                "markDiv": 1
            }
            """)
    ).andExpect(MockMvcResultMatchers.status().isOk());

    String response = mockMvc.perform(MockMvcRequestBuilders.get("/memos/1"))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    MemoDTO memo = objectMapper.readValue(response, MemoDTO.class);
    assertThat(memo.getTitle()).isEqualTo("第100回課題");
    assertThat(memo.getCategory()).isEqualTo("Java");
    assertThat(memo.getDescription()).isEqualTo("100回目の課題");
    assertThat(memo.getDate()).isEqualTo("2025/01/30");
    assertThat(memo.getMarkDiv()).isEqualTo(1);
  }

  @Test
  void 存在しないIDを指定してメモを更新するとNotFoundを返すこと() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.patch("/memos/100")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "title": "第100回課題",
                    "category": "Java",
                    "description": "100回目の課題",
                    "date": "2025/01/30",
                    "markDiv": 1
                }
                """)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void メモを削除できること() throws Exception {

    String countDbBeforeDelete = mockMvc.perform(MockMvcRequestBuilders.get("/memos"))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    List<MemoDTO> beforeDeleteMemo = objectMapper
        .readValue(countDbBeforeDelete, new TypeReference<List<MemoDTO>>() {
        });

    mockMvc.perform(MockMvcRequestBuilders.delete("/memos/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());

    String countDbAfterDelete = mockMvc.perform(MockMvcRequestBuilders.get("/memos"))
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    List<MemoDTO> afterDeleteMemo = objectMapper
        .readValue(countDbAfterDelete, new TypeReference<List<MemoDTO>>() {
        });

    assertThat(beforeDeleteMemo.size()).isGreaterThan(afterDeleteMemo.size());
  }

  @Test
  void 存在しないIDを指定して削除を実行するとNotFoundが発生すること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/memos/100"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
