rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
-- Created on 2017/12/11 by CAOTING 
declare 
  num number :=0;
begin
  for step in 1..2 loop 
    num := 10;
    dbms_output.put_line(num);
    dbms_output.put_line(step);
  end loop;
  dbms_output.put_line(num);
end;
/
