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

    subgraph Intranet
        central-kong["Central Kong (Api Gateway)"]
    end

    central-kong -.-> springapp-kong

    subgraph SpringappNet
        springapp["Spring App"]
        springapp-kong["Spring App Kong (Auth)"]
    end

    springapp-kong -.-> springapp
    springapp -.-> central-kong
```

# Auth schema

```mermaid
graph TD

  subgraph Groups
    PublicGroup["Public Group"]
    AdminGroup[Admin Group]
  end

  PublicGroup -.->|Can Access| PublicEndpoint
  PublicGroup -.->|Can Access| AuthenticatedEndpoint
  AdminGroup -.->|Can Access| PublicEndpoint
  AdminGroup -.->|Can Access| AuthenticatedEndpoint
  AdminGroup -.->|Can Access| AdminEndpoint

  subgraph Users
    Bob -->|Member of| PublicGroup
    Alice -->|Member of| AdminGroup
    Anonymous
  end

  Anonymous -.->|Can Access| PublicEndpoint

  subgraph API Endpoints
    PublicEndpoint["/api/public (Anonymous Access)"]
    AuthenticatedEndpoint["/api/authenticated (Authenticated Users)"]
    AdminEndpoint["/api/admin (Admin Group Only)"]
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

### anonymous access to public endpoint
```bash
curl -i http://localhost:8000/springapp/api/public
```

### anonymous does not have access to the authenticated endpoint
```bash
curl -i http://localhost:8000/springapp/api/authenticated
```

### Bob can access the authenticated endpoint
```bash
curl -i http://localhost:8000/springapp/api/authenticated -H "apikey: bob-key"
```

### Alice can access the authenticated endpoint
```bash
curl -i http://localhost:8000/springapp/api/authenticated -H "apikey: alice-key"
```

### Bob does not have access to the admin endpoint
```bash
curl -i http://localhost:8000/springapp/api/admin -H "apikey: bob-key"
```

### Alice can access the admin endpoint
```bash
curl -i http://localhost:8000/springapp/api/admin -H "apikey: alice-key"
```

## Shutdown the compose
```bash
docker compose down
```
