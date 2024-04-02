insert into user (id,password,email,nickname,name,gender,birthdate,phonenumber,user_date,weight,height)
values ('aaa','a123456!','aaa@naver.com','일동','일길동','MALE','240322','01011111111',now(),60,170);

insert into user (id,password,email,nickname,name,gender,birthdate,phonenumber,user_date,weight,height)
values ('bbb','a123456!','bbb@naver.com','이동','이길동','MALE','240322','010122222222',now(),60,170);

insert into user (id,password,email,nickname,name,gender,birthdate,phonenumber,user_date,weight,height)
values ('ccc','a123456!','ccc@naver.com','삼동','삼길동','MALE','240322','01033333333',now(),60,170);

insert into user (id,password,email,nickname,name,gender,birthdate,phonenumber,user_date,weight,height)
values ('ddd','a123456!','ddd@naver.com','사동','사길동','MALE','240322','01044444444',now(),60,170);

insert into user (id,password,email,nickname,name,gender,birthdate,phonenumber,user_date,weight,height)
values ('eee','a123456!','eee@naver.com','오동','오길동','MALE','240322','01055555555',now(),60,170);

INSERT INTO diet (id, dname, ddatetime, dcalories)
VALUES
('aaa', '치킨', '2024-03-01', 2500),
('aaa', '햄버거', '2024-03-02', 1500),
('aaa', '보쌈', '2024-03-03', 2000),
('aaa', '라면', '2024-03-04', 1000),
('aaa', '치킨', '2024-03-05', 2500),
('aaa', '타코야끼', '2024-03-06', 2500),
('aaa', '냉면', '2024-03-07', 1500),
('aaa', '치킨', '2024-03-08', 2500),
('aaa', '족발', '2024-03-09', 3500),
('aaa', '치킨', '2024-03-10', 2500),
('aaa', '치킨', '2024-03-11', 2500),
('aaa', '보쌈', '2024-03-12', 2000),
('aaa', '치킨', '2024-03-13', 2500),
('aaa', '치킨', '2024-03-14', 2500),
('aaa', '햄버거', '2024-03-15', 1500),
('aaa', '치킨', '2024-03-16', 2500),
('aaa', '짬뽕', '2024-03-17', 1500),
('aaa', '치킨', '2024-03-18', 2500),
('aaa', '피자', '2024-03-19', 1500),
('aaa', '치킨', '2024-03-20', 2500),
('aaa', '치킨', '2024-03-21', 2500),
('aaa', '와플', '2024-03-22', 2500),
('aaa', '치킨', '2024-03-23', 1000),
('aaa', '치킨', '2024-03-24', 2500),
('aaa', '김치찜', '2024-03-25', 2500),
('aaa', '치킨', '2024-03-26', 2500),
('aaa', '등갈비', '2024-03-27', 2000),
('aaa', '치킨', '2024-03-28', 2500),
('aaa', '마라탕', '2024-03-29', 1500),
('aaa', '치킨', '2024-03-30', 2500),
('aaa', '돈까스', '2024-03-31', 2500);
INSERT INTO exercise (ename, ecalories)
VALUES ('걷기(가볍게)', 3);
INSERT INTO exercise (ename, ecalories)
VALUES ('걷기(격렬하게)', 5);
INSERT INTO exercise (ename, ecalories)
VALUES ('달리기(가볍게)', 8);
INSERT INTO exercise (ename, ecalories)
VALUES ('달리기(격렬하게)', 10.5);
INSERT INTO exercise (ename, ecalories)
VALUES ('계단오르기', 5.8);
INSERT INTO exercise (ename, ecalories)
VALUES ('등산', 3.26);
INSERT INTO exercise (ename, ecalories)
VALUES ('맨손체조', 7);
INSERT INTO exercise (ename, ecalories)
VALUES ('수상스키', 3.33);
INSERT INTO exercise (ename, ecalories)
VALUES ('수영', 17.4);
INSERT INTO exercise (ename, ecalories)
VALUES ('스케이트', 8);
INSERT INTO exercise (ename, ecalories)
VALUES ('스키', 7);
INSERT INTO exercise (ename, ecalories)
VALUES ('스트레칭', 2.5);
INSERT INTO exercise (ename, ecalories)
VALUES ('에어로빅', 5.2);
INSERT INTO exercise (ename, ecalories)
VALUES ('요가', 2.5);
INSERT INTO exercise (ename, ecalories)
VALUES ('윗몸일으키기', 2);
INSERT INTO exercise (ename, ecalories)
VALUES ('자전거타기(가볍게)', 3.4);
INSERT INTO exercise (ename, ecalories)
VALUES ('자전거타기(격렬하게)', 4.4);
INSERT INTO exercise (ename, ecalories)
VALUES ('줄넘기', 8.9);
INSERT INTO exercise (ename, ecalories)
VALUES ('팔굽혀펴기', 4.2);
INSERT INTO exercise (ename, ecalories)
VALUES ('볼링', 3.3);
INSERT INTO exercise (ename, ecalories)
VALUES ('야구', 6);
INSERT INTO exercise (ename, ecalories)
VALUES ('축구', 9);
INSERT INTO exercise (ename, ecalories)
VALUES ('탁구', 6);
INSERT INTO exercise (ename, ecalories)
VALUES ('농구', 8);
INSERT INTO exercise (ename, ecalories)
VALUES ('배구', 7);
INSERT INTO exercise (ename, ecalories)
VALUES ('배드민턴', 5.76);
INSERT INTO exercise (ename, ecalories)
VALUES ('테니스', 7.2);
INSERT INTO exercise (ename, ecalories)
VALUES ('목욕', 1.4);
INSERT INTO exercise (ename, ecalories)
VALUES ('손빨래', 3.16);
INSERT INTO exercise (ename, ecalories)
VALUES ('요리', 1.13);
INSERT INTO exercise (ename, ecalories)
VALUES ('청소하기', 1.15);
INSERT INTO record (ename, id, emin, rdatetime)
VALUES
('달리기(격렬하게)', 'aaa', '60', '2024-03-01'),
('달리기(가볍게)', 'aaa', '30', '2024-03-02'),
('걷기(가볍게)', 'aaa', '50', '2024-03-03'),
('걷기(격렬하게)', 'aaa', '30', '2024-03-04'),
('농구', 'aaa', '40', '2024-03-05'),
('목욕', 'aaa', '30', '2024-03-06'),
('등산', 'aaa', '10', '2024-03-07'),
('만월전철타기', 'aaa', '20', '2024-03-08'),
('맨손체조', 'aaa', '30', '2024-03-09'),
('배구', 'aaa', '80', '2024-03-10'),
('볼링', 'aaa', '30', '2024-03-11'),
('손빨래', 'aaa', '30', '2024-03-12'),
('수상스키', 'aaa', '90', '2024-03-13'),
('수영', 'aaa', '30', '2024-03-14'),
('스키', 'aaa', '10', '2024-03-15'),
('팔굽혀펴기', 'aaa', '30', '2024-03-16'),
('요가', 'aaa', '90', '2024-03-17'),
('웃기', 'aaa', '30', '2024-03-18'),
('윗몸일으키기', 'aaa', '50', '2024-03-19'),
('정원손질', 'aaa', '40', '2024-03-20'),
('줄넘기', 'aaa', '30', '2024-03-21'),
('축구', 'aaa', '40', '2024-03-22'),
('탁구', 'aaa', '30', '2024-03-23'),
('테니스', 'aaa', '30', '2024-03-24'),
('줄넘기', 'aaa', '60', '2024-03-25'),
('야구', 'aaa', '50', '2024-03-26'),
('자전기타기(가볍게)', 'aaa', '30', '2024-03-27'),
('수영', 'aaa', '70', '2024-03-28'),
('맨손체조', 'aaa', '60', '2024-03-29'),
('달리기(가볍게)', 'aaa', '50', '2024-03-30'),
('등산', 'aaa', '25', '2024-03-31');