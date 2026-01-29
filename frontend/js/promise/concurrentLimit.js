// Online Javascript Editor for free
// Write, Edit and Run your Javascript code using JS Online Compiler

function promiseAll(inputs) {
    const len = inputs.length;
    let k = 0, result = Array(len).fill(-1);
    
    return new Promise((res) => {
        inputs.forEach((item, index) => {
            item.then(r => {
                result[index] = r;
                k++;
                console.log("resolved ::", r);
                if(k == len)
                    res(result);
            }).catch(err => {
                console.log("error in uper ::", err)
            })
        })
    })
}

function executePromises(inputs, limit) {
    const len = inputs.length;
    const res = [];
    let k = 0, currSize = 0, next = 0;
    return new Promise((resolve) => {
        function runNext() {
            if(currSize >= limit || k >= len)   return;
            currSize++;
            // console.log("start ::", k , "::", currSize);
            inputs[k++].then((response) => {
                console.log("resolved ::", response, "::", k)
                res.push(response);
                currSize--;
                if(res.length == len) {
                    resolve(res);
                }
                runNext();
            })
            runNext();
        }
        runNext();
    }) 
    // return runNext;
}

const resp = [
    new Promise((res) => setTimeout(() => res("done1"),2000)),
    new Promise((res) => setTimeout(() => res("done2"),4000)),
    new Promise((res, rej) => setTimeout(() => res("done3"),6000)),
    new Promise((res) => setTimeout(() => res("done4"),500)),
    new Promise((res) => setTimeout(() => res("done5"),1000))
];

// const res = Promise.all(resp).then((res) => {
//     console.log("fin :: ", res);
// }).catch(err => {
//     console.log("err ::", err);
// })

// const res2 = promiseAll(resp).then((res) => {
//     console.log("fin :: ", res);
// }).catch(err => {
//     console.log("err ::", err);
// })

const respo = executePromises(resp, 2).then((res) => {
    console.log("fin ::", res);
})



