create or replace trigger securityemp
before insert
on emp
begin
   if to_char(sysdate,'day') in ('星期六','星期日','星期二')
     or to_number(to_char(sysdate,'hh24')) not between 9 and 17
     then
       -- 非工作时间禁止insert
       raise_application_error(-20001,'禁止在非工作时间进行插入员工操作');
   end if; 
end securityemp;
/
