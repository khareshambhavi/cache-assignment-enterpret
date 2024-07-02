package com.sk.dsa;

import lombok.Getter;

@Getter
public class LinkedListNode<E> {
    LinkedListNode<E> next;
    LinkedListNode<E> prev;
    E element;

    public LinkedListNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }
}
