## Your snippet in hoisting form

```js
const func1 = () => console.log(1);
func1();
func2();
function func2() {
  console.log(2);
}
func3();
var func3 = function func4() {
  console.log(3);
};
```

Rough mental desugaring:

```js
function func2() {           // full body hoisted
  console.log(2);
}
var func3;                   // hoisted as undefined

const func1 = () => console.log(1);  // in TDZ before this line

func1();  // OK (after const line)
func2();  // OK (declaration hoisted with body)
func3();  // TypeError: func3 is not a function (it's undefined)
func3 = function func4() {
  console.log(3);
};
```

***

## Hoisting rules quick table

| Thing                     | Hoisted?                    | Before init: what happens?     |
|---------------------------|-----------------------------|--------------------------------|
| `function foo() {}`       | Yes, name + body            | Can call anywhere in same scope |
| `var foo = function(){}`  | Name hoisted as `undefined` | `foo()` -> `TypeError`         |
| `let foo = function(){}`  | In TDZ                      | Any access -> `ReferenceError` |
| `const foo = () => {}`    | In TDZ                      | Any access -> `ReferenceError` |
| `var x`                   | Hoisted as `undefined`      | Read -> `undefined`            |
| `let/const x`             | In TDZ                      | Read -> `ReferenceError`       |
| `class C {}`              | In TDZ (like `let`)         | `new C()` -> `ReferenceError`  |

Source: [geeksforgeeks](https://www.geeksforgeeks.org/javascript/javascript-hoisting/)

***

## Three classic error shapes

### 1. `undefined` (var, plain read)

```js
console.log(a); // undefined
var a = 10;
```

Engine view:

```js
var a;
console.log(a); // undefined
a = 10;
```

### 2. `TypeError: x is not a function` (var + function expression)

```js
callMe();           // TypeError: callMe is not a function
var callMe = function () {
  console.log('hi');
};
```

Engine view:

```js
var callMe;         // callMe === undefined
callMe();           // undefined()
callMe = function () { ... };
```

### 3. `ReferenceError` (TDZ with let/const/class)

```js
sayHi();               // ReferenceError
const sayHi = () => {};
```

```js
new User();            // ReferenceError
class User {}
```

***

## Block-level function weirdness

Modern engines treat block-level function declarations as block-scoped, but they are still hoisted inside that block:

```js
if (true) {
  greet();              // OK
  function greet() {
    console.log('hello');
  }
}
greet();                // ReferenceError (outside block)
```

Because behavior used to be inconsistent across engines, most style guides recommend: use function declarations at top level, and use `const` function expressions inside blocks.

***

## Tiny edge-case sampler

- Function declaration vs var:

  ```js
  foo();                  // "decl"
  function foo() { console.log('decl'); }
  var foo = 1;
  foo();                  // TypeError
  ```

  Source: [sitepoint](https://www.sitepoint.com/demystifying-javascript-variable-scope-hoisting/)

- Named function expression:

  ```js
  var f = function inner() {
    inner();              // OK (inside itself)
  };
  inner();                // ReferenceError
  ```

  Source: [dev](https://dev.to/antonzo/hoisting-of-variables-functions-classes-types-interfaces-in-javascripttypescript-3el5)

- Class vs function:

  ```js
  make();                 // OK
  function make() {}

  new Thing();            // ReferenceError
  class Thing {}
  ```

  Source: [freecodecamp](https://www.freecodecamp.org/news/scope-closures-and-hoisting-in-javascript/)
