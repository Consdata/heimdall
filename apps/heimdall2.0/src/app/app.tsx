import {initializeApp} from 'firebase/app';
import {getAuth, connectAuthEmulator} from 'firebase/auth';
import {getDatabase, onValue, ref, connectDatabaseEmulator} from 'firebase/database';
import {environment} from "../environments/environment";

export const App = () => (
  <>
    I'm Heimdall!
    <div/>
  </>
);

export default App;

const firebaseConfig = {
  apiKey: "${{ secrets.apiKey }}",
  authDomain: "${{ secrets.authDomain }}",
  projectId: "${{ secrets.projectId }}",
  storageBucket: "${{ secrets.storageBucket }}",
  messagingSenderId:  "${{ secrets.messagingSenderId }}",
  appId: "${{ secrets.appId }}",
  measurementId: "${{ secrets.measurementId }}",
  databaseURL: "${{ secrets.databaseURL }}",
};

const app = initializeApp(firebaseConfig);

const auth = getAuth(app)
const db = getDatabase(app);

if(environment.runEmulators) {
  connectAuthEmulator(auth, "http://localhost:9099");
  connectDatabaseEmulator(db, "localhost", 9005);
}


const starCountRef = ref(db, '/projects');

onValue(starCountRef, (snapshot) => {
  const data = snapshot.val();
  console.log("Data", data);
});
