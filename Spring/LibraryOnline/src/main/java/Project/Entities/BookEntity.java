package Project.Entities;

import javax.persistence.*;

/**
 * Created by User on 15.03.2016.
 */
@Entity
@Table(name = "book", schema = "applicationproject")
public class BookEntity {
    private long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookGenre;

    public BookEntity(){}

    public BookEntity(String bookName, String bookAuthor, String bookGenre) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
    }

    @Id
    @Column(name = "BookId" )
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "BookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "BookAuthor")
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Basic
    @Column(name = "BookGenre")
    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (bookAuthor != null ? !bookAuthor.equals(that.bookAuthor) : that.bookAuthor != null) return false;
        if (bookGenre != null ? !bookGenre.equals(that.bookGenre) : that.bookGenre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = bookId;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (bookAuthor != null ? bookAuthor.hashCode() : 0);
        result = 31 * result + (bookGenre != null ? bookGenre.hashCode() : 0);
        return (int) result;
    }
}
