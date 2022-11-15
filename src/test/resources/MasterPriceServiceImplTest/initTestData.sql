DELETE from supplier_brands_mapping;
DELETE from master_price_row;
DELETE from master_brand;
DELETE from master_price;
Delete from supplier_price;
Delete from supplier;

INSERT INTO supplier values (1, 'IP Vector');

insert into supplier_price (id, logo, amount_column, brand_column, file_name, file_type,
                            first_row, multiplicity_column, name_column, part_number_column,
                            price_column, source, supplier_id)
VALUES (1,'aaaa', 1,2,'somename','.sometype',1,1,1,1,1,'someSource', 1);

INSERT INTO master_brand (id, name) VALUES (1, 'ACDELCO'),(2, 'AEROQUIP'), (3, 'AGAMA');

insert into supplier_brands_mapping (supplier_id, master_brand_id, supplier_option) VALUES (1,1,'ACDELCO'), (1, 2, 'AEROQUIP'), (1, 3,'AGAMA');