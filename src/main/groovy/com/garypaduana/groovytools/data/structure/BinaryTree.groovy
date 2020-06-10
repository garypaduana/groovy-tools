package com.garypaduana.groovytools.data.structure

class BinaryTree {

    static class Node {
        def leftChild
        def rightChild
        def element
        def parent

        Node(def element) {
            this.element = element
        }

        @Override
        public String toString() {
            return element
        }
    }

    def root

    def insert(def element) {

        if (root == null) {
            root = new Node(element)
        } else {
            def focusNode = root
            while (true) {
                if (element < focusNode.element) {
                    if (focusNode.leftChild == null) {
                        focusNode.leftChild = new Node(element)
                        focusNode.leftChild.parent = focusNode
                        return
                    } else {
                        focusNode = focusNode.leftChild
                    }
                } else if (element > focusNode.element) {
                    if (focusNode.rightChild == null) {
                        focusNode.rightChild = new Node(element)
                        focusNode.rightChild.parent = focusNode
                        return
                    } else {
                        focusNode = focusNode.rightChild
                    }
                } else {
                    return
                }
            }
        }
    }

    def contains(def element) {
        def node = root
        while (true) {
            if (node == null) {
                return false
            }
            if (element < node.element) {
                node = node.leftChild
            } else if (element > node.element) {
                node = node.rightChild
            } else if (element == node.element) {
                return true
            }
        }
    }

    def preOrderTraverse() {
        return preOrderTraverse(root)
    }

    def preOrderTraverse(def node) {
        if (node == null) {
            return []
        }

        def elements = []
        elements.add(node.element)

        if (node.leftChild != null) {
            elements.addAll(preOrderTraverse(node.leftChild))
        }
        if (node.rightChild != null) {
            elements.addAll(preOrderTraverse(node.rightChild))
        }

        return elements
    }

    def inOrderTraverse() {
        return inOrderTraverse(root)
    }

    def inOrderTraverse(def node) {
        if (node == null) {
            return []
        }

        def elements = []
        if (node.leftChild != null) {
            elements.addAll(inOrderTraverse(node.leftChild))
        }
        elements.add(node.element)
        if (node.rightChild != null) {
            elements.addAll(inOrderTraverse(node.rightChild))
        }

        return elements
    }

    def postOrderTraverse() {
        return postOrderTraverse(root)
    }

    def postOrderTraverse(def node) {
        if (node == null) {
            return []
        }

        def elements = []
        if (node.leftChild != null) {
            elements.addAll(postOrderTraverse(node.leftChild))
        }
        if (node.rightChild != null) {
            elements.addAll(postOrderTraverse(node.rightChild))
        }
        elements.add(node.element)

        return elements
    }

    def iterativePreorderTraverse() {
        return iterativePreorderTraverse(root)
    }

    def iterativePreorderTraverse(def node) {
        def elements = []
        def parentStack = new Stack()

        while (parentStack.size() != 0 || node != null) {
            if (node != null) {
                elements.add(node.element)
                if (node.rightChild != null) {
                    parentStack.push(node.rightChild)
                }
                node = node.leftChild
            } else {
                node = parentStack.pop()
            }
        }

        return elements
    }

    def iterativeInOrderTraverse() {
        return iterativeInOrderTraverse(root)
    }

    def iterativeInOrderTraverse(def node) {
        def elements = []
        def parentStack = new Stack()

        while (parentStack.size() != 0 || node != null) {
            if (node != null) {
                parentStack.push(node)
                node = node.leftChild
            } else {
                node = parentStack.pop()
                elements.add(node.element)
                node = node.rightChild
            }
        }
        return elements
    }

    def levelOrderTraverse() {
        return levelOrderTraverse(root)
    }

    def levelOrderTraverse(def node) {
        def q = new Queue()
        q.enqueue(node)
        def elements = []

        while (q.size() > 0) {
            node = q.dequeue()
            elements.add(node.element)

            if (node.leftChild != null) {
                q.enqueue(node.leftChild)
            }
            if (node.rightChild != null) {
                q.enqueue(node.rightChild)
            }
        }
        return elements
    }
}
