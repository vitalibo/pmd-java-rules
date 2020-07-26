package com.github.vitalibo.pmd.lang.java.rule;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.testframework.PMDTestRunner;
import net.sourceforge.pmd.testframework.RuleTst;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

@RunWith(PMDTestRunner.class)
public abstract class PmdRuleTst extends RuleTst {

    @Override
    protected void setUp() {
        // empty, nothing to do
    }

    @Override
    protected List<Rule> getRules() {
        String[] packages = getClass().getPackage().getName().split("\\.");
        String categoryName = packages[packages.length - 1];
        String language = packages[packages.length - 3];
        String rulesetXml = "vitalibo/" + language + "/" + categoryName + ".xml";

        Rule rule = findRule(rulesetXml, getClass().getSimpleName().replaceFirst("Test$", ""));
        return Collections.singletonList(rule);
    }

}
