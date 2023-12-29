create table ducks (
    id         uuid          constraint ducks_pkey primary key,
    name       varchar(255),
    height     integer,
    created_at timestamp
);

create table cat_groups (
    id         uuid          constraint cat_groups_pkey primary key,
    name       varchar(255),
    created_at timestamp
);

create table cats (
    id         uuid          constraint cats_pkey primary key,
    name       varchar(255),
    created_at timestamp,
    group_id   uuid         constraint cats__cat_groups_fkey
        references cat_groups(id) on delete cascade on update cascade
);
