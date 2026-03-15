whatspp hld

func requt
- user creates account
- message any mobile no
- create groups
- do voip calls/ video calls
- set status
- channels follow
- payments (optional)

non func
- highly available
- partition tolerant
- scalalble
- handle instance failure
- seamless experience and notificaiotns

user {
    id
    ph_no
    profile_pic
    bio
    type: personal/business
    phone_no
}
chats {
    id
    from: user_id
    type: Media
    is_read
    sent_at (global time)
    group_id
    chat_room_id : $from-id-to
}
group {
    id
    name
    profile_pic
    admin_id
}
media {
    id
    sent_from
    is_forwared
    chat_room_id
    group_id
    is_read
}
story {...}
payments {...}

API endpoint
-> auth related -> /signin /signout / signup
-> tyep of user : normal/ business

-> post_message (chat-id/groupid) / edit /delete
-> post_status
-> delete_status
-> doPayment



major flow ->
for post message

client -> (running in multiple dcg manchines - autoscaled by kuber)
firewall (filter bot requests)
api gateway - manages rate limiting, redirecting traffic (load balanecr), has actual logic for consitent hashing and redirect the request
-> rest api request directed to 
MessageService microservice (which already has multiple instances running)


-> does db entry and produces message to KafkaMesageProducer

when receiver is online, app start receingin eventstream reues from backend , where messageKafkaConsumer sends notitcation and actual message to other user

-> media upload
entire media is first a multi part data broken into chunks and stored to s3, ad metadata in db table...also its cached in distrinbuted redis (which is regional with ttl)
redis part imaeg is a blurred one..with its actual preview soted in abouterh redis distributed service

in case ttl exprires, and user opens old chat again -> request hits s3, and it also updates redis cache for that char-room-id

also we can use neo4j, so relevant cache iage is also aialable to people in same groupw when they rey to load image

--> auth
goes to auth service depening on type of auth
-> FIDO/jwt

--> audio/video call -> web socket

--> payment service
goes thru some third party rpovdiier maybe like stripe at backedn and yes idemopotent key

--> notficaiton service
any event above produces in kafka rouce...and kafka consumer(with consumer groups at mobile/website) receive it..once online...
also say user never comes online, its sent in archival kafka producer whcih is hardly called by users who are not much online...if not then its stored in db, and once user online, it hits db (in case his last online , ttl redis expired, so his unread messages are taken up and prodcued inside kafka prodcuer, or maybe db update happens from offlin->delivered here where oracle cdc captures,-> and produces kafka and at consumer notication its  cosnumed and sent as notfication)

