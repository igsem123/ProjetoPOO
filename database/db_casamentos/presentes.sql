create table presentes
(
    id              bigint auto_increment
        primary key,
    nome            varchar(255)                       not null,
    tipo            int                                not null,
    valor           decimal(10, 2)                     not null,
    pessoaId        bigint                             null,
    nomePessoa      varchar(255)                       null,
    dataCriacao     datetime default CURRENT_TIMESTAMP not null,
    dataModificacao datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint presentes_ibfk_1
        foreign key (pessoaId) references pessoa (id)
            on delete set null
);

create index pessoaId
    on presentes (pessoaId);

