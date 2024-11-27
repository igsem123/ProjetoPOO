create table fornecedor
(
    id                 bigint auto_increment
        primary key,
    nome               varchar(255)                                                  not null,
    CNPJ               char(14)                                                      not null,
    telefone           varchar(20)                                                   null,
    email              varchar(255)                                                  null,
    valorAPagar        decimal(15, 2)                      default 0.00              null,
    valorParcela       decimal(15, 2)                                                null,
    parcelas           int                                                           null,
    estado             enum ('A Pagar', 'Pago', 'Parcial') default 'A Pagar'         null,
    valorInicial       decimal(15, 2)                      default 0.00              null,
    parcelaInicial     int                                 default 0                 null,
    totalParcelasPagas int                                 default 0                 null,
    dataCriacao        datetime                            default CURRENT_TIMESTAMP not null,
    dataModificacao    datetime                            default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint CNPJ
        unique (CNPJ)
);

