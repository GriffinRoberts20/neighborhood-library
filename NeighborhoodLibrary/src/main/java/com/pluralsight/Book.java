package com.pluralsight;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String isbn;
    private String title;
    private boolean isCheckOut;
    private String checkedOutTo;

    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckOut=false;
        this.checkedOutTo="";
    }

    //checks out book, changing name of who checked out to input name and boolean isCheckedOut to true
    public void checkOut(String name){
        this.setCheckedOutTo(name);
        this.setCheckOut(true);
    }
    //checks in book, resetting name of checked out to empty string and boolean isCheckedOut to false
    public void checkIn(){
        this.setCheckedOutTo("");
        this.setCheckOut(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckOut() {
        return isCheckOut;
    }

    public void setCheckOut(boolean checkOut) {
        isCheckOut = checkOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }
}
