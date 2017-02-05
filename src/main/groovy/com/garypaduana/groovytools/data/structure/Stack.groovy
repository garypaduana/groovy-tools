package com.garypaduana.groovytools.data.structure

class Stack{

    private def data = []

    def pop(){
        if(data.size() > 0){
            return data.pop()
        }
        return null
    }

    def push(def element){
        data.add(element)
    }

    def peek(){
        return data.get(data.size() - 1)
    }

    def size(){
        return data.size()
    }
}
