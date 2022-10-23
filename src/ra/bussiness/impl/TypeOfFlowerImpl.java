package ra.bussiness.impl;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import ra.bussiness.config.ShopConstant;
import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.ShopValidate;
import ra.bussiness.design.IShop;
import ra.bussiness.design.ITypeOfFlower;
import ra.bussiness.entity.TypeOfFlower;
import ra.bussiness.entity.User;
import ra.bussiness.file.FileAll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TypeOfFlowerImpl implements IShop<TypeOfFlower,Integer>, ITypeOfFlower {
    public static List<TypeOfFlower> readFromFile (){
        FileAll<TypeOfFlower> fileAll = new FileAll<>();
        List<TypeOfFlower> list = fileAll.readFromFile(ShopConstant.TYPEOFFLOER_URL);
        return list;

    }
    public static boolean writeFromFile (List<TypeOfFlower> list){
        FileAll<TypeOfFlower> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,ShopConstant.TYPEOFFLOER_URL);
        return returnData;
    }
    @Override
    public boolean create(TypeOfFlower typeOfFlower) {
        List<TypeOfFlower> list = readFromFile();
        if (list==null){
            list = new ArrayList<>();
        }
        list.add(typeOfFlower);
        boolean result = writeFromFile(list);
        return result;
    }

    @Override
    public void update(Scanner scanner) {

    }

    @Override
    public List<TypeOfFlower> findAll() {
        return readFromFile();
    }

    @Override
    public TypeOfFlower inputData(Scanner scanner) {
        List<TypeOfFlower> list = readFromFile();
        if (list==null){
            list = new ArrayList<>();
        }
        TypeOfFlower toFlower = new TypeOfFlower();
        toFlower.setTypeFlowerId(list.size()+1);
        do {
            System.out.print("Nhập vào tên loại hoa: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = ShopValidate.checkValidateName(name);
            if (check){
                toFlower.setTypeFlowerName(name);
                break;
            }else {
                System.err.println(ShopMessage.NAME_WRONG);
            }
        }while (true);
        do {
            System.out.println("Nhập vào mô tả về loại hoa: ");
            String content = scanner.nextLine();
            boolean check = ShopValidate.checkEmptyString(content);
            if (check){
                toFlower.setContent(content);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.println("Vui lòng chọn trạng thái của loại hoa: ");
            System.out.println("1. Có sử dụng.");
            System.out.println("2. Không sử dụng.");
            System.out.print("lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            String check = ShopValidate.checkInputStatus(choice);
            if (choice.equals("1")){
                toFlower.setTypeFlowerStatus(true);
                break;
            }else if (choice.equals("2")){
                toFlower.setTypeFlowerStatus(false);
                break;
            }else {
                System.err.println(ShopMessage.PLEASE_CHOOSE_1_OR_2);
            }
        }while (true);
        return toFlower;
    }


    @Override
    public void displayData(TypeOfFlower typeFlower ) {
        String status  = "";
        if (typeFlower.isTypeFlowerStatus()){
            status = "Còn bán";
        }else {
            status = "Không còn bán";
        }
            System.out.printf("Mã loại hoa: %-5d Tên loại hoa: %-30s Trạng thái: %s\n",typeFlower.getTypeFlowerId(),typeFlower.getTypeFlowerName(),status);
            System.out.printf("Mô tả: %s\n",typeFlower.getContent());
    }

    @Override
    public boolean delete(Integer integer) {
        List<TypeOfFlower> list = readFromFile();
        for (TypeOfFlower tyFlower :list) {
            if (tyFlower.getTypeFlowerId()==integer){
                tyFlower.setTypeFlowerStatus(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean searchByNameOrId(String name, String id) {
       List<TypeOfFlower> list = readFromFile();
        for (TypeOfFlower typeF :list) {
            if (typeF.getTypeFlowerName().contains(name) || (typeF.getTypeFlowerId()+"").contains(id)){
                displayData(typeF);
                return true;
            }
        }
        return false;
    }
}
