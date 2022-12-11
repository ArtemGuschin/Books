package com.application.books.Controllers;

import com.application.books.Dao.BookDao;
import com.application.books.Models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class  BookController {
    @Autowired
    private BookDao bookDao;
    //показывает все книги
    @GetMapping
    public  String index(Model model){
        List<Book> bookList = bookDao.allBooks();
        model.addAttribute("books",bookList);
        return "books/index";
    }
    //показывает инфу по одной книге по id
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        Book book = bookDao.getRecipe(id);
        model.addAttribute("book",book);
        return "books/show";
    }
    //форма для создания книги
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }
    //при нажатии на кнопку "создать книгу"
    @PostMapping
    public String createBook(@ModelAttribute("book")@Valid Book book
    ,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDao.addBook(book);
        return "redirect:/books";
    }

    //форма для редактирования книги
    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") int id, Model model) {
        Book book = bookDao.getRecipe(id);
        model.addAttribute("book", book);
        return "books/edit";
    }
    //при нажатии на кнопку "отредактировать"
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("recipe") Book book,
                             @PathVariable("id") int id) {

        bookDao.updateBook(id, book);
        return "redirect:/books";
    }
    //при нажатии на кнопку "удалить"
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDao.deleteBook(id);
        return "redirect:/books";
    }


}

