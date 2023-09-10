package dailybugle;

import article.Article;
import database.Database;
import users.Author;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
                    runPublishingOrArchiving();
                    break;
                case 3:
                    searchingArticle();
                    break;
                case 4:
                    allPublishedArticles();
                    break;
                case 5:
                    allArchivedArticle();
                    break;

                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("Wrong command! Please Enter a new one!");
            }
        }
    }

    private void authorRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose want you want to do");
        System.out.println("1. Continue");
        System.out.println("2. Go back to main menu");
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            System.out.println("Enter the author's name");
            String authorName = scanner.nextLine();
            if (!authorName.isBlank() && !authorName.isEmpty()) {
                getDATABASE().registerAuthor(authorName);
                System.out.println("Author registered successfully");
            } else {
                System.out.println("The name cannot be empty");
                authorRegistration();
            }
        } else if (input == 2) {
            return;
        } else {
            System.out.println("There is no such menu point");
            authorRegistration();
        }
    }

    public void createArticle(Author author) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the article's title");
        String title = scanner.nextLine();
        if (titleValidation(title)) {
            System.out.println("Enter the article's content");
            String content = scanner.nextLine();
            if (contentValidation(content)) {
                getDATABASE().getPublishedArticles().add(new Article(title, author, content));
                System.out.println("Article published successfully!");
            }
        } else {
            createArticle(author);
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

    private boolean contentValidation(String content) {
        int titleLengthWithoutBlankSpace = content.replace(" ", "").length();
        boolean result = false;
        if (titleLengthWithoutBlankSpace <= 1000) {
            result = true;
        } else {
            System.out.println("The content is too long");
        }
        return result;
    }

    private void runPublishingOrArchiving() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose want you want to do");
        System.out.println("1. Continue to publish article");
        System.out.println("2. Continue to archive article");
        System.out.println("3. Go back to main menu");
        int input = scanner.nextInt();
        scanner.nextLine();

        if (input == 1) {
            System.out.println("Enter a registered author's name");
            String authorName = scanner.nextLine();
            Author author = getDATABASE().searchAuthorByName(authorName);

            if (author != null) {
                createArticle(author);
            }

        } else if (input == 2) {
            allArchivableArticles();
        } else if (input == 3) {
            return;
        } else {
            System.out.println("There is no such menu point");
            runPublishingOrArchiving();
        }
    }

    private void allPublishedArticles() {
        List<Article> articles = getDATABASE().getPublishedArticles();
        if (articles.isEmpty()) {
            System.out.println("There are no published articles yet");
        } else {
            printTitleOfArticles(articles);
            System.out.println("Enter the number of the article you want to read or 0 to go back to the main menu.");
            printChosenArticle(articles);
        }
    }

    private void allArchivedArticle() {
        List<Article> archivedArticles = getDATABASE().getArchivedArticles();
        if (archivedArticles.isEmpty()) {
            System.out.println("There are no archived articles yet");
        } else {
            printTitleOfArticles(archivedArticles);
            System.out.println("Enter the number of the article you want to read or 0 to go back to the main menu.");
            printChosenArticle(archivedArticles);
        }
    }

    private void allArchivableArticles() {
        List<Article> articles = getDATABASE().getPublishedArticles();
        if (articles.isEmpty()) {
            System.out.println("There are no published articles yet");
        } else {
            printTitleOfArticles(articles);
            System.out.println("Enter the number of the article you want to archive or 0 to go back to the main menu.");
            archiveChosenArticle(articles);
        }
    }

    private void archiveChosenArticle(List<Article> articles) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        try {
            input = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number corresponding to the options you see on the screen");
        }
        if (input == 0) {
            return;
        } else if (input <= articles.size() && input > 0) {
            getDATABASE().getArchivedArticles().add(articles.get(input - 1));
            getDATABASE().getPublishedArticles().remove(articles.get(input - 1));
            System.out.println("Article was archived successfully");
        } else {
            System.out.println("There is no article with this number, " +
                    "please chose one from the presented list, or type 0 to go back to the main menu!");
            archiveChosenArticle(articles);
        }
    }

    private void printChosenArticle(List<Article> articles) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        try {
            input = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number corresponding to the options you see on the screen");
        }
        if (input == 0) {
            return;
        } else if (input <= articles.size() && input > 0) {
            System.out.println("The content of the article you have chosen is: ");
            System.out.println(articles.get(input - 1).toString());
        } else {
            System.out.println("There is no article with this number, " +
                    "please chose one from the presented list, or type 0 to go back to the main menu!");
            printChosenArticle(articles);
        }
    }

    private void searchingArticle() {
        System.out.println("Please enter search criteria");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Article> result = new ArrayList<>();
        if (!input.isBlank() && !input.isEmpty()) {
            result = (getDATABASE().searchArticles(input));
        } else {
            System.out.println("Search criteria cannot be empty!");
            searchingArticle();
        }
        if (result.isEmpty()) {
            System.out.println("No result!");
        } else {
            printTitleOfArticles(result);
        }
    }

    private void printTitleOfArticles(List<Article> articles) {
        System.out.println("The articles are: ");
        for (int i = 0; i < articles.size(); i++) {
            System.out.println(i + 1 + ". " + articles.get(i).getTitle());
        }
    }

    private void printMenu() {
        System.out.println("\nType the number of the option you want to choose \n" +
                "1. Register Author \n" +
                "2. Publish/Archive Article \n" +
                "3. Search Articles \n" +
                "4. Show all published Articles \n" +
                "5. Show all archived Articles \n" +
                "6. Quit \n");
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
        printTitleOfArticles(getDATABASE().searchArticles(input));
    }

    public Database getDATABASE() {
        return DATABASE;
    }
}
