# Tricky JavaScript Output Questions (Senior SDE, ~6 YOE)

Assumption:
- Unless stated otherwise, snippets are plain JS (non-module script context).

## Q1) `var` shadowing inside function

Difficulty: **4/10**

```js
var x = 1;

function test() {
  console.log(x);
  var x = 2;
  console.log(x);
}

test();
console.log("global x =", x);
```

Expected output:
```text
undefined
2
global x = 1
```

## Q2) `var` + recursion (fresh local scope each call)

Difficulty: **5/10**

```js
var x = 1;

function test(n) {
  console.log("in test, x =", x);
  var x = n;
  console.log("after assign, x =", x);

  if (n > 0) test(n - 1);
}

test(2);
console.log("global x =", x);
```

Expected output:
```text
in test, x = undefined
after assign, x = 2
in test, x = undefined
after assign, x = 1
in test, x = undefined
after assign, x = 0
global x = 1
```

## Q3) `var` in loop with async callbacks

Difficulty: **4/10**

```js
for (var i = 0; i < 3; i++) {
  setTimeout(() => console.log(i), 0);
}
console.log("done");
```

Expected output:
```text
done
3
3
3
```

## Q4) `let` in loop with async callbacks

Difficulty: **3/10**

```js
for (let i = 0; i < 3; i++) {
  setTimeout(() => console.log(i), 0);
}
console.log("done");
```

Expected output:
```text
done
0
1
2
```

## Q5) Function declaration vs `var` assignment hoisting

Difficulty: **6/10**

```js
console.log(foo);
var foo = 1;
function foo() {}
console.log(foo);
```

Expected output:
```text
[Function: foo]   // display format may vary by runtime
1
```

## Q6) Closure + pre/post increment across calls

Difficulty: **6/10**

```js
function outer() {
  var x = 10;
  return [
    () => console.log(++x),
    () => console.log(x++)
  ];
}

const [a, b] = outer();
a();
b();
a();
```

Expected output:
```text
11
11
13
```

## Q7) Regular method vs arrow method `this`

Difficulty: **6/10**

```js
globalThis.x = 99;

const obj = {
  x: 10,
  regular: function () {
    return this.x;
  },
  arrow: () => this.x
};

console.log(obj.regular(), obj.arrow());
```

Expected output:
```text
10 99
```

## Q8) `call` cannot rebind arrow `this`

Difficulty: **7/10**

```js
globalThis.x = 3;

const obj = {
  x: 1,
  getX: () => this.x
};

const other = { x: 2 };
console.log(obj.getX.call(other));
```

Expected output:
```text
3
```

## Q9) Event loop order: sync vs microtask vs macrotask

Difficulty: **5/10**

```js
console.log("A");
setTimeout(() => console.log("B"), 0);
Promise.resolve().then(() => console.log("C"));
console.log("D");
```

Expected output:
```text
A
D
C
B
```

## Q10) Nested microtasks ordering

Difficulty: **7/10**

```js
Promise.resolve().then(() => {
  console.log(1);
  Promise.resolve().then(() => console.log(2));
});

Promise.resolve().then(() => console.log(3));
console.log(4);
```

Expected output:
```text
4
1
3
2
```

## Q11) `async/await` scheduling

Difficulty: **5/10**

```js
async function f() {
  console.log("f1");
  await null;
  console.log("f2");
}

console.log("s1");
f();
console.log("s2");
```

Expected output:
```text
s1
f1
s2
f2
```

## Q12) Prototype replacement after instance creation

Difficulty: **8/10**

```js
function A() {}
A.prototype.x = 1;

const a = new A();
A.prototype = { x: 2, y: 3 };

console.log(a.x, a.y);

const b = new A();
console.log(b.x, b.y);

a.x = 5;
console.log(a.x, Object.getPrototypeOf(a).x);
```

Expected output:
```text
1 undefined
2 3
5 1
```

## Q13) `NaN` equality and array search behavior

Difficulty: **6/10**

```js
console.log(NaN === NaN);
console.log(Object.is(NaN, NaN));
console.log([NaN].indexOf(NaN));
console.log([NaN].includes(NaN));
```

Expected output:
```text
false
true
-1
true
```

## Q14) Object key ordering

Difficulty: **7/10**

```js
const o = {};
o[2] = "two";
o[1] = "one";
o["b"] = "bee";
o["a"] = "aye";
o[0] = "zero";

console.log(Object.keys(o));
```

Expected output:
```text
["0", "1", "2", "b", "a"]
```

## Q15) Temporal Dead Zone (TDZ)

Difficulty: **6/10**

```js
function tdz() {
  console.log(a);
  let a = 1;
}

tdz();
```

Expected output:
```text
ReferenceError: Cannot access 'a' before initialization
```

## Q16) Coercion edge case

Difficulty: **7/10**

```js
console.log([] == ![]);
console.log([0] == 0);
console.log([0] == "0");
```

Expected output:
```text
true
true
true
```
