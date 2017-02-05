package com.garypaduana.groovytools.system

class Colorize{

    static def red = '\033[0;31m'
    static def green = '\033[0;32m'
    static def blue ='\033[1;34m'
    static def nc = '\033[0m'

    static def colorize(def value, def color){
        return "${color}${value}${nc}"
    }
}
