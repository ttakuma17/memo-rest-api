package com.rtjavamemoapp.integrationtest;

import java.nio.charset.StandardCharsets;
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
public class MemoRestApiIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void メモを全件取得できること() throws Exception{
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/memos")).
        andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals("[" +
            " {" +
            " \"id\": 1," +
            " \"title\": \"第1回課題\"," +
            " \"category\": \"Java\"," +
            " \"description\": \"Hello World\"," +
            " \"date\": \"2022/12/31\"," +
            " \"markDiv\": 1" +
            " }," +
            " {" +
            " \"id\": 2," +
            " \"title\": \"第2回課題\"," +
            " \"category\": \"Java\"," +
            " \"description\": \"オリジナルクラスの実装\"," +
            " \"date\": \"2023/01/01\"," +
            " \"markDiv\": 1" +
            " }," +
            " {" +
            " \"id\": 3," +
            " \"title\": \"第3回課題\"," +
            " \"category\": \"Java\"," +
            " \"description\": \"ListとMapの練習\"," +
            " \"date\": \"2023/01/02\"," +
            " \"markDiv\": 1" +
            " }," +
            " {" +
            " \"id\": 4," +
            " \"title\": \"第4回課題\"," +
            " \"category\": \"Java\"," +
            " \"description\": \"Streamをお試し\"," +
            " \"date\": \"2023/01/09\"," +
            " \"markDiv\": 0" +
            " }" +
            "]", response, JSONCompareMode.STRICT);
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 指定したIDに紐づくメモを取得できること() throws Exception{
    String response = mockMvc.perform(MockMvcRequestBuilders.get("/memos/1")).
        andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    JSONAssert.assertEquals(
        " {" +
            " \"id\": 1," +
            " \"title\": \"第1回課題\"," +
            " \"category\": \"Java\"," +
            " \"description\": \"Hello World\"," +
            " \"date\": \"2022/12/31\"," +
            " \"markDiv\": 1" +
            " }", response, JSONCompareMode.STRICT);
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 存在しないID指定してメモを取得するとNotFoundを返すこと() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get("/memos/100"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void メモを新規作成できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/memos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            " {" +
            "\"title\": \"第100回課題\"," +
            " \"category\": \"Java\"," +
            " \"description\": \"100回目の課題\"," +
            " \"date\": \"2025/01/30\"," +
            " \"markDiv\": 1" +
            " }"
        )
    ).andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void メモを更新できること() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.patch("/memos/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            " {" +
            "\"title\": \"第100回課題\"," +
            " \"category\": \"Java\"," +
            " \"description\": \"100回目の課題\"," +
            " \"date\": \"2025/01/30\"," +
            " \"markDiv\": 1" +
            " }")
    ).andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 存在しないIDを指定してメモを更新するとNotFoundを返すこと() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.patch("/memos/100")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            " {" +
                "\"title\": \"第100回課題\"," +
                " \"category\": \"Java\"," +
                " \"description\": \"100回目の課題\"," +
                " \"date\": \"2025/01/30\"," +
                " \"markDiv\": 1" +
                " }")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void メモを削除できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/memos/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @Sql(
      scripts = {"classpath:/sqlannotation/delete-memos.sql",
          "classpath:/sqlannotation/insert-memos.sql"},
      executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
  )
  @Transactional
  void 存在しないIDを指定して削除を実行するとNotFoundが発生すること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/memos/100"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}


