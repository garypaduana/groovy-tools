package com.garypaduana.groovytools.algorithm

/**
 * Created by gary on 2/5/17.
 */
class Sort{
    static class SortingAlgorithms{

        /**
         * The algorithm starts at the beginning of the data set.
         * It compares the first two elements, and if the first
         * is greater than the second, it swaps them. It continues
         * doing this for each pair of adjacent elements to the
         * end of the data set. It then starts again with the first
         * two elements, repeating until no swaps have occurred on
         * the last pass.
         */
        def static bubbleSort(def array){
            for(int k = array.size() - 1; k > 0; k--){
                for(int i = 0; i < k; i++){
                    if(array[i] > array[i + 1]){
                        swap(array, i, i + 1)
                    }
                }
            }
            return array
        }

        /**
         * The algorithm finds the minimum value, swaps it with
         * the value in the first position, and repeats these steps
         * for the remainder of the list. It does no more than n
         * swaps, and thus is useful where swapping is very expensive.
         */
        def static selectionSort(def array){
            for(int k = 0; k < array.size(); k++){
                def min = array[k]
                def minIndex = k
                for(int i = k; i < array.size(); i++){
                    if(array[i] < min){
                        min = array[i]
                        minIndex = i
                    }
                }
                swap(array, k, minIndex)
            }
            return array
        }

        /**
         * Quicksort is a divide and conquer algorithm which relies
         * on a partition operation: to partition an array an element
         * called a pivot is selected.  All elements smaller than the
         * pivot are moved before it and all greater elements are moved
         * after it. This can be done efficiently in linear time and
         * in-place. The lesser and greater sublists are then
         * recursively sorted. This yields average time complexity of
         * O(n log n), with low overhead, and thus this is a popular algorithm.
         */
        def static quickSort(def array, def low, def high){
            if(low < high){
                def p = partition(array, low, high)
                quickSort(array, low, p - 1)
                quickSort(array, p + 1, high)
            }
            return array
        }

        def static partition(def array, def low, def high){
            def pivotIndex = (int) ((high - low) / 2 + low)
            if(pivotIndex == low){
                pivotIndex++
            }

            def pivotValue = array[pivotIndex]

            swap(array, pivotIndex, high)
            def storeIndex = low

            for(int i = low; i < high; i++){
                if(array[i] <= pivotValue){
                    swap(array, i, storeIndex)
                    storeIndex++
                }
            }
            swap(array, storeIndex, high)
            return storeIndex
        }

        /**
         * Swaps values found at positions i and j within array.
         */
        def static swap(def array, int i, int j){
            def temp = array[i]
            array[i] = array[j]
            array[j] = temp
            return array
        }
    }
}
