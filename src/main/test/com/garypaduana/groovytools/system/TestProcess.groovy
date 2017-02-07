package com.garypaduana.groovytools.system

import spock.lang.Specification

class TestProcess extends Specification{

    def "test process successful"(){
        when:
            def result = Process.execute("ls -lart", true)
        then:
            result.exitValue == 0
            result.body.length() > 0
    }

    def "test colored console text"(){
        setup:
            def line = "Hello, in Red!"
        when:
            def value = "${Colorize.colorize(line, Colorize.RED)}"
            println value
        then:
            value.equals("${Colorize.RED}$line${Colorize.NO_COLOR}")
    }
}
