rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
-- 记录型变量
declare
   emp_rec emp%rowtype;
begin
   select * into emp_rec from emp where empno=7839;
   dbms_output.put_line(emp_rec.ename||'的薪水是'||emp_rec.sal);
end;
/
