CREATE TABLE public.document
(
    id SERIAL not null,
    file_name VARCHAR(255),
    file_autor VARCHAR(255),
    date_creation TIMESTAMP,
    date_uploading TIMESTAMP,
    date_last_editing TIMESTAMP,
    file_version REAL,
    ext VARCHAR(10),
    file_path VARCHAR(255),
    type_doc INTEGER,
);