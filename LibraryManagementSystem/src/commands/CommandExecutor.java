package commands;

import managers.LibraryManager;

import java.util.Arrays;

public class CommandExecutor {
    private final LibraryManager mgr = LibraryManager.getInstance();

    public void execute(String[] parts) {
        switch (parts[0]) {
            case "create_library" -> mgr.createLibrary(Integer.parseInt(parts[1]));
            case "add_book" -> {
                // Validate exactly 6 parts: operation + 5 args
                if (parts.length < 6) break;
                mgr.addBook(parts[1], parts[2],
                        Arrays.asList(parts[3].split(",")),
                        Arrays.asList(parts[4].split(",")),
                        Arrays.asList(parts[5].split(",")));
            }
            case "remove_book_copy" -> mgr.removeBookCopy(parts[1]);
            case "borrow_book" -> mgr.borrowBook(parts[1], parts[2], parts[3]);
            case "borrow_book_copy" ->{
                if (parts.length < 4) {
                    System.out.println("Invalid command");
                    return;
                }
                mgr.borrowBookCopy(parts[1], parts[2], parts[3]);
            }
            case "return_book_copy" -> mgr.returnBookCopy(parts[1]);
            case "print_borrowed" -> mgr.printBorrowed(parts[1]);
            case "search" -> mgr.search(parts[1], parts[2]);
        }
    }
}
