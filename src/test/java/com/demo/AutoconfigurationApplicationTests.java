package com.demo;

import com.demo.pojo.Fruit;
import com.demo.pojo.FruitProperty;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoconfigurationApplicationTests {

	@Value("${property.test.name}")
	private String propertyTestName;

	@Value("${propertyTest}")
	private String propertyTest;

	@Value("${noKey:default value}")
	private String defaultValue;

	@Value("${propertyTestList}")
	private String[] propertyTestArray;

	@Value("#{'${propertyTestList}'.split(',')}")
	private List<String> propertyTestList;

	@Value("#{serverProperties['servletPath']}")
	private String servletPath;

	@Autowired
	FruitProperty fruitProperty;

	@Test
	public void test1() {
		assertThat(propertyTestName, is("property depth test"));
		assertThat(propertyTest, is("test"));
		assertThat(defaultValue, is("default value"));

		assertThat(propertyTestArray[0], is("a"));
		assertThat(propertyTestArray[1], is("b"));
		assertThat(propertyTestArray[2], is("c"));

		assertThat(propertyTestList.get(0), is("a"));
		assertThat(propertyTestList.get(1), is("b"));
		assertThat(propertyTestList.get(2), is("c"));

		assertThat(servletPath, is("/"));
	}

	@Test
	public void test2() {
		List<Fruit> fruitData = fruitProperty.getList();

		assertThat(fruitData.get(0).getName(), is("banana"));
		assertThat(fruitData.get(0).getColor(), is("yellow"));

		assertThat(fruitData.get(1).getName(), is("apple"));
		assertThat(fruitData.get(1).getColor(), is("red"));

		assertThat(fruitData.get(2).getName(), is("water melon"));
		assertThat(fruitData.get(2).getColor(), is("green"));
	}
}
