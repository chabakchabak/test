

-- 기존 시퀀스 삭제
DROP SEQUENCE seq_board;

-- 시퀀스 재생성
CREATE SEQUENCE seq_board
    START WITH 1          -- 시작 값
    INCREMENT BY 1        -- 증가 값
    NOCACHE               -- 시퀀스 캐싱 안함
    NOCYCLE;              -- 최대값 도달 시 다시 시작하지 않음



    
DECLARE
    v_userid VARCHAR2(50 BYTE);
    v_nickname VARCHAR2(50 BYTE);
    TYPE nickname_array IS VARRAY(10) OF VARCHAR2(50 BYTE);
    TYPE userid_array IS VARRAY(10) OF VARCHAR2(50 BYTE);
    v_nicknames nickname_array := nickname_array('토끼', '사자', '호랑이', '곰', '여우', '늑대', '고양이', '강아지', '코끼리', '기린');
    v_userids userid_array := userid_array('rabbit', 'lion', 'tiger', 'bear', 'fox', 'wolf', 'cat', 'dog', 'elephant', 'giraffe');
    v_index PLS_INTEGER;
    v_boardtypeno NUMBER;
    v_category VARCHAR2(20);
BEGIN
    FOR i IN 1..505 LOOP
        v_index := TRUNC(DBMS_RANDOM.VALUE(1, 11)); -- 1부터 10까지의 정수 생성
        v_userid := v_userids(v_index);
        v_nickname := v_nicknames(v_index);
        v_boardtypeno := TRUNC(DBMS_RANDOM.VALUE(1, 13)); -- 1부터 12까지의 정수 생성

        -- 카테고리 결정
        CASE MOD(i-1, 5) + 1
            WHEN 1 THEN v_category := '음식';
            WHEN 2 THEN v_category := '자동차';
            WHEN 3 THEN v_category := '장소';
            WHEN 4 THEN v_category := '핫딜';
            WHEN 5 THEN v_category := '행사';
        END CASE;

        INSERT INTO TBL_BOARD (
            BOARDNO, TITLE, CONTENT, USERID, NICKNAME, BOARDTYPENO, REGDATE, UPDATEDATE, VIEWS, LIKES, REPLYCOUNT
        ) VALUES (
            seq_board.NEXTVAL,
            '[' || v_category || '] 글제목-' || i,
            '글내용-' || i,
            v_userid,
            v_nickname,
            v_boardtypeno,
            SYSDATE,
            SYSDATE,
            0,
            0,
            0
        );
    END LOOP;
END;
/

