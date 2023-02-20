CREATE TABLE files
(
    id         uuid        NOT NULL PRIMARY KEY,
    name       text        NOT NULL,
    type       bigint      NOT NULL,
    size       bigint      NOT NULL,
    crc        bigint      NOT NULL,
    content_id uuid        NOT NULL REFERENCES content(id) ON DELETE CASCADE,
    created_at timestamptz NOT NULL DEFAULT NOW(),
    updated_at timestamptz NOT NULL DEFAULT NOW()
);

CREATE TABLE content
(
    id      uuid NOT NULL PRIMARY KEY,
    content bytea
);

CREATE OR REPLACE FUNCTION trigger_set_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON files
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();