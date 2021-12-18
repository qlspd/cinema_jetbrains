package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        int num = 1;
        String[][] arr = new String[rows][seats];
        setCinema(rows, seats, arr);
        while (num != 0) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            num = scanner.nextInt();
            if (num == 1) {
                showSeats(rows, seats, arr);
            } else if (num == 2) {
                buyTicket(rows, seats, arr);
            } else if (num == 3) {
                stats(rows, seats, arr);
            }
        }
    }

    static void showSeats(int rows, int seats, String[][] arr) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void setCinema(int rows, int seats, String[][] arr) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                arr[i][j] = "S";
            }
        }
    }

    static void buyTicket(int rows, int seats, String[][] arr) {
        while (true) {
            System.out.println("Enter a row number:");
            int rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNum = scanner.nextInt();
            if (rowNum > rows || seatNum > seats) {
                System.out.println("\nWrong input!\n");
            } else if (Objects.equals(arr[rowNum - 1][seatNum - 1], "B")) {
                System.out.println("\nThat ticket has already been purchased!\n");
            } else {
                arr[rowNum - 1][seatNum - 1] = "B";
                int ticketPrice = rows / 2 + rows % 2 > rowNum || rows * seats < 60 ? 10 : 8;
                System.out.println("Ticket price: $" + ticketPrice);
                System.out.println();
                break;
            }
        }
    }

    static void stats(float rows, float seats, String[][] arr) {
        int rowNum;
        int seatNum;
        int formula;
        int totalIncome = 0;
        int income = 0;
        int tickets = 0;
        for (rowNum = 0; rowNum < rows; rowNum++) {
            for (seatNum = 0; seatNum < seats; seatNum++) {
                formula = rows * seats < 60 || rows / 2 + rows % 2 > rowNum + 2 ? 10 : 8;
                totalIncome += formula;
                if (Objects.equals(arr[rowNum][seatNum], "B")) {
                    tickets++;
                    income += formula;
                    System.out.println("formula = " + formula);
                }
            }
        }
        float percentage = tickets / (rows * seats) * 100;
        System.out.println("Number of purchased tickets: " + tickets);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
    }
}
