package com.garypaduana.groovytools.algorithm

import spock.lang.Specification

class TestSort extends Specification{

    def "test bubble sort"(){
        setup:
            def array = [6, 5, 4, 3, 2, 1, 0]
        when:
            Sort.SortingAlgorithms.bubbleSort(array)
        then:
            array == [0, 1, 2, 3, 4, 5, 6]
    }

    def "test selection sort"(){
        setup:
            def array = [6, 5, 4, 3, 2, 1, 0]
        when:
            Sort.SortingAlgorithms.selectionSort(array)
        then:
            array == [0, 1, 2, 3, 4, 5, 6]
    }

    def "test quick sort"(){
        setup:
            def array = [6, 5, 4, 3, 2, 1, 0]
        when:
            Sort.SortingAlgorithms.quickSort(array, 0, array.size() - 1)
        then:
            array == [0, 1, 2, 3, 4, 5, 6]
    }
}
