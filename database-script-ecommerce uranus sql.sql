---creating table admin

CREATE TABLE admins(admin_id varchar2(20) constraint pk_admin_id primary key, email varchar2(50) unique , password varchar2(50) not null          
);
CREATE SEQUENCE admin_id_seq;

CREATE OR REPLACE TRIGGER adm_tgr
    BEFORE INSERT
    ON admins
    REFERENCING NEW AS NEW OLD AS OLD
    FOR EACH ROW
BEGIN
    IF :NEW.admin_id IS NULL THEN
        SELECT 'A'||TO_CHAR(admin_id_seq.NEXTVAL,'0000000') INTO :NEW.admin_id FROM DUAL;
    END IF;
END;
----------------------------------------------------------------------------------

---creating categories table

CREATE TABLE categories(category_id varchar2(20) constraint pk_category_id primary key, category_name varchar2(50))
CREATE SEQUENCE category_id_seq;

CREATE OR REPLACE TRIGGER cat_tgr
    BEFORE INSERT
    ON categories
    REFERENCING NEW AS NEW OLD AS OLD
    FOR EACH ROW
BEGIN
    IF :NEW.category_id IS NULL THEN
        SELECT 'CAT'||TO_CHAR(category_id_seq.NEXTVAL,'0000000') INTO :NEW.category_id FROM DUAL;
    END IF;
END;


---------------------------------------------------------------------------------

--- creating product table

CREATE TABLE products(product_id varchar2(20) constraint pk_product_id primary key , product_name varchar2(50), category_id varchar(20) not null , product_price number(12,2), product_image varchar2(20), product_available_qty varchar2(50),
                        constraint fk_category foreign key (category_id) references categories(category_id)
);

CREATE SEQUENCE product_id_seq;

CREATE OR REPLACE TRIGGER prod_tgr
    BEFORE INSERT
    ON products
    REFERENCING NEW AS NEW OLD AS OLD
    FOR EACH ROW
BEGIN
    IF :NEW.product_id IS NULL THEN
        SELECT 'P'||TO_CHAR(product_id_seq.NEXTVAL,'0000000') INTO :NEW.product_id FROM DUAL;
    END IF;
END;
------------------------------------------------------

-- creating customer table
CREATE TABLE customers(customer_id varchar2(20) constraint pk_customer_id primary key , customer_name varchar2(50), email varchar(50) unique, password varchar2(50) not null, phone_number varchar2(20), address varchar2(50), date_of_registration date default sysdate                         
);
CREATE SEQUENCE customers_id_seq;

CREATE OR REPLACE TRIGGER cust_tgr
    BEFORE INSERT
    ON customers
    REFERENCING NEW AS NEW OLD AS OLD
    FOR EACH ROW
BEGIN
    IF :NEW.customer_id IS NULL THEN
        SELECT 'C'||TO_CHAR(customers_id_seq.NEXTVAL,'0000000') INTO :NEW.customer_id FROM DUAL;
    END IF;
END;
--------------------------------------------
---- creating coupon table

CREATE TABLE coupons(coupon_id varchar2(20) constraint pk_coupon_id primary key , coupon_name varchar2(50), discount_val number(20) , expiry_date date
);

CREATE SEQUENCE coupon_id_seq;

CREATE OR REPLACE TRIGGER coup_tgr
    BEFORE INSERT
    ON coupons
    REFERENCING NEW AS NEW OLD AS OLD
    FOR EACH ROW
BEGIN
    IF :NEW.coupon_id IS NULL THEN
        SELECT 'CO'||TO_CHAR(coupon_id_seq.NEXTVAL,'0000000') INTO :NEW.coupon_id FROM DUAL;
    END IF;
END;
------------------------------------

---- creating cart table
CREATE TABLE carts(cart_id varchar2(20) constraint pk_cart_id primary key , customer_id varchar2(20) not null,
                  constraint fk_cust_id_cart foreign key (customer_id) references customers(customer_id)
);

CREATE SEQUENCE cart_id_seq;

CREATE OR REPLACE TRIGGER cart_tgr
    BEFORE INSERT
    ON carts
    REFERENCING NEW AS NEW OLD AS OLD
    FOR EACH ROW
BEGIN
    IF :NEW.cart_id IS NULL THEN
        SELECT 'CA'||TO_CHAR(cart_id_seq.NEXTVAL,'0000000') INTO :NEW.cart_id FROM DUAL;
    END IF;
END;
-----------------------------------------

---- creating cart item table
CREATE TABLE cart_items(cart_item_id varchar2(20) constraint pk_cart_item_id primary key ,cart_id varchar2(20) not null, customer_id varchar2(20) not null, product_id varchar2(20) not null, product_qty number(20),
                  constraint fk_cart_to_cart_items foreign key (cart_id) references carts(cart_id), constraint fk_cust_to_cart_items foreign key (customer_id) references customers(customer_id), constraint fk_prod_to_cart_items foreign key (product_id) references products(product_id)
);

CREATE SEQUENCE cart_items_id_seq;

CREATE OR REPLACE TRIGGER cart_items_tgr
    BEFORE INSERT
    ON cart_items
    REFERENCING NEW AS NEW OLD AS OLD
    FOR EACH ROW
BEGIN
    IF :NEW.cart_item_id IS NULL THEN
        SELECT 'CI'||TO_CHAR(cart_items_id_seq.NEXTVAL,'0000000') INTO :NEW.cart_item_id FROM DUAL;
    END IF;
END;
---------------------------------------------------

---- creating order details
CREATE TABLE orders(order_id varchar2(20) constraint pk_order_id primary key ,cart_id varchar2(20) not null, customer_id varchar2(20) not null, order_date date DEFAULT sysdate, delivery_date date default sysdate + 7, coupon_id varchar2(20) not null, bill_amount number(12,2), payment_method varchar2(2),
                  constraint fk_cart_to_order foreign key (cart_id) references carts(cart_id), constraint fk_cust_to_order foreign key (customer_id) references customers(customer_id), constraint fk_coupon_order foreign key (coupon_id) references coupons(coupon_id), constraint ck_pay_meth check(payment_method in ('COD', 'CREDIT', 'DEBIT', 'E-WALLET'))
);

CREATE SEQUENCE orders_id_seq;

CREATE OR REPLACE TRIGGER order_tgr
    BEFORE INSERT
    ON orders
    REFERENCING NEW AS NEW OLD AS OLD
    FOR EACH ROW
BEGIN
    IF :NEW.order_id IS NULL THEN
        SELECT 'O'||TO_CHAR(orders_id_seq.NEXTVAL,'0000000') INTO :NEW.order_id FROM DUAL;
    END IF;
END;