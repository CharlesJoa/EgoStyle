drop table if exists list_qrcode;
drop table if exists list_coupon;


CREATE TABLE list_qrcode (
    id_qrcode INTEGER NOT NULL AUTO_INCREMENT,
    phrase_code varchar(255) NOT NULL,
      code_promotion varchar(255),
    id_coupon_fk INTEGER,
    PRIMARY KEY (id_qrcode)
);

CREATE TABLE list_coupon (
    id_coupon INTEGER NOT NULL AUTO_INCREMENT,
    description varchar(255) NOT NULL,
    promotion INTEGER NOT NULL,
    date_limite DATE NOT NULL,
    PRIMARY KEY (id_coupon)
);


ALTER TABLE list_qrcode ENGINE = InnoDB;
alter table list_coupon ENGINE = InnoDB;

ALTER TABLE list_qrcode ADD CONSTRAINT list_qrcode_fk0 FOREIGN KEY (id_coupon_fk) REFERENCES list_coupon(id_coupon);




INSERT INTO list_coupon (description , promotion, date_limite)
 VALUES
 ("-20% sur votre jean préféré", 20, "2020-06-15"),
 ("-40% sur toutes les vestes" , 40 , "2020-06-17"),
 ("10% de réduction pour 2 articles achetés" , 10 , "2020-07-23"),
 ("-30% sur les t-shirts bleus " , 30 , "2020-06-15"),
 ("-20% sur les ensembles ", 20 , "2020-06-15"),
  ("-25% pour les étudiants" , 25 , "2020-07-31");
