-- �������ݿ�
CREATE DATABASE IF NOT EXISTS seckill DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
USE seckill;

-- ���� user ��
CREATE TABLE IF NOT EXISTS user (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    username VARCHAR(50) NOT NULL COMMENT '�û���',
    password VARCHAR(100) NOT NULL COMMENT '����',
    email VARCHAR(100) NOT NULL COMMENT '����',
    nickname VARCHAR(50) COMMENT '�ǳ�',
    avatar VARCHAR(200) COMMENT 'ͷ��',
    role TINYINT DEFAULT 0 COMMENT '��ɫ',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username),
    UNIQUE KEY uk_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='�û���';

-- ���� user_address ��
CREATE TABLE IF NOT EXISTS user_address (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    user_id BIGINT NOT NULL COMMENT '�û�ID',
    receiver_name VARCHAR(50) NOT NULL COMMENT '�ջ�������',
    receiver_phone VARCHAR(20) NOT NULL COMMENT '�ջ��˵绰',
    receiver_region VARCHAR(100) NOT NULL COMMENT '�ջ�����',
    receiver_address VARCHAR(200) NOT NULL COMMENT '�ջ���ַ',
    is_default BOOLEAN DEFAULT FALSE COMMENT '�Ƿ�Ĭ�ϵ�ַ',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='�û���ַ��';

-- ���� category ��
CREATE TABLE IF NOT EXISTS category (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    name VARCHAR(100) NOT NULL COMMENT '�������',
    parent_id BIGINT COMMENT '�����ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
    PRIMARY KEY (id),
    FOREIGN KEY (parent_id) REFERENCES category(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='��Ʒ����';

-- ���� product ��
CREATE TABLE IF NOT EXISTS product (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    name VARCHAR(100) NOT NULL COMMENT '��Ʒ����',
    description TEXT COMMENT '��Ʒ����',
    price DECIMAL(10,2) NOT NULL COMMENT '��Ʒ�۸�',
    stock INT NOT NULL COMMENT '���',
    category_id BIGINT NOT NULL COMMENT '��Ʒ���ID',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id),
    INDEX idx_product_category_id (category_id),
    INDEX idx_product_name (name),
    CONSTRAINT chk_price CHECK (price >= 0),
    CONSTRAINT chk_stock CHECK (stock >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='��Ʒ��';

-- ���� product_image ��
CREATE TABLE IF NOT EXISTS product_image (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    product_id BIGINT NOT NULL COMMENT '��ƷID',
    image_url VARCHAR(200) NOT NULL COMMENT 'ͼƬURL',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='��ƷͼƬ��';

-- ���� seckill_activity ��
CREATE TABLE IF NOT EXISTS seckill_activity (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    name VARCHAR(100) NOT NULL COMMENT '�����',
    product_id BIGINT NOT NULL COMMENT '��ƷID',
    start_time DATETIME NOT NULL COMMENT '��ʼʱ��',
    end_time DATETIME NOT NULL COMMENT '����ʱ��',
    stock INT NOT NULL COMMENT '���',
    status TINYINT DEFAULT 0 COMMENT '�״̬',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    INDEX idx_seckill_activity_time (start_time, end_time),
    CONSTRAINT chk_stock CHECK (stock >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='��ɱ���';

-- ���� cart ��
CREATE TABLE IF NOT EXISTS cart (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    user_id BIGINT NOT NULL COMMENT '�û�ID',
    product_id BIGINT NOT NULL COMMENT '��ƷID',
    quantity INT NOT NULL COMMENT '����',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='���ﳵ��';

-- ���� order ��
CREATE TABLE IF NOT EXISTS `order` (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    user_id BIGINT NOT NULL COMMENT '�û�ID',
    product_id BIGINT NOT NULL COMMENT '��ƷID',
    quantity INT NOT NULL COMMENT '����',
    total_price DECIMAL(10,2) NOT NULL COMMENT '�����ܼ�',
    status ENUM('pending', 'paid', 'cancelled') NOT NULL DEFAULT 'pending' COMMENT '����״̬',
    address_id BIGINT NOT NULL COMMENT '�û���ַID',
    product_name VARCHAR(100) NOT NULL COMMENT '��Ʒ����',
    product_price DECIMAL(10,2) NOT NULL COMMENT '��Ʒ�۸�',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (address_id) REFERENCES user_address(id),
    INDEX idx_order_user_status (user_id, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='������';

-- ���� seckill_order ��
CREATE TABLE IF NOT EXISTS seckill_order (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '����ID',
    user_id BIGINT NOT NULL COMMENT '�û�ID',
    seckill_activity_id BIGINT NOT NULL COMMENT '��ɱ�ID',
    address_id BIGINT NOT NULL COMMENT '�û���ַID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
    total_price DECIMAL(10,2) NOT NULL COMMENT '�����ܼ�',
    status ENUM('pending', 'paid', 'cancelled') NOT NULL DEFAULT 'pending' COMMENT '����״̬',
    pay_expire_time DATETIME NOT NULL COMMENT '֧����ֹʱ��',
    product_name VARCHAR(100) NOT NULL COMMENT '��Ʒ����',
    product_price DECIMAL(10,2) NOT NULL COMMENT '��Ʒ�۸�',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (seckill_activity_id) REFERENCES seckill_activity(id),
    FOREIGN KEY (address_id) REFERENCES user_address(id),
    INDEX idx_seckill_order_user_status (user_id, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='��ɱ������';