package com.github.vitalibo.pmd.lang.java.rule.codestyle;

import net.sourceforge.pmd.lang.java.ast.ASTImportDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import net.sourceforge.pmd.properties.PropertyDescriptor;
import net.sourceforge.pmd.properties.PropertyFactory;

import java.util.List;

public class BlacklistClassUsageRule extends AbstractJavaRule {

    private final PropertyDescriptor<List<String>> blacklist =
            PropertyFactory.stringListProperty("classes")
                    .desc("List of classes to blacklist")
                    .emptyDefaultValue()
                    .build();

    public BlacklistClassUsageRule() {
        definePropertyDescriptor(blacklist);
    }

    @Override
    public Object visit(ASTImportDeclaration node, Object data) {
        String importedName = node.getImportedName();
        for (String blacklistedClassName : getProperty(blacklist)) {
            if (importedName.startsWith(blacklistedClassName)) {
                addViolation(data, node, importedName);
            }
        }

        return super.visit(node, data);
    }

}
