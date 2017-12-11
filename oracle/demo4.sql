rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
-- 重写（统计每年入职的员工个数）
declare
   cursor cemp is select to_char(hiredate,'yyyy') from emp;
   pdate varchar2(4);
   
   count_80 number := 0;
   count_81 number := 0;
   count_82 number := 0;
   count_87 number := 0;
   
begin
   open cemp;
   loop
     fetch cemp into pdate;
     exit when cemp%notfound;
     
     if pdate = '1980' then count_80 := count_80+1;
        elsif pdate = '1981' then count_81 := count_81+1;
        elsif pdate = '1982' then count_82 := count_82+1;
        elsif pdate = '1987' then count_87 := count_87+1;
        else dbms_output.put_line('not exist');
     end if;
     
   end loop;
   close cemp;
   
   --输出
   dbms_output.put_line('Total 1980 1981 1982 1987');
   dbms_output.put_line((count_80+count_81+count_82+count_87)||'    '||count_80||'    '||count_81||'    '||count_82||'    '||count_87);
end;
/
