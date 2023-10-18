# JavaSE282638
Library Project
JavaSE Class code 282638

Requirement : 
    - Please run mft.model.da.db.sql
    - Add mft.lib to project libraries

To Do :
    DataAccess Layer (save, edit, remove, findAll, findById)


Tasks : 

    ArbabZedeh :     BorrowDa

    Zargar :         MemberDa

    Ghahremani :     BookDa

    Chakame :        UserDa


Create New User in DB :

alter session set "_oracle_script" = true;

create user javase identified by java123;

grant dba to javase;

connect javase/java123;