--działy
create table section(
    id int not null primary key,
    name varchar not null unique
);
--kategorie
create table category(
    id int not null primary key,
    section_id int not null unique ,
    name varchar not null,
    CONSTRAINT section_fk FOREIGN KEY (section_id) REFERENCES section (id)
);
--aukcje
create table auction(
    id int not null primary key,
    owner_id int not null,
    category_id int not null,
    price int not null,
    title varchar not null,
    version int not null,
    description TEXT not null,
    CONSTRAINT users_fk FOREIGN KEY (owner_id) REFERENCES users (id),
    CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES category(id)
);
--zdjęcie
create table photo(
    id int not null primary key,
    auction_id int not null,
    name varchar not null,
    position int not null,
    CONSTRAINT auction_fk FOREIGN KEY (auction_id) REFERENCES auction (id)
);
--parametry / wymiary
create table parameter(
    id int not null primary key,
    key varchar not null
);
--łączenie aukcji z parametrami
create table auction_parameter(
    auction_id int not null,
    parameter_id int not null,
    value varchar not null,
    CONSTRAINT parameter_fk FOREIGN KEY (parameter_id) REFERENCES parameter (id),
    CONSTRAINT auction_fk FOREIGN KEY (auction_id) REFERENCES auction (id)
);