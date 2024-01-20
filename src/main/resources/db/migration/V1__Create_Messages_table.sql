DROP TABLE IF EXISTS messages;
CREATE TABLE messages (id UUID PRIMARY KEY DEFAULT gen_random_uuid(), message STRING);