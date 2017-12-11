rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
-- 使用scott用户下的emp表 BY CAOTING
/*
为员工涨工资，从最低工资调起每人涨10%，但是工资总额不能
超过5万元，请计算涨工资的人数和涨工资后的工资总额，并输出
涨工资的人数以及工资总额   
1、从最低工资起（需要获取所有员工的工资升序排列）
   select empno,sal from emp order by sal ----游标
2、需要初始化的变量
   游标、
   涨工资人数：（pperson）遍历累加获取
   涨工资后工资总额：涨前工资（psumsal）加上涨的工资（遍历获取）
3、循环遍历游标
   结束条件：1.notfound  2.当前涨后工资加上当前员工将要涨的工资是否大于5万
*/
declare
   cursor cemp is select empno,sal from emp order by sal;
   pempno emp.empno%type;
   psal emp.sal%type;
   
   pperson number := 0;
   psumsal number;
begin
   -- 获取未涨工资之前的员工的总工资
   select count(sal) into psumsal from emp;
   open cemp;
   
   loop
      fetch cemp into pempno,psal;
      exit when cemp%notfound or psumsal+(psal*0.1)>50000;
      
      update emp set sal=sal*1.1 where empno=pempno;
      psumsal := psumsal+(psal*0.1);
      pperson := pperson+1;
   end loop;
   close cemp;
   
   commit;
   dbms_output.put_line('涨后工资：'||psumsal);
   dbms_output.put_line('涨工资人数：'||pperson);
end;
/
