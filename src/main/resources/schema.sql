drop table if exists foos;

create table foos (
	id bigint primary key,
	uuid varchar(50),
	bar varchar(100),
);

create table bars (
	id bigint primary key,
	asdf varchar(50),
	foo_id bigint,
	FOREIGN KEY (foo_id) REFERENCES foos(id)
);

