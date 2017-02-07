package com.garypaduana.groovytools.system

class Timing{

    static def delay(def start, def end){
        (start..end).each(){ count ->
            Thread.sleep(1000)
            if(count % 10 == 0){
                print count
            } else if(count == end){
                print count
            } else{
                print "."
            }
        }
        println ""
    }

    static def timeIt = { closure ->
        def start = System.nanoTime()
        def result = closure()
        def duration = (System.nanoTime() - start) / 1e9
        return [returnValue: result, duration: duration]
    }
}