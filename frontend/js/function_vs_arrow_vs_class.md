# Method Chaining and `this`: Function vs Arrow vs Class

## 1. Class-based chaining (works predictably)

```js
class Tester2 {
  constructor(v) {
    this.val = v;
  }
  test(no) {
    this.val *= no;
    console.log("testing ::", this.val);
    return this;
  }
  method2() {
    this.val *= 100;
    console.log("method2 ::", this.val);
    return this;
  }
  print() {
    console.log("ending in print ::", this.val);
    return this;
  }
}

const obj = new Tester2(2);
obj.test(9).method2().print();
```

Why this works:
- `new Tester2(2)` creates an instance.
- Inside class methods, `this` is the calling instance (`obj`).
- Returning `this` enables method chaining.

## 2. Function + arrow factory version from your example

```js
const Tester = () => {
  this.val = 10;
  return {
    val: this.val,
    test: function (no) {
      this.val *= no;
      console.log("val ::", this.val);
      return this;
    },
    method2: function () {
      return this;
    },
    print: function () {
      console.log("fin print ::", this.val);
      return this;
    }
  };
};

const x = Tester();
x.test(9).method2().print();
```

Key issue:
- `Tester` is an arrow function, so `this` is lexical (captured from outer scope), not a new instance.
- In modules/strict mode, that `this` is often `undefined`, so `this.val = 10` can fail.
- In non-strict browser script mode, it may write to global object (`window.val`), which is not safe.

## 3. Function vs Arrow vs Class (`this` behavior)

- `function` (normal function):
  - `this` is dynamic (depends on call site).
  - With `new`, `this` becomes the new object.
  - Good for constructor functions and prototype methods.

- `arrow` function:
  - No own `this`; it captures outer `this`.
  - Cannot be used as constructor with `new`.
  - Good for callbacks, not for constructors needing instance `this`.

- `class`:
  - Syntax sugar over prototype-based constructors.
  - Works naturally with `new`.
  - Clean/readable for chained instance methods.

## 4. Safer function-constructor version (if not using class)

```js
function TesterFn(v) {
  this.val = v;
}

TesterFn.prototype.test = function (no) {
  this.val *= no;
  console.log("testing ::", this.val);
  return this;
};

TesterFn.prototype.method2 = function () {
  this.val *= 100;
  console.log("method2 ::", this.val);
  return this;
};

TesterFn.prototype.print = function () {
  console.log("ending in print ::", this.val);
  return this;
};

const y = new TesterFn(2);
y.test(9).method2().print();
```

Rule of thumb:
- Use `class` (or constructor function) when you need instance state + chainable methods.
- Use `arrow` mainly for small callbacks where lexical `this` is desired.
