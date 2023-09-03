package dailybugle;

import article.Article;
import database.Database;
import users.Author;

import java.util.List;
import java.util.Scanner;

public class Engine implements Functions {
    private final Database DATABASE;

    public Engine() {
        this.DATABASE = new Database();
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            printMenu();
            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                    authorRegistration();
                    break;
                case 2:
                    articlePublishing();
                    break;
                case 3:
                    searchingArticle();
                    break;
                case 4:
                    allPublishedArticles();
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Wrong command! Please Enter a new one!");
            }
        }
    }

    private void authorRegistration() {

        System.out.println("Enter the author's name");
        Scanner scanner = new Scanner(System.in);
        String authorName = scanner.nextLine();
        if (!authorName.isBlank() && !authorName.isEmpty()) {
            getDATABASE().registerAuthor(authorName);
        } else {
            System.out.println("The name cannot be empty");
            authorRegistration();
        }
    }

    private void articlePublishing() {
        System.out.println("Enter the article's title");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        System.out.println("Enter the article's content");
        String content = scanner.nextLine();
        System.out.println("Enter the author's name");
        String authorName = scanner.nextLine();
        Author author = getDATABASE().searchAuthorByName(authorName);
        new Article(title, author, content);
    }

    private void allPublishedArticles() {
        List<String> articles = getDATABASE().getAllArticles();
        if (articles == null) {
            System.out.println("There are no published articles yet");
        } else {
            System.out.println(articles);
        }
    }

    private void searchingArticle() {
        System.out.println("Please enter search criteria");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!input.isBlank() && !input.isEmpty()) {
            getDATABASE().searchArticles(input);
        } else {
            System.out.println("Search criteria cannot be empty!");
            searchingArticle();
        }
    }


    private void printMenu() {
        System.out.println("Type the number of the option you want to choose \n" +
                "1. Register Author \n" +
                "2. Publish Article \n" +
                "3. Search Articles \n" +
                "4. Show all published Articles \n" +
                "5. Quit");
    }

    @Override
    public void registerAuthor(String name) {
        getDATABASE().registerAuthor(name);
    }

    @Override
    public void listArticles() {
        System.out.println(getDATABASE().getAllArticles());
    }

    @Override
    public void printContent(String title) {
        System.out.println(getDATABASE().searchArticleByTitle(title));
    }

    @Override
    public void publishArticle(String title, String content, String authorName) {
        new Article(title, getDATABASE().searchAuthorByName(authorName), content);
    }

    @Override
    public void search(String input) {
        System.out.println(getDATABASE().searchArticles(input));
    }

    public Database getDATABASE() {
        return DATABASE;
    }
}
