# PMD Java Rules

This project contains rulesets for [PMD](https://pmd.github.io) source code analyzer.

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
