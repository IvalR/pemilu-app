insert into public.roles ( id, role_name, status, created_date, updated_date, created_by, updated_by) values( 1 ,'admin', 1, now(), now(), null, null);

insert into public.user ( id, full_name, email, pob, dob, role_id, password, status, created_date, updated_date, created_by, updated_by) values( 1 ,'Ival Racha Prtaama','ival@gmail.com','Depok',now(), 1, 'qwerty1234', 1, now(), now(), 1, 1);


