// Online Javascript Editor for free
// Write, Edit and Run your Javascript code using JS Online Compiler

const f = {
    a : 1,
    g : function(params) {
        // console.log("::", params)
        return params.map(item => item * this.a);
    }
}
const rem = f.g.call({a : 2}, [1,2,3]);
const rem2 = f.g.apply({a : 3}, [[1,2,3]]);
const rem3 = f.g.bind({a : 2});
const rem4 = rem3([10,20,30])

console.log(f.g([1,2,3]), "::" , rem, "::" , rem2, "::", rem3, "::", rem4);



// CALL :
// without native implementation :

Function.prototype.myCall = function (context, ...args) {
    context = context ?? globalThis;
    const sym = Symbol();
    context[sym] = this;
    const result = context[sym](...args);
    delete context[sym];
    return result;
};

// BIND :

Function.prototype.myBind = function (context, ...presetArgs) {
    const func = this;
    return function (...laterArgs) {
        // Fallback if context is null/undefined
        const ctx = context ?? globalThis;
        
        // Create a unique property on the context
        const key = Symbol();
        ctx[key] = func;

        // Call as a method so that `this` = ctx
        const result = ctx[key](...presetArgs, ...laterArgs);

        // Clean up
        delete ctx[key];
        return result;
    };
};

const rem5 = f.g.myCall({a : 12}, [1,2,3]);
console.log(rem5);
