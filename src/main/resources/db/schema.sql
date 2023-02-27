-- Создаем таблицу "users" для хранения информации о пользователях
CREATE TABLE users
(
    user_id BIGINT PRIMARY KEY,
    external_id UUID NOT NULL,
    username VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Создаем таблицу "posts" для хранения информации о постах
CREATE TABLE posts
(
    post_id BIGINT PRIMARY KEY,
    external_id UUID NOT NULL,
    title      VARCHAR(100) NOT NULL,
    content    TEXT,
    created_at TIMESTAMP NOT NULL,
    user_id    BIGINT NOT NULL REFERENCES users (user_id) ON DELETE CASCADE
);

-- Создаем таблицу "comments" для хранения информации о комментариях
create TABLE comments
(
    comment_id BIGINT PRIMARY KEY,
    external_id UUID NOT NULL,
    content    TEXT,
    created_at TIMESTAMP NOT NULL,
    user_id    BIGINT NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    post_id    BIGINT NOT NULL REFERENCES posts (post_id) ON DELETE CASCADE
);

-- Создаем таблицу "tags" для хранения информации о тегах
create TABLE tags
(
    tag_id BIGINT PRIMARY KEY,
    external_id UUID NOT NULL,
    name   VARCHAR(50) NOT NULL
);

-- Создаем таблицу "post_tags" для связи между постами и тегами
create TABLE post_tags
(
    post_id BIGINT NOT NULL REFERENCES posts (post_id) ON DELETE CASCADE,
    tag_id  BIGINT NOT NULL REFERENCES tags (tag_id) ON DELETE CASCADE,
    PRIMARY KEY (post_id, tag_id)
);