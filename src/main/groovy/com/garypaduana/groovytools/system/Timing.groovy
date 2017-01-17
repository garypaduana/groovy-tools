package com.garypaduana.groovytools.system

public class Timing {
    
    public static def delay(def start, def end){
        (start..end).each(){count ->
            Thread.sleep(1000)
            if(count % 10 == 0){
                print count
            }
            else if(count == end){
                print count
            }
            else{
                print "."
            }
        }
        println ""
    }
}