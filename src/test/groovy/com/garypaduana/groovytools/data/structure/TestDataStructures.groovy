package com.garypaduana.groovytools.data.structure

import spock.lang.Specification

class TestDataStructures extends Specification{

    def "adding an element to a Stack leads to size increase"(){
        setup:
            def stack = new Stack()
        when:
            stack.push(42)
        then:
            stack.size() == 1
    }

    def "popping an element leads to a size decrease"(){
        setup:
            def stack = new Stack()
        when:
            stack.push(42)
            def value = stack.pop()
        then:
            stack.size() == 0
            value == 42
    }

    def "peeking into a stack doesn't change the size"(){
        setup:
            def stack = new Stack()
        when:
            stack.push(42)
            def value = stack.peek()
        then:
            value == 42
            stack.size() == 1
    }

    def "a stack exhibits lifo ordering"(){
        setup:
            def stack = new Stack()
        when:
            stack.push(42)
            stack.push(78)
        then:
            stack.pop() == 78
            stack.pop() == 42
            stack.pop() == null
    }

    def "a queue exhibits fifo ordering"(){
        setup:
            def queue = new Queue()
        when:
            queue.enqueue(1)
            queue.enqueue(2)
            queue.enqueue(3)
        then:
            queue.dequeue() == 1
            queue.dequeue() == 2
            queue.dequeue() == 3
            queue.dequeue() == null
            queue.size() == 0
    }

    def "peek into queue doesn't change the size"(){
        setup:
            def queue = new Queue()
        when:
            queue.enqueue(42)
            queue.enqueue(45)
        then:
            queue.peek() == 42
            queue.size() == 2
    }

    def "binary tree creation"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            binaryTree.insert(20)
            binaryTree.insert(15)
            binaryTree.insert(18)
            binaryTree.insert(25)
        then:
            binaryTree.root.leftChild.element == 15
            binaryTree.root.leftChild.rightChild.element == 18
            binaryTree.root.rightChild.element == 25
            binaryTree.root.leftChild.parent.element == 20
            binaryTree.root.leftChild.rightChild.parent.element == 15
    }

    /**
     * https://en.wikipedia.org/wiki/Tree_traversal#Types
     */
    def "pre-order traverse test"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            "FBGADICEH".each(){
                binaryTree.insert(it)
            }
        then:
            binaryTree.preOrderTraverse() == "FBADCEGIH".toCharArray()
    }

    def "in-order traverse test"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            "FBGADICEH".each(){
                binaryTree.insert(it)
            }
        then:
            binaryTree.inOrderTraverse() == "ABCDEFGHI".toCharArray()
    }

    def "post-order traverse test"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            "FBGADICEH".each(){
                binaryTree.insert(it)
            }
        then:
            binaryTree.postOrderTraverse() == "ACEDBHIGF".toCharArray()
    }

    def "pre-order iterative equal to recursive"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            "FBGADICEH".each(){
                binaryTree.insert(it)
            }
        then:
            binaryTree.preOrderTraverse() == binaryTree.iterativePreorderTraverse()
    }

    def "in-order iterative equal to recursive"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            "FBGADICEH".each(){
                binaryTree.insert(it)
            }
        then:
            binaryTree.inOrderTraverse() == binaryTree.iterativeInOrderTraverse()
    }

    def "level-order iterative equal to recursive"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            "FBGADICEH".each(){
                binaryTree.insert(it)
            }
        then:
            binaryTree.levelOrderTraverse() == "FBGADICEH".toCharArray()
    }

    def "tree contains elements"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            "FBGADICEH".each(){
                binaryTree.insert(it)
            }
        then:
            "ABCDEFGHI".each(){
                assert binaryTree.contains(it)
            }
    }

    def "tree doesn't contain element"(){
        setup:
            def binaryTree = new BinaryTree()
        when:
            "FBGADICEH".each(){
                binaryTree.insert(it)
            }
        then:
            "JKLMNOPQRSTUV".each(){
                assert binaryTree.contains(it) == false
            }
    }
}
