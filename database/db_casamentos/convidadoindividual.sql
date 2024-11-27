create table convidadoindividual
(
    id              bigint auto_increment
        primary key,
    pessoaId        bigint                               not null,
    familiaId       bigint                               null,
    eventoId        bigint                               not null,
    parentesco      varchar(100)                         null,
    confirmacao     tinyint(1) default 0                 null,
    dataCriacao     datetime   default CURRENT_TIMESTAMP not null,
    dataModificacao datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint convidadoindividual_ibfk_1
        foreign key (pessoaId) references pessoa (id)
            on delete cascade,
    constraint convidadoindividual_ibfk_2
        foreign key (familiaId) references convidadofamilia (id)
            on delete set null,
    constraint convidadoindividual_ibfk_3
        foreign key (eventoId) references evento (id)
            on delete cascade
);

create index eventoId
    on convidadoindividual (eventoId);

create index familiaId
    on convidadoindividual (familiaId);

create index pessoaId
    on convidadoindividual (pessoaId);

