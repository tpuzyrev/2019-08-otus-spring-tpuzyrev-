package ru.otus.spring.jpalibrary.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BOOKS")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GENREID")
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHORID")
    private Author author;

    @Column(name = "PAGE")
    private Integer page;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "COMMENTS", joinColumns = {@JoinColumn(name="ID")},
            inverseJoinColumns = {@JoinColumn(name="BOOKID")} )
    private List<Comment> commentList;

    public Book(String name, Genre genre, Author author, int page) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.page = page;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Книга: ").append(name).
                append(", Автор: " ).append(author != null ? author.getName() : "").
                append(", Жанр: ").append(genre != null ? genre.getName() : "").
                append(", Стр.: ").append(page != null ? page : "").
                append(", ID: ").append(id);
        return sb.toString();
    }
}
