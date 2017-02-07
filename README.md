## Installation

[![](https://jitpack.io/v/garypaduana/groovy-tools.svg)](https://jitpack.io/#garypaduana/groovy-tools)

### gradle

**Step 1**. Add the JitPack repository to your build file

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

**Step 2**. Add the dependency

    dependencies {
        compile 'com.github.garypaduana:groovy-tools:-SNAPSHOT'
    }
    
### maven

**Step 1**. Add the JitPack repository to your build file

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

**Step 2**. Add the dependency

    <dependency>
        <groupId>com.github.garypaduana</groupId>
        <artifactId>groovy-tools</artifactId>
        <version>-SNAPSHOT</version>
    </dependency>

##Quick Usage

Import the dependency as a Grape.

    @GrabResolver(name='jitpack', root='https://jitpack.io')
    @Grab(group='com.github.garypaduana', module='groovy-tools', version='develop-SNAPSHOT')
    
    import com.garypaduana.groovytools.algorithm.FermatsLittleTheorem
    import static com.garypaduana.groovytools.system.Timing.timeIt
    
    def number = 53
    
    println timeIt({
        FermatsLittleTheorem.probablyPrime(number)
    })
    
Produces:

    [returnValue:true, duration:0.000181163]
