-- ��ѯָ��Ա����������

create or replace function queryEmpSal(empid in number) 
return  number
is
  psal emp.sal%type;
  pcomm emp.comm%type;
  
begin
  select sal,comm into psal,pcomm from emp where empno=empid;
  -- �������Ϊ��
  return psal*12+nvl(pcomm,0);
end queryEmpSal;
/