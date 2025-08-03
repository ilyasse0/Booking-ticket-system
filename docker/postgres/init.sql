-- Check if 'ticketing' DB exists. If not, create it.
\connect postgres

SELECT 'CREATE DATABASE ticketing'
WHERE NOT EXISTS (
    SELECT FROM pg_database WHERE datname = 'ticketing'
)\gexec