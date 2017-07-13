package com.garypaduana.groovytools.data

import com.garypaduana.groovytools.formatting.StringCleaner

class Calculations {

    static def calculateParity(String hex, int parityBitLength){

        hex = StringCleaner.stripNonHexCharacters(hex)

        if(parityBitLength > 64){
            throw new IllegalArgumentException("parityBitLength must be less than or equal to 64")
        }

        if((hex.length() * 4) % parityBitLength != 0) {
            throw new IllegalArgumentException("Invalid word length found!")
        }

        def pieces = []
        StringBuilder sb = new StringBuilder(hex)

        while(sb.length() >= (parityBitLength / 4)){
            def end = (int) (parityBitLength / 4)
            pieces.add(Long.decode("0x" + sb.substring(0, end)))
            sb.delete(0, end)
        }

        long parity = 0
        for(long piece : pieces){
            parity = parity ^ piece
        }

        return Long.toHexString(parity).toLowerCase()
    }
}
