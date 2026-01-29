// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class Main {
    public static void main(String[] args) {
        
    }
}

stage 1, 2 => 
// object class
events_key : {
    event_names : []    // payload
    users_list : Set<user_key>
}

// map<user_id, list>
user_key : event_names[]

subscribe(event_key, user_id) {
    event_key.users_list.add(user_id)
}
publish(event_key, event_message) {
    for all users in event_key.users_list
        user_key.add(event_message)
}
fetch(user_id) {
    print user.event_key
    flush user.event_key // make it empty
}
unsubscribe(event_key, user_key){
    event_key.user_list.remove(user_key)
}
getSubscribers(event_key) {
    return event_key.users_list
}

stage 3 =>

// object class
events_key : {
    event_names : []    // payload
    users_list : Set<user_key>
}

event_name {
    event_payload_value
    delayedSecs
    ttlSecs
}

// map<user_id, list>
user_key : Set<event_names>

publish(event_key, event_message, delayed, ttl) {
    for all users in event_key.users_list
        user_key.add({event_message, delayed, ttl})
}

fetch(user_key) {
    curr_ts = current_time
    for all events in user_key.event_names
            if events.delayedSecs <= curr_ts && events.delayedSecs + events.ttlSecs >= curr_ts
                list.add(events.event_payload_value)
                delete this key (maybe add in separate list and then delete to maintain concurrency)
            else if curr_ts > events.delayedSecs + events.ttlSecs
                delete this key (add in same list and then delete to maintain concurrency)
}

// stage 4 =>
broadcast_obj {
    payload
    user_list : Set<user_list>
}

broadcast(payload) {
    br_obj = make boradcast_event object with initially null users
    for all events in event_key.user_list
            for all users in this user_list
                user.add(br_obj)
                br_obj.add(user)
            event.user_list = []    // as no one listens to this event now
}
clear() {
    broadcast_obj.user_list = []
    delete broad_cast_obj
}