package com.github.vitalibo.pmd.lang.java.rule.codestyle;

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import net.sourceforge.pmd.properties.PropertyDescriptor;
import net.sourceforge.pmd.properties.PropertyFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvoidClassNameRule extends AbstractJavaRule {

    private final PropertyDescriptor<Pattern> patternPropertyDescriptor = PropertyFactory.regexProperty("pattern")
            .desc("Regex which applies to class names")
            .defaultValue("")
            .build();

    public AvoidClassNameRule() {
        definePropertyDescriptor(patternPropertyDescriptor);
    }

    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        Pattern pattern = getProperty(patternPropertyDescriptor);
        Matcher matcher = pattern.matcher(node.getSimpleName());
        if (matcher.matches()) {
            addViolation(data, node);
        }

        return super.visit(node, data);
    }

}
