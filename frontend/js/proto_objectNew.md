Here’s a self‑contained `README.md` you can paste into a file.

```md
# JavaScript Prototype Chain, `Object.create`, and Related Concepts

This document explains:

- How property lookup via the **prototype chain** works (`obj.prop`)
- The difference between `__proto__`, `prototype`, and `Object.getPrototypeOf`
- How to implement `myNew` (like `new`) and `Object.myCreate` (like `Object.create`)
- What happens when changing `Fn.prototype` after creating instances
- How `new` works with a bound function from `bind`
- Multiple ways to use `Object.create` and prototypes, with examples

---

## 1. Prototype chain and `obj.prop`

When you access `obj.prop`, JavaScript looks for `"prop"` in a chain of objects called the **prototype chain**.

Steps:

1. Check if `obj` has its **own property** `"prop"`.
2. If not, look at `obj`’s internal `[[Prototype]]` (its prototype).
3. If not found there, continue up the chain: prototype of the prototype, and so on.
4. If the top (`null`) is reached without finding it, the result is `undefined`.

Example:

```js
const grandParent = { g: "grand" };

const parent = Object.create(grandParent);
parent.p = "parent";

const child = Object.create(parent);
child.c = "child";

console.log(child.c); // "child" (own on child)
console.log(child.p); // "parent" (found on parent)
console.log(child.g); // "grand" (found on grandParent)
console.log(child.x); // undefined (not in the chain)
```

Prototype chain here:

```text
child → parent → grandParent → Object.prototype → null
```

---

## 2. `__proto__`, `prototype`, `Object.getPrototypeOf`

### 2.1 `obj.__proto__` (instance side, legacy)

- Non‑standard/deprecated, but widely supported.
- Points to the object’s internal `[[Prototype]]`.

```js
const obj = {};
const proto = Object.getPrototypeOf(obj); // preferred
// proto === obj.__proto__ in most engines
```

Use `Object.getPrototypeOf` in real code; use `__proto__` only for quick debugging.

### 2.2 `Fn.prototype` (constructor side)

- A property that exists on **functions**.
- When you call `new Fn()`:
  - A new object is created.
  - That object’s `[[Prototype]]` is set to `Fn.prototype`.
  - The `Fn` function is called with `this` bound to that new object.

```js
function Person(name) {
  this.name = name;
}

const p = new Person("Alice");

console.log(Object.getPrototypeOf(p) === Person.prototype); // true
```

So:

- `Person.prototype` is the object used as the prototype for instances created by `new Person`.
- `p.__proto__` and `Object.getPrototypeOf(p)` refer to that same prototype object.

### 2.3 `Object.getPrototypeOf(obj)` (preferred)

- Standard, recommended, and works for all ordinary objects.

```js
const proto = Object.getPrototypeOf(p);
```

Summary:

- `obj.__proto__` – instance’s prototype (legacy accessor).
- `Fn.prototype` – “future instances’ prototype”.
- `Object.getPrototypeOf(obj)` – the standard way to read the prototype.

---

## 3. Implementing `myNew` (like `new`)

The `new` operator does roughly:

1. Create an empty object.
2. Set its prototype to `ctor.prototype`.
3. Call `ctor` with that object as `this`.
4. If `ctor` returns an object, use that; otherwise use the created object.

Implementation:

```js
function myNew(ctor, ...args) {
  // 1) Create object linked to ctor.prototype
  const obj = Object.create(ctor.prototype);

  // 2) Call constructor with this = obj
  const result = ctor.apply(obj, args);

  // 3) Use returned object if it's an object; otherwise the created obj
  const isObject =
    result !== null && (typeof result === "object" || typeof result === "function");

  return isObject ? result : obj;
}
```

Example:

```js
function Person(name) {
  this.name = name;
}

const p = myNew(Person, "Bob");

console.log(p.name);                 // "Bob"
console.log(p instanceof Person);    // true
```

---

## 4. Implementing `Object.myCreate(proto)` (like `Object.create`)

### 4.1 Simple version with `Object.setPrototypeOf`

```js
Object.myCreate = function (proto) {
  if (proto === null || (typeof proto !== "object" && typeof proto !== "function")) {
    throw new TypeError("proto must be an object or function and not null");
  }
  const obj = {};
  Object.setPrototypeOf(obj, proto);
  return obj;
};
```

### 4.2 Classic polyfill using a temporary constructor

This mirrors the ES5 polyfill pattern:

```js
Object.myCreate = function (proto) {
  if (proto === null || (typeof proto !== "object" && typeof proto !== "function")) {
    throw new TypeError("proto must be an object or function and not null");
  }

  function Temp() {}
  Temp.prototype = proto;
  const obj = new Temp();
  Temp.prototype = null; // optional cleanup
  return obj;
};
```

Usage:

```js
const animal = { type: "animal" };

const dog = Object.myCreate(animal);
dog.bark = () => console.log("woof");

console.log(dog.type); // "animal" (via prototype)
dog.bark();            // "woof"
```

---

## 5. Changing `Fn.prototype` after creating instances

Two different operations:

### 5.1 Mutating the existing prototype object

All instances share the same prototype object, so they see changes to its properties.

```js
function Box(v) { this.v = v; }

const b1 = new Box(1);

Box.prototype.getV = function () {
  return this.v;
};

console.log(b1.getV()); // 1

// Mutate prototype method
Box.prototype.getV = function () {
  return this.v + 10;
};

console.log(b1.getV()); // 11
```

- `b1.__proto__` still points to the same object; only the method on that object changed.
- Existing and future instances see the updated method.

### 5.2 Replacing `Fn.prototype` with a new object

Existing instances keep pointing to the old prototype; new instances use the new one.

```js
function Box(v) { this.v = v; }

Box.prototype.getV = function () { return this.v; };

const b1 = new Box(1);

// Replace prototype object entirely
Box.prototype = {
  getV() { return this.v * 2; }
};

const b2 = new Box(2);

console.log(b1.getV()); // 1   (old prototype)
console.log(b2.getV()); // 4   (new prototype)
```

- `Object.getPrototypeOf(b1)` is the old prototype object.
- `Object.getPrototypeOf(b2)` is the new object assigned to `Box.prototype`.

---

## 6. `new` with a bound function (`bind`)

`Function.prototype.bind` returns a **bound function** that remembers a `this` value and some initial arguments.

When you call `new` on a bound function:

- The bound `this` is **ignored**; `new` creates its own object and uses that as `this`.
- Bound arguments are still applied (prepended).
- The new instance’s prototype comes from the **original target function’s** `.prototype`.

Example:

```js
function Person(name, age) {
  this.name = name;
  this.age = age;
}

const BoundPerson = Person.bind({ fake: "this" }, "Alice"); // name pre-bound

const p = new BoundPerson(30);

console.log(p.name); // "Alice" (from bound arg)
console.log(p.age);  // 30       (from new arg)

console.log(p instanceof Person);      // true
console.log(p instanceof BoundPerson); // true

console.log(Object.getPrototypeOf(p) === Person.prototype); // true
```

Key points:

- `this` passed to `bind` does not control `this` when using `new`; `new` takes over.
- Pre‑bound arguments are still used.
- Prototypes behave as if you called `new Person(...)`.

---

## 7. Multiple ways to use `Object.create` / prototypes

### 7.1 Direct `Object.create`

```js
const parent = { p: 1 };

const child = Object.create(parent);
child.c = 2;

console.log(child.p); // 1 (from parent)
console.log(child.c); // 2 (own)
```

Prototype chain:

```text
child → parent → Object.prototype → null
```

### 7.2 Custom helper using `Object.myCreate`

```js
function create(proto) {
  function Temp() {}
  Temp.prototype = proto;
  return new Temp();
}

const parent = { p: 1 };
const child = create(parent);
child.c = 2;
```

Equivalent chain as above.

### 7.3 Using `__proto__` (not recommended)

```js
const parent = { p: 1 };
const child = { c: 2 };

child.__proto__ = parent; // sets [[Prototype]] in most engines

console.log(child.p); // 1 (from parent)
```

This is useful to understand conceptually, but `Object.create` or the official helpers are preferred in production code.

---

## 8. Summary

- Property lookup uses the **prototype chain**: own properties first, then up via `[[Prototype]]` until `null`.
- `Fn.prototype` defines what newly constructed objects (`new Fn`) inherit from.
- `__proto__` and `Object.getPrototypeOf(obj)` both expose an object’s prototype; the latter is the standard API.
- `myNew` and `Object.myCreate` can be implemented by:
  - Creating a new object,
  - Linking its prototype properly,
  - Calling the constructor with `this` set to that object.
- Changing `Fn.prototype`:
  - **Mutate properties** → affects all instances.
  - **Reassign the whole object** → affects only future instances.
- `new` on a bound function:
  - Ignores the bound `this`,
  - Keeps bound arguments,
  - Uses the original function’s `.prototype` for the instance.
```