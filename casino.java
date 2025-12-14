package casino;

import java.io.*;

public class Customer {
    private double wallet;

    // Default constructor
    public Customer() {
        this.wallet = 500.0;
    }

    // Constructor loads from file
    public Customer(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            this.wallet = Double.parseDouble(br.readLine());
        }
    }

    public void spend(double amount) {
        if (amount > wallet) {
            System.out.println("Not enough funds! You spend only: $" + wallet);
            wallet = 0;
        } else {
            wallet -= amount;
        }
    }

    public void receive(double amount) {
        wallet += amount;
    }

    public double checkWallet() {
        return wallet;
    }

    public void save(String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(wallet + "\\n");
        }
    }
}
🎰 SlotMachine.java
java
Copy code
package casino;

import java.io.*;
import java.util.Random;

public class SlotMachine {
    private char slot1, slot2, slot3;
    private double moneyPot;
    private Random random = new Random();

    private static final char[] SYMBOLS = {'☺', '❤', '7'};

    // Constructors
    public SlotMachine() {
        this.moneyPot = 1_000_000.0;
    }

    public SlotMachine(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            this.moneyPot = Double.parseDouble(br.readLine());
        }
    }

    public double pullLever(double bet) {
        slot1 = SYMBOLS[random.nextInt(SYMBOLS.length)];
        slot2 = SYMBOLS[random.nextInt(SYMBOLS.length)];
        slot3 = SYMBOLS[random.nextInt(SYMBOLS.length)];

        System.out.println("Slot results: " + toString());

        if (slot1 == slot2 && slot2 == slot3) {
            double payout = bet * 10;
            moneyPot -= payout;
            System.out.println("JACKPOT! You win $" + payout);
            return payout;
        }
        moneyPot += bet;
        System.out.println("Sorry, you lost your bet of $" + bet);
        return 0;
    }

    public double getMoneyPot() {
        return moneyPot;
    }

    public void save(String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(moneyPot + "\\n");
        }
    }

    @Override
    public String toString() {
        return "" + slot1 + " " + slot2 + " " + slot3;
    }
}
🏦 GoodCasino.java
java
Copy code
package casino;

import java.io.*;
import java.util.Scanner;

public class GoodCasino {

    public static double play(Customer c, SlotMachine s, double amount) {
        c.spend(amount);
        double winnings = s.pullLever(amount);
        c.receive(winnings);
        return winnings;
    }

    public static void main(String[] args) {
        try {
            Customer c = new Customer("customer.txt");
            SlotMachine s = new SlotMachine("slot-machine.txt");
            Scanner in = new Scanner(System.in);

            System.out.println("🎰 Welcome to the Good Casino!");
            while (c.checkWallet() > 0 && s.getMoneyPot() > 0) {
                System.out.println("You have $" + c.checkWallet());
                System.out.print("Enter bet amount (or 'quit'): ");
                String input = in.nextLine();
                if (input.equalsIgnoreCase("quit")) break;

                double bet = Double.parseDouble(input);
                play(c, s, bet);
                System.out.println();
            }

            System.out.println("Game over!");
            c.save("customer.txt");
            s.save("slot-machine.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
