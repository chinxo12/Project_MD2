package ra.bussiness.impl;

import ra.bussiness.config.ShopConstant;
import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.ShopValidate;
import ra.bussiness.design.IFlower;
import ra.bussiness.design.IShop;
import ra.bussiness.entity.Flower;
import ra.bussiness.entity.TypeOfFlower;
import ra.bussiness.entity.User;
import ra.bussiness.file.FileAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlowerImpl implements IShop<Flower,String> , IFlower {
    public static List<Flower> readFromFile (){
        FileAll<Flower> fileAll = new FileAll<>();
        List<Flower> flowerList =  fileAll.readFromFile(ShopConstant.FLOWER_URL);
        return flowerList;
    }
    public static boolean writeFromFile (List<Flower> list){
        FileAll<Flower> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,ShopConstant.FLOWER_URL);
        return returnData;
    }
    @Override
    public boolean create(Flower flower) {
        List<Flower> flowerList = readFromFile();
        if (flowerList==null){
            flowerList = new ArrayList<>();
        }
        flowerList.add(flower);
        boolean result = writeFromFile(flowerList);
        return result;
    }

    @Override
    public void update(Scanner scanner) {

    }

    @Override
    public List<Flower> findAll() {
        return readFromFile();
    }

    @Override
    public Flower inputData(Scanner scanner) {
        List<Flower> listFlower = readFromFile();
        if (listFlower==null){
            listFlower = new ArrayList<>();
        }
        Flower flower = new Flower();
        do {
            System.out.print("Nhập vào mã loài hoa: ");
            String id = scanner.nextLine();
            System.out.print("\n");
            boolean check = ShopValidate.checkId5(id);
            if (check){
                check = ShopValidate.checkFlowerId(id);
                if (check){
                    for (Flower flowers :listFlower) {
                        if (flowers.getFlowerId().equals(id)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        flower.setFlowerId(id);
                        break;
                    }else {
                        System.err.println(ShopMessage.THIS_ID_ALREADY_EXISTS);
                    }
                }else {
                    System.err.println("Mã loài hoa bắt đầu bằng ký tự 'H' !!!");
                }
            }else {
                System.err.println(ShopMessage.ID_LENGTH_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nhập vào tên loài hoa: ");
            String name = scanner.nextLine();
            boolean check = ShopValidate.checkValidateName(name);
            if (check){
                for (Flower flowers :listFlower) {
                    if (flowers.getFlowerName().equals(name)){
                        check = false;
                        break;
                    }
                }
                if (check){
                    flower.setFlowerName(name);
                    break;
                }else {
                    System.err.println(ShopMessage.THIS_NAME_ALREADY_EXISTS);
                }
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Vui lòng chọn loại hoa !");
            List<TypeOfFlower> listTypeFlower = TypeOfFlowerImpl.readFromFile();
            System.out.printf("%-10s%-30s\n","STT"," Tên loại hoa ");
            for (int i = 0; i < listTypeFlower.size(); i++) {
                System.out.printf("%-10d%-30s\n",(i+1),listTypeFlower.get(i).getTypeFlowerName());
            }
            System.out.print("Vui lòng chọn số tương ứng: ");
            int choice = 0 ;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice <0 || choice>listTypeFlower.size()){
                    System.err.println("Vui lòng nhập từ 1 - " + listTypeFlower.size());
                }else {
                    flower.setTypeOfFlower(listTypeFlower.get(choice-1));
                    break;
                }
            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }while (true);
        do {
            System.out.println("Nhập vào giá tiền nhập vào: ");
            String price = scanner.nextLine();
            boolean check = ShopValidate.checkInputPrice(price);
            if (check){
                flower.setImportPrince(Float.parseFloat(price));
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nhập vào giá bán: ");
            String price = scanner.nextLine();
            boolean check = ShopValidate.checkInputPrice(price);
            if (check){
                if (Float.parseFloat(price)>flower.getImportPrince()){
                    flower.setExportPrice(Float.parseFloat(price));
                    break;
                }else {
                    System.err.println(ShopMessage.EXPORT_PRICE_MORE_THAN_IMPORT_PRINCE);
                }

            }else {
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
        }while (true);
        do {
            System.out.println("Nhập vào mô tả loài hoa:  ");
            String content = scanner.nextLine();
            boolean check = ShopValidate.checkEmptyString(content);
            if (check){
                flower.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui lòng chọn trạng thái của loại hoa: ");
            System.out.println("1. Còn hoa.");
            System.out.println("2. Hết hoa.");
            System.out.print("lựa chọn của bạn: ");
            System.out.print("\n");
            String choice = scanner.nextLine();
            String check = ShopValidate.checkInputStatus(choice);
            if (check.equals("1")){
                flower.setFlowerStatus(true);
                break;
            }else if (check.equals("2")){
                flower.setFlowerStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return flower;
    }

    @Override
    public void displayData(Flower flower) {
        String status  = "";
        if (flower.isFlowerStatus()){
            status = "Còn hoa";
        }else {
            status = "hết hoa";
        }
        System.out.printf("Mã loại hoa: %-5s Tên loại hoa: %-30s Trạng thái: %s\n",flower.getFlowerId(),flower.getFlowerName(),status);
        System.out.printf("Mô tả: %s\n",flower.getContent());

    }

    @Override
    public boolean delete(String str) {
        List<Flower> list = readFromFile();
        for (Flower flower :list) {
            if (flower.getFlowerId().equals(str)){
                flower.setFlowerStatus(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean searchByNameOrPrice(String str, Float price) {
        List<Flower> list = readFromFile();
        for (Flower flower :list) {
            if (flower.getFlowerName().toLowerCase().contains(str) || flower.getExportPrice()<price){
                displayData(flower);
                return true;
            }
        }
        return false;
    }
    public static void inputFlower (Scanner scanner){
        FlowerImpl flowerImpl = new FlowerImpl();
        System.out.println("Nhập vào số lượng loài hoa bạn muốn nhập lần này: ");
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
            Flower flower = new Flower();
            flower = flowerImpl.inputData(scanner);
            boolean check = flowerImpl.create(flower);
            if (check){
                System.out.println("Đã thêm mới thành công !");
            }else {
                System.err.println("Thêm mới thất bại !!!");
            }
        }
    }
    public static void displayFlower (){
        FlowerImpl flowerImpl = new FlowerImpl();
        List<Flower> list = readFromFile();
        for (Flower flower: list) {
            flowerImpl.displayData(flower);
        }
    }
    public static void deleteFlower (Scanner scanner){
        FlowerImpl flowerImpl = new FlowerImpl();
        List<Flower> list = readFromFile();
       while (true){
           System.out.println("Nhập vào mã loài hoa cần sửa : ");
           String id = scanner.nextLine();
           boolean check = ShopValidate.checkEmptyString(id);
           if (check){
               check = flowerImpl.delete(id);
               if (check){
                   System.out.println("Xóa thành công ! ");
                   break;
               }else {
                   System.err.println("Xóa thất bại !!!");
               }
           }else {
               System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
           }
       }
    }
}
