## 第9回以降の課題用README

ディレクトリ構成

```
├── RtJavaMemoAppApplication.java
├── application (クライアントとのデータの入出力)
│   └──── controller
│       ├──  MemoController.java
│       └──  MemoResponse.java
├── domain (業務処理を実行する)
│   ├── exception（例外処理）
│   │   └── Will do 10回課題の着手時に例外処理を実装
│   ├── model（DBのデータの構造を保存）
│   │   └── Memo.java
│   ├── repository（業務データへのアクセス？）
│   │   └── 現状はなし
│   └── service（ビジネスルールに関わる処理）
│       ├── MemoService.java
│       └── MemoServiceImpl.java
├── infrastructure (ドメイン層の実装を提供する)
│   ├── entity
│   │   └──現状はなし 
│   └── mapper (データの操作を行う / これもrepositoryと名付けることあり)
│       └── MemoMapper.java
```

#### メモ → Readme整備のタイミングで削除

ディレクトリごとの役割の理解が浅いので、もう少し調べてみる
domain 配下の repository と serviceの使い分け / Infra の entity など

ディレクトリ構成について参照ページ → 整理できたら削除
[① ](http://terasolunaorg.github.io/guideline/current/ja/Overview/ApplicationLayering.html)
[② ](https://qiita.com/YutaKase6/items/7d88fa23f81366905270)
[③ ](https://cs27.org/wiki/kobespiral2021/?SpringBoot/%E5%90%84%E3%83%AC%E3%82%A4%E3%83%A4%E3%81%AE%E8%B2%AC%E5%8B%99)
