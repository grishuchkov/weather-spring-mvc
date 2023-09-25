ALTER TABLE locations
    ADD CONSTRAINT unique_location UNIQUE (user_login, latitude, longitude);