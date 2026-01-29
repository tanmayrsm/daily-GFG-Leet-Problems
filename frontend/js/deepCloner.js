const deepClone = (obj) => {
    let o = {};
    for(let l in obj) {
        let val = obj[l];
        // console.log(l, "::", val);
        if (typeof val === 'object') {
            o = {...o, [l] : deepClone(val)}
        } else o[l] = val;
    }
    return o;
}


const source = { a: 1, b: { c: 2 } };
const copy = deepClone(source);

// console.log("copy ::", copy)
copy.b.c = 42;
console.log(source.b.c); // 2, not 42


