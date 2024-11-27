create table muralrecados
(
    id              bigint auto_increment
        primary key,
    pessoaId        bigint                             not null,
    nomePessoa      varchar(255)                       not null,
    eventoId        bigint                             not null,
    comentario      text                               not null,
    dataCriacao     datetime default CURRENT_TIMESTAMP not null,
    dataModificacao datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint muralrecados_ibfk_1
        foreign key (pessoaId) references pessoa (id)
            on delete cascade,
    constraint muralrecados_ibfk_2
        foreign key (eventoId) references evento (id)
            on delete cascade
);

create index eventoId
    on muralrecados (eventoId);

create index pessoaId
    on muralrecados (pessoaId);

