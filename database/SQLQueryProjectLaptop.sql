create database QLBH_PRO1041
go
use QLBH_PRO1041


create table Ram (
	id_Ram int identity (1,1) primary key,
	MaRam as ('RM' + RIGHT('00000' + CAST(id_Ram as varchar(6)), 6)) persisted,
	DungLuongRam varchar(20) not null,
	TrangThai bit default 1
);

create table CPU (
	id_CPU int identity (1,1) primary key,
	MaCPU as ('CPU' + RIGHT('00000' + CAST(id_CPU as varchar(6)), 6)) persisted,
	TenCPU varchar(50) not null,
	TrangThai bit default 1
);

create table OCung (
	id_OCung int identity (1,1) primary key,
	MaOCung as ('OC' + RIGHT('00000' + CAST(id_OCung as varchar(6)), 6)) persisted,
	LoaiOCung varchar(30) not null
);


create table GPU (
	id_GPU int identity (1,1) primary key,
	MaGPU as ('GPU' + RIGHT('00000' + CAST(id_GPU as varchar(6)), 6)) persisted,
	TenGPU varchar(50) not null,
	TrangThai bit default 1
);

create table ManHinh (
	id_ManHinh int identity (1,1) primary key,
	MaManHinh as ('MH' + RIGHT('00000' + CAST(id_ManHinh as varchar(6)), 6)) persisted,
	KichThuoc varchar(20) not null,
	TrangThai bit default 1
);

create table Pin (
	id_Pin int identity (1,1) primary key,
	MaPin as ('Pin' + RIGHT('00000' + CAST(id_Pin as varchar(6)), 6)) persisted,
	DungLuongPin varchar(20) not null,
	TrangThai bit default 1 
);

create table SanPham (
	id_SanPham int identity (1,1) primary key,
	id_Ram int not null,
	id_CPU int not null,
	id_GPU int not null,
	id_ManHinh int not null,
	id_OCung int not null,
	id_Pin int not null,
	MaSanPham as ('SP' + RIGHT('00000' + CAST(id_SanPham as varchar(6)), 6)) persisted,
	TenSanPham varchar(50) not null,
	HinhAnh varchar(Max) not null,
	SoLuong int not null,
	GiaNhap money not null,
	GiaBan money not null,
	TrangThai bit default 1
	FOREIGN KEY (id_Ram) REFERENCES dbo.Ram(id_Ram),
	FOREIGN KEY (id_CPU) REFERENCES dbo.CPU(id_CPU),
	FOREIGN KEY (id_GPU) REFERENCES dbo.GPU(id_GPU),
	FOREIGN KEY (id_ManHinh) REFERENCES dbo.ManHinh(id_ManHinh),
	FOREIGN KEY (id_OCung) REFERENCES dbo.OCung(id_OCung),
	FOREIGN KEY (id_Pin) REFERENCES dbo.Pin(id_Pin),
);

create table KhachHang (
	id_KhachHang int identity (1,1) primary key,
	MaKhachHang as ('KH' + RIGHT('00000' + CAST(id_KhachHang as varchar(6)), 6)) persisted,
	HoTen nvarchar(50) not null,
	NgaySinh Date not null,
	GioiTinh bit,
	SDT char(10) not null,
	Email varchar(100) not null,
	DiaChi varchar(Max) not null,
	TrangThai bit default 1
);

create table NhanVien (
	id_NhanVien int identity (1,1) primary key,
	MaNhanVien as ('NV' + RIGHT('00000' + CAST(id_NhanVien as varchar(6)), 6)) persisted,
	UserName varchar(50) not null,
	Pass varchar(Max) not null,
	HoTen nvarchar(50) not null,
	NgaySinh Date not null,
	GioiTinh bit,
	SDT char(10) not null,
	Email varchar(100) not null,
	DiaChi varchar(Max) not null,
	VaiTro bit,
	TrangThai bit default 1
);
create table VaiTro(
	id_VaiTro int identity (1,1) primary key,
	id_NhanVien int,
	LoaiVaiTro bit not null,
	foreign key (id_NhanVien) references NhanVien(id_NhanVien)

);
create table Voucher (
	id_Voucher int identity (1,1) primary key,
	id_NhanVien int,
	MaVoucher varchar(50) not null,
	MoTa varchar(Max),
	NgayPhatHanh Date not null,
	NgayHetHan Date not null,
	SoLuong int check (SoLuong > 0),
	NgaySuDung Date not null,
	TrangThai bit default 1
	FOREIGN KEY (id_NhanVien) REFERENCES dbo.NhanVien(id_NhanVien),
);


create table HoaDon (
	id_HoaDon int identity (1,1) primary key,
	id_KhachHang int,
	id_NhanVien int,
	id_Voucher int,
	MaHoaDon as ('HD' + RIGHT('00000' + CAST(id_HoaDon as varchar(6)), 6)) persisted,
	NgayThanhToan Date not null,
	TongTien money not null,
	TienTruVoucher money not null,
	ThanhTien money not null,
	PhuongThucThanhToan varchar(30) not null,
	GhiChu varchar(50),
	TrangThai bit default 1
	FOREIGN KEY (id_KhachHang) REFERENCES dbo.KhachHang(id_KhachHang),
	FOREIGN KEY (id_NhanVien) REFERENCES dbo.NhanVien(id_NhanVien),
	FOREIGN KEY (id_Voucher) REFERENCES dbo.Voucher(id_Voucher)
);


create table HoaDonChiTiet (
	id_HDCT int identity (1,1) primary key,
	id_HoaDon int,
	id_SanPham int,
	SoLuong int check (SoLuong > 0),
	DonGia money not null,
	TongTien money not null,
	TrangThai bit default 1
	FOREIGN KEY (id_SanPham) REFERENCES dbo.SanPham(id_SanPham),
	FOREIGN KEY (id_HoaDon) REFERENCES dbo.HoaDon(id_HoaDon)
);
create table Imei (
	id_Imei int identity (1,1) primary key,
	id_SanPham int not null,
	Ma_Imei varchar(50) not null,
	TrangThai bit default 1,
	foreign key (id_SanPham) references dbo.SanPham(id_SanPham)
);
create table ImeiDaBan (
	id_ImeiDaBan int identity (1,1) primary key,
	id_HDCT int,
	Ma_Imei varchar(50) not null,
	TrangThai bit default 1,
	FOREIGN KEY (id_HDCT) REFERENCES dbo.HoaDonChiTiet(id_HDCT),
);
go

INSERT INTO Ram (DungLuongRam) VALUES ('8GB'), ('16GB'), ('32GB'), ('64GB');

INSERT INTO CPU (TenCPU) VALUES ('Intel Core i5'), ('Intel Core i7'), ('AMD Ryzen 5'), ('AMD Ryzen 7');

INSERT INTO OCung (LoaiOCung) VALUES 
('SSD 256GB'), 
('SSD 512GB'), 
('HDD 1TB'), 
('HDD 2TB');

INSERT INTO GPU (TenGPU) VALUES ('NVIDIA GTX 1660'), ('NVIDIA RTX 3060'), ('AMD Radeon RX 5700'), ('AMD Radeon RX 6800');


INSERT INTO ManHinh (KichThuoc) VALUES ('13.3 inch'), ('15.6 inch'), ('17.3 inch'), ('14 inch');


INSERT INTO Pin (DungLuongPin) VALUES ('75 Wh'), ('60 Wh'), ('65 Wh'), ('70Wh');

INSERT INTO SanPham (id_Ram, id_CPU, id_GPU, id_ManHinh, id_OCung, id_Pin, TenSanPham, HinhAnh, SoLuong, GiaNhap, GiaBan)
VALUES 
(1, 1, 1, 1, 1, 1, N'Lenovo Legion 2023', 'hinh1.jpg', 10, 11500000, 15000000),
(2, 2, 2, 2, 2, 2, N'Lenovo Ideapad 5', 'hinh2.jpg', 20, 13800000, 18000000),
(3, 3, 3, 3, 3, 3, N'Lenovo Thinkpad x13', 'hinh3.jpg', 15, 16100000, 21000000),
(4, 4, 4, 4, 4, 4, N'Lenovo LOQ 2023', 'hinh4.jpg', 5, 18400000, 24000000);

INSERT INTO KhachHang (HoTen, NgaySinh, GioiTinh, SDT, Email, DiaChi) 
VALUES 
(N'Nguyễn Văn Lực', '1985-05-10', 1, '0912345678', 'a@gmail.com', N'123 Đường A'),
(N'Trần Thị Sương', '1990-08-15', 0, '0987654321', 'b@yahoo.com', N'456 Đường B'),
(N'Lê Văn Sướng', '1992-09-20', 1, '0901234567', 'c@hotmail.com', N'789 Đường C'),
(N'Phạm Thị Thu Hương', '1988-12-25', 0, '0934567890', 'd@outlook.com', N'101 Đường D');

INSERT INTO NhanVien (UserName, Pass, HoTen, NgaySinh, GioiTinh, SDT, Email, DiaChi, VaiTro) 
VALUES 
('user1', 'password1', N'Lê Quang Hải', '1980-01-01', 1, '0911222333', 'h@example.com', N'100 Đường H', 0),
('user2', 'password2', N'Nguyễn Thị Vy', '1985-02-02', 0, '0922333444', 'k@example.com', N'200 Đường K', 1),
('user3', 'password3', N'Phạm Văn Minh', '1990-03-03', 1, '0933444555', 'm@example.com', N'300 Đường M', 0),
('user4', 'password4', N'Trần Thị Tuyết', '1995-04-04', 0, '0944555666', 'n@example.com', N'400 Đường N', 1);

INSERT INTO VaiTro (id_NhanVien, LoaiVaiTro) 
VALUES 
(1, 0), -- NhanVien with id 1 is a nhân viên
(2, 1), -- NhanVien with id 2 is an admin
(3, 0), -- NhanVien with id 3 is a nhân viên
(4, 1); -- NhanVien with id 4 is an admin

INSERT INTO Voucher (id_NhanVien, MaVoucher, MoTa, NgayPhatHanh, NgayHetHan, SoLuong, NgaySuDung)
VALUES 
(1, 'VOUCHER100', N'Giảm giá 100K', '2024-01-01', '2024-12-31', 50, '2024-01-02'),
(2, 'VOUCHER200', N'Giảm giá 200K', '2024-01-01', '2024-12-31', 30, '2024-01-02'),
(3, 'VOUCHER300', N'Giảm giá 300K', '2024-01-01', '2024-12-31', 20, '2024-01-02'),
(4, 'VOUCHER400', N'Giảm giá 400K', '2024-01-01', '2024-12-31', 10, '2024-01-02');

INSERT INTO HoaDon (id_KhachHang, id_NhanVien, id_Voucher, NgayThanhToan, TongTien,TienTruVoucher, ThanhTien, PhuongThucThanhToan, GhiChu)
VALUES 
(1, 1, 1, '2024-02-01', 15000000, 1, 1, N'Thẻ tín dụng', N'Thanh toán đầy đủ'),
(2, 2, 2, '2024-02-02', 18000000,1,1, N'Tiền mặt', N'Thanh toán đầy đủ'),
(3, 3, 3, '2024-02-03', 21000000,1,1, N'Thẻ tín dụng', N'Thanh toán đầy đủ'),
(4, 4, 4, '2024-02-04', 24000000,1,1, N'Tiền mặt', N'Thanh toán đầy đủ');

INSERT INTO HoaDonChiTiet (id_HoaDon, id_SanPham, SoLuong, DonGia, TongTien)
VALUES 
(1, 1, 1, 15000000, 15000000),
(2, 2, 1, 18000000, 18000000),
(3, 3, 1, 21000000, 21000000),
(4, 4, 1, 24000000, 24000000);

INSERT INTO Imei (id_SanPham, Ma_Imei) 
VALUES 
(1, '1234567890'), 
(2, '2345678901'), 
(3, '3456789012'), 
(4, '4567890123');

select * from CPU
select * from GPU
select * from Ram
select * from ManHinh
select * from Imei
select * from KhachHang
select * from OCung
select * from NhanVien
select * from HoaDon
select * from HoaDonChiTiet
select * from VaiTro
select * from Voucher
select * from SanPham
