package org.example.casino_game;

/**
 * Jack is internally represented as 11, Queen as 12, King as 13 and Ace as 1.
 */

public class Card {
    private int number;
    private Suit suit;

    public Card(){}
    public Card(int number, Suit suit){
        this.number = number;
        this.suit = suit;
    }

    public int getNumber(){
        return number;
    }
    public Suit getSuit(){
        return suit;
    }
    public String getImagePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("/images/cards/card_");
        sb.append(suit.name().toLowerCase());
        sb.append("_");
        sb.append(number);
        sb.append(".png");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card c = (Card) obj;
        return number == c.number && suit == c.suit;
    }
}

/*public record Card(int number, Suit suit) {

    public String getImagePath() {
        StringBuilder sb = new StringBuilder();
        sb.append("/images/cards/card_");
        sb.append(suit.name().toLowerCase());
        sb.append("_");
        sb.append(number);
        sb.append(".png");
        return sb.toString();
    }

}*/
