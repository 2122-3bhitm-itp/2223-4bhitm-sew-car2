docker run --rm=true \
           --name postgres-db \
           -e POSTGRES_USER=postgres \
           -e POSTGRES_PASSWORD=postgres \
           -e POSTGRES_DB=db \
           -v ${PWD}/db-postgres/db:/var/lib/postgresql/data \
           -p 5432:5432 \
           postgres:15.1-alpine