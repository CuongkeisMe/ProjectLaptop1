CREATE DATABASE QLBH_PRO1041;
GO
USE QLBH_PRO1041;
GO
create table Ram (
	id_Ram int identity (1,1) primary key,
	MaRam as ('RM' + RIGHT('000' + CAST(id_Ram as varchar(6)), 6)) persisted,
	DungLuongRam varchar(20) not null,
	TrangThai bit default 1
);

create table CPU (
	id_CPU int identity (1,1) primary key,
	MaCPU as ('CPU' + RIGHT('000' + CAST(id_CPU as varchar(6)), 6)) persisted,
	TenCPU varchar(50) not null,
	TrangThai bit default 1
);

create table OCung (
	id_OCung int identity (1,1) primary key,
	MaOCung as ('OC' + RIGHT('000' + CAST(id_OCung as varchar(6)), 6)) persisted,
	LoaiOCung varchar(30) not null,
	TrangThai bit default 1,
);


create table GPU (
	id_GPU int identity (1,1) primary key,
	MaGPU as ('GPU' + RIGHT('000' + CAST(id_GPU as varchar(6)), 6)) persisted,
	TenGPU varchar(50) not null,
	TrangThai bit default 1,
);

create table ManHinh (
	id_ManHinh int identity (1,1) primary key,
	MaManHinh as ('MH' + RIGHT('00000' + CAST(id_ManHinh as varchar(6)), 6)) persisted,
	KichThuoc varchar(20) not null,
	TrangThai bit default 1
);

create table Pin (
	id_Pin int identity (1,1) primary key,
	MaPin as ('Pin' + RIGHT('000' + CAST(id_Pin as varchar(6)), 6)) persisted,
	DungLuongPin varchar(20) not null,
	TrangThai bit default 1 
);

CREATE TABLE SanPham (
    id_SanPham INT IDENTITY(1,1) PRIMARY KEY,
    id_Ram INT ,
    id_CPU INT ,
    id_GPU INT ,
    id_ManHinh INT,
    id_OCung INT ,
    id_Pin INT ,
    MaSanPham AS ('SP' + RIGHT('000' + CAST(id_SanPham AS VARCHAR(6)), 6)) PERSISTED,
    TenSanPham NVARCHAR(50) NOT NULL,
    HinhAnh VARCHAR(MAX) NOT NULL,
    SoLuong INT NOT NULL,
    GiaNhap float  NOT NULL ,
    GiaBan float  NOT NULL,
    TrangThai int NOT NULL,
    FOREIGN KEY (id_Ram) REFERENCES Ram(id_Ram),
    FOREIGN KEY (id_CPU) REFERENCES CPU(id_CPU),
    FOREIGN KEY (id_GPU) REFERENCES GPU(id_GPU),
    FOREIGN KEY (id_ManHinh) REFERENCES ManHinh(id_ManHinh),
    FOREIGN KEY (id_OCung) REFERENCES OCung(id_OCung),
    FOREIGN KEY (id_Pin) REFERENCES Pin(id_Pin)
);

CREATE TABLE KhachHang (
    id_KhachHang INT IDENTITY(1,1) PRIMARY KEY,
    MaKhachHang AS ('KH' + RIGHT('000' + CAST(id_KhachHang AS VARCHAR(6)), 6)) PERSISTED,
    HoTen NVARCHAR(50) NOT NULL,
    NgaySinh DATE NOT NULL,
    GioiTinh BIT NULL,
    SDT CHAR(10) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    DiaChi NVARCHAR(MAX) NOT NULL,
    TrangThai BIT DEFAULT 1
);

CREATE TABLE NhanVien (
    id_NhanVien INT IDENTITY(1,1) PRIMARY KEY,
    MaNhanVien AS ('NV' + RIGHT('000' + CAST(id_NhanVien AS VARCHAR(6)), 6)) PERSISTED,
    HoTen NVARCHAR(50) NOT NULL,
    NgaySinh DATE NOT NULL,
    GioiTinh BIT,
    SDT CHAR(10) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    DiaChi VARCHAR(MAX) NOT NULL,
    TrangThai BIT DEFAULT 1
);
CREATE TABLE VaiTro (
    id_VaiTro INT IDENTITY(1,1) PRIMARY KEY,
	LoaiVaiTro BIT NOT NULL,
);
create Table TaiKhoan(
	id_TaiKhoan INT IDENTITY(1,1) PRIMARY KEY,
	id_Nhanvien INT,
	id_VaiTro int,
	UserName varchar(50) not null,
	Pass varchar(50) not null,
	TrangThai Bit Default 1,
	foreign key (id_NhanVien) references dbo.NhanVien(id_NhanVien),
	foreign key (id_VaiTro) references dbo.VaiTro(id_VaiTro)
)
CREATE TABLE Voucher (
    id_Voucher INT IDENTITY(1,1) PRIMARY KEY,
    id_NhanVien INT,
    MaVoucher VARCHAR(50) NOT NULL UNIQUE,
	LoaiVoucher BIT not null,
    MoTa NVARCHAR(MAX) NULL,
    NgayPhatHanh DATE NOT NULL,
    NgayHetHan DATE NOT NULL,
    SoLuong INT CHECK (SoLuong > 0),
    MucGiaTri float  NOT NULL,
    GiaTriToiDa float  NOT NULL,
	DieuKien float not null,
	TrangThaiHoatDong BIT NOT NULL,
    TrangThai BIT DEFAULT 1,
    FOREIGN KEY (id_NhanVien) REFERENCES NhanVien(id_NhanVien)
);

CREATE TABLE HoaDon (
    id_HoaDon INT IDENTITY(1,1) PRIMARY KEY,
    id_KhachHang INT,
    id_NhanVien INT,
    id_Voucher INT,
    MaHoaDon AS ('HD' + RIGHT('000' + CAST(id_HoaDon AS VARCHAR(6)), 6)) PERSISTED,
    NgayThanhToan DATE NOT NULL,
    TongTien float  NOT NULL,
    TienVoucher float  NOT NULL,
    ThanhTien float  NOT NULL,
    PhuongThucThanhToan NVARCHAR(50) NOT NULL,
    GhiChu NVARCHAR(50),
	TrangThaiThanhToan BIT DEFAULT 0,
    TrangThai BIT DEFAULT 1,
    FOREIGN KEY (id_KhachHang) REFERENCES KhachHang(id_KhachHang),
    FOREIGN KEY (id_NhanVien) REFERENCES NhanVien(id_NhanVien),
    FOREIGN KEY (id_Voucher) REFERENCES Voucher(id_Voucher)
);

CREATE TABLE HoaDonChiTiet (
    id_HDCT INT IDENTITY(1,1) PRIMARY KEY,
    id_HoaDon INT,
    id_SanPham INT,
    SoLuong INT NOT NULL,
    DonGia float  NOT NULL,
    TongTien float  NOT NULL,
    TrangThai BIT DEFAULT 1,
    FOREIGN KEY (id_SanPham) REFERENCES SanPham(id_SanPham),
    FOREIGN KEY (id_HoaDon) REFERENCES HoaDon(id_HoaDon)
);

CREATE TABLE Imei (
    id_Imei INT IDENTITY(1,1) PRIMARY KEY,
    id_SanPham INT NOT NULL,
    Ma_Imei VARCHAR(50) NOT NULL unique,
    TrangThai BIT DEFAULT 1,
    FOREIGN KEY (id_SanPham) REFERENCES SanPham(id_SanPham)
);

CREATE TABLE ImeiDaBan (
    id_ImeiDaBan INT IDENTITY(1,1) PRIMARY KEY,
    id_HDCT INT,
    Ma_Imei VARCHAR(50) NOT NULL,
    TrangThai BIT DEFAULT 1,
    FOREIGN KEY (id_HDCT) REFERENCES HoaDonChiTiet(id_HDCT)
);
GO
-- Insert data into Ram Table
INSERT INTO Ram (DungLuongRam, TrangThai) VALUES 
('4GB', 1), 
('8GB', 1), 
('16GB', 1), 
('32GB', 1), 
('64GB', 1);

-- Insert data into CPU Table
INSERT INTO CPU (TenCPU, TrangThai) VALUES 
('Intel Core i3', 1), 
('Intel Core i5', 1), 
('Intel Core i7', 1), 
('AMD Ryzen 5', 1), 
('AMD Ryzen 7', 1);

-- Insert data into OCung Table
INSERT INTO OCung (LoaiOCung, TrangThai) VALUES 
('SSD 256GB', 1), 
('SSD 512GB', 1), 
('HDD 1TB', 1), 
('HDD 2TB', 1), 
('SSD 1TB', 1);

-- Insert data into GPU Table
INSERT INTO GPU (TenGPU, TrangThai) VALUES 
('NVIDIA GTX 1650', 1), 
('NVIDIA GTX 1660', 1), 
('NVIDIA RTX 2060', 1), 
('NVIDIA RTX 2070', 1), 
('NVIDIA RTX 2080', 1);

-- Insert data into ManHinh Table
INSERT INTO ManHinh (KichThuoc, TrangThai) VALUES 
('14 inch', 1), 
('15.6 inch', 1), 
('17 inch', 1), 
('13.3 inch', 1), 
('12.5 inch', 1);

-- Insert data into Pin Table
INSERT INTO Pin (DungLuongPin, TrangThai) VALUES 
('30Wh', 1), 
('40Wh', 1), 
('50Wh', 1), 
('60Wh', 1), 
('70Wh', 1);
-- Insert data into SanPham Table
INSERT INTO SanPham (id_Ram, id_CPU, id_GPU, id_ManHinh, id_OCung, id_Pin, TenSanPham, HinhAnh, SoLuong, GiaNhap, GiaBan, TrangThai) VALUES
(1, 1, 1, 1, 1, 1, N'Lenovo Thinkpad x13', 'image1.jpg', 1, 8900000, 11000000, 1),
(2, 2, 2, 2, 2, 2, N'Lenovo Legion 2022', 'image2.jpg', 1, 18600000, 20900000, 1),
(3, 3, 3, 3, 3, 3, N'Lenovo Ideapad 5 pro', 'image3.jpg', 1, 19300000, 21900000, 1),
(4, 4, 4, 4, 4, 4, N'Lenovo LOQ 2024', 'image4.jpg', 1, 20000000, 23500000, 1),
(5, 5, 5, 5, 5, 5, N'Lenovo Slim 3', 'image5.jpg', 1, 13000000 , 15000000, 1),
(1, 2, 2, 2, 1, 2, N'Lenovo Yoga 7i', 'image6.jpg', 1, 18000000, 21000000, 1),
(2, 3, 3, 3, 2, 3, N'Lenovo ThinkBook 15', 'image7.jpg', 1, 19000000, 22000000, 1),
(3, 4, 4, 4, 3, 4, N'Lenovo ThinkPad P14s', 'image8.jpg', 1, 21000000, 24000000, 1),
(4, 5, 5, 5, 4, 5, N'Lenovo Legion Y540', 'image9.jpg', 1, 25000000, 28000000, 1),
(5, 1, 1, 1, 5, 1, N'Lenovo V14', 'image10.jpg', 1, 12000000, 14000000, 1);


-- Insert data into KhachHang Table
INSERT INTO KhachHang (HoTen, NgaySinh, GioiTinh, SDT, Email, DiaChi, TrangThai) VALUES 
(N'Nguyen Van Huy', '1990-01-01', 1, '0123456789', 'huy98@gmail.com', N'Hà Nội', 1), 
(N'Tran Thi Thảo', '1992-02-02', 0, '0987654321', 'thao98@gmail.com', N'Hà Nội', 1), 
(N'Le Van Cường', '1994-03-03', 1, '0912345678', 'cuong@gmail.com', N'Hà Nội', 1), 
(N'Pham Thi Dung', '1996-04-04', 0, '0945678901', 'dung66@gmail.com', N'Hà Nội', 1), 
(N'Do Van Huy', '1998-05-05', 1, '0934567890', 'huy0992@gmail.com', N'Hà Nội', 1);

-- Insert data into NhanVien Table
INSERT INTO NhanVien (HoTen, NgaySinh, GioiTinh, SDT, Email, DiaChi, TrangThai) VALUES 
(N'Nguyen Van Phương', '1980-06-06', 1, '0956789012', 'phuong12@gmail.com', N'Hà Nội', 1), 
(N'Tran Thi Giang', '1985-07-07', 0, '0967890123', 'giang32@gmail.com', N'Hà Nội', 1), 
(N'Le Van Huy', '1990-08-08', 1, '0978901234', 'huy08@gmail.com', N'Hà Nội', 1), 
(N'Pham Thi Thu', '1995-09-09', 0, '0989012345', 'thu78@gmail.com', N'Hà Nội', 1), 
(N'Do Van Ly', '2000-10-10', 1, '0990123456', 'ly78@gmail.com', N'Hà Nội', 1);
-- Insert data into VaiTro Table
INSERT INTO VaiTro (LoaiVaiTro) VALUES 
(0), 
(1)
-- Insert data into TaiKhoan Table
INSERT INTO TaiKhoan (id_Nhanvien, UserName, Pass, id_VaiTro, TrangThai) VALUES 
(1, 'phuong123', 'phuong2376', 1, 1), 
(2, 'giang0980', 'giang9870', 2, 1), 
(3, 'huy8765', 'huy6543', 1, 1), 
(4, 'thu8923', 'thu8733', 1, 1), 
(5, 'ly8983', 'ly7823', 1, 1);
-- Insert data into Voucher Table
INSERT INTO Voucher (id_NhanVien, MaVoucher, LoaiVoucher, MoTa, NgayPhatHanh, NgayHetHan, SoLuong, MucGiaTri, GiaTriToiDa, DieuKien,TrangThaiHoatDong, TrangThai) 
VALUES 
(1, 'VOUCHER1', 0, N'Giảm 500k', '2024-01-01', '2024-08-31', 10, 500000, 500000, 1000000,1, 1), 
(2, 'VOUCHER2', 0, N'Giảm 600k', '2024-02-01', '2024-05-31', 20, 600000, 600000, 2000000,0, 1), 
(3, 'VOUCHER3', 0, N'Giảm 700k', '2024-03-01', '2024-06-30', 30, 700000, 700000,2500000 ,0, 1), 
(4, 'VOUCHER4', 0, N'Giảm 800k', '2024-07-01', '2024-10-31', 40, 800000, 800000,1500000 ,1, 1), 
(5, 'VOUCHER5', 0, N'Giảm 900k', '2024-08-01', '2024-11-30', 50, 900000, 900000, 2500000,1, 1),
(5, 'VOUCHER6', 1, N'Giảm 10%', '2024-08-01', '2024-11-30', 50, 10, 900000, 2500000,1, 1);

-- Insert data into HoaDon Table
INSERT INTO HoaDon (id_KhachHang, id_NhanVien, id_Voucher, NgayThanhToan, TongTien, TienVoucher, ThanhTien, PhuongThucThanhToan, GhiChu, TrangThaiThanhToan, TrangThai) 
VALUES 
(1, 1, 1, '2024-06-01', 31900000, 500000, 31400000, N'Tiền Mặt', N'Đã thanh toán', 1, 1), 
(2, 2, 2, '2024-07-01', 52800000, 600000, 52200000, N'Chuyển Khoản', N'Đã thanh toán', 1, 1), 
(3, 2, 3, '2024-08-01', 67500000, 700000, 66800000, N'Chuyển Khoản', N'Đã thanh toán', 1, 1), 
(4, 1, 4, '2024-09-01', 23500000, 800000,22700000 , N'Tiền Mặt', N'Đã thanh toán', 1, 1), 
(5, 5, 5, '2024-10-01', 75000000, 90000, 75100000, N'Tiền Mặt', N'Đã thanh toán', 1, 1);
-- Insert data into HoaDonChiTiet Table
INSERT INTO HoaDonChiTiet (id_HoaDon, id_SanPham, SoLuong, DonGia, TongTien, TrangThai) VALUES 
(1, 1, 1, 11000000, 11000000, 1), 
(2, 2, 2, 20900000, 41800000, 1), 
(3, 3, 3, 21900000,65700000, 1), 
(4, 4, 4, 23500000, 94000000, 1), 
(5, 5, 5,15000000 , 75000000, 1),
(2, 1, 1, 11000000, 11000000, 1), 
(1, 2, 1, 20900000, 20900000, 1);

-- Insert data into Imei Table
INSERT INTO Imei (id_SanPham, Ma_Imei, TrangThai) VALUES 
(1, '1234567890123', 1), 
(2, '2345678901234', 1), 
(3, '3456789012345', 1), 
(4, '4567890123456', 1), 
(5, '5678901234567', 1),
(6, '6789012345678', 1),
(7, '7890123456789', 1),
(8, '8901234567890', 1),
(9, '9012345678901', 1),
(10, '0123456789012', 1);

-- Insert data into ImeiDaBan Table
INSERT INTO ImeiDaBan (id_HDCT, Ma_Imei, TrangThai) VALUES 
(1, '8573920612345', 1), 
(2, '2948756102345', 1), 
(2, '3918475623456', 1), 
(3, '5091837462345', 1), 
(3, '6210983471234', 1),
(3, '7345091823456', 1),
(4, '8401293672345', 1),
(4, '9102847363456', 1), 
(4, '1234098572345', 1), 
(4, '2345098172345', 1), 
(5, '3451098262345', 1), 
(5, '4562098173456', 1),
(5, '5673098261234', 1),
(5, '6784098152345', 1),
(5, '7895098243456', 1), 
(2, '8906098132345', 1), 
(1, '9017098223456', 1);
Go
CREATE PROCEDURE timKiemVanBan @text NVARCHAR(30)
AS
BEGIN
    SELECT 
        HoaDon.MaHoaDon AS 'MaHoaDon',
        KhachHang.HoTen AS 'kh',
	   Voucher.MaVoucher AS 'MaVoucher',
        NhanVien.HoTen AS 'nv',
        FORMAT(HoaDon.NgayThanhToan, 'dd-MM-yyyy') AS 'NgayThanhToan',
        HoaDon.TongTien AS 'TongTien',
        HoaDon.TienVoucher AS 'TienVoucher',
        HoaDon.ThanhTien AS 'ThanhTien',
        HoaDon.PhuongThucThanhToan AS 'PhuongThuc',
        HoaDon.GhiChu AS 'GhiChu',
		HoaDon.TrangThaiThanhToan as 'TrangThaiThanhToan'
    FROM HoaDon
    JOIN KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang
    JOIN Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher
    JOIN NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien
    WHERE HoaDon.MaHoaDon LIKE '%' + @text + '%'
       OR KhachHang.HoTen LIKE '%' + @text + '%'
       OR KhachHang.SDT LIKE '%' + @text + '%'
       OR NhanVien.HoTen LIKE '%' + @text + '%'
       OR Voucher.MaVoucher LIKE '%' + @text + '%'
	   and HoaDon.TrangThai=0;
END
go
CREATE PROCEDURE timKiemTheoKhoangNgay 
    @startDate DATE = NULL,    
    @endDate DATE = NULL       
AS
BEGIN
    SELECT 
        HoaDon.MaHoaDon AS 'MaHoaDon',
        KhachHang.HoTen AS 'kh',
        Voucher.MaVoucher AS 'MaVoucher',
        NhanVien.HoTen AS 'nv',
        FORMAT(HoaDon.NgayThanhToan, 'dd-MM-yyyy') AS 'NgayThanhToan',
        HoaDon.TongTien AS 'TongTien',
        HoaDon.TienVoucher AS 'TienVoucher',
        HoaDon.ThanhTien AS 'ThanhTien',
        HoaDon.PhuongThucThanhToan AS 'PhuongThuc',
        HoaDon.GhiChu AS 'GhiChu',
		HoaDon.TrangThaiThanhToan as 'TrangThaiThanhToan'
    FROM HoaDon
    JOIN KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang
    JOIN Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher
    JOIN NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien
    WHERE (@startDate IS NULL OR HoaDon.NgayThanhToan >= @startDate)
      AND (@endDate IS NULL OR HoaDon.NgayThanhToan <= @endDate)
	  AND HoaDon.TrangThai =1;
END
go
CREATE PROCEDURE timKiemTheoCbo
	@pt nvarchar(30)
as
begin
	SELECT 
        HoaDon.MaHoaDon AS 'MaHoaDon',
        KhachHang.HoTen AS 'kh',
        Voucher.MaVoucher AS 'MaVoucher',
        NhanVien.HoTen AS 'nv',
        FORMAT(HoaDon.NgayThanhToan, 'dd-MM-yyyy') AS 'NgayThanhToan',
        HoaDon.TongTien AS 'TongTien',
        HoaDon.TienVoucher AS 'TienVoucher',
        HoaDon.ThanhTien AS 'ThanhTien',
        HoaDon.PhuongThucThanhToan AS 'PhuongThuc',
        HoaDon.GhiChu AS 'GhiChu',
		HoaDon.TrangThaiThanhToan as 'TrangThaiThanhToan'
    FROM HoaDon
    JOIN KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang
    JOIN Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher
    JOIN NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien
	where HoaDon.PhuongThucThanhToan LIKE '%' + @pt+ '%'
	AND HoaDon.TrangThai=1;
end
go
CREATE PROCEDURE timKiemAll
    @text NVARCHAR(30),
    @pt NVARCHAR(30),
    @startDate DATE = NULL,   
    @endDate DATE = NULL 
AS
BEGIN
    SELECT 
        HoaDon.MaHoaDon AS 'MaHoaDon',
        KhachHang.HoTen AS 'kh',
        Voucher.MaVoucher AS 'MaVoucher',
        NhanVien.HoTen AS 'nv',
        FORMAT(HoaDon.NgayThanhToan, 'dd-MM-yyyy') AS 'NgayThanhToan',
        HoaDon.TongTien AS 'TongTien',
        HoaDon.TienVoucher AS 'TienVoucher',
        HoaDon.ThanhTien AS 'ThanhTien',
        HoaDon.PhuongThucThanhToan AS 'PhuongThuc',
        HoaDon.GhiChu AS 'GhiChu',
		HoaDon.TrangThaiThanhToan as 'TrangThaiThanhToan'
    FROM HoaDon
    JOIN KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang
    JOIN Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher
    JOIN NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien
    WHERE (
            HoaDon.MaHoaDon LIKE '%' + @text + '%'
            OR KhachHang.HoTen LIKE '%' + @text + '%'
            OR KhachHang.SDT LIKE '%' + @text + '%'
            OR NhanVien.HoTen LIKE '%' + @text + '%'
            OR Voucher.MaVoucher LIKE '%' + @text + '%'
            OR NhanVien.MaNhanVien LIKE '%' + @text + '%'
            OR KhachHang.MaKhachHang LIKE '%' + @text + '%'
          )
      AND HoaDon.PhuongThucThanhToan LIKE '%' + @pt + '%'
      AND (@startDate IS NULL OR HoaDon.NgayThanhToan >= @startDate)
      AND (@endDate IS NULL OR HoaDon.NgayThanhToan <= @endDate)
	  AND HoaDon.TrangThai=1;
END
go
CREATE PROCEDURE timKiemVanBanVaPhuongThuc
    @text NVARCHAR(30),
    @pt NVARCHAR(30)
AS
BEGIN
    SELECT 
        HoaDon.MaHoaDon AS 'MaHoaDon',
        KhachHang.HoTen AS 'kh',
        Voucher.MaVoucher AS 'MaVoucher',
        NhanVien.HoTen AS 'nv',
        FORMAT(HoaDon.NgayThanhToan, 'dd-MM-yyyy') AS 'NgayThanhToan',
        HoaDon.TongTien AS 'TongTien',
        HoaDon.TienVoucher AS 'TienVoucher',
        HoaDon.ThanhTien AS 'ThanhTien',
        HoaDon.PhuongThucThanhToan AS 'PhuongThuc',
        HoaDon.GhiChu AS 'GhiChu',
		HoaDon.TrangThaiThanhToan as 'TrangThaiThanhToan'
    FROM HoaDon
    JOIN KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang
    JOIN Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher
    JOIN NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien
    WHERE (
            HoaDon.MaHoaDon LIKE '%' + @text + '%'
            OR KhachHang.HoTen LIKE '%' + @text + '%'
            OR KhachHang.SDT LIKE '%' + @text + '%'
            OR NhanVien.HoTen LIKE '%' + @text + '%'
            OR Voucher.MaVoucher LIKE '%' + @text + '%'
            OR NhanVien.MaNhanVien LIKE '%' + @text + '%'
            OR KhachHang.MaKhachHang LIKE '%' + @text + '%'
          )
      AND HoaDon.PhuongThucThanhToan LIKE '%' + @pt + '%'
	  and HoaDon.TrangThai=1;
END
go
CREATE PROCEDURE timKiemTheoVanBanNgay
    @text NVARCHAR(30),
    @startDate DATE = NULL,   
    @endDate DATE = NULL 
AS
BEGIN
    SELECT 
        HoaDon.MaHoaDon AS 'MaHoaDon',
        KhachHang.HoTen AS 'kh',
        Voucher.MaVoucher AS 'MaVoucher',
        NhanVien.HoTen AS 'nv',
        FORMAT(HoaDon.NgayThanhToan, 'dd-MM-yyyy') AS 'NgayThanhToan',
        HoaDon.TongTien AS 'TongTien',
        HoaDon.TienVoucher AS 'TienVoucher',
        HoaDon.ThanhTien AS 'ThanhTien',
        HoaDon.PhuongThucThanhToan AS 'PhuongThuc',
        HoaDon.GhiChu AS 'GhiChu',
		HoaDon.TrangThaiThanhToan as 'TrangThaiThanhToan'
    FROM HoaDon
    JOIN KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang
    JOIN Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher
    JOIN NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien
    WHERE (HoaDon.MaHoaDon LIKE '%' + @text + '%'
           OR KhachHang.HoTen LIKE '%' + @text + '%'
           OR KhachHang.SDT LIKE '%' + @text + '%'
           OR NhanVien.HoTen LIKE '%' + @text + '%'
           OR Voucher.MaVoucher LIKE '%' + @text + '%'
           OR NhanVien.MaNhanVien LIKE '%' + @text + '%'
           OR KhachHang.MaKhachHang LIKE '%' + @text + '%'
          )
      AND (@startDate IS NULL OR HoaDon.NgayThanhToan >= @startDate)
      AND (@endDate IS NULL OR HoaDon.NgayThanhToan <= @endDate)
	  and HoaDon.TrangThai=1;
END
go
CREATE PROCEDURE timKiemTheoCboVaNgay
    @startDate DATE = NULL,
    @endDate DATE = NULL,
    @pt NVARCHAR(30)
AS
BEGIN
    SELECT 
        HoaDon.MaHoaDon AS 'MaHoaDon',
        KhachHang.HoTen AS 'kh',
        Voucher.MaVoucher AS 'MaVoucher',
        NhanVien.HoTen AS 'nv',
        FORMAT(NgayThanhToan, 'dd-MM-yyyy') AS 'NgayThanhToan',
        HoaDon.TongTien AS 'TongTien',
        HoaDon.TienVoucher AS 'TienVoucher',
        HoaDon.ThanhTien AS 'ThanhTien',        
        HoaDon.PhuongThucThanhToan AS 'PhuongThuc',
        HoaDon.GhiChu AS 'GhiChu',
        HoaDon.TrangThaiThanhToan AS 'TrangThaiThanhToan'
    FROM HoaDon
    JOIN KhachHang ON KhachHang.id_KhachHang = HoaDon.id_KhachHang
    JOIN Voucher ON Voucher.id_Voucher = HoaDon.id_Voucher
    JOIN NhanVien ON NhanVien.id_NhanVien = HoaDon.id_NhanVien
    WHERE (@startDate IS NULL OR HoaDon.NgayThanhToan >= @startDate)
      AND (@endDate IS NULL OR HoaDon.NgayThanhToan <= @endDate)
      AND (@pt IS NULL OR HoaDon.PhuongThucThanhToan LIKE '%' + @pt + '%')
      AND HoaDon.TrangThai = 1;
END
go
create proc sp_TimKiemVoucher
@text Nvarchar(50)
as
	begin
		SELECT 
			Voucher.MaVoucher AS 'MaVoucher',
			NhanVien.HoTen AS 'HoTen',
			FORMAT(Voucher.NgayPhatHanh, 'dd-MM-yyyy') AS 'NgayPhatHanh',
			FORMAT(Voucher.NgayHetHan, 'dd-MM-yyyy') AS 'NgayHetHan',
			Voucher.SoLuong AS 'SoLuong',
			Voucher.MucGiaTri AS 'MucGiaTri',
			Voucher.GiaTriToiDa AS 'GiaTriToiDa',
			Voucher.LoaiVoucher AS 'Loai',
			Voucher.DieuKien AS 'DieuKien',
			Voucher.MoTa AS 'MoTa',
			Voucher.TrangThaiHoatDong AS 'TrangThai'
		FROM 
			Voucher
		JOIN 
			NhanVien ON NhanVien.id_NhanVien = Voucher.id_NhanVien
	    where Voucher.MaVoucher like '%'+@text+'%'
			  or NhanVien.HoTen like '%'+@text+'%'
			  or NhanVien.MaNhanVien like '%'+@text+'%'
			  and Voucher.TrangThai =1;
	
	end
go

delete from Voucher
