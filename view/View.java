package learnoop2.bai1.view;

import learnoop2.bai1.controller.*;
import learnoop2.bai1.model.Book;
import learnoop2.bai1.model.BookReaderManagement;
import learnoop2.bai1.model.Reader;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        int choice = 0;
        String booksFileName = "BOOK.DAT";
        String readersFileName = "READER.DAT";
        String brmFileName = "BRM.DAT";

        DataController controller = new DataController();
       ControllerUltility ultility = new ControllerUltility();

        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Reader> readers = new ArrayList<Reader>();
        ArrayList<BookReaderManagement> brms = new ArrayList<BookReaderManagement>();

        boolean isReaderChecked = false;
        boolean isBookChecked = false;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("_______________MENU_______________");
            System.out.println("1. Thêm một đầu sách vào file.");
            System.out.println("2. Hiển thị danh sách các sách có trong file.");
            System.out.println("3. Thêm một bạn đọc vào file.");
            System.out.println("4. Hiển thị danh sách các bạn đọc có trong file.");
            System.out.println("5. Lập thông tin quản lý mượn.");
            System.out.println("6. Sắp xếp.");
            System.out.println("7. Tìm kiếm thông tin quản lý mượn theo tên bạn đọc.");
            System.out.println("0. Thoát khỏi ứng dụng.");
            System.out.println("Bạn chọn ? ");

            choice = scanner.nextInt();
           

            switch (choice) {
                case 0:
                    System.out.println("_____________________________________________");
                    System.out.println("Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!");
                    break;

                case 1:
                    if (!isBookChecked) {
                        checkBookID(controller, booksFileName);
                        isBookChecked = true;
                    }
                    String[] specs = {"Science", "Art", "Economic", "IT"};
                    String bookName, author, spec;
                    int year, quan, sp;
                    System.out.println("Nhập tên sách: ");
                    bookName = scanner.nextLine();

                    System.out.println("Nhập tên tác giả: ");
                    author = scanner.nextLine();

                    do {
                        System.out.println("Nhập thể loại sách: ");
                        System.out.println("1. Science.\n2. Art.\n3. Economic.\n4. IT.");
                        sp = scanner.nextInt();
                    } while (sp < 1 || sp > 4);

                    spec = specs[sp - 1];

                    System.out.println("Nhập năm xuất bản: ");
                    year = scanner.nextInt();

                    System.out.println("Nhập số lượng: ");
                    quan = scanner.nextInt();
                    //public Book(int bookID, String bookName, String author,
                    //                String specialization, int publishYear, int quantity)
                    Book book = new Book(0, bookName, author, spec, year, quan);
                    controller.writeBookToFile(book, booksFileName);
                    break;

                case 2:
                    books = controller.readBooksFromFile(booksFileName);
                    showBookInfo(books);
                    break;

                case 3:
                    if (!isReaderChecked) {
                        checkReaderID(controller, readersFileName);
                        isReaderChecked = true;
                    }
                    String fullName, address, phoneNum;
                    System.out.println("Nhập họ và tên: ");
                    fullName = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Nhập địa chỉ: ");
                    address = scanner.nextLine();

                    do {
                        System.out.println("Nhập số điện thoại: ");
                        phoneNum = scanner.nextLine();
                    } while (!phoneNum.matches("\\d{10}"));

                    //Reader(int readerID, String fullName, String address, String phoneNumber)
                    Reader reader = new Reader(0, fullName, address, phoneNum);
                    controller.writeReaderToFile(reader, readersFileName);
                    break;

                case 4:
                    readers = controller.readReadersFromFile(readersFileName);
                    showReadersInfo(readers);
                    break;

                case 5:
                    //B0: khoi tao danh sach
                    readers = controller.readReadersFromFile(readersFileName);
                    books = controller.readBooksFromFile(booksFileName);
                    brms = controller.readBRMsFromFile(brmFileName);
           
                    //B1:
                    int readerID, booksID;
                    boolean isBorrowable = false;
                    boolean flag=false;
                    boolean flagq=false;
                    boolean flat=true;
                    do {
                        showReadersInfo(readers);
                        System.out.println("________________________________________________");
                        System.out.println("Nhập mã bạn đọc, nhập 0 để bỏ qua: ");
                        readerID = scanner.nextInt();
                        if (readerID == 0) {
                            break;// tat ca ban doc da dc muon du sach quy dinh
                        }
                        else if(readerID!=0){
                            for(int i=0 ; i<readers.size() ;i++)
                            {
                                if(readerID!=readers.get(i).getReaderID()){
                                            flagq=false;
                                }else{
                                    isBorrowable = checkBorrowed(brms, readerID);
                                    if (isBorrowable) {
                                       flag=true;
                                    } else {
                                        System.out.println("Bạn đọc này đã mượn đủ số lượng cho phép.");
                                      readerID=0;
                                      flat=false;
                                    }

                                }
                            }
                        }
                        if(flag==true)
                        break;
                        if(!flat)
                        break;

                            if(flagq==false)
                            {
                                 System.out.println("nhap ma sai nhap lai");
                            }
                        
                    } while (true);
                    System.out.println("");
                    if(readerID==0)
                    {
                        break;
                    }else{
                    //B2:
                    boolean isFull = false;
                    boolean flag1=false;
                 
                    boolean isFull2=false;
                    boolean uuu=false;
                    do {
                       
                        showBookInfo(books);
                        System.out.println("________________________________________________");
                        System.out.println("Nhập mã sách, nhập 0 để bỏ qua: ");
                        booksID = scanner.nextInt();

                        if(booksID == 0){
                            break;
                        }
                        else if(booksID!=0)
                        {
                            for(int j=0 ; j<books.size() ;j++)
                            {
                                if(booksID!=books.get(j).getBookID())
                                {       

                                }else{

                                    isFull = checkFull(brms, readerID, booksID);// true nếu đã mượn đủ 3
                                    if(isFull){
                                        System.out.println("Vui lòng chọn đầu sách khác!");
                                    }else{
                                       flag1=true;
                                    }
                                }
                            }
                        }
                        if(flag1==true)
                        break;
                        

                    } while (true);
                    if(booksID==0&&readerID==0){
                        break;
                    }else{
                    //B3:
                    int total=0;
                    do{
                        total = getTotal(brms, readerID, booksID);
                        System.out.println("Nhập số lượng mượn, tối đa 3 cuốn ( đã mượn " + total + "): (or Enter 0 de bo qua)");
                        int x = scanner.nextInt();
                        if(x==0)
                        break;
                        if((x + total) >= 1 && (x + total) <= 3){
                            total+= x;
                           
                        if(x!=0)
                        {
                           isFull2= checkBorrowed(brms ,readerID , total);
                           if(isFull2){
                               System.out.println("Nhập quá số lượng quy định. Vui lòng nhập lại.");
                              
                           }else{
                            uuu=true;   

                        }
                        }
                        }
                        if(uuu==true)
                        break;
                    } while(true);
                    scanner.nextLine(); // đọc bỏ dòng có chứa số

                    
                    System.out.println("Nhập tình trạng: (nhap [next] de bo qua");
                    String status = "";
                    status = scanner.nextLine();
                    if(status.equals("next"))
                    {
                        break;
                    }else{ 
                     
                    //B4:
                    //Book book, Reader reader,
                    //                 int numOfBorrow, String state, int totalBorrowed
                    Book currentBook = getBook(books, booksID);
                    Reader currentReader = getReader(readers, readerID);
                   
                   
                    BookReaderManagement b = new BookReaderManagement(currentBook,
                            currentReader, total, status, 0);
                    // B4:
                  
                    brms = ultility.updateBRMInfo(brms, b); // cập nhật danh sách quản lý mượn
                    controller.updateBRMFile(brms, brmFileName); // cập nhật file dl

                    //B5:
                    showBRMInfo(brms);
                    }
                } 
            }
            
                    break;

                case 6:
                    brms = controller.readBRMsFromFile(brmFileName); // doc ra danh sach ql
                    //update tong so luong muon
                    if(brms.size()==0){
                        System.out.println("khong co ai trong list muon");
                    break;
                      }else{
                    brms = ultility.updateTotalBorrowed(brms);

                    System.out.println("___________________________________________________");
                    System.out.println("_______________Các lựa chọn sắp xếp_______________");
                    int x = 0;

                    do{
                        System.out.println("1. Sắp xếp theo tên bạn đọc(A-Z)");
                        System.out.println("2. Sắp xếp theo tổng số lượng mượn(Giảm dần)");
                        System.out.println("0. Trở lại menu chính.");
                        System.out.println("Bạn chọn?");
                        x = scanner.nextInt();
                        if(x == 0){
                            break;
                        }
                        switch (x){
                            case 1:
                                // sap xep the ten
                                brms = ultility.sortByReaderName(brms);
                                showBRMInfo(brms);
                                break;
                            case 2:
                                // sap xep theo so luong muon
                                brms = ultility.sortByNumOfBorrowed(brms);
                                showBRMInfo(brms);
                                break;
                        }
                    } while(true);
                }
                    break;

                case 7:
                    brms = controller.readBRMsFromFile(brmFileName);
                    System.out.println("Nhập cụm từ có trong tên bạn đọc cần tìm: ");
                    String key = scanner.nextLine();
                    ArrayList<BookReaderManagement> result = ultility.searchByReaderName(brms, key);
                    if(result.size() == 0){
                        System.out.println("Không tìm thấy bạn đọc!");
                    }else{
                        showBRMInfo(result);
                    }
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }

   

    private static boolean checkBorrowed(ArrayList<BookReaderManagement> brms, int readerID, int i) {
        int count =0;
        for(BookReaderManagement r : brms)
        {
            if(r.getReader().getReaderID()==readerID)
             count+=r.getNumOfBorrow();
        }
        if(count+i>5)
        return true;
        else if(count+i<=5)
        return false;
    return false;
    }

    private static void showBRMInfo(ArrayList<BookReaderManagement> brms) {
        for (BookReaderManagement b : brms) {
            System.out.println(b);
        }
    }

    private static Book getBook(ArrayList<Book> books, int bookID) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookID() == bookID) {
                return books.get(i);
            }
        }
        return null;
    }

    private static Reader getReader(ArrayList<Reader> readers, int readerID) {

        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getReaderID() == readerID) {
                return readers.get(i);
            }
        }
        return null;
    }

    private static int getTotal(ArrayList<BookReaderManagement> brms,
                                int readerID, int bookID) {
        for (BookReaderManagement r : brms) {
            if (r.getReader().getReaderID() == readerID
                    && r.getBook().getBookID() == bookID) {
                return r.getNumOfBorrow();
            }
        }
        return 0;
    }

    private static boolean checkFull(ArrayList<BookReaderManagement> brms,
                                     int readerID, int bookID) {
        for (BookReaderManagement r : brms) {
            if (r.getReader().getReaderID() == readerID
                    && r.getBook().getBookID() == bookID && r.getNumOfBorrow() == 3) {
                return true;// không đc mượn tiếp đầu sách này
            }
        }
        return false; //đc mượn tiếp
    }

    private static boolean checkBorrowed(ArrayList<BookReaderManagement> brms,
                                         int readerID) {
        int count = 0;
        for (BookReaderManagement r : brms) {
            if (r.getReader().getReaderID() == readerID) {
                count += r.getNumOfBorrow();
            }
        }
        if (count >= 5) {
            return false; // khong con co hoi muon
        }
        return true;// duoc phep muon.
    }

    private static void showReadersInfo(ArrayList<Reader> readers) {
        System.out.println("___________________Thông tin các bạn đọc trong file___________________");
        for (Reader r : readers) {
            System.out.println(r);
        }
    }

    private static void checkReaderID(DataController controller, String readersFileName) {
        ArrayList<Reader> readers = controller.readReadersFromFile(readersFileName);
        if (readers.size() == 0) {
            // do nothing
        } else {
            Reader.setId(readers.get(readers.size() - 1).getReaderID() + 1);
        }
   }

    private static void checkBookID(DataController controller, String fileName) {
        ArrayList<Book> listBooks = controller.readBooksFromFile(fileName);
        if (listBooks.size() == 0) {
            //do nothing
        } else {
            Book.setId(listBooks.get(listBooks.size() - 1).getBookID() + 1);
        }
    }

    private static void showBookInfo(ArrayList<Book> books) {
        System.out.println("_________________Thông tin sách trong file_________________");
        for (Book b : books) {
            System.out.println(b);
        }
    
    }
    
}