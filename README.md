# Spring Boot REST API メモアプリ

Spring Boot の学習用にメモアプリのバックエンドAPIを作成しました。

2022年12月19日 より RaiseTechの[Javaコース](https://raise-tech.net/courses/java-full-course)で学習しており
Javaコースの最終課題として以下の要件を与えられ、メモアプリの作成を行うことにしました。

```
アプリケーションの要件
- CRUD機能をもったREST APIを作成してください
- 作成したREST APIに対するJUnitによるテストコードを作成してください
- テストコードの書き方は次回以降に説明します
- たとえば名前と生年月日を持ったユーザーを管理するようなシンプルなもので構いません
```

## Pull Request Review

API開発にあたりReviewいただいたプルリクエストの一覧を記載します。

- [MyBatis で READ処理の実装](https://github.com/ttakuma17/rt-java-9/pull/2)
- [CRUD処理すべてを備えたREST APIの作成](https://github.com/ttakuma17/rt-java-9/pull/6)
- [例外処理 / バリデーションの追加](https://github.com/ttakuma17/rt-java-9/pull/8)
- [単体テストの実装](https://github.com/ttakuma17/rt-java-9/pull/9)
- [結合テストの実装 ](https://github.com/ttakuma17/rt-java-9/pull/13)

## API仕様

| HTTPメソッド | URL         | 処理内容            |
|----------|-------------|-----------------|
| GET      | /memos      | 登録されている全てのメモを取得 |
| GET      | /memos/{id} | 指定されたidのメモを取得   |
| POST     | /memos      | メモを新規作成する       |
| PATCH    | /memos/{id} | 指定されたidのメモを更新   |
| DELETE   | /memos/{id} | 指定されたidのメモを削除   |

## 動作確認手順

### 1. 起動

Dockerコンテナ上のMySQLを利用するため、Dockerが稼働する環境へcloneしてください。

```zsh
$ docker compose up -d
```

IntelliJでアプリケーションを起動し、RtJavaMemoAppApplication よりmainメソッドを実行し、動作確認用のコマンドを実行してください。

### 2. 動作確認用コマンド

GET - /memos

```zsh
# リクエスト
$ curl http:localhost:8080/memos

# レスポンス 例
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
  }
]
```

GET - /memos/{id}

```zsh
# リクエスト
curl http:localhost:8080/memos/1
# レスポンス
{
  "id": 1,
  "title": "第1回課題",
  "category": "Java",
  "description": "Hello World",
  "date": "2022/12/31",
  "markDiv": 1
}
```

POST - /memos

```zsh
# リクエスト
curl curl -X POST -H "Content-Type: application/json" -d '{"title": "新規作成", "category": "CREATE", "description": "メモを作成しました", "date": "2023/02/08", "markDiv": 1}'
# レスポンス
memo has been successfully created
```

PATCH - /memos/{id}

```zsh
# リクエスト
curl -X PATCH -H "Content-Type: application/json" -d '{"title": "新規作成", "category": "CREATE", "description": "メモを作成しました", "date": "2023/02/08", "markDiv": 1}' http:localhost:8080/memos/1
# レスポンス
memo has been successfully updated
```

DELETE - /memos/{id}

```zsh
# リクエスト
curl -X DELETE http://localhost:8080/memos/1
# レスポンス
memo has been successfully deleted
```

## 使用技術

- Java 17.0.5
- Spring Boot 3.0.1
- MySQL 8.0
- MyBatis 2.2.2
- JUnit5
- Mockito
- Gradle
- GitHub Actions

## ディレクトリ構成

```
com.rtjavamemoapp
├── RtJavaMemoAppApplication.java
├── application
│   ├──── controller
│   │   ├──  MemoController.java
│   │   └──  MemoControllerAdvice.java
│   └──── resources
│       ├──  MemoForm.java
│       └──  MemoResponse.java
├── domain
│   ├── exception
│   │   └── ResourceNotFountException.java
│   ├── model
│   │   └── Memo.java
│   ├── object
│   │   └── MemoDTO.java
│   └── service
│       ├── MemoService.java
│       └── MemoServiceImpl.java
├── infrastructure
│   └── mapper 
│       └── MemoMapper.java
```

## 作成期間

2022年12月31日 〜 2023年2月8日

## 作成にあたり詰まったこと

- Spring Boot のディレクトリ構成

- 単体テストおよび結合テストの実装

## 残課題と今後の開発方針

残課題
- 

今後の開発方針
- 

## RaiseTechでの学習内容について

### Javaの基礎知識

型 / クラス / メソッド/ fot文 / if文 / List / Map / 例外 / Stream API

### 現場で使うツールの基礎知識

IntelliJ

### Gitを用いた開発

Git / GitHub / Pull Requestを利用したレビュー

### Webアプリケーションの基礎知識

Webアプリケーション/ RESTful API / MVC / データベース(MySQL/Oracle/H2/PostgreSQL)
CRUD / トランザクション / TCP / IP / セッション / クッキー

### フレームワークおよびライブラリの知識と経験、実践経験

Spring Framework / Spring Boot / MyBatis

### Spring Framework（Spring Boot）を用いた開発実践

RESTful API / データベースアクセス / 外部APIへのアクセス / 例外ハンドリング / Spring Securityによる認証・認可

### DevOpsや自動化に対する知識と理解(CI/CD)

GitHub Actions

### AWSのサービスに対する基本知識と基本的な使い方への理解

VPC / EC2 / RDS / ELB

### 構成管理ツールに対する知識と理解および実践経験

Maven / Gradle

### テスト手法に対する知識と経験

ブラックボックステスト / ホワイトボックステスト / 境界値テスト / 条件網羅 / 分岐網羅

### 自動テストに対する知識と経験

Junit / Mockito / Spring Test / DBテスト

### 開発手法

さまざまな開発手法への知識と理解 / V字モデル / ウォーターフォール / アジャイル / スクラム

### 生産性を上げる為の取り組みに対する知識と理解

カンバン / ふりかえり(KPT)
