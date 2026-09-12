package org.example.casino_game.memory;

import org.example.casino_game.Card;
import org.example.casino_game.Suit;

public class MemoryCard extends Card {
    private boolean turned;
    private boolean found;

    public MemoryCard(int number, Suit suit, boolean turned, boolean found){
        super(number, suit);
        this.turned = turned;
        this.found = found;
    }

    @Override
    public String getImagePath() {
        if (!turned) return "/images/cards/card backs/blue_back_suits_dark.png";
        StringBuilder sb = new StringBuilder();
        sb.append("/images/cards/card_");
        sb.append(super.getSuit().name().toLowerCase());
        sb.append("_");
        sb.append(super.getNumber());
        sb.append(".png");
        return sb.toString();
    }

    public void setTurned(boolean t){
        turned = t;
    }
    public void found(){
        found = true;
    }
}
