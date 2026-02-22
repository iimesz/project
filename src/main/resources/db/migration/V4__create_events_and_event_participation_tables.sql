CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    pet_type VARCHAR(255) NOT NULL,
    datetime TIMESTAMP NOT NULL,
    address VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_event_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE event_participation (
    id BIGSERIAL PRIMARY KEY,
    event_id BIGINT NOT NULL,
    pet_type VARCHAR(255) NOT NULL,
    CONSTRAINT fk_participation_event
        FOREIGN KEY (event_id)
        REFERENCES events(id)
        ON DELETE CASCADE
);
