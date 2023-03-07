--   Database: Bubble tea Java
drop database MilkTea;
--   Init database

create database MilkTea CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use MilkTea;

-- Cơ sở dữ liệu chung cho nhân viên
-- Employee
create table Employee
(
	ID int(4) zerofill not null auto_increment,
	EmployeeName varchar(30) not null,
	EmployeePhone varchar(12) not null,
	WorkPositionID int(4) zerofill not null,
	WorkTypeID int(4) zerofill not null,
	constraint PK_employee primary key (ID)
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
	ID int(4) zerofill not null auto_increment,
	WorkPositionName varchar(30) not null,
	WorkPositionLVL varchar(10),
	constraint PK_workposition primary key (ID)
);

-- Work type
create table WorkType
(
	ID int(4) zerofill not null auto_increment,
	WorkTypeName varchar(30) not null,
	constraint PK_WorkType primary key (ID)
);

-- Position type: Chi tiết vị trí việc làm của nhân viên
create table PositionType
(
	WorkPositionID int(4) zerofill not null,
	WorkTypeID int(4) zerofill not null,
	SalaryPerHour int not null,
	constraint PK_PositionType primary key (WorkPositionID, WorkTypeID)
);

-- Giờ làm việc của nhân viên
create table DailyWork
(
	DailyDate date not null,
	EmployeeID int(4) zerofill not null,
	Checkin time not null,
	Checkout time not null,
	WorkingHour float not null,
	constraint PK_DailyWork primary key (DailyDate, EmployeeID)
);


-- Loại
create table Category
(
	ID int(4) zerofill not null auto_increment,
	CategoryName nvarchar(30) not null,
	constraint PK_Category primary key (ID)
);

-- kích cỡ và giá
create table ProductPrice
(
	ProductID int(4) zerofill not null,
	ProductSize varchar(2) not null,
	ProductPrice float not null,
	constraint PK_ProductPrice primary key (ProductID, ProductSize)
);
 
-- Cơ sở dữ liệu chung cho sản phẩm
-- Sản phẩm
create table Product
(
	ID int(4) zerofill not null auto_increment,
	ProductName text not null,
	CategoryID int(4) zerofill not null,
	ImagePath varchar(255),
	createAt date,
	deleteAt date,
	constraint PK_Product primary key (ID)
);

-- Topping
create table Topping
(
	ID int(4) zerofill not null auto_increment,
	ToppingName text not null,
	SellPrice float not null,
	UnitOnePart int not null,
	ImportPrice float not null,
	amount float,
	createAt date,
	deleteAt date,
	constraint PK_topping primary key (ID)
);



-- Sản phẩm kèm topping
create table ProductTopping
(
	ProductID int(4) zerofill not null,
	ToppingID int(4) zerofill not null,
	constraint PK_ProductTopping primary key (ProductID, ToppingID)
);


-- Cơ sở dữ liệu chung cho hóa đơn
-- Hóa đơn
create table Orders
(
	ID int(4) zerofill not null auto_increment,
	TotalPrice int not null,
	OrderDate date not null,
	OrderTime time not null,
	Cashier int(4) zerofill not null,
	constraint PK_Orders primary key (ID)
);

-- Chi tiết hóa đơn
create table OrderDetail
(
	OrderID int(4) zerofill not null,
	ProductID int(4) zerofill not null,
	Quantity int not null,
	productSize varchar(2),
	price float,
	constraint PK_OrderDetail primary key (OrderID, ProductID)
);

-- Cơ sở dữ liệu chung cho báo cáo
-- Tổng bán trong ngày
create table DailySales
(
	reportId int(4) zerofill not null,
	DailySalesDate date not null,
	Total int not null,
	constraint PK_DailySales primary key (DailySalesDate)
);


-- Bảng nguyên vật liệu
create table Ingredients
(
	ID int(4) zerofill not null auto_increment,
	ingredientName varchar(40),
	ingredientType varchar(20),
	storage int,
	price int,
	createAt date,
	deleteAt date,
	constraint PK_Ingredients primary key (ID)
);


-- Bảng công thức sản phẩm
create table ProductRecipes
(
	productID int(4) zerofill not null,
	ingredientID int(4) zerofill not null,
	productQty float not null,
	ingredientQty float not null,
	constraint PK_ProductRecipes primary key (productID,ingredientID)
);

-- Bảng lưu báo cáo hàng hủy
create table DiscardReports
(
	ID int(4) zerofill not null auto_increment,
	created int(4) zerofill not null,
	comfirmed int(4) zerofill,
	reportDate Date not null,
	constraint PK_DiscardReports primary key (ID)
);

-- Bảng lưu báo cáo doanh thu
create table SaleReports
(
	id int(4) zerofill not null auto_increment,
	created int(4) zerofill not null,
	comfirmed int(4) zerofill,
	reportDate Date not null,
	constraint PK_SaleReports primary key (id)
);

-- Bảng lưu báo cáo nhập hàng
create table IncomeReports
(
	id int(4) zerofill not null auto_increment,
	created int(4) zerofill not null,
	comfirmed int(4) zerofill,
	supplier varchar(100) not null,
	reportDate Date not null,
	stateReport varchar(20) not null,
	constraint PK_IncomeReports primary key (id)
);


-- Bảng lưu chi tiết hàng hủy
create table DiscardDetails
(
	productID int(4) zerofill not null,
	reportID int(4) zerofill not null,
	qty int not null,
	discardDate date not null,
	constraint PK_DISCARDDETAILS primary key (productID,reportID)
);

-- Bảng lưu chi tiết hàng nhập
create table IncomeDetails
(
	reportID int(4) zerofill not null,
	ingredientID int(4) zerofill not null,
	qty int not null,
	receiveQty int,
	constraint PK_INCOMEDETAILS primary key (reportID,ingredientID)
);




-- Khóa ngoại cho Employee to PositionType
alter table Employee add Constraint FK_Employee_PositionType foreign key (WorkPositionID,WorkTypeID)
	references PositionType (WorkPositionID,WorkTypeID);


-- Khóa ngoại PositionType đến WorkPosition và PositionType đến WorkType
alter table PositionType add constraint FK_PositionType_WorkPosition foreign key (WorkPositionID) references WorkPosition (ID);
alter table PositionType add constraint	FK_PositionType_WorkType foreign key (WorkTypeID) references WorkType (id);

-- Khóa ngoại account đến nhân viên
alter table Account add constraint FK_Account_Employee foreign key (EmployeeID) references Employee (id);

-- Khóa ngoại bảng chấm công đến nhân viên
alter table DailyWork add constraint FK_DailyWork_Employee foreign key (EmployeeID) references Employee (id);

-- Khóa ngoại giá sản phẩm đến sản phẩm
alter table ProductPrice add constraint FK_ProductPrice_Product foreign key (ProductID) references Product (id);

-- Khóa ngoại cho bảng đơn hàng
alter table Orders add constraint FK_Orders_DailySales foreign key (OrderDate) references DailySales (DailySalesDate);

-- Khóa ngoại từ bảng Orders đến bảng Employee
alter table Orders add constraint FK_Orders_Employee foreign key (Cashier) references Employee(id);

-- Khóa ngoại Thể loại sản phầm
alter table Product add constraint FK_Product_Category foreign key (CategoryID) references Category (id);

-- Khóa ngoại Chi tiết đơn hàng
alter table OrderDetail add constraint FK_OrderDetail_Orders foreign key (OrderID) references Orders (id);
alter table OrderDetail add constraint FK_OrderDetails_Product foreign key (ProductID) references Product (id);

-- Khóa ngoại công thức sản phẩm
alter table ProductRecipes add constraint FK_PRODUCTRECIPES_PRODUCT foreign key (productID)
		references Product(id);
alter table ProductRecipes add constraint FK_PRODUCTRECIPES_INGREDIENTS foreign key (ingredientID)
		references INGREDIENTS(id);

-- Khóa ngoại chi tiết hàng hủy
alter table DiscardDetails add constraint FK_DISCARDDETAILS_PRODUCT foreign key (productID)
		references Product(id);
alter table DiscardDetails add constraint FK_DISCARDDETAILS_DISCARDREPORTS foreign key (reportID)
		references DiscardReports(id);

-- Khóa ngoại chi tiết hàng nhập
alter table IncomeDetails add constraint FK_INCOMEDETAILS_INGREDIENTS foreign key (ingredientID)
		references Ingredients (id);
alter table IncomeDetails add constraint FK_INCOMEDETAILS_INCOMEREPORTS foreign key (reportID)
		references IncomeReports (id);

--   Khóa ngoại doanh thu mỗi ngày đến báo cáo
alter table DailySales add constraint FK_DAILYSALES_SALESREPORT foreign key (reportId)
		references SaleReports(id);
		
-- Khóa ngoại bảng ProductTopping đến bảng Topping và bảng Product
alter table ProductTopping add constraint FK_ProductTopping_Product foreign key (ProductID)
		references Product(ID);
alter table ProductTopping add constraint FK_ProductTopping_Topping foreign key (ToppingID)
		references Topping(ID);