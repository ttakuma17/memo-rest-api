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

INSERT INTO memos (title, category, description, date, mark_div) VALUES ("今日の仕事", "Java", "課題に取り組みました","2022/12/31",1);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("明日の仕事", "Java", "課題が進みません","2023/01/01",1);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("明後日の仕事", "Java", "課題を終えたい","2023/01/02",0);
INSERT INTO memos (title, category, description, date, mark_div) VALUES ("来週の仕事", "Java", "課題が終わるはずです","2023/01/09",0);

