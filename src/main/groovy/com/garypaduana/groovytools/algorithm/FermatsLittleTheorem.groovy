package com.garypaduana.groovytools.algorithm

class FermatsLittleTheorem{

    /**
     * Implementation of Fermat's Little Theorm to determine if a
     * number is probably prime.
     */
    static def probablyPrime(def p){
        for(int i = 0; i < 30; i++){
            def a = randomBetween(2, p)
            if(!congruentModulo(a.pow(p.intValue() - 1), 1, p)){
                return false
            }
        }
        return true
    }

    /**
     * Returns a number between the two arguments inclusive to
     * the first argument and exclusive of the second.  Ordering
     * does not matter.
     */
    static def randomBetween(def a, def b){
        Random r = new Random()
        return BigInteger.valueOf((long) r.nextDouble() * (b - a) + a)
    }

    /**
     * Determines if arguments a and b are congruent modulo to n.
     */
    static def congruentModulo(def a, def b, def n){
        return (a - b) % n == 0
    }
}
