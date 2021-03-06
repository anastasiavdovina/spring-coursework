CREATE TABLE Groups (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    NAME VARCHAR
);

CREATE TABLE Subjects (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    NAME VARCHAR
);

CREATE TABLE People (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    FIRST_NAME VARCHAR,
    LAST_NAME VARCHAR,
    FATHER_NAME VARCHAR,
    GROUP_ID NUMERIC,
    TYPE VARCHAR,
    FOREIGN KEY(GROUP_ID) REFERENCES Groups(ID)
);

CREATE TABLE Marks (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    STUDENT_ID NUMERIC,
    SUBJECT_ID NUMERIC,
    TEACHER_ID NUMERIC,
    VALUE NUMERIC,
    FOREIGN KEY(TEACHER_ID) REFERENCES People(ID),
    FOREIGN KEY(STUDENT_ID) REFERENCES People(ID),
    FOREIGN KEY(SUBJECT_ID) REFERENCES Subjects(ID)
);
