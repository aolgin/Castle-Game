// assignment 4
// pair 026
// Olgin, Adam
// aolgin
// Levine, Ben
// levinebe

public interface IBook {

    // how many days overdue is this book given today's date?
    int daysOverdue(int today);
    
    // is this book overdue on the given day?
    boolean isOverdue(int day);
    
    // what is the fine for this book
    // if returned on the given day
    // gives fee in cents
    int computeFine(int day);
}

abstract class ABook implements IBook {
    String title;
    int dayTaken; 
    
    ABook(String title, int dayTaken) {
        this.title = title;
        this.dayTaken = dayTaken;
    }
    
    /* Template
      
      Fields:
      ...this.title...    -String
      ...this.dayTaken... -int
      
      Methods:
      
      Methods for Fields:
      
     */
    
    // how many days overdue is this book given today's date?
    public int daysOverdue(int today) {
        return today - this.dayTaken - 14;
    }
    
    // is this book overdue on the given day?
    public boolean isOverdue(int day) {
        return this.daysOverdue(day) > 0;
    }
    
    // what is the overdue fee for this book on the given day?
    // gives price in cents
    public int computeFine(int day) {
        if (this.daysOverdue(day) < 0) 
            return 0;
        else 
            return (this.daysOverdue(day) * 10);
    }

}

class Book extends ABook {
    String author;
    
    Book(String title, String author, int dayTaken) {
        super(title, dayTaken);
        this.author = author;
    }
    
    /* Template
    
    Fields:
    ...this.title...    -String
    ...this.dayTaken... -int
    
    Methods:
    ...daysOverdue(int today)... -int
    ...isOverdue(int day)...     -boolean
    ...computeFine(int day)...   -int
    
   */
}

class RefBook extends ABook {
    
    RefBook(String title, int dayTaken) {
        super(title, dayTaken);
    }
    
    // how many days overdue is this RefBook given today's date?
    public int daysOverdue(int today) {
        return today - this.dayTaken - 2;
    }
}

class AudioBook extends ABook {
    String author;
    
    AudioBook(String title, String author, int dayTaken) {
        super(title, dayTaken);
        this.author = author;
    }
    
    // what is the fine for this book
    // if returned on the given day
    public int computeFine(int day) {
        if (this.daysOverdue(day) < 0)
            return 0;
        else
            return this.daysOverdue(day) * 20;
    }
}
