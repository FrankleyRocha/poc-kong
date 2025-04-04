# POC-KONG

```mermaid
graph TD;

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

## Enter into `.devcontainer` folder
```bash
cd .devcontainer
```

## Startup the compose
```bash
docker compose up -d
```

## Shutdown the compose
```bash
docker compose down
```

## Testing

### anonymous access to public endpoint
```bash
curl -i http://localhost:8000/api/public
```

### anonymous does not have access to the authenticated endpoint
```bash
curl -i http://localhost:8000/api/authenticated
```

### Bob can access the authenticated endpoint
```bash
curl -i http://localhost:8000/api/authenticated -H "apikey: bob-key"
```

### Alice can access the authenticated endpoint
```bash
curl -i http://localhost:8000/api/authenticated -H "apikey: alice-key"
```

### Bob does not have access to the admin endpoint
```bash
curl -i http://localhost:8000/api/admin -H "apikey: bob-key"
```

### Alice can access the admin endpoint
```bash
curl -i http://localhost:8000/api/admin -H "apikey: alice-key"
```