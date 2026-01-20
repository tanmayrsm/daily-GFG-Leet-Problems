const once = (fn) => {
    let res = null;
    return function(...args) {
        if(res != null) return res;
        res = fn(...args);
        return res;
    }
}

const popei = once((param) => {
    console.log("p ::", param);
    return param;
});

const jak = popei(9);
const jak1 = popei(19);
const jak2 = popei(29);
console.log(jak, "::" , jak1, "::", jak2);