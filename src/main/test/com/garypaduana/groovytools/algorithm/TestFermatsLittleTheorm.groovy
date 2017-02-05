package com.garypaduana.groovytools.algorithm

import com.garypaduana.groovytools.system.Timing
import spock.lang.Specification

class TestFermatsLittleTheorm extends Specification{

    def "test with known prime"(){
        when:
            def result = Timing.timeIt({FermatsLittleTheorm.probablyPrime(15485867)})
        then:
            result.returnValue == true
    }

    def "test with known composite"(){
        when:
            def result = Timing.timeIt({FermatsLittleTheorm.probablyPrime(2398932)})
        then:
            result.returnValue == false
    }
}
