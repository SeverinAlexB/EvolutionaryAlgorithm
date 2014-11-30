package ch.hsr.sevi.ea;

import java.util.ArrayList;

/**
 * An implementation of the Merge Sort algorithm
 * Source: https://github.com/JoshuaKissoon/Merge-Sort
 * @author Joshua Kissoon
 * @param <T>
 *
 * @since 20140301
 */
public class MergeSort<T extends Comparable>
{

    private final ArrayList<T>  items, aux;

    public MergeSort(ArrayList<T> items)
    {
        this.items = items;

        /* We have to make aux a distinct array from items */
        this.aux = (ArrayList<T>) items.clone();
    }

    /**
     * Method that initiates the sort the given items array
     */
    public void sort()
    {
        this.sort(0, this.items.size() - 1);
    }

    private void sort(int low, int high)
    {
        if (low >= high)
        {
            /* Were done sorting this section, do nothing */
            return;
        }

        /* Compute the midpoint */
        int mid = low + ((high - low) / 2);

        /* Sort the first half */
        this.sort(low, mid);

        /* Sort the second half */
        this.sort(mid + 1, high);

        /* Merge the 2 halves */
        this.merge(low, mid, high);
    }

    /**
     * Merge the values from low - high into the main array
     *
     * @param low  The starting point of the merge
     * @param mid  The dividing index to divide the arrays by
     * @param high The ending point of the merge
     */
    private void merge(int low, int mid, int high)
    {
        int i = low;        // Index value in main array

        int j = low;        // Index to the first half of the aux array
        int k = mid + 1;    // Index to the second half of the aux array

        /* Update the aux Array */
        for (int x = low; x <= high; x++)
        {
        	this.aux.set(x, this.items.get(x));
        }

        while (j <= mid || k <= high)
        {
            if (j > mid)
            {
                /* The first half is already finished, lets just go through the second half */
            	this.items.set(i, this.aux.get(k++));
            }
            else if (k > high)
            {
                /* The second half is already finished, lets just go through the first half */
            	this.items.set(i, this.aux.get(j++));
            }
            else if (this.aux.get(j).compareTo(this.aux.get(k)) > 0)
            {
                /* If aux[j] is > aux[k] */
            	this.items.set(i, this.aux.get(k++));
            }
            else
            {
                /* If aux[j] is <= aux[k] */
            	this.items.set(i, this.aux.get(j++));
            }

            i++;    // Increment the position we're at in the main array
        }
    }

    public ArrayList<T> getSortedItems()
    {
        return this.items;
    }
}