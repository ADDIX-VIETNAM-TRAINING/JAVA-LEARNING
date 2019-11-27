/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplethreading;

/**
 *
 * @author Thuan.Truong
 */
public class MultipleThreading {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ExcelManager manager = new ExcelManager("D:\\Working\\testing.xlsx");
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ExcelManager.COL_NUMBER; i++) {
                manager.writeToFile(0, i, 0, "Thread 1");
                System.out.println("Thread 1 write to file: " + i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ExcelManager.COL_NUMBER; i++) {
                manager.writeToFile(0, i, 1, "Thread 2");
                System.out.println("Thread 2 write to file: " + i);
            }
        });
        thread1.start();
        thread2.start();
    }
    
}
