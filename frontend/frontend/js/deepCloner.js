// Online Javascript Editor for free
// Write, Edit and Run your Javascript code using JS Online Compiler
const wk = new WeakMap();

const deepClone = (obj) => {
    // Handle primitives and functions
    if (obj === null || typeof obj !== 'object') return obj;
    
    // Handle circular references
    if (wk.has(obj)) return wk.get(obj);

    const val = Array.isArray(obj) ? [] : {};
    wk.set(obj, val);
    
    for(let key in obj) {
        val[key] = deepClone(obj[key]);
    }
    return val;
};

// obj -> obj.key can be primitive, obj, array, function

const rem = {
    1 : ["dog", "cat"],
    "keyer" : {
        "34" : {
            "r" : [{1 : "okay"}]
        }
    }
}

const res = deepClone(rem);

rem["keyer"] = 10;

console.log(JSON.stringify(res));