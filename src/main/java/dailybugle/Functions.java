package dailybugle;

import article.Article;
import users.Author;

import java.util.List;

public interface Functions {
    void registerAuthor (String name);
    void listArticles ();
    void printContent (String title);
    void publishArticle (String title, String content, String authorName);
    void search (String input);


}
