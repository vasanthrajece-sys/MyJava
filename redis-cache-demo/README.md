# Vasanth Redis Cache Practice

Small Spring Boot project I can use to practice Redis cache in interviews.

## What I Am Practicing

- `@Cacheable` for reading from cache
- `@CachePut` for updating the cache
- `@CacheEvict` for deleting from cache
- Redis TTL, currently 10 seconds

## Start Redis

If Docker is available:

```powershell
docker compose up -d
```

Or run Redis locally on:

```text
localhost:6379
```

## Run

Open this folder in IntelliJ:

```text
redis-cache-demo
```

Then run:

```text
RedisCacheDemoApplication
```

## URLs I Can Try

First call waits because the value is not in Redis yet:

```text
GET http://localhost:8080/employees/1
```

Second call should be fast because Redis has the value:

```text
GET http://localhost:8080/employees/1
```

Update Vasanth and refresh the cache:

```text
PUT http://localhost:8080/employees/1?name=Vasanth&department=Backend&salary=95000
```

Delete Vasanth from cache and local store:

```text
DELETE http://localhost:8080/employees/1
```
