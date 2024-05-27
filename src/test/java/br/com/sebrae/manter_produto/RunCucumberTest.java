package br.com.sebrae.manter_produto;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/bdd"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunCucumberTest {
}
