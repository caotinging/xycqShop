rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
-- 自定义异常 Created on 2017/12/11 by CAOTING 
declare
   cursor cemp is select ename from emp where deptno=60;
   pename emp.ename%type;
   
   -- 自定义异常
   no_emp_found exception;
begin
   open cemp;
   fetch cemp into pename;
   if cemp%notfound then
      -- 抛出异常
      raise no_emp_found;
   end if;
   
   close cemp;
exception
   when no_emp_found then dbms_output.put_line('没有员工');
   when others then dbms_output.put_line('其他异常');
end;
/
