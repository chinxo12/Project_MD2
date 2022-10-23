package ra.bussiness.impl;

import ra.bussiness.config.ShopConstant;
import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.ShopValidate;
import ra.bussiness.design.ICatalog;
import ra.bussiness.design.IShop;
import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.Flower;
import ra.bussiness.entity.Product;
import ra.bussiness.entity.User;
import ra.bussiness.file.FileAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogImpl implements IShop<Catalog,String> , ICatalog {
    public static List<Catalog> readFromFile (){
        FileAll <Catalog> fileAll = new FileAll<>();
        List<Catalog> list = fileAll.readFromFile(ShopConstant.CATALOG_URL);
        return list;
    }
    public static boolean writeFromFile (List<Catalog> list){
        FileAll<Catalog> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,ShopConstant.CATALOG_URL);
        return returnData;
    }
    @Override
    public boolean create(Catalog catalog) {
        List<Catalog> catalogList = readFromFile();
        if (catalogList==null){
            catalogList = new ArrayList<>();
        }
        catalogList.add(catalog);
        boolean result = writeFromFile(catalogList);
        return result;
    }

    @Override
    public boolean update(Catalog catalog) {
        return false;
    }

    @Override
    public List<Catalog> findAll() {
        return readFromFile();
    }

    @Override
    public Catalog inputData(Scanner scanner) {
        List <Catalog> listCatalog = readFromFile();
        if (listCatalog==null){
            listCatalog = new ArrayList<>();
        }
        Catalog catalog = new Catalog();
        do {
            System.out.println("Nhập vào mã danh mục sản phẩm: ");
            String id = scanner.nextLine();
            boolean check = ShopValidate.checkId5(id);
            if (check){
                check = ShopValidate.checkCatalogId(id);
                if (check){
                    for (Catalog cat :listCatalog) {
                        if (cat.getCatalogId().equals(id)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        catalog.setCatalogId(id);
                        break;
                    }else {
                        System.err.println(ShopMessage.THIS_ID_ALREADY_EXISTS);
                    }
                }else {
                    System.err.println(ShopMessage.CATALOG_ID_WRONG);
                }
            }else {
                System.err.println(ShopMessage.ID_LENGTH_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nhập vào tên danh mục: ");
            String name = scanner.nextLine();
            boolean check = ShopValidate.checkValidateName(name);
            if (check){
                for (Catalog cat :listCatalog) {
                    if (cat.getCatalogName().equals(name)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    catalog.setCatalogName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nhập vào mô tả danh mục: ");
            String content = scanner.nextLine();
            boolean check= ShopValidate.checkEmptyString(content);
            if (check){
                catalog.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui lòng chọn trạng thái của loại hoa: ");
            System.out.println("1. Hoạt động .");
            System.out.println("2. Không hoạt động.");
            System.out.print("lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            String check = ShopValidate.checkInputStatus(choice);
            if (check.equals("1")){
                catalog.setCatalogStatus(true);
                break;
            }else if (check.equals("2")){
                catalog.setCatalogStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        do {
            try {
                System.out.println("Nhập vào độ ưu tiên: ");
                int priority = Integer.parseInt(scanner.nextLine());
                catalog.setPriority(priority);
                break;
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }

        }while (true);

        return catalog;
    }

    @Override
    public void displayData(Catalog catalog) {
        String status = "";
        if (catalog.isCatalogStatus()){
            status = " Hoạt động ";
        }else {
            status = "Không hoạt động";
        }
        System.out.printf("Mã danh mục: %-10s  Tên Danh Mục: %-30s  Trạng thái: %-20s\n",catalog.getCatalogId(),catalog.getCatalogName(),status);
        System.out.printf("Mô tả: %s\n",catalog.getContent());
    }

    @Override
    public boolean delete(String id) {
        List<Catalog> list = readFromFile();
        for (Catalog cat :list) {
            if (cat.getCatalogId().equals(id)){
                cat.setCatalogStatus(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public void searchByName(String name) {
        List<Catalog> list = readFromFile();
        for (Catalog catalog:list) {
            if (catalog.getCatalogName().contains(name)){
                displayData(catalog);
            }
        }

    }
    public static void inputCatalog (Scanner scanner){
        CatalogImpl catalogImpl = new CatalogImpl();
        System.out.println("Nhập vào số danh mục sản phẩm bạn muốn nhập lần này: ");
        int number = 0;
        while (true){
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }

        for (int i = 0; i < number; i++) {
            System.out.println("Nhập vào loài hoa thứ " + (i+1));
            Catalog catalog = new Catalog();
            catalog = catalogImpl.inputData(scanner);
            boolean check = catalogImpl.create(catalog);
            if (check){
                System.out.println("Đã thêm mới thành công !");
            }else {
                System.err.println("Thêm mới thất bại !!!");
            }
        }
    }
    public static void displayCatalog (){
        CatalogImpl catalogImpl = new CatalogImpl();
        List<Catalog> list = readFromFile();
        for (Catalog catalog: list) {
            catalogImpl.displayData(catalog);
        }
    }
    public static void deleteCatalog (Scanner scanner){
        CatalogImpl catalogImpl = new CatalogImpl();
        List<Catalog> list = readFromFile();
        while (true){
            System.out.println("Nhập vào mã loài hoa cần sửa : ");
            String id = scanner.nextLine();
            boolean check = ShopValidate.checkEmptyString(id);
            if (check){
                check = catalogImpl.delete(id);
                if (check){
                    System.out.println("Xóa thành công ! ");
                    break;
                }else {
                    System.err.println("Không tìm thấy mã này vui lòng nhập vào mã khác !!!");
                }
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }
    }
    public static void searchCatalogByName (Scanner scanner){
        CatalogImpl catalogImpl = new CatalogImpl();
        List<Catalog> list = readFromFile();
        System.out.println("Nhập vào tên danh mục sản phẩm bạn muốn tìm kiếm : ");
        String name = scanner.nextLine();
         catalogImpl.searchByName(name);
    }
}
