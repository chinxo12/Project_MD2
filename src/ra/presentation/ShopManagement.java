package ra.presentation;

import ra.bussiness.config.ShopConstant;
import ra.bussiness.config.ShopMessage;
import ra.bussiness.entity.TypeOfFlower;
import ra.bussiness.entity.User;
import ra.bussiness.file.FileAll;
import ra.bussiness.impl.*;

import java.io.File;
import java.util.*;

public class ShopManagement {
    static UserImpl userImpl = new UserImpl();
    static TypeOfFlowerImpl tOFlowerImpl = new TypeOfFlowerImpl();
    static FileAll fileAll = new FileAll();
    static   TypeOfFlowerImpl typeOfFlowerImpl = new TypeOfFlowerImpl();
    static FlowerImpl  flowerImpl = new FlowerImpl();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> listUser = new ArrayList<>();
        Date date = new Date();
        User user = new User(1, "admin", "123456", "Trần Đình Tín", "chinxo12@gmail.com", "84383468186",1, true ,date);
        listUser.add(user);
        UserImpl.writeFromFile(listUser);
        do {
            System.out.println("*********************** CỬA HÀNG HOA chưa đặt tên ********************");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    do {
                        User user1 = login(scanner);
                        if (user1.getPermission()==1){
                            adminMenu(scanner);
                            break;
                        }else {
                            userMenu(scanner);
                        }
                    }while (true);
                    break;
                case 2:
                    regester(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
            }
        }while (true);
    }
    public static void adminMenu(Scanner scanner){
        boolean checkExit = true;
        do {
            System.out.println("*********************** QUẢN LÝ CỬA HÀNG ********************");
            System.out.println("1. Quản lý loại hoa.");
            System.out.println("2. Quản lý loài hoa.");
            System.out.println("3. Quản lý danh mục Sản phẩm.");
            System.out.println("4. Quản lý Sản phẩm. ");
            System.out.println("5. Quản lý người dùng.");
            System.out.println("6. Quản lý đơn hàng.");
            System.out.println("7. Quản lý phản hồi của khách hàng.");
            System.out.println("8. Thoát.");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    typeOfFlowerManagement(scanner);
                    break;
                case 2:
                    flowerMenu(scanner);
                    break;
                case 3:
                    catalogMenu(scanner);
                    break;
                case 4:
                    productMenu(scanner);
                    break;
                case 5:
                    userManagementMenu(scanner);
                    break;
                case 6:

                    break;
                case 7:
                    feedback();
                    break;
                case 8:
                    checkExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-8 !!!");
            }

        }while (checkExit);
    }
    public static void typeOfFlowerManagement (Scanner scanner){
        boolean exit = true;
        do {
            System.out.println("***************QUẢN LÝ LOẠI HOA**********************");
            System.out.println("1. Danh sách loại hoa.");
            System.out.println("2. Tạo mới loại hoa. ");
            System.out.println("3. Cập nhật thông tin loại hoa");
            System.out.println("4. Xóa loại hoa.");
            System.out.println("5. Tim kiếm loại hoa theo tên hoặc mã. ");
            System.out.println("6. Thoát.");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    displayTypeOfFlower();
                    break;
                case 2:
                    inputTypeOfFlower(scanner);
                    break;
                case 3:

                    break;
                case 4:
                    deleteTypeOfFlower(scanner);
                    break;
                case 5:
                    searchTypeFlowerByNameOrId(scanner);
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-6 !!!");
            }
        }while (exit);
    }
    public  static void flowerMenu (Scanner scanner){
        boolean exit = true;
        do {
            System.out.println("***************QUẢN LÝ LOÀI HOA**********************");
            System.out.println("1. Danh sách loài hoa.");
            System.out.println("2. Tạo mới loài hoa. ");
            System.out.println("3. Cập nhật thông tin loài hoa");
            System.out.println("4. Xóa loài hoa.");
            System.out.println("5. Tim kiếm loại hoa theo tên hoặc giá tiền. ");
            System.out.println("6. Thoát.");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    FlowerImpl.displayFlower();
                    break;
                case 2:
                    FlowerImpl.inputFlower(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    FlowerImpl.deleteFlower(scanner);
                    break;
                case 5:
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-6 !!!");
            }
        }while (exit);
    }
    public static void catalogMenu (Scanner scanner){
        boolean exit = true;
        do {
            System.out.println("***************QUẢN LÝ DANH MỤC SẢN PHẨM **********************");
            System.out.println("1. Danh sách danh mục.");
            System.out.println("2. Tạo mới danh mục. ");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục.");
            System.out.println("5. Tim kiếm loại hoa theo tên  ");
            System.out.println("6. Thoát.");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    CatalogImpl.displayCatalog();
                    break;
                case 2:
                    CatalogImpl.inputCatalog(scanner);
                    break;
                case 3:

                    break;
                case 4:
                    CatalogImpl.deleteCatalog(scanner);
                    break;
                case 5:
                    CatalogImpl.searchCatalogByName(scanner);
                    break;
                case 6:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-6 !!!");
            }
        }while (exit);
    }
    public static void productMenu (Scanner scanner){
        boolean exit = true;
        do {
            System.out.println("***************QUẢN LÝ SẢN PHẨM**********************");
            System.out.println("1. Danh sách sản phẩm.");
            System.out.println("2. Tạo mới sản phẩm. ");
            System.out.println("3. Cập nhật thông tin sản phẩm.");
            System.out.println("4. Xóa loài hoa.");
            System.out.println("5. Thoát.");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    ProductImpl.displayProduct();
                    break;
                case 2:
                    ProductImpl.inputProduct(scanner);
                    break;
                case 3:

                    break;
                case 4:
                    ProductImpl.deleteProduct(scanner);
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-5 !!!");
            }
        }while (exit);
    }
    public  static  void userManagementMenu (Scanner scanner){
        boolean exit = true;
        do {
            System.out.println("***************QUẢN LÝ NGƯỜI DÙNG**********************");
            System.out.println("1. Danh sách người dùng.");
            System.out.println("2. Thêm tài khoản quản trị. ");
            System.out.println("3. Cập nhật tài khoản quản trị.");
            System.out.println("4. Tìm kiếm tài khoản khách hàng.");
            System.out.println("5. Thoát.");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-5 !!!");
            }
        }while (exit);
    }
    public static void userMenu (Scanner scanner){
        boolean checkExit = true;
        do {
            System.out.println("*************************** CỬA HÀNG HOA BẤT ỔN ************************** ");
            System.out.println("1. Xem danh sách các sản phẩm.");
            System.out.println("2. Đặt mua các sản phẩm.");
            System.out.println("3. Đổi mật khẩu tài khoản.");
            System.out.println("4. Phản hồi với cửa hàng.");
            System.out.println("5. Đăng xuất.");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println(ShopMessage.PLEASE_PRESS_NUMBER);
            }
            System.out.print("\n");
            switch (choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    checkExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-5 !!!");
            }

        }while (checkExit);
    }
    public static void feedback (){

    }
    public static User login (Scanner scanner){
        List<User> listUser = UserImpl.readFromFile();
        System.out.print("Tên đăng nhập: ");
        String userName =  scanner.nextLine();
        System.out.println("Mật khẩu: ");
        String password = scanner.nextLine();
        for (User user:listUser ) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public static void regester(Scanner scanner){
        User user = userImpl.inputData(scanner);
       boolean check = userImpl.create(user);
       if(check){
           System.out.println("Dang ky thanh cong");
       }else {
           System.err.println("Dang ky khong thanh cong");
       }
    }
    public static void displayTypeOfFlower (){
        List<TypeOfFlower> typeOfFlowerList = TypeOfFlowerImpl.readFromFile();
        Collections.sort(typeOfFlowerList, new Comparator<TypeOfFlower>() {
            @Override
            public int compare(TypeOfFlower o1, TypeOfFlower o2) {
                return (int) (o1.getTypeFlowerName().charAt(5) - o2.getTypeFlowerName().charAt(5));
            }
        });
        for (TypeOfFlower typeOfFlower: typeOfFlowerList) {
                typeOfFlowerImpl.displayData(typeOfFlower);
        }
    }
    public static void inputTypeOfFlower (Scanner scanner){
        System.out.println("Nhập vào số lượng loại hoa bạn muốn nhập lần này: ");
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
            System.out.println("Nhập vào loại hoa thứ " + (i+1));
            TypeOfFlower typeOfFlower = new TypeOfFlower();
            typeOfFlower = typeOfFlowerImpl.inputData(scanner);
            boolean check = typeOfFlowerImpl.create(typeOfFlower);
            if (check){
                System.out.println("Đã thêm mới thành công !");
            }else {
                System.err.println("Thêm mới thất bại !!!");
            }
        }
    }
    public static void deleteTypeOfFlower (Scanner scanner){

        int id = 0;
        while (true){
            try {
                System.out.println("Nhập vào mã loại hoa cần xóa : ");
                id = Integer.parseInt(scanner.nextLine());
                boolean check = typeOfFlowerImpl.delete(id);
                if (check){
                    System.out.println("Đã xóa thành công !");
                    break;
                }else {
                    System.err.println("Xóa không thành công ");
                }

            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }
    }
    public static void searchTypeFlowerByNameOrId (Scanner scanner){
        do {
            System.out.println("Nhập vào tên loại hoa hoặc mã loại hoa ");
            String search = scanner.nextLine();
            boolean check = typeOfFlowerImpl.searchByNameOrId(search,search);
            if (check){
                break;
            }else {
                System.err.println("Vui lòng nhập lại !!!");
            }
        }while (true);
    }

}
