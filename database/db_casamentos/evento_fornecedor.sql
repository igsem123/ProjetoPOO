create table evento_fornecedor
(
    eventoId     bigint not null,
    fornecedorId bigint not null,
    primary key (eventoId, fornecedorId),
    constraint evento_fornecedor_ibfk_1
        foreign key (eventoId) references evento (id)
            on delete cascade,
    constraint evento_fornecedor_ibfk_2
        foreign key (fornecedorId) references fornecedor (id)
            on delete cascade
);

create index fornecedorId
    on evento_fornecedor (fornecedorId);

