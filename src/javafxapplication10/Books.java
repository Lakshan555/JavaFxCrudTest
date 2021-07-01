/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication10;

/**
 *
 * @author Ishanka
 */
public class Books {
    
    private int id;
    private String titel;
    private String author;
    private int year;
    private int pages;

    public Books(int id, String titel, String author, int year, int pages) {
        this.id = id;
        this.titel = titel;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }
    
    
    
    
 
    
    
}
