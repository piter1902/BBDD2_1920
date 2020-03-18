select 'drop type '||type_name||' force;'  from user_types;
select 'drop table '||table_name||' force;'  from user_tables;

select type_name from user_types;
select table_name from user_tables;