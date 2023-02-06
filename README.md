# Spring Boot REST API メモアプリ

Spring Boot の学習用にメモアプリのバックエンドAPIを作成しました。

2022年12月19日 より RaiseTechの[Javaコース](https://raise-tech.net/courses/java-full-course)で学習しており
Javaコースの最終課題としては以下の要件を提示いただき、メモアプリのAPI作成を行いました。
プルリクエストの作成時には GitHub Action で単体テストが動作するよう実装しています。

```
アプリケーションの要件
- CRUD機能をもったREST APIを作成してください
- 作成したREST APIに対するJUnitによるテストコードを作成してください
- たとえば名前と生年月日を持ったユーザーを管理するようなシンプルなもので構いません
```

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
$ curl localhost:8080/memos

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
curl localhost:8080/memos/1
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
curl -X POST -H "Content-Type: application/json" -d '{"title": "新規作成", "category": "CREATE", "description": "メモを作成しました", "date": "2023/02/08", "markDiv": 1}' localhost:8080/memos
# レスポンス
{"message":"memo has been successfully created"}
```

PATCH - /memos/{id}

```zsh
# リクエスト
curl -X PATCH -H "Content-Type: application/json" -d '{"title": "新規作成", "category": "CREATE", "description": "メモを作成しました", "date": "2023/02/08", "markDiv": 1}' localhost:8080/memos/1
# レスポンス
{"message":"memo has been successfully updated"}
```

DELETE - /memos/{id}

```zsh
# リクエスト
curl -X DELETE localhost:8080/memos/1
# レスポンス
{"message":"memo has been successfully deleted"}
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

2022年12月31日 〜 2023年2月6日

## Pull Request Review

API開発にあたりReviewいただいたプルリクエストの一覧です。

- [MyBatis で READ処理の実装](https://github.com/ttakuma17/rt-java-9/pull/2)
- [CRUD処理すべてを備えたREST APIの作成](https://github.com/ttakuma17/rt-java-9/pull/6)
- [例外処理 / バリデーションの追加](https://github.com/ttakuma17/rt-java-9/pull/8)
- [単体テストの実装](https://github.com/ttakuma17/rt-java-9/pull/9)
- [結合テストの実装 ](https://github.com/ttakuma17/rt-java-9/pull/13)

## 作成にあたり悩んだこと

- ### Spring Boot のディレクトリ構成
  ディレクトリ構成はプロジェクトによって最適なものが変わると認識しており、どのようにディレクトリを切るべきかに悩みました。  
  今回作成したアプリケーションはシンプルな機能のみですが、アプリケーション層、ドメイン層、インフラストラクチャ層の3つのレイヤに分けて開発を進めました。  
  ディレクトリ構成の検討にあたっては基本的に[TERASOLUNA](http://terasolunaorg.github.io/guideline/current/ja/)
  を参考に決定しています。


- ### 単体テストおよび結合テストの実装
  単体テストの実装時にテストを記載しにくいコードが存在していました。  
  ここでテストを通すことを目的にテストを編集するのではなく、設計として正しいのかやテスタビリティの高いプロダクトコードにできないかを検討した上で、テスト実装する必要があると感じました。
  結合テストでは main ディレクトリと test ディレクトリが一部異なっていたことから、結合テストの実行が上手くいかず原因の特定に時間がかかりました。
  テストを書くこと自体で品質があがるわけではなく、テスト実装を通してプロダクトの設計の見直しや  
  プロダクトコードの修正が加わることで、結果としてプロダクトの品質を向上できると感じました。

## RaiseTechでの学習内容

#### Javaの基礎知識

型 / クラス / メソッド/ fot文 / if文 / List / Map / 例外 / Stream API

#### 現場で使うツールの基礎知識

IntelliJ

#### Gitを用いた開発

Git / GitHub / Pull Requestを利用したレビュー

#### Webアプリケーションの基礎知識

Webアプリケーション/ RESTful API / MVC / データベース(MySQL/Oracle/H2/PostgreSQL)
CRUD / トランザクション / TCP / IP / セッション / クッキー

#### フレームワークおよびライブラリの知識と経験、実践経験

Spring Framework / Spring Boot / MyBatis

#### Spring Framework（Spring Boot）を用いた開発実践

RESTful API / データベースアクセス / 外部APIへのアクセス / 例外ハンドリング / Spring Securityによる認証・認可

#### DevOpsや自動化に対する知識と理解(CI/CD)

GitHub Actions

#### AWSのサービスに対する基本知識と基本的な使い方への理解

VPC / EC2 / RDS / ELB

#### 構成管理ツールに対する知識と理解および実践経験

Maven / Gradle

#### テスト手法に対する知識と経験

ブラックボックステスト / ホワイトボックステスト / 境界値テスト / 条件網羅 / 分岐網羅

#### 自動テストに対する知識と経験

Junit / Mockito / Spring Test / DBテスト

#### 開発手法

さまざまな開発手法への知識と理解 / V字モデル / ウォーターフォール / アジャイル / スクラム

#### 生産性を上げる為の取り組みに対する知識と理解

カンバン / ふりかえり(KPT)

## 追加で学習したいこと

1. React × Next.js でフロントを用意し、本アプリケーションと連携して動作するよう実装したい。
2. テストが非常に重要な要素であることを体感したため、単体テスト、結合テストの実装を通して、テスト実装の速度を早くできるようにしたい
3. 今回はSpring Bootの学習を目的に非常にシンプルなアプリケーションを作成しましたが、オリジナルのアプリケーションを検討して、CRUD処理以外の機能も含まれたREST
   API を作成したいと考えています。
4. 少し先にはなるが、適切なコードかを判断するためにアプリケーションの設計がどうあるべきかを判断できるよう知識を習得したい。
