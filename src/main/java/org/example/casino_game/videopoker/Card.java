package org.example.casino_game.videopoker;

/**
 * Jack is internally represented as 11, Queen as 12, King as 13 and Ace as 14.
 */
public record Card(int number, Suit suit) {

    public boolean hasSameSuit(Card card) {
        return suit == card.suit;
    }

    public boolean hasSameNumber(int number) {
        return number == this.number;
    }

    public String getImagePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("/images/cards/card_");
        sb.append(suit.name().toLowerCase());
        sb.append("_");

        if (number == 14) {
            sb.append(1);
        } else {
            sb.append(number);
        }

        sb.append(".png");
        return sb.toString();
    }

}
