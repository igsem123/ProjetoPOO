create table pessoa
(
    id              bigint auto_increment
        primary key,
    nome            varchar(255)                                                not null,
    sexo            enum ('Homem', 'Mulher', 'Outro') default 'Outro'           null,
    dataNascimento  date                                                        null,
    telefone        varchar(20)                                                 null,
    login           varchar(100)                                                not null,
    senha           varchar(255)                                                not null,
    tipoUsuario     int                                                         not null,
    cpf             char(11)                                                    not null,
    dataCriacao     datetime                          default CURRENT_TIMESTAMP not null,
    dataModificacao datetime                          default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint cpf
        unique (cpf),
    constraint login
        unique (login)
);

