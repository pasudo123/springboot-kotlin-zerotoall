package com.example.springbootjpabasis.domain.book.repository

import com.example.springbootjpabasis.domain.book.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long>