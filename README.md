第10回課題用

ディレクトリ構成
```
├── application
│   ├── controller
│   │   ├── MemoController
│   │   └── MemoResponse
│   └── resource
│       ├──  
│       └── 
├── domain
│   ├── exception
│   │   └── 
│   ├── model
│   │   └── Memo
│   ├── repository
│   │   └── 
│   └── service
│       ├── MemoService
│       └── MemoServiceImpl
├── infrastructure
│   ├── entity
│   │   └── 
│   └── mapper
│       ├── MemoMapper
│       └── 
```

[Spring Boot のディレクトリ構成 - 参考記事](https://qiita.com/YutaKase6/items/7d88fa23f81366905270)


<メモ> 
→ 後ほど削除
SQL文を更新したところ、変更が反映されずにトラブルシューティング

単純に
docker compose downのみの実行だと、前のコンテナの情報が残っていたのか、変更が反映されず。

完全に前の情報を削除するため以下を実行
docker-compose down --rmi all --volumes

その後、
docker compose up を実行し、期待通り変更がDBへ反映される


