--DROP TABLE IF EXISTS T_USERS;
CREATE TABLE t_users
     ( user_id INT PRIMARY KEY
     , name VARCHAR(50)
     , date_added DATE
     , description VARCHAR(255)
     )
;

--DROP TABLE IF EXISTS T_ENTRIES;
CREATE TABLE t_entries
     ( entry_id INT PRIMARY KEY
     , user_id INT
     , val DOUBLE
     , date_added DATE
     , description  VARCHAR(255)
     , FOREIGN KEY (user_id) REFERENCES t_users(user_id)
     )
;

COMMIT;