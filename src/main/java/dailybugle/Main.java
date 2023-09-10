package dailybugle;

import article.Article;
import users.Author;

public class Main {
    public static void main(String[] args) {

        Engine engine = new Engine();
        engine.run();

        Author author = new Author("S");
        Author author2 = new Author("X");
        Article article = new Article("X", author, " ");


    }
}