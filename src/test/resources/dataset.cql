-- CREATE KEYSPACE IF NOT EXISTS testKeySpace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

-- DROP TABLE IF EXISTS testKeySpace.chatmessage;

-- CREATE TABLE testKeySpace.chatroom (
--     id uuid,
--     name text,
--     PRIMARY KEY (id)
-- );

-- CREATE TABLE testKeySpace.chatmessage (
--     id uuid,
--     chatroom chatroom,
--     sender sender,
--     body text,
--     PRIMARY KEY (id)
-- );
