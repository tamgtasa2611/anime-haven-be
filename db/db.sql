-- Roles Table
CREATE TABLE IF NOT EXISTS Roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    date_of_birth DATE,
    gender CHAR(1),
    address TEXT,
    avatar TEXT,
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES Roles(id)
);

-- Brands Table
CREATE TABLE IF NOT EXISTS Brands (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Categories Table
CREATE TABLE IF NOT EXISTS Categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Payment Methods Table
CREATE TABLE IF NOT EXISTS Payment_methods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Products Table
CREATE TABLE IF NOT EXISTS Products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(6, 2) NOT NULL,
    stock INT,
    brand_id INT,
    category_id INT,
    FOREIGN KEY (brand_id) REFERENCES Brands(id),
    FOREIGN KEY (category_id) REFERENCES Categories(id)
);

-- Carts Table
CREATE TABLE IF NOT EXISTS Carts (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    total_price DECIMAL(6, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Users(id)
);

-- Cart Items Table
CREATE TABLE IF NOT EXISTS Cart_items (
    id SERIAL PRIMARY KEY,
    cart_id INT,
    product_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES Carts(id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- Orders Table
CREATE TABLE IF NOT EXISTS Orders (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    status INT NOT NULL,
    customer_id INT NOT NULL,
    total_price DECIMAL(6, 2) NOT NULL,
    employee_id INT,
    receiver_name VARCHAR(255) NOT NULL,
    receiver_phone VARCHAR(20) NOT NULL,
    receiver_address TEXT NOT NULL,
    method_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Users(id),
    FOREIGN KEY (employee_id) REFERENCES Users(id),
    FOREIGN KEY (method_id) REFERENCES Payment_methods(id)
);

-- Order Details Table
CREATE TABLE IF NOT EXISTS Order_details (
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    price DECIMAL(6, 2) NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES Orders(id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- Ratings Table
CREATE TABLE IF NOT EXISTS Ratings (
    id SERIAL PRIMARY KEY,
    product_id INT,
    customer_id INT,
    date TIMESTAMP NOT NULL,
    point INT NOT NULL,
    review TEXT,
    FOREIGN KEY (product_id) REFERENCES Products(id),
    FOREIGN KEY (customer_id) REFERENCES Users(id)
);

-- Product Attributes Table
CREATE TABLE IF NOT EXISTS Product_attributes (
    product_id INT,
    key VARCHAR(255) NOT NULL,
    value JSONB,
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- Product Images Table
CREATE TABLE IF NOT EXISTS Product_images (
    id SERIAL PRIMARY KEY,
    product_id INT,
    path TEXT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- Wish List Table
CREATE TABLE IF NOT EXISTS Wish_list (
    id SERIAL PRIMARY KEY,
    product_id INT,
    customer_id INT,
    FOREIGN KEY (product_id) REFERENCES Products(id),
    FOREIGN KEY (customer_id) REFERENCES Users(id)
);

INSERT INTO Roles(name) VALUES 
('Admin'),
('Employee'),
('Customer');
