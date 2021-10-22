# Context4j

## Examples

### Quick Start

#### Create properties file in your resource folder
`context4j.properties`
```properties
packages=com.example
```

#### Create some resource classes
```java
package com.example;

import dev.demmage.context4j.annotations.Component;

@Component
public class Resource {
    
    public static final Object field = new Object();

    public void doSomeThings() {
        // ...
    }
}
```

#### Inject
```java
package com.example;

import dev.demmage.context4j.annotations.Component;
import dev.demmage.context4j.annotations.Inject;

@Component
public class ResourceUser {
    
    @Inject
    public Resource resource;
    
    public void use() {
        resource.doSomeThings();
        System.out.println(resource.field);
    }
}
```
#### Get Component By Name
```java
package com.example;

public class Main {
    public static void main(String[] args) {
        Context4j.init();
        
        ResourceUser r = (ResourceUser) Context4j.getComponent("ResourceUser");
        r.use();
    }
}
```

### Own Component Processor

```java
package com.example.processor;

import dev.demmage.context4j.processor.ComponentProcessor;

public class CustomProcessor implements ComponentProcessor {
    
    public Object processBeforeInitialization(Object o) {
        // Custom logic...
        return o;
    }
    
    public Object processAfterInitialization(Object o) {
        // Custom logic...
        return o;
    }
}
```

