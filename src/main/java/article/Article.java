package article;

import users.Author;

import java.time.LocalDate;

public class Article {

    private String title;
    private Author author;
    private String content;
    private LocalDate dateOfPublication;

    public Article(String title, Author author, String content) {
        if (titleValidation(title) && authorValidation(author)) {
            this.title = title;
            this.author = author;
            this.content = content;
            dateOfPublication = LocalDate.now();
            author.getPublishedArticles().add(this);
        }
    }

    private boolean titleValidation(String title) {
        boolean result = false;
        if (!title.isEmpty() && !title.isBlank()) {
            result = true;
        } else {
            System.out.println("The title cannot be empty");
        }
        return result;
    }

    private boolean authorValidation(Author author) {
        boolean result = false;
        if (author != null) {
            result = true;
        } else {
            System.out.println("The author field cannot be empty");
        }
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    @Override
    public String toString() {
        return title + "\n" + "\n" + content;
    }
}
