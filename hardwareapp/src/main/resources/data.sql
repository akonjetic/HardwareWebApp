delete from review;
delete from hardware;
delete from  user_authority;
delete from authority;
delete from user;

insert into hardware (product_name, code, price, product_type, available_num)
values ('SAPPHIRE PULSE Radeon RX 6600 Gaming', '051800940', 3999.00, 'GPU', 10);

insert into hardware (product_name, code, price, product_type, available_num)
values ('PC-28800, 16 GB, G.SKILL Trident Z Royal Elite', '051200472', 1399.00, 'RAM', 5);

insert into hardware (product_name, code, price, product_type, available_num)
values ('Ryzen 3600X', '051803819', 2550.00, 'CPU', 7);



insert into review (title, text, rating, code_hardware)
values ( 'Not too bad', 'This hardware gets the job done, but it just looks ugly.', 3, '051803819' );
insert into review (title, text, rating, code_hardware)
values ( 'Amazing!!', 'Wow, everything looks and works great, I love it!!!', 5, '051200472' );
insert into review (title, text, rating, code_hardware)
values ( 'Terrible', 'This is the 4th time I am asking for a refund, it just stops working after 2 weeks.', 1, '051800940' );

insert into user(id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'), -- password = admin
    (3, 'creator', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG');


insert into authority (id, authority_name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_CREATOR');
insert into user_authority (user_id, authority_id)
values
    (1, 2),
    (2, 1),
    (3, 3);





