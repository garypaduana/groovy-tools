package com.garypaduana.groovytools.system

import spock.lang.Specification

class TestFiles extends Specification {

    def "test find images larger than size"(){
        setup:
            def source = new File("src/main/resources/images")
        when:
            def results = Files.findImagesLargerThan(source, 800, 800)
        then:
            results.each(){
                println it
            }
            results.size() == 1
    }

    /**
     * Sample test data taken from:
     * http://www.nsrl.nist.gov/testdata/
     */
    def "test hash single file"(){
        setup:
            def source = new File("src/main/resources/test-data/hash-sample")
        when:
            def result = Files.generateDigest(source, "SHA-256", 64)
        then:
            result.equalsIgnoreCase("248D6A61D20638B8E5C026930C3E6039A33CE45964FF2167F6ECEDD419DB06C1")
    }

    def "test hash directory"(){
        setup:
            def source = new File("src/main/resources/test-data")
        when:
            def result = Files.generateDigest(source, "SHA-256", 64)
        then:
            result.equalsIgnoreCase("b1e430c0e26df940c9a31fcd5280857fcbc28e491f157fd7c3bbfce0d3f8058c")
    }
}
