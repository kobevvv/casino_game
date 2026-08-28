package org.example.casino_game.videopoker;

/**
 * Jack is internally represented as 11, Queen as 12, King as 13 and Ace as 1.
 */
public record Card(int number, Suit suit) {

    public String getImagePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("/images/cards/card_");
        sb.append(suit.name().toLowerCase());
        sb.append("_");
        sb.append(number);
        sb.append(".png");
        return sb.toString();
    }

}
