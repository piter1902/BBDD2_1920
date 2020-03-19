--select 'drop type '||type_name||' force;'  from user_types;

drop type CUENTA_AHORROUDT force;
drop type OPERACIONUDT force;
drop type TRANSFERENCIAUDT force;
drop type CUENTAUDT force;
drop type LISTAPROPIETARIOS force;
drop type CUENTA_CORRIENTEUDT force;
drop type SUCURSALUDT force;
drop type TRANSACCIONUDT force;
drop type REALIZADASUDT force;
drop type CLIENTEUDT force;
drop type LISTACUENTAS force;


select 'drop table '||object_name||' force;' from user_objects where object_type='TABLE';

select type_name from user_types;
select object_name from user_objects where object_type='TABLE';
--select table_name from user_tables;
--select 'drop table '||table_name||' force;'  from user_tables;