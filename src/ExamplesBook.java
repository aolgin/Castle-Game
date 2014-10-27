import tester.Tester;

//assignment 4
//pair 026
//Olgin, Adam
//aolgin
//Levine, Ben
//levinebe

public class ExamplesBook {

    // example books
    ABook moby = new Book("Moby Dick", "Herman Melville", 1200);
    ABook britanica = new RefBook("Encyclopedia Britanica", 1500);
    ABook potter = new AudioBook("Harry Potter and the Philosopher's Stone", 
            "J.K. Rowling", 1350);
    
    // tests for daysOverdue
    boolean testDaysOverdue(Tester t) {
        return
                t.checkExpect(this.moby.daysOverdue(1215), 1) &&
                t.checkExpect(this.britanica.daysOverdue(1515), 13) &&
                t.checkExpect(this.potter.daysOverdue(1354), -10) &&
                t.checkExpect(this.britanica.daysOverdue(1501), -1);
    }
    
    // tests for isOverdue
    boolean testIsOverdue(Tester t) {
        return
                t.checkExpect(this.moby.isOverdue(1215), true) &&
                t.checkExpect(this.britanica.isOverdue(1515), true) &&
                t.checkExpect(this.potter.isOverdue(1375), true) &&
                t.checkExpect(this.potter.isOverdue(1204), false) &&
                t.checkExpect(this.britanica.isOverdue(1501), false);
    }
    
    // test for compute fine
    boolean testComputeFine(Tester t) {
        return
                t.checkExpect(this.moby.computeFine(1215), 10) &&
                t.checkExpect(this.britanica.computeFine(1515), 130) &&
                t.checkExpect(this.potter.computeFine(1375), 220) &&
                t.checkExpect(this.potter.computeFine(1204), 0) &&
                t.checkExpect(this.britanica.computeFine(1501), 0);
    }
}
