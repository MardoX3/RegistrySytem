package pl.gornik;
import pl.gornik.acts.BirthAct;
import pl.gornik.acts.DeathAct;
import pl.gornik.acts.MarriageAct;
import pl.gornik.person.*;
import pl.gornik.users.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class RegistrySystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        List<Person> person = new ArrayList<>();
        List<BirthAct> birthActs = new ArrayList<>();
        List<DeathAct> deathActs = new ArrayList<>();
        List<MarriageAct> marriageActs = new ArrayList<>();
        List<Person> foundPeopleList = new ArrayList<>();
        addToList(person);
        users.add(new Admin("admin", "admin",-1));
        System.out.println("Run the RegistrySystem");
        boolean check = true;
        while (check) {
            int choice =  getUserChoiceLogin(scanner);
            switch (choice) {
                case (1):
                    int id = login(users);
                    if (id != 0) {

                        boolean check1 = true;
                        System.out.println("Welcome to the  System ,Please choice what do you want");
                        while (check1) {
                            if(id < 0) {
                                choice = getAdminChoiceMainMenu(scanner);
                            }
                            else choice = getUserChoiceMainMenu(scanner);
                            switch (choice) {
                                case (1):
                                    if (confirmAction("Are you sure you want to register your child? (yes/no)")) {
                                        System.out.println("--------------------------------------------------------------");
                                        System.out.println("Child's Personal Information: ");
                                        childRegister(person);
                                        break;
                                    }
                                    break;
                                case (2):
                                    if (confirmAction("Are you sure you want to register Death Certificate? (yes/no)")) {
                                        System.out.println("--------------------------------------------------------------");
                                        System.out.println("Register Death Certificate");
                                        registerDeathCertificate(person);
                                        break;
                                    }
                                    break;
                                case (3):
                                        if (id < 0) {
                                            managePersonAdmin(person,foundPeopleList);
                                        }
                                        else{
                                            managePersonUser(person,foundPeopleList,id);
                                        }
                                    break;
                                case(4):
                                    registerMarried(person,"0");
                                    break;
                                case(5):
                                    diverce(person);
                                    break;

                case (0):
                if(confirmAction("Are you sure you want to log out and exit? (yes/no)")){
                    check1 = false;
                    check = false;
                    System.out.println("Logging out... and exit");
                    break;
                }
                else {
                    check1 = false;
                    System.out.println("Logging out...");
                    break;
                }

            }
        }
    }
                break;
                case (2):
                    register(users,person);
                    break;
                case (0):
                    check = false;
                    System.out.println("Bye");
                    break;
            }
        }
}


    public static int login(List<User> users) {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Username:");
            String username = scanner.nextLine();
            System.out.println("Password:");
            String password = scanner.nextLine();
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    System.out.println("Sucsefull login");
                     int id = user.getId();
                    return id;
                }
            }
                System.out.println("login failed");
            return 0;
        }
    public static void register (List<User> users,List<Person> person) {
        Scanner scanner = new Scanner(System.in);
        String firstname,lastname,gender,dateOfBirth,residentalAdress,identificationNumber;
        System.out.println("First name:");
        firstname = scanner.nextLine();
        System.out.println("Last name:");
        lastname = scanner.nextLine();
        System.out.println("DateOfBirth:");
        dateOfBirth = scanner.nextLine();
        System.out.println("Gender:");
        gender = scanner.nextLine();
        System.out.println("ResidentalAdress:");
        residentalAdress = scanner.nextLine();
        System.out.println("IndentificationNumber:");
        identificationNumber = scanner.nextLine();
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        person.add(new Person(firstname, lastname, gender, residentalAdress,identificationNumber, dateOfBirth, false));
        users.add(new User(username, password, person.get(person.size() - 1).getId()));
        System.out.println("Successful registration");
    }
    private static int getUserChoiceLogin(Scanner scanner) {
        int choice = -1;
        while (choice < 0) {
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("0.Exit");
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 0 || choice > 3) {
                    System.out.println("Invalid choice. Please enter a number between 0 and 2.");
                    choice = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return choice;
    }
    private static int getAdminChoiceMainMenu(Scanner scanner) {
        int choice = -1;
        while (choice < 0) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. Registration of Civil Status Birth Certificates");
            System.out.println("2. Register Death Certificate");
            System.out.println("3. Manage personality");
            System.out.println("4. Register Marriage");
            System.out.println("5. Divorce");
            System.out.println("0. Log out and exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 0 || choice > 5) {
                    System.out.println("Invalid choice. Please enter a number between 0 and 5.");
                    choice = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return choice;
    }
    private static int getUserChoiceMainMenu(Scanner scanner) {
        int choice = -1;
        while (choice < 0) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. request for Registration of Civil Status Birth Certificates");
            System.out.println("2. request for Register Death Certificate");
            System.out.println("3. Manage your personality");
            System.out.println("0. Log out and exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 0 || choice > 3) {
                    System.out.println("Invalid choice. Please enter a number between 0 and 4.");
                    choice = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return choice;
    }
    public static void diverce(List<Person> person){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the first person: ");
        boolean check = true;
        int id = 0;
        int attempt = 0;
        while (check && attempt != 3) {
            String person1Id = scanner.next();
            for (Person persons : person) {
                if (persons.getIdentificationNumber() != null) {
                    if (persons.getIdentificationNumber().equals(person1Id)) {
                        persons.setSpouse(false);
                        id = persons.getId();
                        check = false;
                    }
                }
            }
            if(id == 0) {
                System.out.println("Invalid ID plese try again");
                attempt++;
            }

        }
        System.out.println("Enter the ID of the twice person: ");
        boolean check1 = true;
        int attempt1 = 0;
        int check2 = 0;
        while(check1 && attempt1 != 3) {
            String person2Id = scanner.next();
            for (Person persons : person) {
                if (persons.getIdentificationNumber() != null) {
                    if (persons.getIdentificationNumber().equals(person2Id)) {
                        persons.setSpouse(false);
                        persons.setId(id);
                        check1 = false;
                        System.out.println("Successful diverce");
                        check2++;
                    }
                }

            }
            if(check2 == 0) {
                System.out.println("Invalid ID plese try again");
                attempt1++;
            }
        }
    }
    private static int getUserChoiceManageMenu(Scanner scanner) {
        int choice = -1;
        while (choice < 0) {
            System.out.println("1.FirstName");
            System.out.println("2.LastName");
            System.out.println("3.Gender");
            System.out.println("4.ResidentalAdress");
            System.out.println("0.Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 0 || choice > 4) {
                    System.out.println("Invalid choice. Please enter a number between 0 and 3.");
                    choice = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return choice;
    }
    public static void registerMarried(List<Person> person,String pesel) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the first person: ");
        boolean check = true;
        int id = 0;
        int attempt = 0;
        while (check && attempt != 3) {
            String person1Id = scanner.next();
            for (Person persons : person) {
                if (persons.getIdentificationNumber() != null) {
                    if (persons.getIdentificationNumber().equals(person1Id)) {
                        persons.setSpouse(true);
                        pesel = person1Id;
                        id = persons.getId();
                        check = false;
                    }
                }
            }
            if(id == 0) {
                System.out.println("Invalid ID plese try again");
                attempt++;
        }

    }
            System.out.println("Enter the ID of the twice person: ");
            boolean check1 = true;
            int attempt1 = 0;
            int check2 = 0;
            while(check1 && attempt1 != 3) {
                String person2Id = scanner.next();
                for (Person persons : person) {
                    if (persons.getIdentificationNumber() != null || !persons.getIdentificationNumber().equals(pesel)) {
                        if (persons.getIdentificationNumber().equals(person2Id)) {
                            persons.setSpouse(true);
                            persons.setId(id);
                            check1 = false;
                            System.out.println("Successful married");
                            check2++;
                        }
                    }
                }
                if(check2 == 0) {
                    System.out.println("Invalid ID plese try again");
                    attempt1++;
                }
            }
        }
    public static void childRegister(List<Person> person){
        Scanner scanner = new Scanner(System.in);
            String firstname,lastname,gender,dateOfBirth;
            System.out.println("First name:");
            firstname = scanner.nextLine();
            System.out.println("Last name:");
            lastname = scanner.nextLine();
            System.out.println("Gender:");
            gender = scanner.nextLine();
            System.out.println("DateOfBirth:");
            dateOfBirth = scanner.nextLine();
            person.add(new Person(firstname, lastname, gender,dateOfBirth,false));
    }
    public static void registerDeathCertificate(List<Person> person) {
        Scanner scanner = new Scanner(System.in);
        String deceasedFirstName,deceasedLastName,deceasedGender,dateOfBirth,dateOfDeath,identificationNumber,residentalAdress;
        System.out.println("First name:");
        deceasedFirstName = scanner.nextLine();
        System.out.println("Last name:");
        deceasedLastName = scanner.nextLine();
        System.out.println("Gender:");
        deceasedGender = scanner.nextLine();
        System.out.println("Date of birth: ");
        dateOfBirth = scanner.nextLine();
        System.out.println("Date of death: ");
        dateOfDeath = scanner.nextLine();
        System.out.println("ResidentalAdress:");
        residentalAdress = scanner.nextLine();
        System.out.println("identificationNumber:");
        identificationNumber = scanner.nextLine();
        person.add(new Person(deceasedFirstName, deceasedLastName, deceasedGender, dateOfBirth, dateOfDeath,residentalAdress,identificationNumber,true));
    }
    public static void managePersonAdmin(List<Person> person,List<Person> foundPeopleList) {
        Scanner scanner = new Scanner(System.in);
        boolean accessGranted = false;

        while (!accessGranted) {
            clearConsole();
            System.out.print("Please enter your search term (name, surname, gender, PESEL, date of birth, or address): ");
            String searchTerm = scanner.next();
            clearConsole();
            int resultCount = 0;
            foundPeopleList.clear();
            for (Person persons : person) {
                if ((persons.getFirstName() != null && persons.getFirstName().equalsIgnoreCase(searchTerm))
                        || (persons.getLastName() != null && persons.getLastName().equalsIgnoreCase(searchTerm))
                        || (persons.getGender() != null && persons.getGender().equalsIgnoreCase(searchTerm))
                        || (persons.getIdentificationNumber() != null && persons.getIdentificationNumber().equalsIgnoreCase(searchTerm))
                        || (persons.getDateOfBirth() != null && persons.getDateOfBirth().equalsIgnoreCase(searchTerm))
                        || (persons.getResidentalAdress() != null && persons.getResidentalAdress().equalsIgnoreCase(searchTerm))) {
                    System.out.print("Person " + (resultCount + 1) + ": ");
                    System.out.print(persons);
                    foundPeopleList.add(persons);
                    System.out.println();
                    resultCount++;
                }
            }

            if (resultCount == 0) {
                System.out.println("Any person not found. Please try again.");
            } else {
                System.out.print("Enter the number of the person you want to manage (1-" + resultCount + "): ");
                int choice4 = scanner.nextInt();

                if (choice4 >= 1 && choice4 <= resultCount) {
                    boolean check = true;
                    clearConsole();
                    System.out.println("You selected " + foundPeopleList.get(choice4 - 1).getFirstName() + " " + foundPeopleList.get(choice4 - 1).getLastName() + " isMarried " + foundPeopleList.get(choice4 - 1).getSpouse() + " What you want to change ?");
                    while (check) {
                        int choice = getUserChoiceManageMenu(scanner);

                        switch (choice) {
                            case (1):
                                clearConsole();
                                System.out.println("First name: ");
                                for (Person persons : person) {
                                    if (persons.getId() == foundPeopleList.get(choice4 - 1).getId()) {
                                        persons.setFirstName(scanner.next());
                                        System.out.println("Changes have been saved");
                                        System.out.println("-------------------------------------------------------------");
                                        System.out.println(persons);
                                    }
                                }
                                break;
                            case (2):
                                clearConsole();
                                System.out.println("Last name: ");
                                for (Person persons : person) {
                                    if (persons.getId() == foundPeopleList.get(choice4 - 1).getId()) {
                                        persons.setLastName(scanner.next());
                                        System.out.println("Changes have been saved");
                                        System.out.println("-------------------------------------------------------------");
                                        System.out.println(persons);
                                    }
                                }
                                break;
                            case (3):
                                clearConsole();
                                System.out.println("Gender: ");
                                for (Person persons : person) {
                                    if (persons.getId() == foundPeopleList.get(choice4 - 1).getId()) {
                                        persons.setGender(scanner.next());
                                        System.out.println("Changes have been saved");
                                        System.out.println("-------------------------------------------------------------");
                                        System.out.println(persons);
                                    }
                                }
                                break;
                            case (4):
                                clearConsole();
                                System.out.println("ResidentalAdress: ");
                                for (Person persons : person) {
                                    if (persons.getId() == foundPeopleList.get(choice4 - 1).getId()) {
                                        persons.setResidentalAdress(scanner.next());
                                        System.out.println("Changes have been saved");
                                        System.out.println("-------------------------------------------------------------");
                                        System.out.println(persons);
                                    }
                                }
                                break;
                            case (0):
                                check = false;
                                break;
                        }
                    }
                }
            }

            if (confirmAction("Can you exit (y/n)")) {
                accessGranted = true;
            } else continue;
        }
    }
    public static void managePersonUser(List<Person> person,List<Person> foundPeopleList,int id){
        Scanner scanner = new Scanner(System.in);
        boolean accessGranted = false;

        while (!accessGranted) {
            clearConsole();
            int resultCount = 0;
            foundPeopleList.clear();
            for (Person persons : person) {
                if (persons.getId() == id) {
                    System.out.print("Person " + (resultCount + 1) + ": ");
                    System.out.print(persons);
                    foundPeopleList.add(persons);
                    System.out.println();
                    resultCount++;
                }
            }

            if (resultCount == 0) {
                System.out.println("Any person not found. Please try again.");
            } else {
                boolean check = true;
                clearConsole();
                System.out.println("You Personality "+ foundPeopleList.get(0).getFirstName()+" "+foundPeopleList.get(0).getLastName()+" isMarried "+foundPeopleList.get(0).getSpouse()+" What you want to change ?");
                while (check) {
                    int choice = getUserChoiceManageMenu(scanner);

                    switch (choice) {
                        case (1):
                            clearConsole();
                            System.out.println("First name: ");
                            for (Person persons : person) {
                                if (persons.getId() == foundPeopleList.get(0).getId()) {
                                    persons.setFirstName(scanner.next());
                                    System.out.println("Changes have been saved");
                                    System.out.println("-------------------------------------------------------------");
                                    System.out.println(persons);
                                }
                            }
                            break;
                        case (2):
                            clearConsole();
                            System.out.println("Last name: ");
                            for (Person persons : person) {
                                if (persons.getId() == foundPeopleList.get(0).getId()) {
                                    persons.setLastName(scanner.next());
                                    System.out.println("Changes have been saved");
                                    System.out.println("-------------------------------------------------------------");
                                    System.out.println(persons);
                                }
                            }
                            break;
                        case (3):
                            clearConsole();
                            System.out.println("Gender: ");
                            for (Person persons : person) {
                                if (persons.getId() == foundPeopleList.get(0).getId()) {
                                    persons.setGender(scanner.next());
                                    System.out.println("Changes have been saved");
                                    System.out.println("-------------------------------------------------------------");
                                    System.out.println(persons);
                                }
                            }
                            break;
                        case (4):
                            clearConsole();
                            System.out.println("ResidentalAdress: ");
                            for (Person persons : person) {
                                if (persons.getId() == foundPeopleList.get(0).getId()) {
                                    persons.setResidentalAdress(scanner.next());
                                    System.out.println("Changes have been saved");
                                    System.out.println("-------------------------------------------------------------");
                                    System.out.println(persons);
                                }
                            }
                            break;
                        case (0):
                            check = false;
                            break;
                    }
                }
            }

            if (confirmAction("Can you exit (y/n)")) {
                accessGranted = true;
            } else continue;
        }
    }

    private static boolean confirmAction(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");
    }
    public static void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }
    public static void addToList(List<Person> person) {
        person.add(new Person("John", "Doe", "Male", "1928-12-10", false));
        person.add(new Person("Jane", "Smith", "Female", "1985-08-22", false));
        person.add(new Person("Michael", "Johnson", "Male", "1978-03-10", false));
        person.add(new Person("Emily", "Williams", "Female", "1995-11-27", false));
        person.add(new Person("Robert", "Brown", "Male", "1982-09-05", false));
        person.add(new Person("Olivia", "Davis", "Female", "2000-02-18", false));
        person.add(new Person("Daniel", "Miller", "Male", "1993-06-30", false));
        person.add(new Person("Sophia", "Anderson", "Female", "1989-12-12", false));
        person.add(new Person("William", "Taylor", "Male", "1975-07-08", false));
        person.add(new Person("Emma", "Jones", "Female", "1998-04-25", false));
        person.add(new Person("Alice", "Johnson", "Female", "1992-08-10", "123 Oak St", "17122193697", false));
        person.add(new Person("Bob", "Miller", "Male", "1987-03-25", "456 Pine St", "76092193498", false));
        person.add(new Person("Eva", "Taylor", "Female", "1995-12-03", "789 Maple St", "59040275163", false));
        person.add(new Person("David", "Brown", "Male", "1980-09-18", "101 Elm St", "56062761474", false));
        person.add(new Person("Grace", "Smith", "Female", "1998-05-01", "202 Cedar St", "00321015244", false));
        person.add(new Person("John", "Doe", "Male", "1990-05-13", "2022-03-20", "123 Main St", "97122193697", true));
        person.add(new Person("Jane", "Smith", "Female", "1985-08-22", "2021-10-15", "456 Oak St", "76092193498", true));
        person.add(new Person("Michael", "Johnson", "Male", "1978-03-10", "2023-01-05", "789 Pine St", "59040275163", true));
        person.add(new Person("Emily", "Williams", "Female", "1995-11-27", "2022-06-12", "101 Elm St", "56062761474", true));
        person.add(new Person("Robert", "Brown", "Male", "1982-09-05", "2020-12-03", "202 Maple St", "00321015244", true));
        person.add(new Person("Olivia", "Davis", "Female", "2000-02-18", "2022-09-18", "303 Cedar St", "96010627122", true));
        person.add(new Person("Daniel", "Miller", "Male", "1993-06-30", "2021-04-30", "404 Birch St", "74040911361", true));
        person.add(new Person("Sophia", "Anderson", "Female", "1989-12-12", "2023-07-22", "505 Spruce St", "05302042235", true));
        person.add(new Person("William", "Taylor", "Male", "1975-07-08", "2022-11-10", "606 Fir St", "67042042665", true));
        person.add(new Person("Emma", "Jones", "Female", "1998-04-25", "2021-08-05", "707 Redwood St", "05271424159", true));
        person.add(new Person("John", "Miller", "Male", "1990-05-15", "2022-03-20", "123 Main St", "74080566145", true));
        person.add(new Person("Jane", "Anderson", "Female", "1985-08-22", "2021-10-15", "456 Oak St", "81020943573", true));
        person.add(new Person("Michael", "Smith", "Male", "1978-03-10", "2023-01-05", "789 Pine St", "90011045558", true));
        person.add(new Person("Emily", "Johnson", "Female", "1995-11-27", "2022-06-12", "101 Elm St", "89052694797", true));
        person.add(new Person("Robert", "Williams", "Male", "1982-09-05", "2020-12-03", "202 Maple St", "02290657539", true));
        person.add(new Person("Olivia", "Taylor", "Female", "2000-02-18", "2022-09-18", "303 Cedar St", "48120829534", true));
        person.add(new Person("Daniel", "Brown", "Male", "1993-06-30", "2021-04-30", "404 Birch St", "52121347713", true));
        person.add(new Person("Sophia", "Smith", "Female", "1989-12-12", "2023-07-22", "505 Spruce St", "68091935555", true));
        person.add(new Person("William", "Johnson", "Male", "1975-07-08", "2022-11-10", "606 Fir St", "52021493213", true));
        person.add(new Person("Emma", "Williams", "Female", "1998-04-25", "2021-08-05", "707 Redwood St", "00242253635", true));
    }
}


