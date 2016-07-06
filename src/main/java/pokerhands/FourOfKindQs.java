package pokerhands;

import noidea.PokerCard;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 24.06.16.
 */
public class FourOfKindQs implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        producedDeadCards.clear();
        long countInCards = cards.stream().filter(e -> e.value == 'Q').count();
        long countInDeadCards = deadCards.stream().filter(e -> e.value == 'Q').count();

        if(countInCards == 4) return true;
        else if(countInCards == 3 && countInDeadCards == 0 && cards.size() < 5) {producedDeadCards.add(new PokerCard("Qx")); return true;}
        else if(countInCards == 2 && countInDeadCards == 0 && cards.size() < 4) {for(int i=0;i<2;i++) producedDeadCards.add(new PokerCard("Qx"));return true;}
        else if(countInCards == 1 && countInDeadCards == 0 && cards.size() < 3) {for(int i=0;i<3;i++) producedDeadCards.add(new PokerCard("Qx"));return true;}
        else if(countInCards == 0 && countInDeadCards == 0 && cards.size() < 2) {for(int i=0;i<4;i++) producedDeadCards.add(new PokerCard("Qx"));return true;}
        else return false;
    }

    @Override
    public int getValue(int row) {
        if(row == 2) return 20;
        else return 10;
    }

    @Override
    public String getName() {
        return "Four of kind - Qs";
    }

    @Override
    public int getPriority() {
        return 76;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        int cardsNeeded = 4 - (int)cards.stream().filter(e -> e.value == 'Q').count();
        for(int i=0;i<cardsNeeded;i++) comboTemporaryDeadCards.add(new PokerCard(new StringBuilder().append('Q').append('x').toString()));
        if(cards.size() == 5 - cardsNeeded) return 1;
        else {
            comboTemporaryDeadCards.add(new PokerCard(new StringBuilder().append('x').append('x').toString()));
            return 13;
        }
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}