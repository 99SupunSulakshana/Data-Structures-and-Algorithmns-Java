
package booklistsupun;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
class BookList {

   public String isbn[];// ISBN no
   public String title[];// title
   public String price[];//price
   public String author[];//author
   public String edition[];//edition
   public String tempname[];//tempisbn
   public String temptitle[];//temp title
   public String tempprice[];
   public String tempauthor[];
   public String tempedition[];
   
   public String arr[];
   
   
     public static void main(String[] args) {
    //insert(String isbn, String title, String author, String edition, String price);
     Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    BookList obj =new BookList();
     
      System.out.println("Insert          - 1");
      System.out.println("Delete          - 2");
      System.out.println("Update          - 3");
      System.out.println("Search          - 4");
      System.out.println("Order By Price  - 5");
      System.out.println("View            - 6");
      System.out.println("Exit Program    - 0"+'\n');
      String x="";// this is a input value
      x = myObj.nextLine(); 
      
     switch(x){
       case "1" -> {
         
           System.out.println("Enter ISBN");
           String ISBNNO = myObj.nextLine(); 
          System.out.println("Enter TITLE");
          String TITLE = myObj.nextLine(); 
          System.out.println("Enter AUTHOR");
          String  AUTHOR = myObj.nextLine(); 
          System.out.println("Enter EDITION");
          String EDITION = myObj.nextLine(); 
          System.out.println("Enter PRICE");
           String PRICE = myObj.nextLine();
          // Output user input
          obj.insert(ISBNNO, TITLE, AUTHOR, EDITION, PRICE);
          System.out.println(ISBNNO+"-"+ TITLE+"-"+ AUTHOR+"-"+ EDITION+"-"+ PRICE);  
          main();
        }
       case  "2" -> {
           System.out.println("ENTER DELETE ISBN");
           String delISBNNO = myObj.nextLine(); 
          int p = obj.delete(delISBNNO);
          if(p==1){
             System.out.println("Successfully Delete"); 
          }else{
             System.out.println("Can not find ISBN !"); 
          }
           main();
        }
       case  "3" -> {
          System.out.println("Enter ISBN");
          String ISBNNO = myObj.nextLine(); 
          System.out.println("Enter TITLE");
          String TITLE = myObj.nextLine(); 
          System.out.println("Enter AUTHOR");
          String  AUTHOR = myObj.nextLine(); 
          System.out.println("Enter EDITION");
          String EDITION = myObj.nextLine(); 
          System.out.println("Enter PRICE");
           String PRICE = myObj.nextLine();
          // Output user input
        int p = obj.update(ISBNNO,ISBNNO, TITLE, AUTHOR, EDITION, PRICE);
          if(p==1){
             System.out.println("Successfully Updated"); 
          }else{
             System.out.println("Can not find ISBN !"); 
          }
           main();
        }
       case  "4" -> {
            System.out.println("Enter Search PRICE");
           String PRICE = myObj.nextLine();
           String[] outputArrm = new String[7];
           outputArrm = obj.commonsearch(PRICE,1);
            if(outputArrm[0].length() != 0){
                System.out.println("Enter ISBN    : " +outputArrm[0]);
                System.out.println("Enter TITLE   : " +outputArrm[1]);
                System.out.println("Enter AUTHOR  : " +outputArrm[2]);
                System.out.println("Enter EDITION : " +outputArrm[3]);
                System.out.println("Enter PRICE   : " +outputArrm[4]);
            }else{
            System.out.println("Can not find PRICE !");
            }
             main();
        }

       case  "5" -> {
           System.out.println("Enter Asscending : A");
           System.out.println("Enter Dessending : D");
           String order = myObj.nextLine();
           obj.sortOrdre(order);
            main();
        }
       case  "6" -> {
            obj.allView();
             main();
        }
       
       case  "0" -> {
            System.exit(0);
             main();
        }
     
     default -> {
    }
    }
     
  }
   
    public void openFile(){
       try {
            File  file=new File("src/booklistsupun/a.txt");
            file.createNewFile();
            System.out.println("File a created.");
       } catch (IOException ex) { System.out.println(ex); }
    }
    
    public int insert(String isbn, String title, String author, String edition, String price){
        int x=1;
        try{
            FileWriter fw=new FileWriter("src/booklistsupun/book.txt", true); //file write append enable;
            //write data file inside
            arr =new String[2];
            fw.write(isbn +  "," + title +  "," + author+ "," + edition +","+ price + "," + '\n');
            fw.close();
            System.out.println("File write Successfull."); 
            
          x=1;
        }catch (IOException ex) {  System.out.println(ex); x=0;}
        
        return x;
    }
    public int update(String search, String newName, String newCity ,  String newauthor, String newedition, String newprice ){
            int x=1;
            int i=0; 
            //4-search the index where to update
            try{
                 //1-read data
            view(); //execute the view method
                i=0;
                for(i=0; i<isbn.length; i++){
                // System.out.println(i + "--"+ name[i]);
                 String x=isbn[i];
                 if(x.equals(search)){
                    isbn[i]=newName;
                    title[i]=newCity;
                    author[i]=newauthor;
                    edition[i]=newedition;
                    price[i]=newprice;
                   x=1;
                   break;
                 }else{
                     x=0;
                 }
                }
                    
            }catch(Exception ex){
                System.out.println(ex);
            }
              
           try{
             FileWriter nfw = new FileWriter("src/booklistsupun/book.txt");//thin file eke data dele krl aluthin add krnw 
             for(i=0; i<isbn.length; i++)
             {
                 nfw.write(isbn[i] +  "," + title[i] +"," + author[i] +  "," + edition[i] + "," + price[i]+ "," +  '\n' );
             }
             nfw.close();
             x=1;
           }catch (IOException ex) {System.out.println(ex); x=0; }
             
        return x;
    }
     
    public int delete(String search){
    //1-read data
           view();//execute view method
              int y=1;
           int i=0;
            //4-search the index where to update
           int index=0;
           try{  
           for(i= 0; i<isbn.length; i++){
                 String x=isbn[i];
                 if(x.equals(search)){
                   index=i;
                   // System.out.println(i+"-"+ name[i] + "-"+city[i]);
                   //   System.out.println("Arrays ID FOUND TO DELETE");
                   y=1;
                   break;
                 }else{
                     y=0;
                     
                 }
           }     
             
           if(y==1){
           
            //5-copy data to tempary array
            i=isbn.length-1;
            tempname=new String[i];
            temptitle=new String[i];
            tempauthor=new String[i];//author
            tempedition=new String[i];//edition
            tempprice=new String[i];//price
            
            for(i=0; i<index ; i++){
                    tempname[i]=isbn[i];
                    temptitle[i]=title[i];
                    tempauthor[i]=author[i];
                    tempprice[i]=price[i];
                    tempedition[i]=edition[i];
                }
            int end=tempname.length;
            for(i=index; i<end; i++){
                    tempname[i]=isbn[i+1];
                    temptitle[i]=title[i+1];
                    tempauthor[i]=author[i+1];
                    tempprice[i]=price[i+1];
                    tempedition[i]=edition[i+1];
                }
                 y=1;
                y = rewrite();
              
               System.out.println(y+"-if-");  
             }else{
                y=0;
                     System.out.println(y+"-else-");
                 }  
            }
            catch(Exception ex){System.out.println(ex); y=0; }
           return y;    
    } 
        //call in delete method rewrite temp array data in text file
    public int rewrite(){
        //6-again re-write data to textfile
        int i=0; int s=0; int index=0;
           try{
            try (FileWriter newfw = new FileWriter("src/booklistsupun/book.txt")) {
                index=tempname.length;
                i=0;
                for(i=0; i<index; i++)
                {
                    newfw.write(tempname[i] + "," + tempcity[i] + "," + tempauthor[i] + "," + tempedition[i]+ "," + tempprice[i] + "," + '\n');

                }
            }
             s=1;
           }catch (IOException ex) {System.out.println(ex); k=0; }
           
           return s;
    }
    
    public  void view(){
            try{
            int i=0;    
            String textline;
                try (FileReader fr = new FileReader("src/booklistsupun/book.txt"); Scanner scanLine = new Scanner(fr)) {
                    //2-count tle text file txt lines
                    while(scanLine.hasNext()){ String x = scanLine.nextLine(); i++; }
                }
            //System.out.println(i);
            //assign the arary size
            isbn=new String[i];
            title=new String[i];
            author=new String[i];
            edition=new String[i];
            price=new String[i];
            //3- put txt data to pararalen arrays
            i=0;
                try (FileReader frnew = new FileReader("src/booklistsupun/book.txt"); Scanner scanLineNew = new Scanner(frnew)) {
                    while(scanLineNew.hasNext()){
                        textline=scanLineNew.nextLine();
                        // System.out.println("===="+textline);
                        Scanner scanWord = new Scanner(textline); //that string scan
                        scanWord.useDelimiter(","); //separate by that ","
                        //'yourname','city"
                        isbn[i]= scanWord.next();
                        title[i]=scanWord.next();//title
                        author[i]= scanWord.next();//Author
                        edition[i]=scanWord.next();//edition
                        price[i]= scanWord.next();//price
                        
                        i++;
                    }
       
            }catch (IOException ex) { System.out.println(ex); }
 
    }
    
   public void allView(){
         view();
       int i=0;
          for(i=0; i<isbn.length; i++){
              System.out.println(isbn[i]+"-"+title[i]+"-"+author[i]+"-"+edition[i]+"-"+price[i]+'\n');
          }
   }
    public  void  sortOrdre(String type){ 
        //price arary string to double convert to sorting numbers
        double[] beforeSort= new double[price.length];
        int n = price.length; 
        double[] arr = new double[n];
        for(int i=0; i < n; i++){  beforeSort[i] = Double.parseDouble(price[i]); arr[i] = Double.parseDouble(price[i]);  }
         if("A".equals(type)){
           arr=bblSort(arr,n);
         }else if("D".equals(type)){
            arr=bblSort(arr,n);
            int p=0;
           double[] temparr=new double[n];
            for(int i=n-1; i>=0; i--){ temparr[p] = arr[i];   p++;}
            // System.out.println(Arrays.toString(temparr));
            System.arraycopy(temparr, 0, arr, 0, temparr.length);
         }
        int[] z =new int[n] ;// this array used to after sort array datae where the store in before arary index values
        for(int i=0; i<n; i++){for(int j=0; j<n; j++){if(arr[i] == beforeSort[j]){ z[i]=j;}}}
        //positions chages in other array indexes
        tempname=new String[n];tempcity=new String[n];tempauthor=new String[n];tempedition=new String[n];tempprice=new String[n];//price
        
        for(int i=0; i<z.length; i++){int val=z[i]; tempname[i]=isbn[val];tempcity[i]=title[val];tempauthor[i]=author[val];tempedition[i]=edition[val];tempprice[i]=price[val];}

         int i=0;
          for(i=0; i<isbn.length; i++){
              System.out.println(tempname[i]+"-"+tempcity[i]+"-"+tempauthor[i]+"-"+tempedition[i]+"-"+tempprice[i]+'\n');
          }
    } 
     public double[] bblSort(double[] arr, int n){
        double temparr = 0;
        for(int i=0; i < n; i++)
        {
            for(int j=1; j < (n-i); j++)
            {    if(arr[j-1] > arr[j])
            {  //swap elements  
            tempaarr = arr[j-1];  
            arr[j-1] = arr[j];  
            arr[j] = temparr;  
                            }  
                 }  
         }
        //end bubble sort 
         return arr;
     } 
      
   public String[] commonsearch(String searchvalue, int x){
         view();
         int n = price.length;
         String[] parts;
         String word;
         String xp="";
         String[] Ncopyarr=new  String[n]; 
         String[] Nbeforecopyarr=new  String[n];
         String[] index=new String[7];
         /*default values*/
         index[0]="";index[1]="";index[2]="";index[3]="";index[4]="";index[5]="";index[6]="";
       //binary search
       int i=0;
       for(i= 0; i<isbn.length; i++){
                xp =price[i];
                 if(xp.equals(searchvalue)){
                   index[0]=isbn[i];
                   index[1]=title[i];
                   index[2]=author[i];
                   index[3]=edition[i]; 
                   index[4]=price[i];
                   index[5]=String.valueOf(i);
                   index[6]=String.valueOf(i);
                   break;
                 }else{
                      
                 }
           }  
        return index;
   }
 }
  





