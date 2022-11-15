Delete from supplier;
DELETE from master_brand;
DELETE from supplier_brands_mapping;

INSERT INTO master_brand (id, name) VALUES (1,'Toyota'), (2,'Mazda');
INSERT INTO supplier values (1, 'IP Vector');
INSERT INTO supplier_brands_mapping values (1,1,'Toyota-Motors');