package ra.bussiness.design;

import java.util.List;
import java.util.Scanner;

public interface IShop <T,E>{
    boolean create(T t);
    void update (Scanner scanner);
    List<T>  findAll ();
    T inputData (Scanner scanner);
    void displayData (T t);
    boolean delete(E e);
}
