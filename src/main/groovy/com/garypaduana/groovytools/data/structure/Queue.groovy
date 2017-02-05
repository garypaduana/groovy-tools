package com.garypaduana.groovytools.data.structure

class Queue{

    def data = []

    def enqueue(def element){
        // add element just before index 0
        data.add(0, element)
    }

    def dequeue(def element){
        if(data.size() > 0){
            return data.pop()
        }
        return null
    }

    def size(){
        return data.size()
    }

    def peek(){
        return data.get(data.size() - 1)
    }
}
