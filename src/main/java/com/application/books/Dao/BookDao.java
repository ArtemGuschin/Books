package com.application.books.Dao;

import com.application.books.Models.Book;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDao {
    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\art69\\IdeaProjects\\Books\\database.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //возвращаем список всех книг
    //READ
    public List<Book> allBooks(){
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Book";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                int id = set.getInt("id");
                String author = set.getString("author");
                String nameOfBook = set.getString("nameOfBook");
                String genre = set.getString("genre");
                books.add(new Book(id,author,nameOfBook,genre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //возвращаем книгу по id
    //READ
    public Book getRecipe(int id) {
        try {
            String query = "SELECT * FROM Book WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int bookId = set.getInt("id");
                String author = set.getString("author");
                String nameOfBook = set.getString("nameOfBook");
                String genre = set.getString("genre");
                return new Book(bookId, author, nameOfBook,genre);
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //Создаем книгу
    //CREATE
    public void addBook(Book book) {
        try {
            String query = "INSERT INTO Book(author, nameOfBook,genre) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getNameOfBook());
            statement.setString(3, book.getGenre());
            statement.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    //Обновляем книгу
    //UPDATE
    public void updateBook(int id, Book updatedBook) {
        try {
            String query = "UPDATE Book SET author = ?, nameOfBook = ?, genre = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, updatedBook.getAuthor());
            statement.setString(2, updatedBook.getNameOfBook());
            statement.setString(3, updatedBook.getGenre());
            statement.setInt(4, id);
            statement.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    //Удаляем книгу
//DELETE
    public void deleteBook(int id){
        try{
            String query = "DELETE FROM Book WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}

