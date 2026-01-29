// ===== curriedAdd =====

// Normal add: 4 arguments
function add(a, b, c, d) {
  return a + b + c + d;
}

// Generic curry helper (variadic)
function curry(fn) {
  return function curried(...args) {
    if (args.length >= fn.length) {
      return fn(...args);
    }
    return function (...nextArgs) {
      return curried(...args, ...nextArgs);
    };
  };
}

// Curried version of add
const curriedAdd = curry(add);

// Examples:
console.log(curriedAdd(1, 2, 3, 4));   // 10
console.log(curriedAdd(1)(2, 3, 4));   // 10
console.log(curriedAdd(1, 2)(3, 4));   // 10
console.log(curriedAdd(1)(2)(3, 4));   // 10
console.log(curriedAdd(1)(2)(3)(4));   // 10
// All of the above end up calling add(1, 2, 3, 4).[web:158][web:161]

// USGAE -> multi arg function

const log = (level, prefix, message) => {
  console.log(`[${level}] ${prefix}: ${message}`);
};

// Curried:
const curryLog = (level) => (prefix) => (message) =>
  console.log(`[${level}] ${prefix}: ${message}`);

const infoLog = curryLog("INFO");
const appLog = infoLog("[APP]");

appLog("Started");   // [INFO] [APP]: Started
appLog("Finished");  // [INFO] [APP]: Finished

