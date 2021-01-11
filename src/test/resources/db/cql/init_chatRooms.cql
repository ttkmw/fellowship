CREATE TABLE IF NOT EXISTS chat_room (name text, room_id uuid, PRIMARY KEY (name, room_id)) WITH CLUSTERING ORDER BY (room_id ASC);

INSERT INTO chat_room (name, room_id) VALUES ('Wangsimni', 110841e3-e6fb-4191-8fd8-5674a5107c33) IF NOT EXISTS;
INSERT INTO chat_room (name, room_id) VALUES ('Campus', 4f0a4a02-26c6-4441-915d-c0f61cda0178) IF NOT EXISTS;
INSERT INTO chat_room (name, room_id) VALUES ('Dormitory', cd865f7d-3923-4065-a5e6-5c093e7b5442) IF NOT EXISTS;
INSERT INTO chat_room (name, room_id) VALUES ('Itaewon', 56645394-80e0-4fb3-a2ad-097832e39233) IF NOT EXISTS;
