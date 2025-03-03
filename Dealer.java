import greenfoot.*;
import java.util.ArrayList;

/**
 * This class declares, instantiates, and initializes a new deck.  Students 
 * should then implement the sort method at the bottom of this class to see 
 * if they understand basic ArrayList sort algorithms.
 * 
 * @author Alex Tan
 * @version Feb 27, 2025
 */

public class Dealer extends Actor
{
    private Deck deck;
    private Card[] shuffledDeck;
    private Card[] selectionSort;
    private Card[] insertionSort;
    private Card[] mergeSort;
    
    
    public Dealer()
    {
        deck = new Deck();
        shuffledDeck = deck.getShuffledDeck();
        initializeSortArrays();
    }
    
    
    public void addedToWorld(World world)
    {
        showCards(world, shuffledDeck, 80);
        showCards(world, selectionSort(selectionSort, shuffledDeck.length), 220);
        showCards(world, insertionSort(insertionSort, shuffledDeck.length), 360);
        showCards(world, mergeSort(mergeSort, shuffledDeck.length), 500);
    }    
    
    
    private void initializeSortArrays()
    {
        selectionSort = new Card[shuffledDeck.length];
        insertionSort = new Card[shuffledDeck.length];
        mergeSort = new Card[shuffledDeck.length];
        
        for(int i = 0; i < shuffledDeck.length; i++)
        { 
            Card card1 = new Card(shuffledDeck[i].getImageFile(),shuffledDeck[i].getValue());
            Card card2 = new Card(shuffledDeck[i].getImageFile(),shuffledDeck[i].getValue());
            Card card3 = new Card(shuffledDeck[i].getImageFile(),shuffledDeck[i].getValue());
            selectionSort[i] = card1;
            insertionSort[i] = card2;
            mergeSort[i] = card3;
        }
    }
    
    
    private void showCards(World world, Card[] cards, int y)
    {
       for(int i = 0; i < cards.length; i++)
       {
           world.addObject(cards[i],58 + 30 * i, y);
       } 
    }
    
    
    private Card[] selectionSort(Card[] arr, int n)
    {
        
        for (int i = 0; i < n - 1; i++)
        {
            int minIndex = i; 
            
            
            for (int j = i + 1; j < n; j++)
            {
                if (arr[j].getValue() < arr[minIndex].getValue())
                {
                    minIndex = j;
                }
            }
            
            
            Card temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        
        return arr; 
    }
    
    
    private Card[] insertionSort(Card[] arr, int n)
    {
        
        for (int i = 1; i < n; i++)
        {
            Card key = arr[i];  
            int j = i - 1;
            
            while (j >= 0 && arr[j].getValue() > key.getValue())
            {
                arr[j + 1] = arr[j];
                j--;
            }
            
            arr[j + 1] = key;
        }
        
        return arr; 
    }
    
    private Card[] mergeSort(Card[] arr, int n)
    {
        
        if (n <= 1)
        {
            return arr;
        }
        
        
        int mid = n / 2;
        Card[] left = new Card[mid];
        Card[] right = new Card[n - mid];
        
        
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, n - mid);
        
        
        left = mergeSort(left, mid);
        right = mergeSort(right, n - mid);
        
        
        return merge(left, right);
    }
    
    
    private Card[] merge(Card[] left, Card[] right)
    {
        int leftSize = left.length;
        int rightSize = right.length;
        Card[] merged = new Card[leftSize + rightSize];
        
        int i = 0, j = 0, k = 0;
        
        
        while (i < leftSize && j < rightSize)
        {
            if (left[i].getValue() <= right[j].getValue())
            {
                merged[k++] = left[i++];
            }
            else
            {
                merged[k++] = right[j++];
            }
        }
        
        
        while (i < leftSize)
        {
            merged[k++] = left[i++];
        }
        
        
        while (j < rightSize)
        {
            merged[k++] = right[j++];
        }
        
        return merged; 
    }
}
