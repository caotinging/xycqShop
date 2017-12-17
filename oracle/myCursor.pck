  -- Author  : CAOTING
  -- Created : 2017/12/12 9:19:40
  -- Purpose : 使用自定义类型实现返回光标

create or replace package myCursor
is
    -- 自定义类型
    type empcursor is ref cursor;
    procedure queryEmpList(dno in number,EmpList out empcursor);
    
end myCursor;
/
create or replace package body myCursor is
   
    procedure queryEmpList(dno in number,empList out empcursor)
      as
      begin
         open empList for select * from emp where deptno=dno;
      end queryEmpList;
end myCursor;
/
