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
}
