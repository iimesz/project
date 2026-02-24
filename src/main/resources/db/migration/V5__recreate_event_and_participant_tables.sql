DROP TABLE IF EXISTS event_participation;
DROP TABLE IF EXISTS events;

CREATE TABLE event (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    pet_type VARCHAR(255) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    address VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_event_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE event_participant (
    id BIGSERIAL PRIMARY KEY,
    event_id BIGINT NOT NULL,
    pet_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_participant_event
        FOREIGN KEY (event_id)
        REFERENCES event(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_participant_pets
        FOREIGN KEY (pet_id)
        REFERENCES pets(id)
        ON DELETE CASCADE
);
