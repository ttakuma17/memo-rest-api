## Spring bootでのRESTAPI作成

メモアプリ（REST API)
使用技術：Java Spring boot Junit5 Mockito
テストの実装までを終えた段階でReactとの繋ぎ込みを行う予定

ディレクトリ構成

```
├── RtJavaMemoAppApplication.java
├── application (クライアントとのデータの入出力)
│   ├──── controller
│   │   ├──  MemoController.java
│   │   └──  MemoControllerAdvice.java
│   └──── resources
│   │   ├──  MemoForm.java
│   │   └──  MemoResponse.java
├── domain (業務処理を実行する)
│   ├── exception（例外処理）
│   │   └── ResourceNotFountException.java
│   ├── model（DBのデータの構造を保存）
│   │   └── Memo.java
│   ├── repository（業務データへのアクセス？）
│   │   └── 現状はなし
│   └── service（ビジネスルールに関わる処理）
│       ├── MemoService.java
│       └── MemoServiceImpl.java
├── infrastructure (ドメイン層の実装を提供する)
│   └── mapper (データの操作を行う / これもrepositoryと名付けることあり)
│       └── MemoMapper.java
```

参考資料
[RESTful Web Services](http://terasolunaorg.github.io/guideline/current/ja/ArchitectureInDetail/WebServiceDetail/REST.html#resthowtouseapplicationsettings) 
[ドメイン層の実装](http://terasolunaorg.github.io/guideline/current/ja/ImplementationAtEachLayer/DomainLayer.html)  
[インフラストラクチャ層の実装](http://terasolunaorg.github.io/guideline/current/ja/ImplementationAtEachLayer/InfrastructureLayer.html)  
[アプリケーション層の実装](http://terasolunaorg.github.io/guideline/current/ja/ImplementationAtEachLayer/ApplicationLayer.html)  
[例外ハンドリング](http://terasolunaorg.github.io/guideline/current/ja/ArchitectureInDetail/WebApplicationDetail/ExceptionHandling.html)  
