package model;

public class CardModel {
    private String cardName;
    private String cardNumber;
    private Integer cardCVC;
    private Integer expiryMonth;
    private Integer expiryYear;

    public CardModel() {}

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return String.format(
                "CardFormModel{ cardName='%s', cardNumber='%s', cardCVC='%d', expiryMonth='%d', expiryYear='%d'}",
                cardName, cardNumber, cardCVC, expiryMonth, expiryYear
        );
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardCVC(Integer cardCVC) {
        this.cardCVC = cardCVC;
    }

    public Integer getCardCVC() {
        return cardCVC;
    }

    public void setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }
}
