CREATE table tbl_pizza_01(
pcode char(4) not null PRIMARY KEY,
pname varchar2(30),
cost number default 0
);

insert into tbl_pizza_01(pcode,pname,cost)
VALUES ('AA01','������������',6000);

insert into tbl_pizza_01(pcode,pname,cost)
VALUES ('AA02','ġ������',6500);

insert into tbl_pizza_01(pcode,pname,cost)
VALUES ('AA03','���۷δ�����',7000);

insert into tbl_pizza_01(pcode,pname,cost)
VALUES ('AA04','�޺���̼�����',7500);

insert into tbl_pizza_01(pcode,pname,cost)
VALUES ('AA05','��������',6000);

insert into tbl_pizza_01(pcode,pname,cost)
VALUES ('AA06','������������',7000);

insert into tbl_pizza_01(pcode,pname,cost)
VALUES ('AA07','�Ұ������',8000);

insert into tbl_pizza_01(pcode,pname,cost)
VALUES ('AA08','����������',8000);

CREATE table tbl_shop_01(
scode char(4) not null PRIMARY KEY,
sname varchar2(20)
);

insert into tbl_shop_01(scode,sname)
VALUES ('S001','������');

insert into tbl_shop_01(scode,sname)
VALUES ('S002','������');

insert into tbl_shop_01(scode,sname)
VALUES ('S003','������');

insert into tbl_shop_01(scode,sname)
VALUES ('S004','��û��');

insert into tbl_shop_01(scode,sname)
VALUES ('S005','�ӽ���');

CREATE table tbl_salelist_01(
saleno number not null PRIMARY KEY,
scode char(4) not null,
saledate date,
pcode char(4) not null,
amount number(5),
FOREIGN key (scode) REFERENCES tbl_shop_01 (scode),
FOREIGN key (pcode) REFERENCES tbl_pizza_01 (pcode)
);


insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100001,'S001','2018/12/02','AA01',50);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100002,'S001','2018/12/02','AA02',30);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100003,'S001','2018/12/02','AA03',20);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100004,'S001','2018/12/02','AA04',50);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100005,'S003','2018/12/03','AA01',40);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100006,'S003','2018/12/03','AA02',60);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100007,'S003','2018/12/03','AA04',60);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100008,'S003','2018/12/04','AA05',70);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100009,'S005','2018/12/02','AA01',80);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100010,'S005','2018/12/02','AA03',30);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100011,'S005','2018/12/02','AA04',40);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100012,'S005','2018/12/02','AA05',50);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100013,'S004','2018/12/04','AA01',30);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100014,'S004','2018/12/04','AA02',20);

insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES (100015,'S004','2018/12/04','AA06',50);


select * from tbl_salelist_01;

select * from tbl_pizza_01;

select * from tbl_shop_01;

commit;

--�Ѹ���Ʈ ���̺�3�� ������-- �������
select a.saleno,a.scode,to_char(a.saledate,'YYYY-MM-DD') as saledate,a.pcode,a.amount,b.pname,b.cost,c.sname
from tbl_salelist_01 a
inner join tbl_pizza_01 b on a.pcode = b.pcode
inner join tbl_shop_01 c on a.scode = c.scode;

--������ǥ ���--
insert into tbl_salelist_01(saleno,scode,saledate,pcode,amount)
VALUES ((select nvl(max(saleno),0)+1 from tbl_salelist_01),'S004','2018-12-06','AA06',10);

--������ǥ��ȣ �ڵ�����--
select nvl(max(saleno),0)+1 as saleno from tbl_salelist_01;

--�����ڵ� ����Ʈ--
select scode,sname
from tbl_shop_01;

--�����ڵ�  ����Ʈ--
select pcode,pname
from tbl_pizza_01;

--����--
DELETE from tbl_salelist_01 
where saleno = 200015;

--���ո�����ȸ--
select a.saleno,a.scode,b.sname,to_char(a.saledate,'YYYY-MM-DD')as saledate,a.pcode,c.pname,a.amount,to_char(a.amount*c.cost) as sumcost
from tbl_salelist_01 a
inner join tbl_shop_01 b on a.scode = b.scode
inner join tbl_pizza_01 c on a.pcode = c.pcode
order by a.saleno DESC;

--������ ���� ��Ȳ--
select a.scode,a.sname,to_char(b.amount*c.cost,'fml999,999,999') as sumcost
from tbl_shop_01 a
inner join tbl_salelist_01 b on a.scode = b.scode
inner join tbl_pizza_01 c on b.pcode = c.pcode
order by a.scode DESC;

--��ǰ�� ���� ��Ȳ--
select a.pcode,a.pname,to_char(b.amount*a.cost,'fml999,999,999') as sumcost
from tbl_pizza_01 a
inner join tbl_salelist_01 b on a.pcode = b.pcode
order by a.pcode desc;