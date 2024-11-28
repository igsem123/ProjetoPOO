create table evento
(
    id              bigint auto_increment
        primary key,
    dataEvento      date                               not null,
    cerimonial      bigint                             null,
    igreja          varchar(255)                       null,
    cartorio        varchar(255)                       null,
    pessoaNoivo1    bigint                             not null,
    pessoaNoivo2    bigint                             not null,
    nomeDoEvento    varchar(255)                       not null,
    dataCriacao     datetime default CURRENT_TIMESTAMP not null,
    dataModificacao datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint evento_ibfk_1
        foreign key (cerimonial) references pessoa (id),
    constraint evento_ibfk_2
        foreign key (pessoaNoivo1) references pessoa (id),
    constraint evento_ibfk_3
        foreign key (pessoaNoivo2) references pessoa (id)
);

create index cerimonial
    on evento (cerimonial);

create index pessoaNoivo1
    on evento (pessoaNoivo1);

create index pessoaNoivo2
    on evento (pessoaNoivo2);

