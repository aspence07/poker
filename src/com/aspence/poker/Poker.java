package com.aspence.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.aspence.poker.Poker.Card.CardValue;

public class Poker {
	
	public static class CardComparator implements Comparator<Card> {

		@Override
		public int compare(Card card1, Card card2) {
			return card1.getCardValue().compareTo(card2.getCardValue());
		}
		
	}
	
	public static class HandComparator implements Comparator<List<Card>> {

		@Override
		public int compare(List<Card> hand1, List<Card> hand2) {
			
			Collections.sort(hand1, new CardComparator());
			Collections.sort(hand2, new CardComparator());
			
			Map<CardValue, Integer> valueCountMap1 = new TreeMap<CardValue, Integer>();
			Map<CardValue, Integer> valueCountMap2 = new TreeMap<CardValue, Integer>();
		
			for (Card card : hand1) {
				
				CardValue cardValue = card.getCardValue();
				
				Integer count = valueCountMap1.get(cardValue);
				
				if (count == null) {
					count = 1;
				} else {
					count++;
				}
				
				valueCountMap1.put(cardValue, count);
			}
			
			for (Card card : hand2) {
				
				CardValue cardValue = card.getCardValue();
				
				Integer count = valueCountMap2.get(cardValue);
				
				if (count == null) {
					count = 1;
				} else {
					count++;
				}
				
				valueCountMap2.put(cardValue, count);
			}
			
			System.out.println("hand one counts:");
			for (CardValue cardValue : valueCountMap1.keySet()) {
				Integer count = valueCountMap1.get(cardValue);
				System.out.println(cardValue + " : " + count);
			}
			
			System.out.println("hand two counts:");
			for (CardValue cardValue : valueCountMap2.keySet()) {
				Integer count = valueCountMap2.get(cardValue);
				System.out.println(cardValue + " : " + count);
			}
			
			// TODO
			return 0;
			
		}
		
	}
	
	private List<Card> deck = new LinkedList<Card>();
	
	public Poker() {
		this.reset();
	}
	
	public void reset() {
		
		deck.clear();
		
		int numSuites = Card.Suite.values().length;
		int numCards = Card.CardValue.values().length;
		
		for (int i = 0; i < numSuites; i++) {
			Card.Suite suite = Card.Suite.values()[i];
			for (int j = 0; j < numCards; j++) {
				Card.CardValue cardValue = Card.CardValue.values()[j];
				deck.add(new Card(suite, cardValue));
			}
		}
		
		Collections.shuffle(deck);
	}
	
	public List<Card> dealHand() {
		
		List<Card> hand = new ArrayList<Card>(5);
		
		for (int i = 0; i < 5; i++) {
			if (!deck.isEmpty()) {
				hand.add(deck.remove(0));
			}
		}
		
		return hand;
		
	}
	
	public static enum HAND_CATEGORIES {
		HIGH_CARD,
		ONE_PAIR,
		TWO_PAIR,
		THREE_OF_A_KIND,
		STRAIGHT,
		FLUSH,
		FULL_HOUSE,
		FOUR_OF_A_KIND,
		STRAIGHT_FLUSH,
		FIVE_OF_A_KIND
	}
	
	public static void main(String[] args) {
	
		Poker poker = new Poker();
		
		List<Card> hand1 = poker.dealHand();
		List<Card> hand2 = poker.dealHand();
		
		Collections.sort(hand1, new CardComparator());
		Collections.sort(hand2, new CardComparator());
				
		System.out.println("hand one:");
		for (Card card : hand1) {
			System.out.println(card);
		}
		
		System.out.println("hand two:");
		for (Card card : hand2) {
			System.out.println(card);
		}
		
		List<List<Card>> handList = new ArrayList<List<Card>>(2);
		handList.add(hand1);
		handList.add(hand2);
		
		Collections.sort(handList, new HandComparator());
		
	}

	public static class Card {
		
		private Suite suite;
		private CardValue cardValue;
		
		public String toString() {
			return "" + cardValue + " OF " + suite;
		}
		
		public Suite getSuite() {
			return suite;
		}
		
		public CardValue getCardValue() {
			return cardValue;
		}
		
		public Card(Suite suite, CardValue cardValue) {
			this.suite = suite;
			this.cardValue = cardValue;
		}
		
		public static enum Suite {
			HEARTS,
			CLUBS,
			DIAMONDS,
			SPADES
		}
		
		public static enum CardValue {
			TWO,
			THREE,
			FOUR,
			FIVE,
			SIX,
			SEVEN,
			EIGHT,
			NINE,
			TEN,
			JACK,
			QUEEN,
			KING,
			ACE
		}
		
	}
	
}
