use DOCUMENTS_SCHEMA;

CREATE TABLE IF NOT EXISTS users (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(256) UNIQUE NOT NULL,
    date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS teams (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(256) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_teams (
    user_id bigint NOT NULL,
    team_id bigint NOT NULL,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, team_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE IF NOT EXISTS documents (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    body VARCHAR(8000) NOT NULL,
    word_count INT NOT NULL,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);