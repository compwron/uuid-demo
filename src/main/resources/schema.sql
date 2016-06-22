drop table if exists foos;

create table foos (
	uuid uuid primary key,
	bar varchar(100),
);

create table bars (
	uuid uuid primary key,
	asdf varchar(50),
	foo_uuid bigint,
	FOREIGN KEY (foo_uuid) REFERENCES foos(uuid)
);

