create or replace trigger checksalary
before update
on emp
for each row
  begin
    -- 涨后的薪水不能低于涨前的薪水
    if :new.sal<:old.sal then
      -- 错误码不能重复。有固定范围
      raise_application_error(-20002,'涨后薪水不能低于涨前'||'  涨前:'||:old.sal||' 涨后:'||:new.sal);
    end if;
end checksalary;
/
