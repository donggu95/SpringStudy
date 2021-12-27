
-- news, comments 

-- group by 사용시 그룹화된 컬럼만이 출력대상이 될 수 있다....
-- 단, 집계함수는 예외...
select dname , count(e.deptno) 
from Dept d, emp e
where d.deptno = e.deptno
group by dname;	


select title, writer, regdate, hit, count(c.news_id) 
from news n , comments c
where n.news_id = c.news_id
group by title, writer, regdate, hit;


select * from news;
select * from comments;





select * from dept;
select  * from emp;

-- 이너조인 공통된 것만 가져옴
select *
from dept d, emp e 
where d.deptno=e.deptno;

-- outer join 지정한 테이블의 레코드는 공통되지 않아도 무조건 가져온다.
select *
from dept d left outer join emp e 
on d.deptno = e.deptno;


