create table convidadofamilia
(
    id              bigint auto_increment
        primary key,
    nomeFamilia     varchar(255)                       not null,
    acesso          varchar(100)                       not null,
    eventoId        bigint                             not null,
    dataCriacao     datetime default CURRENT_TIMESTAMP not null,
    dataModificacao datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint acesso
        unique (acesso),
    constraint convidadofamilia_ibfk_1
        foreign key (eventoId) references evento (id)
            on delete cascade
);

create index eventoId
    on convidadofamilia (eventoId);

