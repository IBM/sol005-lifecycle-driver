# Javascript Function Reference

If you add the following line to your Javascript script (prior to using them), the functions defined below will be available to be used within your scripts.
```js
load('classpath:scripts/lib.js');
```

#### addProperty
```js
addProperty(message, propertyName, propertyValue);
```
Adds a property to the `message` object, converting the `propertyName` into a nested structure based the name being in dot notation.

E.g. `my.nested.property` (with a value of `value1`) would become
```jsonc
{
    "my": {
        "nested": {
            "property": "value1"
        }
    }
}
```

#### setPropertyIfNotNull
```js
setPropertyIfNotNull(sourceMap, targetObject, propertyName);
```
Sets a value in the `targetObject` as taken from the `sourceMap`, identified by the `propertyName`.

**NOTE**: The name for the key in the `sourceMap` and the field in the `targetObject` will be the same.

#### flattenPropertyMap
```js
flattenPropertyMap(map);
```
Flattens a nested Javascript Map object `map` into a single-level key-value Map where the keys are in dot notation.

E.g.
```json
{
    "my": {
        "nested": {
            "property": "value1"
        },
        "other": {
            "nestedproperty": "value2"
        }
    }
}
```
becomes
```json
{
    "my.nested.property": "value1",
    "my.other.nestedproperty": "value2"
}
```
