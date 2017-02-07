package com.garypaduana.groovytools.system

class Colorize{

    static def RED = '\033[0;31m'
    static def GREEN = '\033[0;32m'
    static def BLUE ='\033[1;34m'
    static def NO_COLOR = '\033[0m'

    static def colorize(def value, def color){
        return "${color}${value}${NO_COLOR}"
    }
}
