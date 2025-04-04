# POC-KONG

# Network schema

```mermaid
graph TD
    subgraph Internet
        Client1["Client 1"]
        Client2["Client 2"]
        Client3["Client 3"]

        Firewall
    end

    Client1 -.-> Firewall
    Client2 -.-> Firewall
    Client3 -.-> Firewall
    Firewall -.-> central-kong

    subgraph intranet["Intranet"]
        central-kong["Central Kong (Api Gateway)"]
    end

    central-kong -.-> springapp-kong
    central-kong -.-> phpapp-kong

    subgraph springapp-net["Spring App Net"]
        springapp["Spring App"]
        springapp-kong["Spring App Kong (Auth)"]
    end

    springapp-kong -.-> springapp
    springapp -.-> central-kong

    subgraph phpapp-net["Php App Net"]
        phpapp["PHP App"]
        phpapp-kong["PHP App Kong (Auth)"]
    end

    phpapp-kong -.-> phpapp
    phpapp -.-> central-kong
```

# Auth schema

```mermaid
graph LR
  subgraph Groups
    PublicGroup["Public Group"]
    AdminGroup[Admin Group]
  end

  PublicGroup -.->|Can Access| Phpapp
  PublicGroup -.->|Can Access| SpringappPublicEndpoint
  PublicGroup -.->|Can Access| SpringappAuthenticatedEndpoint
  AdminGroup -.->|Can Access| Phpapp
  AdminGroup -.->|Can Access| SpringappPublicEndpoint
  AdminGroup -.->|Can Access| SpringappAuthenticatedEndpoint
  AdminGroup -.->|Can Access| SpringappAdminEndpoint
  AdminGroup -.->|Can Access| PhpappAdminEndpoint

  subgraph Users
    Bob -->|Member of| PublicGroup
    Alice -->|Member of| AdminGroup
    Anonymous
  end

  Anonymous -.->|Can Access| SpringappPublicEndpoint
  Anonymous -.->|Can Access| Phpapp

  subgraph Springapp Endpoints
    SpringappPublicEndpoint["/springapp/api/public (Anonymous Access)"]
    SpringappAuthenticatedEndpoint["/springapp/api/authenticated (Authenticated Users)"]
    SpringappAdminEndpoint["/springapp/api/admin (Admin Group Only)"]
  end

  subgraph Phpapp Endpoints
    Phpapp["/phpapp (Anonymous Access)"]
    PhpappAdminEndpoint["/phpapp/admin (Admin Group Only)"]
  end
```

## Clone the project
```bash
git clone https://github.com/FrankleyRocha/poc-kong.git
```

## Enter into `poc-kong/.container` folder
```bash
cd poc-kong/.container
```

## Startup the compose
```bash
docker compose up -d
```

## Testing

### anonymous access to phpapp
```bash
curl -i http://localhost:8000/phpapp
```

### anonymous does not have access to the phpgapp admin endpoint
```bash
curl -i http://localhost:8000/phpapp/admin
```

### Bob does not have access to the phpgapp admin endpoint
```bash
curl -i http://localhost:8000/phpapp/admin -H "apikey: bob-key"
```

### Alice can access the phpgapp admin endpoint
```bash
curl -i http://localhost:8000/phpapp/admin -H "apikey: alice-key"
```

### anonymous access to springapp public endpoint
```bash
curl -i http://localhost:8000/springapp/api/public
```

### anonymous does not have access to the springapp authenticated endpoint
```bash
curl -i http://localhost:8000/springapp/api/authenticated
```

### Bob can access the springapp authenticated endpoint
```bash
curl -i http://localhost:8000/springapp/api/authenticated -H "apikey: bob-key"
```

### Alice can access the springapp authenticated endpoint
```bash
curl -i http://localhost:8000/springapp/api/authenticated -H "apikey: alice-key"
```

### Bob does not have access to the springapp admin endpoint
```bash
curl -i http://localhost:8000/springapp/api/admin -H "apikey: bob-key"
```

### Alice can access the springapp admin endpoint
```bash
curl -i http://localhost:8000/springapp/api/admin -H "apikey: alice-key"
```

## Shutdown the compose
```bash
docker compose down
```
