CREATE TABLE doctors (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL UNIQUE,

    phone VARCHAR(15) NOT NULL,

    document VARCHAR(6) NOT NULL UNIQUE,

    specialty VARCHAR(50) NOT NULL,

    state VARCHAR(100),

    city VARCHAR(100),

    street VARCHAR(150),

    number VARCHAR(20),

    complement VARCHAR(150)

);