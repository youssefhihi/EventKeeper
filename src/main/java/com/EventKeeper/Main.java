package com.EventKeeper;
import com.EventKeeper.entity.Event;
import com.EventKeeper.enums.type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // This will cause an ArithmeticException
        } catch (ArithmeticException e) {
            e.printStackTrace(); // Prints the stack trace but does not terminate the program
        }

        // This line will still execute
        System.out.println("Program continues running after exception handling.");
    }
}