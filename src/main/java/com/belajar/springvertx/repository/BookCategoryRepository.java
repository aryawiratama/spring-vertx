package com.belajar.springvertx.repository;

import com.belajar.springvertx.domain.BookCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookCategoryRepository extends PagingAndSortingRepository<BookCategory, String> {
}
