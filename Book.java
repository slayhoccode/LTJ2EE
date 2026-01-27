/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.btbuoi1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author phamt
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private long price;

    public Book() {

    }

    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập ID sách");
        this.id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập tên sách");
        this.title = sc.nextLine();
        System.out.println("Nhập tác giả sách");
        this.author = sc.nextLine();
        System.out.println("Nhập giá sách");
        this.price = sc.nextLong();
    }

    public void output() {
        String msg = """
                     Book: id= %d, title= %s, author= %s, price= %d
                     """.formatted(id, title, author, price);
        System.out.println(msg);
    }

    public static void main(String[] args) {
        List<Book> listbook = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String msg = """
                     Chương trình quản lý sách
                        1. Thêm 1 cuốn sách
                        2. Xoá 1 cuốn sách
                        3. Thay đổi sách
                        4. Xuất thông tin
                        5. Tim sách lập trình
                        6. Lấy sách tối đa theo giá
                        7. Tìm sách theo tác giả
                        0. Thoát
                     Chọn chức năng: 
                     """;
        int chon = 0;
        do {
            System.out.println(msg);
            chon = s.nextInt();
            switch (chon) {
                case 1 -> {
                    Book newbook = new Book();
                    newbook.input();
                    listbook.add(newbook);
                }
                case 2 -> {
                    System.out.println("Nhập ID sách cần xoá");
                    int bookid = s.nextInt();
                    s.nextLine();
                    Book find = listbook.stream().filter(p -> p.getId() == bookid).findFirst().orElseThrow();
                    listbook.remove(find);
                    System.out.println("Đã xoá sách có ID: " + bookid);
                }
                case 3 -> {
                    System.out.println("Nhập vào ID sách cần thay đổi");
                    int bookid = s.nextInt();
                    s.nextLine();
                    Book find = listbook.stream().filter(p -> p.getId() == bookid).findFirst().orElseThrow();
                    if (find == null) {
                        System.out.println("Không tìm thấy sách có ID: " + bookid);
                    } else {
                        System.out.println("Nhập tên sách mới:");
                        String newTitle = s.nextLine();
                        find.setTitle(newTitle);

                        System.out.println("Đã cập nhật tên sách thành công!");
                    }
                }
                case 4 -> {
                    System.out.println("\n Xuất thông tin danh sách sách: ");
                    listbook.forEach(p -> p.output());
                }
                case 5 -> {
                    listbook.stream()
                            .filter(b -> b.getTitle() != null
                            && b.getTitle().toLowerCase().contains("lập trình"))
                            .forEach(Book::output);
                }
                case 6 -> {
                    System.out.println("Nhập số lượng sách muốn lấy:");
                    int n = s.nextInt();

                    listbook.stream()
                            .sorted((b1, b2) -> Long.compare(b2.getPrice(), b1.getPrice()))
                            .limit(n)
                            .forEach(Book::output);
                }
                case 7 -> {
                    s.nextLine(); 
                    System.out.println("Nhập số lượng tác giả cần tìm:");
                    int n = Integer.parseInt(s.nextLine());

                    Set<String> authors = new HashSet<>();
                    for (int i = 0; i < n; i++) {
                        System.out.println("Nhập tên tác giả " + (i + 1) + ":");
                        authors.add(s.nextLine().toLowerCase());
                    }

                    listbook.stream()
                            .filter(b -> b.getAuthor() != null
                            && authors.contains(b.getAuthor().toLowerCase()))
                            .forEach(Book::output);
                }

            }
        } while (chon != 0);
    }
}
