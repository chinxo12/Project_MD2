package ra.bussiness.impl;

import ra.bussiness.config.ShopConstant;
import ra.bussiness.config.ShopMessage;
import ra.bussiness.config.ShopValidate;
import ra.bussiness.design.IShop;
import ra.bussiness.design.IUser;
import ra.bussiness.entity.User;
import ra.bussiness.file.FileAll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements IShop <User , String> {
    public static List<User> readFromFile (){
        FileAll<User> fileAll = new FileAll<>();
        List<User> listUser =  fileAll.readFromFile(ShopConstant.USER_URL);
        return listUser;
    }
    public static boolean writeFromFile (List<User> list){
        FileAll<User> fileAll = new FileAll<>();
        boolean returnData = fileAll.writeFromFile(list,ShopConstant.USER_URL);
        return returnData;
    }


    @Override
    public boolean create(User user) {
        List<User> listUser = readFromFile();
        if (listUser==null){
            listUser = new ArrayList<>();
        }
        listUser.add(user);
        boolean result = writeFromFile(listUser);
        return result;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return readFromFile();
    }

    @Override
    public User inputData(Scanner scanner) {
        List<User> listUser = readFromFile();
        if (listUser.size()==0){
            listUser = new ArrayList<>();
        }
        User user = new User();
        user.setUserId(listUser.size()+1);
        do {
            System.out.print("Nhập vào tên tài khoản: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = ShopValidate.checkUserNameLength(name);
            if (check){
                check = ShopValidate.checkValidateUserName(name);
                if (check){
                    for (User userEx :listUser) {
                        if (userEx.getUserName().equals(name)){
                            check = false;
                            break;
                        }
                    }
                    if (check){
                        user.setUserName(name);
                        break;
                    }else {
                        System.err.println(ShopMessage.ALREAD_THIS_USERNAME);
                    }
                }
            }else {
                System.err.println(ShopMessage.USER_NAME_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập vào mật khẩu: ");
            String password = scanner.nextLine();
            System.out.print("\n");
            boolean check = ShopValidate.checkPassword(password);
            if (check){
                System.out.print("Nhập lại mật khẩu: ");
                String repeatPasswood = scanner.nextLine();
                check = ShopValidate.checkPassword(repeatPasswood);
                if (check){
                    if (password.equals(repeatPasswood)){
                        user.setPassword(password);
                        break;
                    }else {
                        System.err.println(ShopMessage.REPEAT_PASSWORD_WRONG);
                    }
                }
            }else {
                System.err.println(ShopMessage.PASSWORD_LENGTH);
            }
        }while (true);
        do {
            System.out.print("Nhập họ và tên của bạn: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            boolean check = ShopValidate.checkEmptyString(name);
            if (check){
                user.setFullName(name);
                break;
            }else {
                System.err.println(ShopMessage.DO_NOT_LEAVE_IT_BLANK);
            }
        }while (true);
        do {
            System.out.print("Nhập vào email:");
            String email = scanner.nextLine();
            System.out.print("\n");
            boolean check = ShopValidate.checkEmail(email);
            if (check){
                user.setEmail(email);
                break;
            }else {
                System.err.println(ShopMessage.EMAIL_WRONG);
            }
        }while (true);
        do {
            System.out.print("Nhập vào số điện thoại (Số điện thoại bắt đầu bằng 84) :  ");
            String phoneNumber = scanner.nextLine();
            System.out.println("\n");
            boolean checkPhone = ShopValidate.checkPhoneNumber(phoneNumber);
               if (checkPhone){
                    user.setPhoneNumber(phoneNumber);
                    break;
                }else {
                    System.err.println(ShopMessage.PHONE_NUMBER_WRONG);
                }

        }while (true);
        Date date = new Date();
        user.setDate(date);
        return user;

    }

    @Override
    public void displayData(User member) {
        List<User> list = readFromFile();

        Date date = new Date();
        String status = "";
        if (member.isUserStatus()){
            status = "Còn hoạt động";
        }else {
            status = "không còn hoạt động";
        }
        String permision = "" ;
        if (member.getPermission()==1){
            permision = "Admin";
        }else if (member.getPermission()==2){
            permision = "Dimond";
        }else if (member.getPermission()==3){
            permision = "Gold";
        }else {
            permision = "Member";
        }

        System.out.printf("%-10d%-20s%-30s%-15d%-20s%-25s%-10s%-20s",member.getUserId(),member.getUserName(),member.getFullName(),member.getPhoneNumber(),member.getEmail(),permision,status);
    }

    @Override
    public boolean delete(String str) {
      List<User> userList = readFromFile();
        for (User user :userList) {
            if (user.getUserName().equals(str)){
                user.setUserStatus(false);
                return  true;
            }
        }
        return false;
    }
}
