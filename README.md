# POC-KONG

```bash
    cd .devcontainer
```

```bash
    docker compose up -d
```

```bash
    docker compose down
```

```bash
curl -i http://localhost:8000/api/public
```

```bash
curl -i http://localhost:8000/api/authenticated
```

```bash
curl -i http://localhost:8000/api/authenticated -H "apikey: bob-key"
```

```bash
curl -i http://localhost:8000/api/authenticated -H "apikey: alice-key"
```

```bash
curl -i http://localhost:8000/api/admin -H "apikey: bob-key"
```

```bash
curl -i http://localhost:8000/api/admin -H "apikey: alice-key"
```