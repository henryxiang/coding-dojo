package com.demo

import spock.lang.Specification
import spock.lang.Title

@Title("Demo Spock Test for Java ArrayList")
class ListTest extends Specification {
    List<Integer> list

    def "Adding items to list"() {
        given: "An empty ArrayList<Integer>"
        list = new ArrayList<>()

        when: "Added two integer items: 1 and 2"
        list << 1 << 2

        then: "List size should be 1"
        list.size() == 2

        and: "First item should be 1"
        list[0] == 1

        and: "Second item should be 2"
        list[1] == 2
    }
}
