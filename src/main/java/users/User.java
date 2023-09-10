package users;

public abstract class User {
    private String name;

   protected User(String name) {
        if (nameValidator(name)) {
            this.name = name;
        }
    }

    private boolean nameValidator (String name){
        boolean result = false;
        if (!name.isEmpty() && !name.isBlank()){
            result = true;
        } else {
            System.out.println("The author's name cannot be empty");
        }
        return result;
    }

    public String getName() {
        return name;
    }
}
