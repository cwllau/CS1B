//// Assignment 2 - July 11, 2018 - Colleen Lau - CS1B Summer 2018
//Program: Creates a deck of cards with the ability to have more than one deck of cards in that array with other features such as shuffle and inspecting cards. We can distribute a set of cards from the deck to a hand, the distributed set can also be shuffled.

import java.util.Random;
import java.util.Scanner;

public class A2
{
   public static void main(String[] args)
   {
      //----------PHASE 1 ----------
      System.out.println("----------PHASE 1 TESTING----------\n\n");

      //declare a deck with 2 packs
      Deck deck = new Deck(2);
      int tCard  = deck.getTopCard(); //test topCard to get amt of cards in array

      //test inspectCard
      System.out.println("Inspecting card at int k = 50: " + deck.inspectCard(50).toString() + "\n");

      //testing to make sure the whole deck is okay
      System.out.println("2 decks of cards: ");
      String str = "[";
      for (int i = 0; i < tCard; i++)
      { //dealing out the deck
         str += deck.dealCard().toString() + " / ";
      }
      str += "]";
      System.out.println(str + "\n\n");

      //test shuffle
      System.out.println("Shuffled cards:");
      deck.init(3); //reset the deck
      deck.shuffle();

      str = "[";
      for (int i = 0; i < tCard; i++)
      { //dealing out the deck
         str += deck.dealCard().toString() + " / ";
      }
      str += "]";
      System.out.println(str + "\n\n");

//////DECLARE A DECK WITH ONE PACK////////////
      //declare a deck with 1 packs
      Deck deck1 = new Deck(1);
      int tCard1  = deck1.getTopCard(); //test topCard to get amt of cards in array

      //test inspectCard
      System.out.println("Inspecting card at int k = 50: " + deck1.inspectCard(50).toString() + "\n");

      //testing to make sure the whole deck is okay
      System.out.println("1 deck of cards: ");
      str = "[";
      for (int i = 0; i < tCard1; i++)
      { //dealing out the deck1
         str += deck1.dealCard().toString() + " / ";
      }
      str += "]";
      System.out.println(str + "\n\n");

      //test shuffle
      System.out.println("Shuffled cards:");
      deck1.init(1); //reset the deck1
      deck1.shuffle();

      str = "[";
      for (int i = 0; i < tCard1; i++)
      { //dealing out the deck1
         str += deck1.dealCard().toString() + " / ";
      }
      str += "]";
      System.out.println(str + "\n\n");



      ////////////////////////-------------------------------------
      //PHASE 2 TESTING HAND AND DECK

      System.out.println("\n\n ---------PHASE 2 HAND AND DECK ---------\n\n");


      //user input
      Scanner reader = new Scanner(System.in);  // Reading from System.in
      System.out.println("How many hands? (1 - 10, please): ");
      int hands = reader.nextInt(); // Scans the next token of the input as an int.
      //once finished
      reader.close();

      if (hands >= 1 && hands <= 10)
      {
         System.out.println(hands + " hands");

         //deal hands
         Deck hands_deck = new Deck();
         //array of hands
         Hand[] hands_array = new Hand[hands];
         for (int i = 0; i < hands; i++)
         {
            hands_array[i] = new Hand();
         }

         //DEALING CARDS ONE BY ONE
         while ((hands_deck.getTopCard()+1) != 0 )
         {
            for (int j = 0; j <= hands; j++)
            {
               if((hands_deck.getTopCard()+1) == 0)
                  break;
               if (j >= hands)
                  j = 0;
               hands_array[j].takeCard(hands_deck.dealCard());
            }
         }

         //print the hands
         System.out.println("Hands from unshuffled deck:");
         for(int i = 0; i < hands; i++)
            System.out.println(hands_array[i].toString() + "\n");


         //reset deck with 1 set of cards
         hands_deck.init(1);
         //Shuffle
         hands_deck.shuffle();
         //clear hand array
         for(int i = 0; i < hands; i++)
            hands_array[i].resetHand();


         //DEALING CARDS ONE BY ONE
         while ((hands_deck.getTopCard()+1) != 0 )
         {
            for (int j = 0; j <= hands; j++)
            {
               if((hands_deck.getTopCard()+1) == 0)
                  break;
               if (j >= hands)
                  j = 0;
               //System.out.println(j + " hands" + hands_deck.getTopCard() +   "card");
               hands_array[j].takeCard(hands_deck.dealCard());
            }
         }
         //print the hands
         System.out.println("Hands from Shuffled deck:");
         for(int i = 0; i < hands; i++)
            System.out.println(hands_array[i].toString() + "\n");
      }
   }
}

//Deck class
//Creates a deck of cards with the ability to have more than one deck of cards in that array with other features such as shuffle and inspecting cards
 //------------------------------------------------------------------
class Deck
{
   public static final int MAX_PACKS = 6;
   public static final int NUM_CARDS_PER_PACK = 52;
   public static final int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;

   private static Card[] masterPack = new Card[NUM_CARDS_PER_PACK];
   private Card[] cards;
   private int topCard; // how many cards are actually in cards[] array
   private int numPacks;

   //default constructor
   public Deck(int numPacks)
   {
      allocateMasterPack();
      this.numPacks = numPacks;
      init(this.numPacks);
   }

   public Deck()
   {
      this(1); //same as numPacks = 1
   }

   //accessors
   public int getTopCard()
   {
      return topCard;
   }

   public int getNumPacks()
   {
      return numPacks;
   }

   public void init(int numPacks) //makes the cards[] array
   {
      int i, j;
      cards = new Card[NUM_CARDS_PER_PACK * numPacks];
      topCard = numPacks * NUM_CARDS_PER_PACK - 1;
      for (i = 0; i < numPacks; i++)
      {
         for (j = 0; j < NUM_CARDS_PER_PACK; j++)
         {
            //reference the master pack
            cards[i*NUM_CARDS_PER_PACK+j] = masterPack[j];
         }
      }
   }

   //private helper method
   private static void allocateMasterPack()
   {
      if(masterPack[0] != null) //make masterPack once
         return;

      //assign 52 standard cards to masterPack
      int k, j;
      Card.Suit st; //array example used ints not enums
      char val;

      // instantiate the array elements
      for (k = 0; k < 52; k++)
         masterPack[k] = new Card();

      for (k = 0; k < 4; k++)
      {
         // set the suit for this loop pass
         switch(k)
         {
         case 0:
            st = Card.Suit.clubs; break;
         case 1:
            st = Card.Suit.diamonds; break;
         case 2:
            st = Card.Suit.hearts; break;
         case 3:
            st = Card.Suit.spades; break;
         default:
            // should not happen but ...
            st = Card.Suit.spades; break;
        }

         // now set all the values for this suit
         masterPack[13*k].set('A', st);
         for (val='2', j = 1; val<='9'; val++, j++)
            masterPack[13*k + j].set(val, st);
         masterPack[13*k+9].set('T', st);
         masterPack[13*k+10].set('J', st);
         masterPack[13*k+11].set('Q', st);
         masterPack[13*k+12].set('K', st);
      }
   }

   public void shuffle() //shuffles cards
   {
      //create instance of the random class
      Random rand = new Random();

      for (int i = 0; i < topCard; i++)//like switching places
      {
         int random_int = rand.nextInt(topCard);
         Card temp = cards[i];
         cards[i] = cards[random_int];
         cards[random_int] = temp;
      }
   }

   public Card dealCard()
   {
      Card copy = new Card();
      if (topCard>=0)
      {
         copy = cards[topCard];
         topCard--;
      }
      return copy;
   }
   // - returns and removes  (effectively, not physically) the card in the top occupied position of cards[].  Here we have to return a copy of the card, not the actual reference to the object in the cards[] array, since that object is also the object in the masterPack[] array, which the client must not be allowed to change.

   public Card inspectCard(int k)
   //- Accessor for an individual card.  Returns a card with errorFlag = true if k is bad.  Otherwise returns a copy of the card (see admonition for dealCard()).
   {
      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases

      if (k < 0 || k >= topCard)
         return errorReturn;
      else
         return cards[k];
   }
}

//Hand class
//Creates an array of Cards which is considered a "Hand". Allows users to keep track of how many cards are in hand, check validity of cards in hand and play cards from hand.
 //------------------------------------------------------------------
class Hand
{
   private Card[] myCards = new Card[MAX_CARDS];
   private int numCards;
   //final makes it a cst
   public static final int MAX_CARDS = 50;

   // accessors

   public int getNumCards()
   {
      return numCards;
   }

//default constructor
   public Hand()
   {
    myCards = new Card[MAX_CARDS];
    numCards = 0;
   }

   //remove all cards from the hand (in the simplest way)
   public void resetHand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

//adds a card to the next available position in the myCards array
   public boolean takeCard(Card card)
   {
      if (numCards>= MAX_CARDS) {
         //Hand is full
         return false;
      }

      if (myCards[numCards] == null)
         myCards[numCards] = new Card();

      // don't just assign:  mutator assures active/undeleted
      myCards[numCards++].set( card.getVal(), card.getSuit() );
      return true;

   }

   //returns and removes (effectively, not physically) the card in the top occupied position of the array.
   public Card playCard()
   {
      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases

      if (numCards == 0)
         return errorReturn;
      else
         return myCards[--numCards];
   }

   // stringizer
   public String toString()
   {
      //System.out.println(numCards);
      String str = "Hand = [";
      for (int j = 0; j <= numCards -1; j++)
      {
          str += myCards[j].toString() + " , ";
      }
      str += "]";
      return str;
   }

// Accessor for an individual card.  Returns a card with errorFlag = true if k is bad.
   public Card inspectCard(int k)
   {

      Card errorReturn = new Card('E', Card.Suit.spades); // in rare cases

      if (k < 0 || k >= numCards)
         return errorReturn;
      else
         return myCards[k];
   }

}

//Card class
//Creates a Card object which has a Suit and value. Allows users to set cards, check validity of cards, and check if cards have same suit and value
// --------------------------------------------------------------------
class Card
{
   // type and constants
   public enum Suit { clubs, diamonds, hearts, spades }

   // private data
   private char value;
   private Suit suit;
   boolean errorFlag;

   // 4 overloaded constructors
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   public Card(char value)
   {
      this(value, Suit.spades);
   }
   public Card()
   {
      this('A', Suit.spades);
   }
   // copy constructor
   public Card(Card card)
   {
      this(card.value, card.suit);
   }

   // mutators
   public boolean set(char value, Suit suit)
   {
      char upVal;            // for upcasing char

      // convert to uppercase to simplify
      upVal = Character.toUpperCase(value);

      if ( !isValid(upVal, suit))
      {
         errorFlag = true;
         return false;
      }

      // else implied
      errorFlag = false;
      this.value = upVal;
      this.suit = suit;
      return true;
   }

   // accessors
   public char getVal()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getErrorFlag()
   {
      return errorFlag;
   }

   public boolean equals(Card card)
   {
      if (this.value != card.value)
         return false;
      if (this.suit != card.suit)
         return false;
      if (this.errorFlag != card.errorFlag)
         return false;
      return true;
   }

   // stringizer
   public String toString()
   {
      String retVal;

      if (errorFlag)
         return "** illegal **";

      // else implied

      retVal =  String.valueOf(value);
      retVal += " of ";
      retVal += String.valueOf(suit);

      return retVal;
   }

   // helper
   private boolean isValid(char value, Suit suit)
   {
      char upVal;

      // convert to uppercase to simplify (need #include <cctype>)
      upVal = Character.toUpperCase(value);

      // check for validity
      if (
            upVal == 'A' || upVal == 'K'
            || upVal == 'Q' || upVal == 'J'
            || upVal == 'T'
            || (upVal >= '2' && upVal <= '9')
            )
         return true;
      else
         return false;
   }
}

//----------Console output----------
/*
----------PHASE 1 TESTING----------


Inspecting card at int k = 50: Q of spades

2 decks of cards:
[K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of spades / A of spades / K of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of clubs / A of clubs / K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of spades / A of spades / K of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of clubs / ]


Shuffled cards:
[K of spades / J of clubs / 3 of spades / 9 of hearts / 8 of diamonds / 5 of diamonds / J of clubs / A of hearts / 6 of spades / 5 of clubs / Q of diamonds / K of clubs / K of spades / T of clubs / 6 of hearts / 9 of diamonds / 8 of diamonds / 7 of diamonds / Q of hearts / T of diamonds / 2 of hearts / 2 of hearts / J of spades / 7 of spades / 5 of hearts / 9 of spades / 9 of clubs / 7 of spades / T of clubs / 6 of diamonds / 9 of clubs / 4 of hearts / 8 of hearts / 3 of diamonds / A of spades / T of spades / A of spades / 2 of spades / A of clubs / 6 of clubs / 5 of hearts / 5 of hearts / 8 of clubs / 8 of diamonds / 8 of spades / 7 of diamonds / Q of clubs / K of diamonds / 4 of diamonds / T of diamonds / K of spades / A of hearts / A of diamonds / 6 of clubs / K of hearts / 9 of hearts / 9 of spades / 2 of spades / A of clubs / K of diamonds / A of spades / 4 of clubs / J of diamonds / 2 of diamonds / Q of spades / Q of clubs / J of hearts / 9 of diamonds / 7 of clubs / 4 of spades / T of diamonds / 2 of spades / T of hearts / 9 of hearts / J of spades / 5 of clubs / T of spades / 2 of diamonds / 4 of spades / 3 of clubs / 6 of spades / J of diamonds / 3 of clubs / 8 of clubs / 3 of diamonds / 4 of spades / 3 of hearts / 3 of spades / 5 of spades / 4 of hearts / 7 of clubs / 3 of diamonds / Q of spades / T of spades / J of diamonds / J of hearts / Q of hearts / 6 of hearts / A of diamonds / 5 of diamonds / 5 of clubs / Q of hearts / 9 of spades / ]


Inspecting card at int k = 50: Q of spades

1 deck of cards:
[K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of spades / A of spades / K of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of clubs / ]


Shuffled cards:
[K of spades / A of hearts / 5 of diamonds / T of clubs / K of clubs / 6 of hearts / 9 of clubs / K of diamonds / 4 of clubs / 2 of clubs / 6 of spades / Q of hearts / 9 of spades / K of hearts / Q of spades / 4 of diamonds / 7 of clubs / A of clubs / 8 of spades / 5 of clubs / 8 of clubs / T of hearts / A of spades / 5 of spades / 4 of spades / 7 of diamonds / 7 of spades / Q of diamonds / 6 of diamonds / J of diamonds / 6 of clubs / 8 of hearts / 2 of diamonds / 5 of hearts / 9 of diamonds / J of hearts / 2 of hearts / 4 of hearts / 3 of hearts / 7 of hearts / J of spades / 9 of hearts / 3 of clubs / A of diamonds / 2 of spades / 8 of diamonds / 3 of spades / 3 of diamonds / T of diamonds / T of spades / Q of clubs / ]




 ---------PHASE 2 HAND AND DECK ---------


How many hands? (1 - 10, please):
7
7 hands
Hands from unshuffled deck:
Hand = [K of spades , 6 of spades , Q of hearts , 5 of hearts , J of diamonds , 4 of diamonds , T of clubs , 3 of clubs , ]

Hand = [Q of spades , 5 of spades , J of hearts , 4 of hearts , T of diamonds , 3 of diamonds , 9 of clubs , 2 of clubs , ]

Hand = [J of spades , 4 of spades , T of hearts , 3 of hearts , 9 of diamonds , 2 of diamonds , 8 of clubs , A of clubs , ]

Hand = [T of spades , 3 of spades , 9 of hearts , 2 of hearts , 8 of diamonds , A of diamonds , 7 of clubs , ]

Hand = [9 of spades , 2 of spades , 8 of hearts , A of hearts , 7 of diamonds , K of clubs , 6 of clubs , ]

Hand = [8 of spades , A of spades , 7 of hearts , K of diamonds , 6 of diamonds , Q of clubs , 5 of clubs , ]

Hand = [7 of spades , K of hearts , 6 of hearts , Q of diamonds , 5 of diamonds , J of clubs , 4 of clubs , ]

Hands from Shuffled deck:
Hand = [K of spades , 4 of hearts , 7 of diamonds , 2 of spades , T of diamonds , 8 of clubs , T of spades , T of clubs , ]

Hand = [Q of clubs , A of clubs , 5 of hearts , 5 of spades , 7 of spades , A of hearts , 5 of clubs , 6 of diamonds , ]

Hand = [9 of diamonds , 8 of hearts , 2 of diamonds , 8 of diamonds , 4 of diamonds , J of hearts , 2 of clubs , J of clubs , ]

Hand = [K of clubs , 5 of diamonds , T of hearts , 4 of clubs , Q of hearts , 9 of clubs , 3 of clubs , ]

Hand = [6 of hearts , 8 of spades , 4 of spades , Q of diamonds , 6 of clubs , J of diamonds , 3 of hearts , ]

Hand = [A of diamonds , K of hearts , 9 of hearts , 7 of clubs , K of diamonds , 2 of hearts , 3 of diamonds , ]

Hand = [3 of spades , J of spades , 7 of hearts , 6 of spades , 9 of spades , Q of spades , A of spades , ]
*/
