package com.charsharing.bootcamp.filter.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class FilterServiceTest {

    private FilterService filterService = new FilterService();

    @Test
    public void shouldCensorString() {
        final  String actual = filterService.filterText("Hey du arsch");

        final String expected = "Hey du *****";

        MatcherAssert.assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void shouldCensorStringCaseSensitive(){
        final  String actual = filterService.filterText("Hey du ArsCh");

        final String expected = "Hey du *****";

        MatcherAssert.assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void shouldCensorStringPunctuation(){
        final  String actual = filterService.filterText("Hey du Arsch?");

        final String expected = "Hey du *****?";

        MatcherAssert.assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void shouldCensorStringPunctuation2(){
        final  String actual = filterService.filterText("!Hey      du,  ArSCh!??");

        final String expected = "!Hey du, *****?";

        MatcherAssert.assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void shouldNotFilterSmile(){
        final  String actual = filterService.filterText("Hallo :))");

        final String expected = "Hallo :))";

        MatcherAssert.assertThat(actual, Matchers.is(expected));

    }

    @Test
    public void shouldNotFilterMareike(){
        final  String actual = filterService.filterText("Mareike sagt: Hallo du Arsch!");

        final String expected = "Mareike sagt: Hallo du *****!";

        MatcherAssert.assertThat(actual, Matchers.is(expected));
    }@Test
    public void shouldNotFilterSpider(){
        final  String actual = filterService.filterText("9Achtung Spinne!     //\\//(::::)\\//\\     du olle 9Xantippe! ");

        final String expected = "9Achtung Spinne! //\\//(::::)\\//\\ du olle *****!";

        MatcherAssert.assertThat(actual, Matchers.is(expected));
    }
}