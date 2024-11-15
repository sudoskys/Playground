CREATE TABLE product
(
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    image_url   VARCHAR(255)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    description TEXT           NOT NULL,
    uploader_id BIGINT         NOT NULL,
    upload_time TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    event_id    BIGINT,
    tags        VARCHAR(255)
);