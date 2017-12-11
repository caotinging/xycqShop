rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
-- 查询emp中指定数据存储在新表中  create by caoting 2017/12/11
/*
实现按部门进行工资分段（6000以上，<3000,6000>，3000以下）
统计各工资段的职工人数，以及各部门的工资总额
（工资总额中不包括奖金）

1、用户存储数据的表：
   create table msg1(
          deptno number,
          emp_num1 number,
          emp_num2 number,
          emp_num3 number,
          sum_sal number);
2、按部门（需要获取所有部门）
进行工资分段（需要分别获取每个部门所属的员工的工资）
    select deptno from emp group by deptno;
    select sal from emp where deptno = ?（遍历获取）
3、两层循环：
    循环部门：获取部门号----无参游标
         循环员工：获取员工工资进行判断----有参游标（参数：部门号）
                   对数据进行保存（deptno,
    （循环结束条件）：notfound
4、初始化的变量：
   游标2：部门cemp1（无参） 员工cemp2（有参：部门号）
   工资总额：psumsal（遍历员工之前置为0，遍历员工时累加） 
   小于3000：num1
   3000到6000：num2
   大于6000：num3
*/
declare
   cursor cemp1 is select deptno from emp group by deptno;
   cursor cemp2(dno number) is select sal from emp where deptno=dno;
   pdeptno emp.deptno%type;
   psal emp.sal%type;
   
   psumsal number;
   num1 number;
   num2 number;
   num3 number;
begin
   open cemp1;
   loop
      fetch cemp1 into pdeptno;
      exit when cemp1%notfound;
      
      psumsal:=0;num1:=0;num2:=0;num3:=0;
      open cemp2(pdeptno);
      
      -- 遍历各部门中的员工
      loop
         fetch cemp2 into psal;
         exit when cemp2%notfound;
         
         -- 对员工的薪水进行判断
         if psal <3000 then num1 := num1+1;
            elsif psal >3000 and psal <6000 then num2 := num2+1;
            elsif psal >6000 then num3 :=num3+1;
            else dbms_output.put_line('有人大于6000');
         end if;
         
         psumsal := psumsal+psal;
      end loop;
      close cemp2;
      
      -- 将刚刚获取的部门的数据存储入表
      insert into msg1 values(pdeptno,num1,num2,num3,psumsal);
    end loop;
    close cemp1;
    
    commit;
    dbms_output.put_line('successful');
end;
/
