# PMD Java Rules

This project contains rulesets for [PMD](https://pmd.github.io) source code analyzer.

[![Build Status](https://travis-ci.org/vitalibo/pmd-java-rules.svg?branch=master)](https://travis-ci.org/vitalibo/pmd-java-rules)

## Usage
### Maven 3

In order to use PMD with [Maven](https://maven.apache.org), you need add `maven-pmd-plugin` to your `pom.xml` with following configuration:

```xml
<properties>
    <pmd.version>6.26.0</pmd.version>
</properties>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-pmd-plugin</artifactId>
            <executions>
                <execution>
                    <phase>verify</phase>
                    <goals>
                        <goal>check</goal>
                        <goal>cpd-check</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <excludeRoots>
                    <excludeRoot>target/generated-sources</excludeRoot>
                </excludeRoots>
                <rulesets>
                    <ruleset>https://raw.githubusercontent.com/vitalibo/pmd-java-rules/master/ruleset.xml</ruleset>
                </rulesets>
                <failOnViolation>true</failOnViolation>
                <linkXRef>false</linkXRef>
                <printFailingErrors>true</printFailingErrors>
                <targetJdk>${java.version}</targetJdk>
                <sourceEncoding>${project.build.sourceEncoding}</sourceEncoding>
            </configuration>
        </plugin>
    </plugins>
    <pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-pmd-plugin</artifactId>
                <version>3.13.0</version>
                <dependencies>
                    <dependency>
                        <groupId>net.sourceforge.pmd</groupId>
                        <artifactId>pmd-core</artifactId>
                        <version>${pmd.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>net.sourceforge.pmd</groupId>
                        <artifactId>pmd-java</artifactId>
                        <version>${pmd.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>net.sourceforge.pmd</groupId>
                        <artifactId>pmd-javascript</artifactId>
                        <version>${pmd.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>net.sourceforge.pmd</groupId>
                        <artifactId>pmd-jsp</artifactId>
                        <version>${pmd.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>com.github.vitalibo</groupId>
                        <artifactId>pmd-java-rules</artifactId>
                        <version>1.0-SNAPSHOT</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </pluginManagement>
</build>
```
more details you can find on an [official page](https://pmd.github.io/pmd-6.26.0/pmd_userdocs_tools_maven.html).

## Rule Reference
### Design
#### DependencyInversion

Priority: High (1)

Rule catches cases where broken dependency inversion principle in onion architecture, namely, 
if the class from the core layer has a dependency on the infrastructure layer.

This rule is defined by the following Java class: [com.github.vitalibo.pmd.lang.java.rule.design.DependencyInversionRule](src/main/java/com/github/vitalibo/pmd/lang/java/rule/design/DependencyInversionRule.java)

Example(s):

```java
package com.github.vitalibo.core;

import com.github.vitalibo.core.Bar;
import com.github.vitalibo.infrastructure.Baz; // bad

public class Foo {
}
```

Use this rule by referencing it:

```xml
<rule ref="vitalibo/java/design.xml/DependencyInversion" />
```

### Code Style
#### AvoidClassName

Priority: High (1)

Configurable naming for classes that must avoid.
This rule reports type declarations which match the regex that applies to class names.
By default, this rule raises when the developer creates generic utils or helpers classes.

This rule is defined by the following Java class: [com.github.vitalibo.pmd.lang.java.rule.codestyle.AvoidClassNameRule](src/main/java/com/github/vitalibo/pmd/lang/java/rule/codestyle/AvoidClassNameRule.java)

Use this rule by referencing it:

```xml
<rule ref="vitalibo/java/codestyle.xml/AvoidClassName">
    <properties>
        <property name="pattern" value=".*(Utils?|Constants)|Translators?"/>
    </properties>
</rule>
```
