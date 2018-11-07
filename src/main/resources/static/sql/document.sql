CREATE TABLE public.document
(
    id SERIAL NOT NULL PRIMARY KEY,
    file_name VARCHAR (80),
    file_autor VARCHAR (20),
    date_uploading BIGINT,
    file_version REAL,
    ext VARCHAR (10),
    file_path VARCHAR (255),
    type_doc INTEGER,
    size NUMERIC (5,3)
);