package library.library.services;

import library.library.entities.Book;
import library.library.repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void save(Book book){
        bookRepo.save(book);
    }

    public Book getById(Long id){
        return bookRepo.getReferenceById(id);
    }

    public void remove(Book book){
        bookRepo.delete(book);
    }

    public void remove(Long id){
        bookRepo.deleteById(id);
    }
}
