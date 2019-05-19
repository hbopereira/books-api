package books.book;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

import books.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book extends BaseEntity {
	
	private String title;
	private String image;
	private LocalDate publishedDate;


}
