
--   Tạo dữ liệu thể loại
insert into Category(CategoryName) values
('milktea'),
('tea'),
('coffee'),
('smoothie'),
('juice');

--   Tạo dữ liệu sản phẩm
insert into Product(ProductName, CategoryID, ImagePath) values
('Classic Milk Tea', 1, 'src/Assets/Images/Classic Milk Tea.png'),
('Chocolate Milk Tea', 1, 'src/Assets/Images/Chocolate Milk Tea.png'),
('Caramel Milk Tea', 1, 'src/Assets/Images/Caramel Milk Tea.png'),
('Jasmine Milk Tea', 1, 'src/Assets/Images/Jasmine Milk Tea.png'),
('Blueberry Milk Tea', 1, 'src/Assets/Images/Blueberry Milk Tea.png'),
('Earl Grey Tea', 1, 'src/Assets/Images/Earl Grey Tea.png'),
('Brown Sugar Boba Fresh Milk', 1, 'src/Assets/Images/Brown Sugar Boba Fresh Milk.png'),
('Brown Sugar Boba Matcha Milk', 1, 'src/Assets/Images/Brown Sugar Boba Matcha Milk.png'),
('Brown Sugar Boba Black Milk', 1, 'src/Assets/Images/Brown Sugar Boba Black Milk.png'),
('Brown Sugar Boba Strawberry Mix', 1, 'src/Assets/Images/Brown Sugar Boba Strawberry Mix.png'),
('Black Tea With Peach', 2, 'src/Assets/Images/Black Tea With Peach.png'),
('Lychee Tea', 2, 'src/Assets/Images/Lychee Tea.png'),
('Lotus Tea', 2, 'src/Assets/Images/Lotus Tea.png'),
('Jasmine Tea', 2, 'src/Assets/Images/Jasmine Tea.png'),
('Orange Cinnamon Peach Tea', 2, 'src/Assets/Images/Orange Cinnamon Peach Tea.png'),
('Passion Pomelo Tea', 2, 'src/Assets/Images/Passion Pomelo Tea.png'),
('Rose Tea', 2, 'src/Assets/Images/Rose Tea.png'),
('Strawberry-Oolong Tea', 2, 'src/Assets/Images/Strawberry-Oolong Tea.png'),
('Honey Daisy Tea', 2, 'src/Assets/Images/Honey Daisy Tea.png'),
('Green Tea', 2, 'src/Assets/Images/Green Tea.png'),
('Black Coffee', 3, 'src/Assets/Images/Black Coffee.png'),
('White Coffee', 3, 'src/Assets/Images/White Coffee.png'),
('Frappuccino', 4, 'src/Assets/Images/Frappuccino.png'),
('Cookies & Cream', 4, 'src/Assets/Images/Cookies & Cream.png'),
('Matcha Freeze', 4, 'src/Assets/Images/Matcha Freeze.png'),
('Chocolate Freeze', 4, 'src/Assets/Images/Chocolate Freeze.png'),
('Blueberry Smoothie', 4, 'src/Assets/Images/Blueberry Smoothie.png'),
('Avocado Smoothie', 4, 'src/Assets/Images/Avocado Smoothie.png'),
('Mango Smoothie', 4, 'src/Assets/Images/Mango Smoothie.png'),
('Strawberry Smoothie', 4, 'src/Assets/Images/Strawberry Smoothie.png'),
('Orange Juice', 5, 'src/Assets/Images/Orange Juice.png'),
('Pineapple Juice', 5, 'src/Assets/Images/Pineapple Juice.png'),
('Honey Lemon Juice', 5, 'src/Assets/Images/Honey Lemon Juice.png'),
('Plum Lemon Juice', 5, 'src/Assets/Images/Plum Lemon Juice.png'),
('Aloe Vera Kiwi Juice', 5, 'src/Assets/Images/Aloe Vera Kiwi Juice.png'),
('Aloe Vera Grape Juice', 5, 'src/Assets/Images/Aloe Vera Grape Juice.png');

-- Tạo dữ liệu giá sản phẩm theo size
insert into ProductPrice(ProductID, ProductSize, ProductPrice) values
(1, 'S', 25000.0),
(1, 'M', 30000.0),
(1, 'L', 35000.0),
(2, 'S', 25000.0),
(2, 'M', 30000.0),
(2, 'L', 35000.0),
(3, 'S', 25000.0),
(3, 'M', 30000.0),
(3, 'L', 35000.0),
(4, 'S', 25000.0),
(4, 'M', 30000.0),
(4, 'L', 35000.0),
(5, 'S', 25000.0),
(5, 'M', 30000.0),
(5, 'L', 35000.0),
(6, 'S', 25000.0),
(6, 'M', 30000.0),
(6, 'L', 35000.0),
(7, 'S', 40000.0),
(7, 'M', 45000.0),
(7, 'L', 50000.0),
(8, 'S', 40000.0),
(8, 'M', 45000.0),
(8, 'L', 50000.0),
(9, 'S', 40000.0),
(9, 'M', 45000.0),
(9, 'L', 50000.0),
(10, 'S', 40000.0),
(10, 'M', 45000.0),
(10, 'L', 50000.0),
(11, 'S', 30000.0),
(11, 'M', 35000.0),
(11, 'L', 40000.0),
(12, 'S', 30000.0),
(12, 'M', 35000.0),
(12, 'L', 40000.0),
(13, 'S', 30000.0),
(13, 'M', 35000.0),
(13, 'L', 40000.0),
(14, 'S', 30000.0),
(14, 'M', 35000.0),
(14, 'L', 40000.0),
(15, 'S', 30000.0),
(15, 'M', 35000.0),
(15, 'L', 40000.0),
(16, 'S', 30000.0),
(16, 'M', 35000.0),
(16, 'L', 40000.0),
(17, 'S', 30000.0),
(17, 'M', 35000.0),
(17, 'L', 40000.0),
(18, 'S', 30000.0),
(18, 'M', 35000.0),
(18, 'L', 40000.0),
(19, 'S', 30000.0),
(19, 'M', 35000.0),
(19, 'L', 40000.0),
(20, 'S', 30000.0),
(20, 'M', 35000.0),
(20, 'L', 40000.0),
(21, 'M', 40000.0),
(21, 'L', 45000.0),
(22, 'M', 40000.0),
(22, 'L', 45000.0),
(23, 'M', 40000.0),
(23, 'L', 45000.0),
(24, 'M', 40000.0),
(24, 'L', 45000.0),
(25, 'M', 40000.0),
(25, 'L', 45000.0),
(26, 'M', 40000.0),
(26, 'L', 45000.0),
(27, 'M', 40000.0),
(27, 'L', 45000.0),
(28, 'M', 40000.0),
(28, 'L', 45000.0),
(29, 'M', 40000.0),
(29, 'L', 45000.0),
(30, 'M', 40000.0),
(30, 'L', 45000.0),
(31, 'S', 30000.0),
(31, 'M', 35000.0),
(31, 'L', 40000.0),
(32, 'S', 30000.0),
(32, 'M', 35000.0),
(32, 'L', 40000.0),
(33, 'S', 30000.0),
(33, 'M', 35000.0),
(33, 'L', 40000.0),
(34, 'S', 30000.0),
(34, 'M', 35000.0),
(34, 'L', 40000.0),
(35, 'S', 30000.0),
(35, 'M', 35000.0),
(35, 'L', 40000.0),
(36, 'S', 30000.0),
(36, 'M', 35000.0),
(36, 'L', 40000.0)

--   Thêm dữ liệu nguyên liệu cho bảng Ingredients
-- insert into Ingredients(ingredientName, ingredientType, storage, Producer, price) values
-- (N'Cà phê hạt nguyên chất', N'Cà phê hạt', 100, N'Trung Nguyên Legend', 150000),
-- (N'Đường', N'Gia vị', 100, N'Dalat Farm', 12000),
-- (N'Sữa đặc nguyên chất', N'Sữa' , 100, N'Dalat Farm', 20000),
-- (N'Sữa tươi', N'Sữa' , 100, N'Dalat Farm', 12000),
-- (N'Trà lài khô', N'Trà', 100, N'Dalat Farm', 20000),
-- (N'Trà đào khô', N'Trà', 100, N'Dalat Farm', 20000),
-- (N'Trà hương vải khô', N'Trà', 100, N'Dalat Farm', 20000),
-- (N'Trà xanh matcha khô', N'Trà', 100, N'Dalat Farm', 22000)

INSERT INTO WorkType(WorkTypeName) VALUES ('Parttime'), ('Fulltime');
INSERT INTO WorkPosition(WorkPositionName, WorkPositionLVL) VALUES 
('Cashier', '1'),
('Barista', '1'),
('Manager', '2'),
('ShiftLeader', '1');

INSERT INTO PositionType VALUES
(1,1,20000),
(1,2,30000),
(2,1,25000),
(2,2,35000),
(3,2,50000);

insert into Employee(EmployeeName, EmployeePhone, WorkPositionID, WorkTypeID) values
(N'Nguyen Thi Thanh Huyen', '0909090909', 3, 2);

insert into Account(AccountUsername, AccountPassword, EmployeeID) values
('thanhhuyen', '123456', 1);

-- Một số lệnh có thể dùng đến khi cần
-- alter table ORDERs drop CONSTRAINT FK_Orders_DailySales
-- ALTER TABLE Product AUTO_INCREMENT=1;