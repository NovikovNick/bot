#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER metalheart WITH password 'metalheart';
    CREATE DATABASE bot;
    GRANT ALL PRIVILEGES ON DATABASE bot TO metalheart;
EOSQL