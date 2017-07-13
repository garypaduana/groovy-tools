package com.garypaduana.groovytools.formatting

import spock.lang.Specification

class TestStringCleaner extends Specification{

    def "test cleaning non hex from string"(){
        setup:
            def testCase = "77%\$ueallp009eacd"
        when:
            def result = StringCleaner.stripNonHexCharacters(testCase)
        then:
            result.equals("77ea009eacd")
    }

    def "test safe substring"(){
        setup:
            def source = "This is the test string"
        when:
            def result1 = StringCleaner.safeSubstring(source, 0, 100)
            def result2 = StringCleaner.safeSubstring(source, -10, 5)
            def result3 = StringCleaner.safeSubstring(source, -10, 200)
            def result4 = StringCleaner.safeSubstring(source, 2, 5)
            def result5 = StringCleaner.safeSubstring(source, 50, 0)
        then:
            result1.equals(source)
            result2.equals("This ")
            result3.equals(source)
            result4.equals("is ")
            result5.equals("")
    }

    def "test formatParityText"(){
        setup:
            def source = "012345678rtyuiop"
        when:
            def result = StringCleaner.formatParityText(4, 8, source)
        then:
            result.parityText.equals("0123 4567 8")
            result.label.equals("Length: 9 hex chars; 36 bits; 5 bytes")
            result.parityRowCounter.equals("00000000 ")
            println "ascii value: ${result.ascii}"
    }

    def "test decodeMessyHexToByteArray"(){
        setup:
            def source = "44uu66ppp999"
        when:
            def result = StringCleaner.decodeMessyHexToByteArray(source)
        then:
            result.length == 4
            result == [68, 102, -103, 9]

    }
}
