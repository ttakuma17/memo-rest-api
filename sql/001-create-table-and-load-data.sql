DROP TABLE IF EXISTS memos;

CREATE TABLE memos (
  id int unsigned AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  category VARCHAR(100),
  description VARCHAR(100),
  date VARCHAR(100),
  mark_div int,
  PRIMARY KEY(id)
);

INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第1回課題", "Java", "Hello World","2022/12/31",1);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第2回課題", "Java", "オリジナルクラスの実装","2023/01/01",1);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第3回課題", "Java", "ListとMapの練習","2023/01/02",1);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第4回課題", "Java", "Streamをお試し","2023/01/09",0);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第5回課題", "Java", "プルリクエストの作成","2023/01/16",0);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第6回課題", "Java", "Spring BootでHello World","2023/01/23",0);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第7回課題", "Java", "REST controllerの作成","2023/01/30",0);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第1回課題", "AWS", "AWSアカウントの作成","2021/12/31",1);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第2回課題", "AWS", "Cloud9とGithub設定","2022/01/01",1);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第3回課題", "AWS", "サンプルアプリの起動","2022/01/02",1);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第4回課題", "AWS", "EC2とRDSの接続","2022/01/09",0);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第5回課題", "AWS", "ELBの追加","2022/01/16",0);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第6回課題", "AWS", "CloudWatchアラームの追加","2022/01/23",0);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("第7回課題", "AWS", "脆弱性の調査","2022/01/30",0);
