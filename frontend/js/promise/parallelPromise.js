// Implement runWithConcurrency(tasks, limit):
// tasks is an array of functions that each return a promise.
// Run at most limit tasks in parallel.
// Return a promise that resolves to all results in order.


const runWithConcurrency = (tasks, limit) => {
    let ans = [], n = tasks.length, ct = 0;
    return new Promise((res, rej) => {
        tasks.forEach((task, index) => {
            Promise.resolve(task).then(act => {
                console.log("::", act);
                ans[index] = act; 
                ct++;
                if(ct == n)
                    res(ans);
            });
        });    
    });
}

const lists = [
    new Promise((resolve, rej) => setTimeout(() => {
        resolve("resolved 1");
    }, 2000)),
    new Promise((resolve, rej) => setTimeout(() => {resolve("resolved 2")}, 1000)),
    new Promise((resolve, rej) => setTimeout(() => {resolve("resolved 3")}, 20)),
    new Promise((resolve, rej) => setTimeout(() => {resolve("resolved 4")}, 200)),    
]
const res = runWithConcurrency(lists, 3).then(res => {
    console.log("final res ::", res);
}).catch(rej => {
    console.log("fin error ::", rej);
});

