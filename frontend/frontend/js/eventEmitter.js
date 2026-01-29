class EventEmitter {
    constructor() {
        this.events = new Map(); // key -> array of functions
    }
    
    on(eventName, callback) {
        if (!this.events.has(eventName)) {
            this.events.set(eventName, []);
        }
        this.events.get(eventName).push(callback);
    }
    
    emit(eventName, ...args) {
        if (!this.events.has(eventName)) {
            console.log(`No listeners for event: ${eventName}`);
            return;
        }
        this.events.get(eventName).forEach(callback => {
            callback(...args);
        });
    }
    
    off(eventName, callback) {
        if (!this.events.has(eventName)) return;
        
        const listeners = this.events.get(eventName);
        const index = listeners.indexOf(callback);
        if (index !== -1) {
            listeners.splice(index, 1);
        }
    }
}
