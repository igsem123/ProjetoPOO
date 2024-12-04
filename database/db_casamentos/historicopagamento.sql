create table historicopagamento
(
    id            bigint auto_increment
        primary key,
    fornecedorId  bigint         not null,
    valorPago     decimal(10, 2) not null,
    dataPagamento date           not null,
    constraint historicopagamento_ibfk_1
        foreign key (fornecedorId) references fornecedor (id)
            on delete cascade
);

create index fornecedorId
    on historicopagamento (fornecedorId);

