// Online Javascript Editor for free
// Write, Edit and Run your Javascript code using JS Online Compiler

// debounce
const debounce = (fn, timer) => {
    let last;
    return function(...args) {
        clearTimeout(last);
        last = setTimeout(() => {
            console.log("executing debounce::", fn.call({}, ...args));
            clearTimeout(last);
        }, timer);
    } 
}

// throttle
const throttle = (fn, delay) => {
  let lastTime = 0;

  return function (...args) {   
    // no setTimeout here..why ?
    // instead u shld say, debounce -> execute at end of x amount of time
    // throttle -> execute now, but stay silent for next execution
    const now = Date.now();
    if (now - lastTime >= delay) {
      lastTime = now;
      let r = fn.apply(this, args);
      console.log("executing throttle::", r);
    } else {
      console.log("blocked for ::", ...args);
    }
  };
};


const called = (params) => {
    console.log("called with::", params);
    return params;
}

const res = debounce(called, 1000);
res("1sr time");
res("2sr time");
res("3sr time");    // executed



const res2 = throttle(called, 1000);
res2("1sr time");   // executed
res2("2sr time");
res2("3sr time");
