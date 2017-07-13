package com.garypaduana.groovytools.data

import spock.lang.Specification

class TestCalculations extends Specification {

    def "test calculateParity"(){
        setup:
            def source = "AAnnn"
        when:
            def parity = Calculations.calculateParity(source, 8)
        then:
            parity.equals("aa")
    }
}
