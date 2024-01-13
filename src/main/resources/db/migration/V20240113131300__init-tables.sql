CREATE TYPE VideoSourceQuality AS ENUM ('vl', 'lq', 'sd', 'hd');

CREATE TABLE video (
    id bigint PRIMARY KEY,
    title varchar NOT NULL,
    duration bigint NOT NULL
);

CREATE TABLE video_source (
    id bigint PRIMARY KEY,
    quality VideoSourceQuality NOT NULL,
    source varchar NOT NULL,
    video_id bigint,

    CONSTRAINT video_id_quality_source_constraint UNIQUE (video_id, quality),
    FOREIGN KEY (video_id) REFERENCES video (id)
);

CREATE SEQUENCE video_source_id_seq START 1 INCREMENT 1;

