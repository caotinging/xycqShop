-- 为指定的职工在原工资的基础上长10%的工资并打印 create by caoting

create or replace procedure raiseSalary(empid in number)
is
   -- 这部分相当于declare（声明变量的部分）
   psal emp.sal%type;       
begin
   -- 查询该员工的工资
   select sal into psal from emp where empno=empid;
   update emp set sal=psal*1.1 where empno=empid;
   
   -- 打印涨工资前后的工资
   dbms_output.put_line('涨前：'||psal||'   涨后：'||psal*1.1);
end raiseSalary;
/
