package books.book;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import books.base.BaseController;

@RestController
@RequestMapping("/api/books")
public class BookController extends BaseController<Book,BookRepository,BookService> {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(value="/json")
	@CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> listarJsonBook(@RequestParam("descricao") String descricao) throws IOException{
        System.out.println(descricao);
		return ResponseEntity.status(HttpStatus.OK).body(bookService.pesquisarLivros(descricao));
	}

}



