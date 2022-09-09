INSERT INTO `tb_user` (`id`, `username`, `password`, `no_looked_flag`, `no_expired_flag`, `credential_no_expired`, `enabled`) VALUES (1,'XJH','$2a$10$rnfqWtZwDlmalPeuFVi1lennMqiPAkFuNUask2YxB.MmuR4MmZRnq',1,1,1,1);
INSERT INTO `tb_user` (`id`, `username`, `password`, `no_looked_flag`, `no_expired_flag`, `credential_no_expired`, `enabled`) VALUES (2,'KCY','$2a$10$vCvt2Nb0t6gdPC/Y9jKbd./osDnlDbc5/30Ws5CQVRdLJsv0GEH.a',1,1,1,1);
INSERT INTO `tb_user` (`id`, `username`, `password`, `no_looked_flag`, `no_expired_flag`, `credential_no_expired`, `enabled`) VALUES (3,'ZYF','$2a$10$JHQV.FbKGxj900Njk1MyeOyaXBdgerfIXZnWjprzuoG/PDEKlfpqq',1,1,1,1);
INSERT INTO `tb_user` (`id`, `username`, `password`, `no_looked_flag`, `no_expired_flag`, `credential_no_expired`, `enabled`) VALUES (4,'WXY','$2a$10$EndFKjjhphduK1Fp4S91U.nGp.W6BVnfztH6/WlkwvO306MECNda2',1,1,1,1);

INSERT INTO `tb_role` (`id`, `role_name`, `role_desc`) VALUES (1,'user','用户角色');
INSERT INTO `tb_role` (`id`, `role_name`, `role_desc`) VALUES (2,'admin','管理员角色');
INSERT INTO `tb_role` (`id`, `role_name`, `role_desc`) VALUES (3,'guest','游客');

INSERT INTO `tb_permission` (`id`, `permission_name`, `permission_desc`) VALUES (1,'add','添加权限');
INSERT INTO `tb_permission` (`id`, `permission_name`, `permission_desc`) VALUES (2,'query','查询权限');
INSERT INTO `tb_permission` (`id`, `permission_name`, `permission_desc`) VALUES (3,'update','修改权限');
INSERT INTO `tb_permission` (`id`, `permission_name`, `permission_desc`) VALUES (4,'delete','删除权限');

INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`) VALUES (1,1,2);
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`) VALUES (2,2,1);
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`) VALUES (3,3,3);
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`) VALUES (4,4,3);

INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`) VALUES (1,1,1);
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`) VALUES (2,1,2);
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`) VALUES (3,2,1);
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`) VALUES (4,2,2);
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`) VALUES (5,2,3);
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`) VALUES (6,2,4);
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`) VALUES (7,3,2);