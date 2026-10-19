package model;


public class PaymentModel {
    private CardModel card;

    public PaymentModel() {}

    public void setCard(CardModel card) {
        this.card = card;
    }

    public CardModel getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "PaymentModel{ " +
                card.toString() +
                "}";
    }
}
