package atm;

import atm.core.ATM;
import atm.core.BankService;
import atm.core.CashDispenser;
import atm.enums.Denomination;
import atm.models.Card;

public class ATMDemo {

    public static void main(String[] args) {

        // 1. Create BankService
        BankService bankService = new BankService();

        // 2. Create test accounts
        bankService.createAccount("ACC001", 5000);
        bankService.createAccount("ACC002", 1000);

        // 3. Create cards
        bankService.createCard("CARD001", "1234", "ACC001");
        bankService.createCard("CARD002", "5678", "ACC002");

        // 4. Setup ATM cash
        CashDispenser cashDispenser = new CashDispenser();

        cashDispenser.addCash(Denomination.HUNDRED, 10);
        cashDispenser.addCash(Denomination.FIFTY, 20);
        cashDispenser.addCash(Denomination.TWENTY, 30);
        cashDispenser.addCash(Denomination.TEN, 50);

        // 5. Create ATM
        ATM atm = ATM.getInstance(bankService, cashDispenser);

        // 6. Use ATM
        Card card = new Card("CARD001", "1234", "ACC001");

        atm.insertCard(card);

        atm.authenticate("1234");

        System.out.println("Balance: $" + atm.checkBalance());

        atm.withdraw(170);

        System.out.println(
                "Balance after withdrawal: $" + atm.checkBalance()
        );

        atm.ejectCard();
    }
}
