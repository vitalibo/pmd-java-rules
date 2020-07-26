package com.github.vitalibo.pmd.lang.java.rule.design;

import net.sourceforge.pmd.lang.java.ast.ASTImportDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTPackageDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class DependencyInversionRule extends AbstractJavaRule {

    private String packageName;

    @Override
    public Object visit(ASTPackageDeclaration node, Object data) {
        this.packageName = node.getPackageNameImage();
        return data;
    }

    @Override
    public Object visit(ASTImportDeclaration node, Object data) {
        final String importPackageName = node.getPackageName();

        if (packageName.contains(".core") && importPackageName.contains(".infrastructure")) {
            addViolation(data, node);
        }

        return super.visit(node, data);
    }

}
