package com.belajar.springvertx.service;

import com.belajar.springvertx.domain.BookCategory;
import com.belajar.springvertx.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookCategoryService {

    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @Transactional(readOnly = false)
    public BookCategory saveBookCategory(BookCategory bookCategory){
        return bookCategoryRepository.save(bookCategory);
    }

    public List<BookCategory> findAllBookCategory(){
        List<BookCategory> bookCategories = new ArrayList<>();
        bookCategoryRepository.findAll().forEach(bookCategories::add);
        return bookCategories;
    }
}
