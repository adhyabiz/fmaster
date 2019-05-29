// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

exports.sendNotification = functions.firestore.document('Users/11/Geo/{doc_id}')
.onCreate(async context => {
    console.log("Started");

    var payload = {
        data:{
            title: 'User is outside',
            body: 'User is outside GeoFenced Area',
            badge : '1',
            sound : 'default'
        }
    };



    return admin.firestore().collection('Master').doc('111')
    .get().then(doc => {
            console.log('doc ',doc.data().token);
            //const token = Object.keys(allToken.val());
            return admin.messaging().sendToDevice(doc.data().token,payload);
        });
})
