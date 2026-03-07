CREATE TABLE patients (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL UNIQUE,

    phone VARCHAR(20) NOT NULL,

    document VARCHAR(10) NOT NULL UNIQUE,

    birth_date DATE NOT NULL,

    state VARCHAR(100) NOT NULL,

    city VARCHAR(100) NOT NULL,

    street VARCHAR(150) NOT NULL,

    number VARCHAR(20) NOT NULL,

    complement VARCHAR(150)

);

CREATE UNIQUE INDEX uk_patients_email ON patients(email);
CREATE UNIQUE INDEX uk_patients_document ON patients(document);
