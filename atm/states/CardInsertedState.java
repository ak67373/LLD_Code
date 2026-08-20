package atm.states;

import atm.core.ATM;
import atm.enums.ATMState;
import atm.exceptions.ATMException;
import atm.models.Account;
import atm.models.Card;

public class CardInsertedState implements ATMStateHandler {
    @Override
    public void insertCard(ATM atm, Card card) {
        throw new ATMException("A card is already inserted");
    }

    @Override
    public void authenticate(ATM atm, String pin) {
        Account account = atm.getBankService().authenticate(
                atm.getCurrentCard().getCardNumber(),
                pin
        );

        atm.setCurrentAccount(account);
        atm.setState(ATMState.AUTHENTICATED);
    }

    @Override
    public void withdraw(ATM atm, double amount) {
        throw new ATMException("Please authenticate first");
    }

    @Override
    public void deposit(ATM atm, double amount) {
        throw new ATMException("Please authenticate first");
    }

    @Override
    public double checkBalance(ATM atm) {
        throw new ATMException("Please authenticate first");
    }

    @Override
    public void ejectCard(ATM atm) {
        atm.setCurrentCard(null);
        atm.setState(ATMState.IDLE);
    }
}