// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

exports.sendNotification = functions.firestore.document("Users/{user_id}/Notification/{noti_id}")
.onCreate((change, context) => {
    console.log('Started new 2');

    const user_id = context.params.user_id;
    const noti_id = context.params.noti_id;

    console.log('user id ',user_id);
    console.log('noti id ',noti_id)

    var payload = {
        data:{
            title: 'User ${user_id} is outside',
            body: 'User ${user_id} is outside GeoFenced Area',
            badge : '1',
            sound : 'default'
        }
    };

    console.log('payload ',payload);

    return admin.firestore().collection('Master').doc('111')
    .get().then(doc => {
            console.log('doc ',doc.data().token);
            return admin.messaging().sendToDevice(doc.data().token,payload).then(function(response) {
                return console.log('Successfully sent message:', response.error);
              })
              .catch(function(error) {
                return console.log('Error sending message:', error);
              });
        }); 
})
