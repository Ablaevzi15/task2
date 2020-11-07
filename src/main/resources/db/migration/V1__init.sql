create table IF NOT EXISTS public.products
(
    id   bigint not null
            primary key,
    name varchar
);

create table IF NOT EXISTS public.price_product
(
    id   bigint not null
            primary key,
    price  double precision,
    date_string varchar,
    date timestamp,
    product_id bigint constraint price_product__products_entity_id_fk
        references public.products
);