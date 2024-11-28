create table pagamento
(
    id              bigint auto_increment
        primary key,
    pessoaId        bigint                               not null,
    fornecedorId    bigint                               not null,
    descricao       varchar(255)                         null,
    valor           decimal(10, 2)                       not null,
    parcela         int        default 1                 not null,
    agendado        tinyint(1) default 0                 not null,
    dataPagamento   date                                 not null,
    dataCriacao     datetime   default CURRENT_TIMESTAMP not null,
    dataModificacao datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint pagamento_ibfk_1
        foreign key (pessoaId) references pessoa (id)
            on delete cascade,
    constraint pagamento_ibfk_2
        foreign key (fornecedorId) references fornecedor (id)
            on delete cascade
);

create index fornecedorId
    on pagamento (fornecedorId);

create index pessoaId
    on pagamento (pessoaId);

