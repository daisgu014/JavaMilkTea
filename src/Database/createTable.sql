--   Database: Bubble tea Java
drop database if exists MilkTea;
--   Init database

create database MilkTea CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use MilkTea;

-- Cơ sở dữ liệu chung cho nhân viên
-- Employee
create table Employee
(
	EmployeeId int(4) zerofill not null auto_increment,
	EmployeeName varchar(30) not null,
	EmployeePhone varchar(12) not null,
	WorkPositionID int(4) zerofill not null,
	constraint PK_Employee primary key (EmployeeId)
);

-- Account
create table Account
(
	AccountUsername varchar(20) not null,
	AccountPassword varchar(20) not null,
	EmployeeID int(4) zerofill not null,
	constraint PK_Account primary key (AccountUsername)
);


-- Work Position of employee
create table WorkPosition
(
	PositionId int(4) zerofill not null auto_increment,
	WorkPositionName varchar(30) not null,
	WorkPositionLVL int,
	constraint PK_WorkPosition primary key (PositionId)
);

-- Member
create table Customer
(
	Phone varchar(10) not null,
	CustomerName varchar(30) not null,
	Points int,
	constraint PK_Customer primary key (Phone)
);


-- Loại
create table Category
(
	CategoryId int(4) zerofill not null auto_increment,
	CategoryName varchar(255) not null,
	createAt date not null default (CURRENT_DATE),
	deleteAt date,
	constraint PK_Category primary key (CategoryId),
	unique (CategoryName)
);

-- Size
create table Sizes(
	sign varchar(5) not null,
	description text,
	constraint PK_Sizes primary key (sign)
);

-- kích cỡ và giá
create table ProductSize
(
	ProductID int(4) zerofill not null,
	Sizes varchar(5) not null,
	ProductPrice float not null,
	Storage int not null default 50,
	constraint PK_ProductSize primary key (ProductID, Sizes)
);

-- Sản phẩm
create table Product
(
	ProductId int(4) zerofill not null auto_increment,
	ProductName varchar(255) not null,
	CategoryId int(4) zerofill not null,
	ImagePath varchar(255),
	createAt date not null default (CURRENT_DATE),
	deleteAt date,
	constraint PK_Product primary key (ProductId),
	unique (ProductName)
);

-- Hóa đơn
create table Orders
(
	OrderId int(4) zerofill not null auto_increment,
	TotalPrice int not null,
	OrderDate date not null default (CURRENT_DATE),
	CustomerPhone varchar(10),
	Cashier int(4) zerofill not null,
	constraint PK_Orders primary key (OrderId)
);

-- Chi tiết hóa đơn
create table OrderDetail
(
	OrderID int(4) zerofill not null,
	ProductID int(4) zerofill not null,
	Sizes varchar(5) not null,
	Quantity int not null,
	constraint PK_OrderDetail primary key (OrderID, ProductID, Sizes)
);


-- Khóa ngoại bảng Employee đến WorkPosition
alter table Employee add constraint FK_Employee_Position foreign key (WorkPositionID) references WorkPosition (PositionId);

-- Khóa ngoại bảng account đến nhân viên
alter table Account add constraint FK_Account_Employee foreign key (EmployeeID) references Employee (EmployeeID);

-- Khóa ngoại bảng variation sản phẩm đến sản phẩm
alter table ProductSize add constraint FK_ProductSize_Product foreign key (ProductID) references Product (ProductId);

-- Khóa ngoại bảng variation sản phẩm đến bảng kích thước
alter table ProductSize add constraint FK_ProductSize_Sizes foreign key (Sizes) references Sizes (sign);

-- Khóa ngoại từ bảng Orders đến bảng Employee
alter table Orders add constraint FK_Orders_Employee foreign key (Cashier) references Employee(EmployeeID);

-- Khóa ngoại Thể loại sản phầm
alter table Product add constraint FK_Product_Category foreign key (CategoryID) references Category (CategoryId);

-- Khóa ngoại bảng Orders đến Customer
alter table Orders add constraint FK_Orders_Customer foreign key (CustomerPhone) references Customer (Phone);

-- Khóa ngoại bảng Chi tiết đơn hàng
alter table OrderDetail add constraint FK_OrderDetail_Orders foreign key (OrderID) references Orders (OrderID);
alter table OrderDetail add constraint FK_OrderDetail_Product foreign key (ProductId) references Product (ProductId);