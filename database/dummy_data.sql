USE mydb;

INSERT INTO `users` VALUES
(1,'migros_talas@mail.com','123456','Migros Talas','MARKET'),
(2,'a101_anayurt@mail.com','123456','A101 Anayurt','MARKET'),
(3,'kizilay_kayseri@mail.com','123456','Kızılay Kayseri','NGO'),
(4,'ahbap_kayseri@mail.com','123456','Ahbap Kayseri','NGO'),
(5,'bim_ildem@mail.com','123456','BİM İldem Şubesi','MARKET'),
(6,'sok_emniyet@mail.com','123456','Şok Emniyet Müdürlüğü Yanı','MARKET'),
(7,'carrefour_alpaslan@mail.com','123456','CarrefourSA Alpaslan','MARKET'),
(8,'kayseri_asevi@mail.com','123456','Kayseri Büyükşehir Aşevi','NGO'),
(9,'hayrat_kayseri@mail.com','123456','Hayrat Yardım Kayseri','NGO'),
(10,'iyilikder_kayseri@mail.com','123456','İyilikder Kayseri Temsilciliği','NGO');

INSERT INTO `categories` VALUES
(1,'Fırın Ürünleri'),
(2,'Manav'),
(3,'Süt ve Şarküteri'),
(4,'Konserve');

INSERT INTO `addresses` VALUES
(1,'Kayseri','Talas','Yenidoğan Mah. No:1',1),
(2,'Kayseri','Melikgazi','Cumhuriyet Meydanı No:5',3),
(3,'Kayseri','Melikgazi','İldem Cumhuriyet Mah. No:34',5),
(4,'Kayseri','Kocasinan','Zümrüt Mah. Emniyet Cad.',6),
(5,'Kayseri','Melikgazi','Alpaslan Mah. Kızılırmak Cad.',7),
(6,'Kayseri','Kocasinan','Fevzi Çakmak Mah. No:12',8);

INSERT INTO `products` VALUES
(1,'Tam Buğday Ekmeği',15,'2026-04-10 18:00:00','ACTIVE',1,1),
(2,'Salkım Domates',10,'2026-04-08 12:00:00','ACTIVE',1,2),
(3,'Susamlı Simit',40,'2026-04-06 10:00:00','ACTIVE',1,1),
(4,'Roll Ekmek',100,'2026-04-07 20:00:00','ACTIVE',1,1),
(5,'Yerli Muz',25,'2026-04-08 18:00:00','ACTIVE',5,2),
(6,'Granny Smith Elma',50,'2026-04-12 15:00:00','ACTIVE',5,2),
(7,'Yarım Yağlı Süt 1L',12,'2026-04-09 23:59:59','ACTIVE',6,3),
(8,'Süzme Yoğurt 900g',8,'2026-04-07 12:00:00','ACTIVE',6,3),
(9,'Bezelye Konservesi',30,'2026-05-20 00:00:00','ACTIVE',7,4),
(10,'Haşlanmış Nohut',20,'2026-06-15 00:00:00','ACTIVE',7,4),
(11,'Çavdar Ekmeği',10,'2026-04-06 19:00:00','ACTIVE',2,1),
(12,'Köy Tipi Tereyağı',5,'2026-04-10 09:00:00','ACTIVE',2,3);

-- claims tablosunda şu an veri yok