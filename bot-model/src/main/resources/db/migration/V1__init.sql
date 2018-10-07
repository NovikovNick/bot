CREATE TABLE activity_category(
    id                    SERIAL PRIMARY KEY NOT NULL,
    name                  varchar(100)
);

insert into activity_category ("name") values
  ('Работа'),
  ('Сон'),
  ('Отдых'),
  ('Быт'),
  ('Развлечения'),
  ('Обучение')
  ;

CREATE TABLE activity_log(
    id                    SERIAL PRIMARY KEY NOT NULL,
    started_at            TIMESTAMP WITH TIME ZONE NOT NULL,
    finished_at           TIMESTAMP WITH TIME ZONE,
    activity_category_id  INTEGER NOT NULL,
    contact_id            INTEGER NOT NULL
);

ALTER TABLE public.activity_log
ADD CONSTRAINT activity_log_activity_category_id_fk
FOREIGN KEY (activity_category_id) REFERENCES activity_category (id);

CREATE TABLE contact(
    id                    INTEGER PRIMARY KEY NOT NULL,
    phone_number          varchar(100),
    first_name            varchar(100),
    last_name             varchar(100)
);

ALTER TABLE public.activity_log
ADD CONSTRAINT activity_log_contact_id_fk
FOREIGN KEY (contact_id) REFERENCES contact (id);









