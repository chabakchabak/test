create table tbl_board_type(
    bt_no number(10) primary key,
    bt_name varchar2(40)
    );

create table tbl_user(
    u_no number(10) primary key,
    u_id varchar2(20) not null,
    u_pw varchar2(20) not null,
    u_name varchar2(20) not null,
    nickname varchar2(20) not null,
    email varchar2(30),
    regDate date default sysdate
    );
    
CREATE TABLE tbl_board (
    b_no NUMBER(10) PRIMARY KEY,
    title VARCHAR2(30) NOT NULL,
    content VARCHAR2(2000) NOT NULL,
    writer NUMBER(10) NOT NULL,
    b_type NUMBER(10) NOT NULL,
    regDate DATE DEFAULT SYSDATE,
    updatedate DATE DEFAULT SYSDATE,
    views NUMBER(10) DEFAULT 0, -- 조회수 컬럼 추가, 기본값 0
    CONSTRAINT fk_b_type FOREIGN KEY (b_type) REFERENCES tbl_board_type (bt_no),
    CONSTRAINT fk_writer FOREIGN KEY (writer) REFERENCES tbl_user (u_no)
);



create sequence seq_board;
create sequence seq_board_type;
create sequence seq_user;


SELECT b.b_no, b.title, b.content, u.u_id writer, b.regdate, t.bt_name type
FROM tbl_board b
JOIN tbl_user u ON b.writer = u.u_no
JOIN (SELECT * FROM tbl_board_type WHERE bt_name = '자유게시판') t ON b.b_type = t.bt_no
WHERE b.b_type = t.bt_no;


select * from tbl_board;



