create table article(
	id uuid primary key,
	title text not null,
	content text
);

create table category(
	id uuid primary key,
	article_id uuid references article(id),
	content text
);