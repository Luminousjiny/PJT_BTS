package com.ssafy.bts.Repository;

import com.ssafy.bts.Domain.GuestBook.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
}
