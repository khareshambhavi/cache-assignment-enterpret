package com.sk.dsa;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void testDLLAddition() {
        LinkedListNode<Integer> node1 = new LinkedListNode<>(1);
        LinkedListNode<Integer> node2 = new LinkedListNode<>(2);
        LinkedListNode<Integer> node3 = new LinkedListNode<>(3);
        LinkedListNode<Integer> node4 = new LinkedListNode<>(4);

        LinkedList<Integer> dll = new LinkedList<>();

        dll.addNodeAtLast(node1);
        verifyDLL(dll, ImmutableList.of(1));

        dll.addNodeAtLast(node2);
        verifyDLL(dll, ImmutableList.of(1, 2));

        dll.addNodeAtLast(node3);
        verifyDLL(dll, ImmutableList.of(1, 2, 3));

        dll.addNodeAtLast(node4);
        verifyDLL(dll, ImmutableList.of(1, 2, 3, 4));

        dll.addElementAtLast(5);
        verifyDLL(dll, ImmutableList.of(1, 2, 3, 4, 5));
    }

    @Test
    void testDLLNodeDetachment() {
        LinkedList<Integer> dll = new LinkedList<>();

        LinkedListNode<Integer> node1 = dll.addElementAtLast(1);
        LinkedListNode<Integer> node2 = dll.addElementAtLast(2);
        LinkedListNode<Integer> node3 = dll.addElementAtLast(3);
        LinkedListNode<Integer> node4 = dll.addElementAtLast(4);
        LinkedListNode<Integer> node5 = dll.addElementAtLast(5);

        verifyDLL(dll, ImmutableList.of(1, 2, 3, 4, 5));

        dll.detachNode(node1);
        verifyDLL(dll, ImmutableList.of(2, 3, 4, 5));

        dll.detachNode(node5);
        verifyDLL(dll, ImmutableList.of(2, 3, 4));

        dll.detachNode(node3);
        verifyDLL(dll, ImmutableList.of(2, 4));

        dll.detachNode(null);
        verifyDLL(dll, ImmutableList.of(2, 4));
    }

    void verifyDLL(LinkedList<Integer> dll, List<Integer> expectedListElements) {
        assertEquals(expectedListElements.get(expectedListElements.size() - 1), dll.getLastNode().getElement());
        assertEquals(expectedListElements.get(0), dll.getFirstNode().getElement());

        LinkedListNode<Integer> currentNode = dll.getFirstNode();
        for (Integer expectedListElement : expectedListElements) {
            assertNotNull(currentNode);
            assertEquals(expectedListElement, currentNode.getElement());
            currentNode = currentNode.getNext();
        }
        assertNull(currentNode.next);
    }
}
